package com.zte.ums.an.uni.dsl.conf.cdf.collect.parser.common;

import java.util.Arrays;


/**
 * <p>�ļ�����: FilterInfo</p>
 * <p>�ļ�����: </p>
 * <p>��Ȩ����: ��Ȩ����(C)2007-2012</p>
 * <p>��    ˾: ����ͨѶ�ɷ����޹�˾</p>
 * <p>����ժҪ: </p>
 * <p>����˵��: </p>
 * <p>������ڣ�2012��7��12��</p>
 * <p>�޸ļ�¼1:</p>
 * <pre>
 *    �޸����ڣ�
 *    �� �� �ţ�
 *    �� �� �ˣ�
 *    �޸����ݣ�
 * </pre>
 * <p>�޸ļ�¼2��</p>
 * @version 1.0
 * @author ljy
 */
public class FilterInfo
{
    private int indexFilter = -1;
    private String[] valuesFilter = null;
    
    public FilterInfo()
    {
        
    }
    
    public FilterInfo(int index, String[] values)
    {
        indexFilter = index;
        valuesFilter = values;
    }
    
    public int getIndexFilter()
    {
        return indexFilter;
    }
    public void setIndexFilter(int indexFilter)
    {
        this.indexFilter = indexFilter;
    }
    public String[] getValuesFilter()
    {
        return valuesFilter;
    }
    public void setValuesFilter(String[] valuesFilter)
    {
        this.valuesFilter = valuesFilter;
    }
    
    @Override
    public String toString()
    {
        StringBuffer buf = new StringBuffer();
        
        buf.append(" indexFilter == " + indexFilter + "\n");
        buf.append(" valuesFilter == " + Arrays.toString(valuesFilter) + "\n");
        return buf.toString();
    }
}
