package com.zte.ums.an.uni.dsl.conf.cdf.collect.parser.common;


/**
 * <p>�ļ�����: AppendInfo.java</p>
 * <p>�ļ�����: </p>
 * <p>��Ȩ����: ��Ȩ����(C)2001-2012</p>
 * <p>��    ˾: ����ͨѶ�ɷ����޹�˾</p>
 * <p>����ժҪ: </p>
 * <p>����˵��: </p>
 * <p>������ڣ�2012-2-29</p>
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
public class FileAppendInfo
{ 
    private final int indexMerge;
    private final int indexAppend;
    private final int[] valueAppend;
    
    public FileAppendInfo(int indexMerge, int indexAppend, int[] valueAppend)
    {
        this.indexMerge = indexMerge;
        this.indexAppend = indexAppend;
        this.valueAppend = valueAppend;
    }
    
    public int getIndexMerge()
    {
        return indexMerge;
    }


    public int getIndexAppend()
    {
        return indexAppend;
    }


    public int[] getValueAppend()
    {
        return valueAppend;
    }
    
    @Override
    public String toString()
    {
        StringBuffer buf = new StringBuffer();
        
        buf.append(" indexMerge    == " + indexMerge + "\n");
        buf.append(" indexAppend   == " + indexAppend + "\n");
        buf.append(" valueAppend   == [");
        for(int i : valueAppend)
        {
            buf.append(i + ",");
        }
        if(valueAppend.length != 0)
        {
            buf.delete(buf.length() - 1, buf.length());
        }
        buf.append("]\n");
        
        return buf.toString();
    }
}
