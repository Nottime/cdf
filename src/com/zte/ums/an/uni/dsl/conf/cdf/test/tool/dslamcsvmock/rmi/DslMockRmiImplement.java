package com.zte.ums.an.uni.dsl.conf.cdf.test.tool.dslamcsvmock.rmi;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import org.apache.log4j.Logger;

import com.zte.ums.an.uni.dsl.conf.cdf.common.FtpInfo;
import com.zte.ums.an.uni.dsl.conf.cdf.common.LogPrint;
import com.zte.ums.an.uni.dsl.conf.cdf.test.tool.dslamcsvmock.threadmanager.ThreadManager;
import com.zte.ums.n3common.api.ZXLogger;

/**
 * <p>�ļ�����: DispatchImplement.java</p>
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
public class DslMockRmiImplement extends UnicastRemoteObject implements IDslamMock
{  
    private Logger logger = ZXLogger.getLogger(this.getClass().getName());
    
    ThreadManager tm = new ThreadManager();
    
    protected DslMockRmiImplement() throws RemoteException
    {
//        super();
    }

    @Override
    public void requestCsvByFTP(String ipAddr, FtpInfo ftpInfo) throws RemoteException
    {
        LogPrint.logDebug(logger, "request csv by " + ipAddr);
        
        tm.addTask(ipAddr, ftpInfo);
    }

    @Override
    public int checkStatus(String ipAddr) throws RemoteException
    {
        int result = tm.getResult(ipAddr);
        LogPrint.logDebug(logger, "checkStatus by " + ipAddr + " and return " + result);
        return result;
    }

}
