package com.zte.ums.an.uni.dsl.conf.cdf.dispatch;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.Vector;

import com.zte.ums.an.uni.dsl.conf.cdf.common.SubCollectInfo;
import com.zte.ums.an.uni.dsl.conf.cdf.dispatch.dispatch.IDispatch;

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
public class DispatchImplement extends UnicastRemoteObject implements IDispatch
{
    private IDispatch realWorker;

    public DispatchImplement() throws RemoteException
    {
        this.realWorker = DispatchFactory.createDispatchProc();
    }

    @Override
    public void heartBeatFroCollectServer(SubCollectInfo subCollectInfo) throws RemoteException
    {
        realWorker.heartBeatFroCollectServer(subCollectInfo);
    }

    @Override
    public Vector getCollectNesFroDispatchServer(SubCollectInfo subCollectInfo) throws RemoteException
    {
        return realWorker.getCollectNesFroDispatchServer(subCollectInfo);
    }

    @Override
    public ArrayList<SubCollectInfo> getSubCollectSvrList() throws RemoteException
    {
        return realWorker.getSubCollectSvrList();
    }
}
