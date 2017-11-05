package com.zte.ums.an.uni.dsl.conf.cdf.collect;

import java.rmi.RemoteException;
import java.util.Vector;

import org.apache.log4j.Logger;

import com.zte.ums.an.uni.dsl.conf.cdf.collect.listener.ListenerCollection;
import com.zte.ums.an.uni.dsl.conf.cdf.collect.wathdog.TimeoutObserver;
import com.zte.ums.an.uni.dsl.conf.cdf.collect.wathdog.Watchdog;
import com.zte.ums.an.uni.dsl.conf.cdf.common.LogPrint;
import com.zte.ums.an.uni.dsl.conf.cdf.common.SubCollectCache;
import com.zte.ums.an.uni.dsl.conf.cdf.common.SubCollectInfo;
import com.zte.ums.an.uni.dsl.conf.cdf.dispatch.dispatch.IDispatch;
import com.zte.ums.api.common.snmpnode.ppu.entity.SnmpNode;
import com.zte.ums.n3common.api.ZXLogger;

/**
 * <p>�ļ�����: Collector.java</p>
 * <p>�ļ�����: �ֲɼ������������������ַ�����������ɼ�����һ����Ԫ��Ӧһ���ɼ����񣩣������̵߳ش�����Щ����</p>
 * <p>��Ȩ����: ��Ȩ����(C)2007-2012</p>
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
public class Collector implements Runnable, TimeoutObserver
{
    private Logger logger = ZXLogger.getLogger(this.getClass().getName());
    
    private SubCollectInfo subCollectInfo = null;
    private byte[] isRestart = new byte[1];
    
    Collector(SubCollectInfo subCollectInfo)
    {
        this.isRestart[0] = 0;
        this.subCollectInfo = subCollectInfo;
        initThreadPool();
    }

    private void process()
    {
        try
        {
            IDispatch dispatchInterface = DispatchRemoteAgent.getInstance().getDispatchInterface();

            synchronized(this.isRestart)
            {
                if(!ThreadPoolService.instance().isBusy() && !isRestart())
                {
                    Vector nes = dispatchInterface.getCollectNesFroDispatchServer(subCollectInfo);
                    doCollect(nes);
                }
            }
        }
        catch(RemoteException e)
        {
            LogPrint.logError(logger, "Get collect task error. Please ensure the dispatch server already startup.");
            SubCollectCache.needReconnectToDispatchSvr = true;
        }
        catch(Exception e)
        {
            LogPrint.logError(logger, "process", e);
        }
    }

    private void doCollect(Vector nes) throws RemoteException
    {
        logCollectTask(nes.size());
        
        for(int i = 0; i < nes.size(); i++)
        {
            ThreadPoolService.instance().submit(new ThreadTask((SnmpNode)nes.get(i)));
        }
    }

    private void logCollectTask(int size)
    {
        if(size == 1)
        {
            String strLog = "Get " + size + " NE from dispatch server.";
            LogPrint.logInfo(logger, strLog);
        }
        else if(size > 1)
        {
            String strLog = "Get " + size + " NEs from dispatch server.";
            LogPrint.logInfo(logger, strLog);
        }
        else
        {
            String strLog = "Get " + size + " NE from dispatch server.";
            LogPrint.logDebug(logger, strLog);
        }
    }

    private void initThreadPool()
    {
        ThreadPoolService.instance().initalize();
    }

    private void releaseThreadPool()
    {
        ThreadPoolService.instance().release();
    }

    public void run()
    {
        process();
    }
    
    private boolean isRestart()
    {
        return this.isRestart[0] == 1 ? true : false;
    }

    @Override
    public void timeoutOccured(Watchdog w)
    {
        synchronized(this.isRestart)
        {
            try
            {
                LogPrint.logInfo(logger, "Scheduled for reinitializing Thread pool.");
                this.isRestart[0] = 1;
                initThreadPool();
                LogPrint.logInfo(logger, "Complete in reinializing thread pool");
                ListenerCollection.getInstance().notifyOnStart();
                this.isRestart[0] = 0;
            }
            catch(Throwable t)
            {
                LogPrint.logError(logger, "timeoutOccured" , t);
            }
        }
    }
}
