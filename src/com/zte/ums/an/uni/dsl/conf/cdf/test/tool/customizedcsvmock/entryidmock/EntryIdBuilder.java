package com.zte.ums.an.uni.dsl.conf.cdf.test.tool.customizedcsvmock.entryidmock;


/**
 * <p>�ļ�����: EntryIdMocker.java</p>
 * <p>�ļ�����: </p>
 * <p>��Ȩ����: ��Ȩ����(C)2001-2012</p>
 * <p>��    ˾: ����ͨѶ�ɷ����޹�˾</p>
 * <p>����ժҪ: </p>
 * <p>����˵��: </p>
 * <p>������ڣ�2011-12-26</p>
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
public class EntryIdBuilder
{   
    private String separate = "/";
    
    private EntryIdField[] fields;
      
    public EntryIdBuilder(EntryIdField[] fields)
    {
        this.fields = new EntryIdField[fields.length];
        for(int i = 0; i < fields.length; i++)
        {
            this.fields[i] = (EntryIdField)(fields[i].clone());
        }
        
        buildChain();
    }
    
    public EntryIdBuilder(EntryIdField[] fields, String separate)
    {
        this(fields);
        
        this.separate = separate;
    }

    private void buildChain()
    {        
        if(fields.length >= 2)
        {
            for(int i = fields.length - 2; i >= 0; i--)
            {
                fields[i + 1].setNextField(fields[i]);
            }
        }
    }
    
    private boolean isEnd()
    {
        return fields[0].isEnd;
    }
    
    private void next()
    {
        fields[fields.length - 1].next();
    }
    
    public String getNext()
    {
        String nextEntry = null;
        
        if(!isEnd())
        {
            nextEntry = assembleEntryId();
            next();
        }
        
        return nextEntry;
    }

    private String assembleEntryId()
    {
        StringBuffer buf = new StringBuffer();
        buf.append(fields[0].getFieldValue());
        
        if(fields.length >= 2)
        {
            for(int i = 1; i <= fields.length - 1; i++)
            {
                buf.append(separate).append(fields[i].getFieldValue());
            }
        }

        return buf.toString();
    }
}
