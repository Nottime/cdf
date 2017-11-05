package com.zte.ums.an.uni.dsl.conf.cdf.test.common.timing;

/**
 * <p>�ļ�����: OriginalTimeMark</p>
 * <p>�ļ�����: </p>
 * <p>��Ȩ����: ��Ȩ����(C)2001-2012</p>
 * <p>��    ˾: ����ͨѶ�ɷ����޹�˾</p>
 * <p>����ժҪ: </p>
 * <p>����˵��: </p>
 * <p>������ڣ�2011-12-29</p>
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
public class OriginalTimeMark
{
    private String mark;
    private long startTime;
    private long endTime;
    
    public OriginalTimeMark(String markName)
    {
        this.mark = markName;
    }
    
    public String getMark()
    {
        return mark;
    }

    public void setMark(String mark)
    {
        this.mark = mark;
    }

    public long getStartTime()
    {
        return startTime;
    }

    public void setStartTime(long startTime)
    {
        this.startTime = startTime;
    }

    public long getEndTime()
    {
        return endTime;
    }

    public void setEndTime(long endTime)
    {
        this.endTime = endTime;
    }
    
    public void markStart()
    {
        startTime = System.currentTimeMillis();
    }
    
    public void markEnd()
    {
        endTime = System.currentTimeMillis();
        OriTimeMarkCollection.getInstance().addMark(this);
    }
    
    @Override
    public String toString()
    {
        StringBuffer buf = new StringBuffer();
        buf.append("markName == " + this.mark + "\n");
        buf.append("startTime == " + this.startTime + "\n");
        buf.append("endTime == " + this.endTime + "\n");
        
        return buf.toString();
    }
}
