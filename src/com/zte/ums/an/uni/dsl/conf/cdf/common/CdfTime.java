package com.zte.ums.an.uni.dsl.conf.cdf.common;

/**
 * <p>�ļ�����: CdfTime</p>
 * <p>�ļ�����: </p>
 * <p>��Ȩ����: ��Ȩ����(C)2007-2012</p>
 * <p>��    ˾: ����ͨѶ�ɷ����޹�˾</p>
 * <p>����ժҪ: </p>
 * <p>����˵��: </p>
 * <p>������ڣ�2011��11��14��</p>
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
public class CdfTime
{
    private long startTime;
    private long endTime;
    
    public void markStartTime()
    {
        startTime = System.currentTimeMillis();
    }
    
    public void markEndTime()
    {
        endTime = System.currentTimeMillis();
    }
    
    public float caculateTimeCost() throws Exception
    {
        if(endTime == 0 || startTime == 0)
        {
            throw new Exception("remark start time and end time first.");
        }
        
        if(endTime < startTime)
        {
            throw new Exception("end time must be larger than start time.");
        }
        
       return (float)(endTime - startTime)/1000;
    }
}
