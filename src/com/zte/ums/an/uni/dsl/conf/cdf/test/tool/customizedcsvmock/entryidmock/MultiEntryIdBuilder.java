package com.zte.ums.an.uni.dsl.conf.cdf.test.tool.customizedcsvmock.entryidmock;

import java.util.ArrayList;

/**
 * <p>�ļ�����: MultyEntryIdBuilder.java</p>
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
public class MultiEntryIdBuilder
{
    private ArrayList<EntryIdBuilder> singleBuilders;
    private int index = 0;
    
    public MultiEntryIdBuilder(EntryIdField[][] arrFields)
    {
        for(EntryIdField[] fields : arrFields)
        {
            EntryIdBuilder singleBuilder = new EntryIdBuilder(fields);
            singleBuilders.add(singleBuilder);
        }
    }
    
    public String getNext()
    {
        String next = singleBuilders.get(index).getNext();

        if(next == null)
        {
            while(index < singleBuilders.size() - 1)
            {
                index++;

                next = singleBuilders.get(index).getNext();
                return next;
            }
        }

        return next;
    }
    
}
