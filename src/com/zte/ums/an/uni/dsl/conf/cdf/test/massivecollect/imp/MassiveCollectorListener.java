package com.zte.ums.an.uni.dsl.conf.cdf.test.massivecollect.imp;

import java.util.Hashtable;

import com.zte.ums.an.uni.dsl.conf.cdf.collect.listener.ICollectorListener;
import com.zte.ums.an.uni.dsl.conf.cdf.test.common.timing.KPNTimeMarkConst;
import com.zte.ums.an.uni.dsl.conf.cdf.test.common.timing.OriTimeMarkCollection;
import com.zte.ums.an.uni.dsl.conf.cdf.test.common.timing.OriginalTimeMark;
import com.zte.ums.api.common.snmpnode.ppu.entity.SnmpNode;

/**
 * <p>�ļ�����: MassiveCollectorListener.java</p>
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
public class MassiveCollectorListener implements ICollectorListener
{
//    private OriginalTimeMark tMarkTotal = new OriginalTimeMark(KPNTimeMarkConst.TOTAL);
    
    private Hashtable<String, OriginalTimeMark> tMarkCollect;
    private Hashtable<String, OriginalTimeMark> tMarkParser;
    
    private int count;
    private int failedDSLAMs = 0;
    
    public MassiveCollectorListener()
    {
        initialize();
    }
    
    private void initialize()
    {
        this.count = 0;
        this.failedDSLAMs = 0;
        
        tMarkCollect = null;
        tMarkCollect = new Hashtable<String, OriginalTimeMark>();
        
        tMarkParser = null;
        tMarkParser = new Hashtable<String, OriginalTimeMark>();
        OriTimeMarkCollection.getInstance().reInistialize();
    }
    
    @Override
    public synchronized void onStart()
    {
        if(count != 0)
        {
            OriTimeMarkCollection.getInstance().mergeAndPrint(count, failedDSLAMs);
            initialize();
        }
    }
    
    @Override
    public void onStartCollectSingleDSLAM(Object arg)
    {
    }
    
    @Override
    public void onBeforeCollect(Object arg)
    {        
        String ipAddr = ((SnmpNode)arg).getIpAddress();
        OriginalTimeMark t = new OriginalTimeMark(KPNTimeMarkConst.COLLECT);
        t.markStart();
        
        tMarkCollect.put(ipAddr, t);
    }

    @Override
    public void onAfterCollect(Object arg)
    {
        String ipAddr = ((SnmpNode)arg).getIpAddress();
        tMarkCollect.get(ipAddr).markEnd();
    }

    @Override
    public void onBeforeParser(Object arg)
    {
        String ipAddr = ((SnmpNode)arg).getIpAddress();
        
        OriginalTimeMark t = new OriginalTimeMark(KPNTimeMarkConst.PARSE);
        t.markStart();
        
        tMarkParser.put(ipAddr, t);
    }

    @Override
    public void onAfterPaser(Object arg)
    {
        String ipAddr = ((SnmpNode)arg).getIpAddress();
        tMarkParser.get(ipAddr).markEnd();
    }

    @Override
    public synchronized void onFinishCollectSingleDSLAM(boolean isCollectSuccess, Object arg)
    {
        count++;
        if(!isCollectSuccess)
        {
            failedDSLAMs++;
        }
    }
}
