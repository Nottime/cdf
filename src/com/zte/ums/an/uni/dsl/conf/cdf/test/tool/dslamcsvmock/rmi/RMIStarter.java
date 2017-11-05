package com.zte.ums.an.uni.dsl.conf.cdf.test.tool.dslamcsvmock.rmi;

import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;

import org.apache.log4j.Logger;

import com.zte.ums.an.uni.dsl.conf.cdf.common.LogPrint;
import com.zte.ums.an.uni.dsl.conf.cdf.test.tool.customizedcsvmock.common.CdfMockServerXmlParser;
import com.zte.ums.n3common.api.ZXLogger;

/**
 * <p>�ļ�����: StartRmi.java</p>
 * <p>�ļ�����: </p>
 * <p>��Ȩ����: ��Ȩ����(C)2001-2012</p>
 * <p>��    ˾: ����ͨѶ�ɷ����޹�˾</p>
 * <p>����ժҪ: </p>
 * <p>����˵��: </p>
 * <p>������ڣ�2011-12-22</p>
 * <p>�޸ļ�¼1:</p>
 * <pre>
 *    �޸����ڣ�
 *    �� �� �ţ�
 *    �� �� �ˣ�
 *    �޸����ݣ�
 * </pre>
 * <p>�޸ļ�¼2��</p>
 * @version 1.0
 * @author ChenDuoduo_10087118
 */
public class RMIStarter
{
    private static Logger logger = ZXLogger.getLogger(RMIStarter.class.getName());
    
    public static void start()
    {
        String mainServerIP = CdfMockServerXmlParser.getInstance().getServerIP();
        String mainServerPort = CdfMockServerXmlParser.getInstance().getServerPort();
        String dispatchRemoteObject = CdfMockServerXmlParser.getInstance().getRemoteObjectName();
        
        try
        {
            System.setProperty("java.rmi.server.hostname", mainServerIP);
            LocateRegistry.createRegistry(Integer.parseInt(mainServerPort));
            DslMockRmiImplement dslMockService = new DslMockRmiImplement();
            
            String rmiName = "rmi://" + mainServerIP + ":" + mainServerPort + "/" + dispatchRemoteObject;
            Naming.rebind(rmiName, dslMockService);
            
            LogPrint.logInfo(logger, "RMI starts at " + rmiName);
        }
        catch(Exception e)
        {
        }
    }
}
