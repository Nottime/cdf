package com.zte.ums.an.uni.dsl.conf.cdf.test.tool.customizedcsvmock.entryidmock;


/**
 * <p>�ļ�����: IPv4Field</p>
 * <p>�ļ�����: </p>
 * <p>��Ȩ����: ��Ȩ����(C)2001-2012</p>
 * <p>��    ˾: ����ͨѶ�ɷ����޹�˾</p>
 * <p>����ժҪ: </p>
 * <p>����˵��: </p>
 * <p>������ڣ�2011��11��21��</p>
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

public class EntryIdField implements Cloneable
{
    int min;
    int max;
    int value;
    
    EntryIdField nextField;
    boolean isEnd = false;
    
    public EntryIdField(int min, int max)
    {
        this.min = min;
        this.max = max;
        
        value = min;
    }
  
    public void setNextField(EntryIdField nextField)
    {
        this.nextField = nextField;
    }
        
    public void next()
    {
        if(value >= max)
        {
            if(this.nextField != null)
            {
                this.nextField.next();
            }
            else
            {
                this.isEnd = true;
            }
        }
        
        increaseFieldValue();
    }
    
    private void increaseFieldValue()
    {
        if(value >= max)
        {            
            value = min;
        }
        else
        {
            ++value;
        }
    }
    
    public int getFieldValue()
    {
        return value;
    }
    
    public boolean isEnd()
    {
        return this.isEnd;
    }
    
    @Override
    public String toString()
    {
        return "min == " + min + "max == " + max;
    }
    
    @Override
    protected Object clone()
    {
        EntryIdField oc = null;
        try
        {
            oc = (EntryIdField)super.clone();
            
            if(this.nextField != null)
            {
                oc.nextField = (EntryIdField)(this.nextField.clone());
            }
        }
        catch(CloneNotSupportedException ex)
        {
            ex.printStackTrace();
        }

        return oc;
    }
}
