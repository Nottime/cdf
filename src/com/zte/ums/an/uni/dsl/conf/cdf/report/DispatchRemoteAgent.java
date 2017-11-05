package com.zte.ums.an.uni.dsl.conf.cdf.report;

import java.rmi.Naming;

import org.apache.log4j.Logger;

import com.zte.ums.an.uni.dsl.conf.cdf.common.xml.ReportXmlParser;
import com.zte.ums.an.uni.dsl.conf.cdf.dispatch.dispatch.IDispatch;
import com.zte.ums.n3common.api.ZXLogger;

/**
 * <p>�ļ�����: DispatchRemoteAgent.java</p>
 * <p>�ļ�����: </p>
 * <p>��Ȩ����: ��Ȩ����(C)2007-2012</p>
 * <p>��    ˾: ����ͨѶ�ɷ����޹�˾</p>
 * <p>����ժҪ: </p>
 * <p>����˵��: </p>
 * <p>������ڣ�2011��12��29��</p>
 * <p>�޸ļ�¼1:</p>
 * <pre>
 *    �޸����ڣ�
 *    �� �� �ţ�
 *    �� �� �ˣ�
 *    �޸����ݣ�
 * </pre>
 * <p>�޸ļ�¼2��</p>
 * @version 1.0
 * @author jingxueshi
 */
public class DispatchRemoteAgent
{
    protected Logger logger = ZXLogger.getLogger(this.getClass().getName());
    private static DispatchRemoteAgent instance = null;
    private IDispatch dispatchInterface = null;

    private DispatchRemoteAgent()
    {
        initDispatchInterface();
    }

    public static DispatchRemoteAgent getInstance()
    {
        instance = new DispatchRemoteAgent();
        return instance;
    }

    public IDispatch getDispatchInterface()
    {
        return dispatchInterface;
    }

    private synchronized void initDispatchInterface()
    {
        String mainServerIP = ReportXmlParser.getInstance().getServerIP();
        String mainServerPort = ReportXmlParser.getInstance().getServerPort();
        String dispatchRemoteObject = ReportXmlParser.getInstance().getRemoteObjectName();
       
        try
        {
            dispatchInterface = (IDispatch)Naming.lookup("rmi://" + mainServerIP + ":" + mainServerPort + "/" + dispatchRemoteObject);
        }
        catch(Exception e)
        {
            dispatchInterface = null;
        }
    }
}
