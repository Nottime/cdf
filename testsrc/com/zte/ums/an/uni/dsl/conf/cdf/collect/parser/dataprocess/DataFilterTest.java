package com.zte.ums.an.uni.dsl.conf.cdf.collect.parser.dataprocess;

import java.util.ArrayList;

import junit.framework.TestCase;

import com.zte.ums.an.uni.dsl.conf.cdf.collect.parser.common.FilterInfo;
import com.zte.ums.an.uni.dsl.conf.cdf.collect.parser.dataprocess.filter.DataFilter;

/**
 * <p>�ļ�����: DataFilterTest.java</p>
 * <p>�ļ�����: </p>
 * <p>��Ȩ����: ��Ȩ����(C)2001-2012</p>
 * <p>��    ˾: ����ͨѶ�ɷ����޹�˾</p>
 * <p>����ժҪ: </p>
 * <p>����˵��: </p>
 * <p>������ڣ�2012-7-13</p>
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
public class DataFilterTest extends TestCase
{
    private DataFilter filter;
    
    private FilterInfo f1;
    private FilterInfo f2;
    private FilterInfo f3;
    
    private ArrayList<String> line1 = new ArrayList<String>();
    private ArrayList<String> line2 = new ArrayList<String>();
    private ArrayList<String> line3 = new ArrayList<String>();
    private ArrayList<String> line4 = new ArrayList<String>();
    
    @Override
    protected void setUp() throws Exception
    {
        f1 = new FilterInfo(2, new String[]{"a"});
        f2 = new FilterInfo(4, new String[]{"5", "7"});
        f3 = new FilterInfo(0, new String[]{"1/1/1/3/.*"});

        filter = new DataFilter();
        
        FilterInfo[] infos = new FilterInfo[3];
        infos[0] = f1;
        infos[1] = f2;
        infos[2] = f3;
        
        filter.setFilterInfos(infos);

        addToArrayList(line1, "1/1/1/1", "11", "12", "13", "14");
        addToArrayList(line2, "1/1/1/2", "22", "22", "23", "5");
        addToArrayList(line3, "1/1/1/3/4094", "22a", "22a", "23a", "5a");
        addToArrayList(line4, "1/1/1/4/4094", "22a", "22a", "23a", "5a");
    }
    
    private void addToArrayList(ArrayList<String> list, String... params)
    {
        for(String p : params)
        {
            list.add(p);
        }
    }
    
    public void testNoneFilter()
    {
        assertTrue(new DataFilter().isNeedReserve(line1));
    }
    
    public void testNoMatchFilter()
    {
        assertFalse(filter.isNeedReserve(line1));
    }
    
    public void testMatchFilter()
    {
        assertTrue(filter.isNeedReserve(line2));
    }
    
    public void testMatchRegex()
    {
        assertTrue(filter.isNeedReserve(line3));
    }
    
    public void testNoMatchRegex()
    {
        assertFalse(filter.isNeedReserve(line4));
    }
}
