package com.zte.ums.an.uni.dsl.conf.cdf.test.tool.dslamcsvmock.rmi;

import java.rmi.Remote;
import java.rmi.RemoteException;

import com.zte.ums.an.uni.dsl.conf.cdf.common.FtpInfo;

/**
 * <p>�ļ�����: DispatchInterface.java</p>
 * <p>�ļ�����: </p>
 * <p>��Ȩ����: ��Ȩ����(C)2001-2012</p>
 * <p>��    ˾: ����ͨѶ�ɷ����޹�˾</p>
 * <p>����ժҪ: </p>
 * <p>����˵��: </p>
 * <p>������ڣ�2011��12��22��</p>
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
public interface IDslamMock extends Remote
{
    public void requestCsvByFTP(String ipAddr, FtpInfo ftpInfo) throws RemoteException;
    public int checkStatus(String ipAddr) throws RemoteException;
}
