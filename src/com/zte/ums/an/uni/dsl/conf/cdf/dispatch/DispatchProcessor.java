package com.zte.ums.an.uni.dsl.conf.cdf.dispatch;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NoSuchObjectException;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.server.UnicastRemoteObject;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;

import com.zte.ums.an.uni.dsl.conf.cdf.common.LogPrint;
import com.zte.ums.an.uni.dsl.conf.cdf.common.xml.DispatchXmlParser;
import com.zte.ums.an.uni.dsl.conf.cdf.dispatch.subsvrlist.SubServerConnManager;
import com.zte.ums.n3common.api.ZXLogger;

/**
 * <p>�ļ�����: DispatchServer.java</p>
 * <p>�ļ�����: </p>
 * <p>��Ȩ����: ��Ȩ����(C)2007-2010</p>
 * <p>��    ˾: ����ͨѶ�ɷ����޹�˾</p>
 * <p>����ժҪ: </p>
 * <p>����˵��: </p>
 * <p>������ڣ�2011��9��1��</p>
 * <p>�޸ļ�¼1:</p>
 * <pre>
 *    �޸����ڣ�
 *    �� �� �ţ�
 *    �� �� �ˣ�
 *    �޸����ݣ�
 * </pre>
 * <p>�޸ļ�¼2��</p>
 * @version 1.0
 * @author lixiaochun
 */
public class DispatchProcessor
{
    private Logger logger = ZXLogger.getLogger(this.getClass().getName());
    
    private static DispatchProcessor instance = null;
    private Remote reg = null;

    private DispatchProcessor()
    {
    }

    public static DispatchProcessor getInstance()
    {
        if(instance == null)
        {
            instance = new DispatchProcessor();
        }
        return instance;
    }

    public boolean startRmi()
    {
        String dispatchRemoteObject = "";
        
        try
        {
            String mainServerIP = DispatchXmlParser.getInstance().getServerIP();
            String mainServerPort = DispatchXmlParser.getInstance().getServerPort();
            dispatchRemoteObject = DispatchXmlParser.getInstance().getRemoteObjectName();

            System.setProperty("java.rmi.server.hostname", mainServerIP);
            reg = LocateRegistry.createRegistry(Integer.parseInt(mainServerPort));

            DispatchImplement dispatchService = new DispatchImplement();
            String rmiName = "rmi://" + mainServerIP + ":" + mainServerPort + "/" + dispatchRemoteObject;
            Naming.rebind(rmiName, dispatchService);

            return true;
        }
        catch(NumberFormatException e)
        {
            LogPrint.logError(logger, "", e);
        }
        catch(RemoteException e)
        {
            LogPrint.logError(logger, "", e);
        }
        catch(MalformedURLException e)
        {
            LogPrint.logError(logger, "", e);
        }
        
        return false;
    }
    
    public void stopRmi()
    {
        if(reg != null)
        {
            try
            {
                UnicastRemoteObject.unexportObject(reg, true);
            }
            catch(NoSuchObjectException e)
            {
                LogPrint.logError(logger, "stopRmi exception ", e);
            }
            
            reg = null;
        }
    }

    private void startCdf()
    {
        //�����Ի�ȡȫ�����ɼ���Ԫ�б�
        int dispatchPeriod = Integer.parseInt(DispatchXmlParser.getInstance().getDispatchPeriod());
        final ScheduledExecutorService connectScheduler = Executors.newSingleThreadScheduledExecutor();
        connectScheduler.scheduleAtFixedRate(new CdfPolicy(), 0, dispatchPeriod, TimeUnit.SECONDS);
    }
    
    private void startSubSvrConnManager()
    {
        int subSvrConnMaintenancePeriod = Integer.parseInt(DispatchXmlParser.getInstance().getSubSvrConnMaintenancePeriod());
        final ScheduledExecutorService connectScheduler = Executors.newSingleThreadScheduledExecutor();
        connectScheduler.scheduleAtFixedRate(SubServerConnManager.getInstance(), 0, subSvrConnMaintenancePeriod, TimeUnit.SECONDS);
    }

    public void start()
    {
        if(startRmi())
        {
            startCdf();
            startSubSvrConnManager();
        }
    }
    
    public static void main(String[] args)
    {
        DispatchProcessor d = new DispatchProcessor();
        while(true)
        {
            d.startRmi();
            d.stopRmi();
        }
            
//        (new DispatchProcessor()).startRmi();
    }
}
