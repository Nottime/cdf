package com.zte.ums.an.uni.dsl.conf.cdf.collect;

import java.rmi.RemoteException;

import org.apache.log4j.Logger;

import com.zte.ums.an.uni.dsl.conf.cdf.common.LogPrint;
import com.zte.ums.an.uni.dsl.conf.cdf.common.SubCollectCache;
import com.zte.ums.an.uni.dsl.conf.cdf.common.SubCollectInfo;
import com.zte.ums.n3common.api.ZXLogger;

/**
 * <p>�ļ�����: Connector</p>
 * <p>�ļ�����: </p>
 * <p>��Ȩ����: ��Ȩ����(C)2001-2012</p>
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
public class Connector implements Runnable
{
    private Logger logger = ZXLogger.getLogger(this.getClass().getName());
    
    private SubCollectInfo subServer = null;

    Connector(SubCollectInfo subServer)
    {
        this.subServer = subServer;
    }

    public void regAndSendHeartBeat()
    {
        try
        {
            subServer.nanoTime = System.nanoTime();
            DispatchRemoteAgent.getInstance().getDispatchInterface().heartBeatFroCollectServer(subServer);
            LogPrint.logDebug(logger, subServer.getName() + " sends heart beat.");
        }
        catch(RemoteException e)
        {
            LogPrint.logError(logger, "HartBeart error, Please ensure the dispatch server already startup.");
            SubCollectCache.needReconnectToDispatchSvr = true;
        }
    }

    public void run()
    {
        regAndSendHeartBeat();
    }
}