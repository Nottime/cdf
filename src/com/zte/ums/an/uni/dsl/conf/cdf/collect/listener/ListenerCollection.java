package com.zte.ums.an.uni.dsl.conf.cdf.collect.listener;

import java.util.Vector;

/**
 * <p>�ļ�����: ListenerCollection.java</p>
 * <p>�ļ�����: </p>
 * <p>��Ȩ����: ��Ȩ����(C)2001-2012</p>
 * <p>��    ˾: ����ͨѶ�ɷ����޹�˾</p>
 * <p>����ժҪ: </p>
 * <p>����˵��: </p>
 * <p>������ڣ�2011-12-31</p>
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
public class ListenerCollection
{
    private Vector<ICollectorListener> fListeners = new Vector<ICollectorListener>();
    private static ListenerCollection instance = new ListenerCollection();
    
    public static ListenerCollection getInstance()
    {
        return instance;
    }
    
    private ListenerCollection()
    {
    }
    
    public void addListener(ICollectorListener fListener)
    {
        fListeners.add(fListener);
    }
    
    public void removeListener(ICollectorListener fListener)
    {
        fListeners.remove(fListener);
    }
    
    public void notifyOnStart()
    {
        for(ICollectorListener lsn : fListeners)
        {
            lsn.onStart();
        }
    }
    
    public void notifyOnStartCollectSingleDSLAM(Object arg)
    {
        for(ICollectorListener lsn : fListeners)
        {
            lsn.onStartCollectSingleDSLAM(arg);
        }
    }
        
    public void notifyOnBeforeCollect(Object arg)
    {
        for(ICollectorListener lsn : fListeners)
        {
            lsn.onBeforeCollect(arg);
        }
    }
    
    public void notifyOnAfterCollect(Object arg)
    {
        for(ICollectorListener lsn : fListeners)
        {
            lsn.onAfterCollect(arg);
        }
    }
    
    public void notifyOnBeforeParser(Object arg)
    {
        for(ICollectorListener lsn : fListeners)
        {
            lsn.onBeforeParser(arg);
        }
    }
    
    public void notifyOnAfterParser(Object arg)
    {
        for(ICollectorListener lsn : fListeners)
        {
            lsn.onAfterPaser(arg);
        }
    }

    public void notifyOnFinishCollectSingleDSLAM(boolean isCollectSuccess, Object arg)
    {
        for(ICollectorListener lsn : fListeners)
        {
            lsn.onFinishCollectSingleDSLAM(isCollectSuccess, arg);
        }
    }
}
