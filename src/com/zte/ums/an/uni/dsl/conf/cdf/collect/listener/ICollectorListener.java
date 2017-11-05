package com.zte.ums.an.uni.dsl.conf.cdf.collect.listener;

/**
 * <p>�ļ�����: ICollectorListener.java</p>
 * <p>�ļ�����: </p>
 * <p>��Ȩ����: ��Ȩ����(C)2001-2012</p>
 * <p>��    ˾: ����ͨѶ�ɷ����޹�˾</p>
 * <p>����ժҪ: </p>
 * <p>����˵��: </p>
 * <p>������ڣ�2011-12-30</p>
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
public interface ICollectorListener
{    
    public void onStart();
    
    public void onStartCollectSingleDSLAM(Object arg);
    
    public void onBeforeCollect(Object arg);
    public void onAfterCollect(Object arg);
    
    public void onBeforeParser(Object arg);
    public void onAfterPaser(Object arg);

    public void onFinishCollectSingleDSLAM(boolean isCollectSuccess, Object arg);
}
