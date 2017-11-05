package com.zte.ums.an.uni.dsl.conf.cdf.collect;

import java.rmi.Naming;

import com.zte.ums.an.uni.dsl.conf.cdf.common.SubCollectCache;
import com.zte.ums.an.uni.dsl.conf.cdf.common.xml.CollectXmlParser;
import com.zte.ums.an.uni.dsl.conf.cdf.dispatch.dispatch.IDispatch;

/**
 * <p>�ļ�����: DispatchRemoteAgent.java</p>
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
public class DispatchRemoteAgent
{
    private static DispatchRemoteAgent instance = null;
    private IDispatch dispatchInterface = null;

    private DispatchRemoteAgent()
    {
        initDispatchInterface();
    }

    public static DispatchRemoteAgent getInstance()
    {
        if(instance == null)
        {
            instance = new DispatchRemoteAgent();
        }
        return instance;
    }

    public IDispatch getDispatchInterface()
    {
        if(SubCollectCache.needReconnectToDispatchSvr)
        {
            initDispatchInterface();
        }
        
        return dispatchInterface;
    }
    
    public void reconnect()
    {
        initDispatchInterface();
    }

    private void initDispatchInterface()
    {
        String mainServerIP = CollectXmlParser.getInstance().getRemoteMainServerIp();
        String mainServerPort = CollectXmlParser.getInstance().getRemoteServerPort();
        String dispatchRemoteObject = CollectXmlParser.getInstance().getRemoteObjectName();
       
        try
        {
            dispatchInterface = (IDispatch)Naming.lookup("rmi://" + mainServerIP + ":" + mainServerPort + "/" + dispatchRemoteObject);
        }
        catch(Exception e)
        {
        }
    }
}
