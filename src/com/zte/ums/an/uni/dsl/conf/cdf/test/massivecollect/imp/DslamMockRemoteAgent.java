package com.zte.ums.an.uni.dsl.conf.cdf.test.massivecollect.imp;

import java.rmi.Naming;

import org.apache.log4j.Logger;

import com.zte.ums.an.uni.dsl.conf.cdf.common.LogPrint;
import com.zte.ums.an.uni.dsl.conf.cdf.test.tool.customizedcsvmock.common.CdfMockDslamXmlParser;
import com.zte.ums.an.uni.dsl.conf.cdf.test.tool.dslamcsvmock.rmi.IDslamMock;
import com.zte.ums.n3common.api.ZXLogger;

/**
 * <p>�ļ�����: DispatchRemoteAgent.java</p>
 * <p>�ļ�����: </p>
 * <p>��Ȩ����: ��Ȩ����(C)2001-2012</p>
 * <p>��    ˾: ����ͨѶ�ɷ����޹�˾</p>
 * <p>����ժҪ: </p>
 * <p>����˵��: </p>
 * <p>������ڣ�2011��12��23��</p>
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
public class DslamMockRemoteAgent
{
    private Logger logger = ZXLogger.getLogger(this.getClass().getName());
    
    private static DslamMockRemoteAgent instance = new DslamMockRemoteAgent();
    private IDslamMock DslamMockInterface = null;

    private DslamMockRemoteAgent()
    {
        initDslamMockInterface();
    }

    public static DslamMockRemoteAgent getInstance()
    {
        return instance;
    }

    public IDslamMock getDslamMockInterface()
    {
        if(DslamMockInterface == null)
        {
            LogPrint.logError(logger, "Please ensure the DSLAM Mocker Interface server already startup.");
        }
        return DslamMockInterface;
    }

    private void initDslamMockInterface()
    {
        String mainServerIP = CdfMockDslamXmlParser.getInstance().getServerIP();
        String mainServerPort = CdfMockDslamXmlParser.getInstance().getServerPort();
        String dispatchRemoteObject = CdfMockDslamXmlParser.getInstance().getRemoteObjectName();
       
        try
        {
            DslamMockInterface = (IDslamMock)Naming.lookup("rmi://" + mainServerIP + ":" + mainServerPort + "/" + dispatchRemoteObject);
        }
        catch(Exception e)
        {
            DslamMockInterface = null;
        }
    }
}
