package com.zte.ums.an.uni.dsl.conf.cdf.dispatch.dispatch;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Vector;

import com.zte.ums.an.uni.dsl.conf.cdf.common.SubCollectInfo;

/**
 * <p>�ļ�����: DispatchInterface.java</p>
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
public interface IDispatch extends Remote
{
    /**
     * Invoked by sub server send heart beat to main dispatcher
     */
    public void heartBeatFroCollectServer(SubCollectInfo subServer) throws RemoteException;

    /**
     * Invoked by sub server to get NEs to be collected
     */
    public Vector getCollectNesFroDispatchServer(SubCollectInfo subServer) throws RemoteException;
    
    /**
     * Invoked by report server to get active sub-server list
     */
    public ArrayList<SubCollectInfo> getSubCollectSvrList() throws RemoteException;
}
