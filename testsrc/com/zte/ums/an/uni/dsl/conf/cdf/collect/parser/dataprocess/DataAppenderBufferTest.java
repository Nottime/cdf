package com.zte.ums.an.uni.dsl.conf.cdf.collect.parser.dataprocess;

import java.util.ArrayList;

import junit.framework.TestCase;

import com.zte.ums.an.uni.dsl.conf.cdf.collect.parser.common.FileInfo;
import com.zte.ums.an.uni.dsl.conf.cdf.collect.parser.dataprocess.append.DataAppenderBuffer;

/**
 * <p>�ļ�����: DataAppenderBufferTest.java</p>
 * <p>�ļ�����: </p>
 * <p>��Ȩ����: ��Ȩ����(C)2001-2012</p>
 * <p>��    ˾: ����ͨѶ�ɷ����޹�˾</p>
 * <p>����ժҪ: </p>
 * <p>����˵��: </p>
 * <p>������ڣ�2012-3-1</p>
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
public class DataAppenderBufferTest extends TestCase
{
    private DataAppenderBuffer buf;
    
    private FileInfo f1;
    private FileInfo f2;
    private FileInfo f3;
    
    private ArrayList<String> line1 = new ArrayList<String>();
    private ArrayList<String> line2 = new ArrayList<String>();
    private ArrayList<String> line3 = new ArrayList<String>();
    
    @Override
    protected void setUp() throws Exception
    {
        f1 = new FileInfo("aaa");
        f2 = new FileInfo("bbb");
        f3 = new FileInfo("ccc");

        buf = new DataAppenderBuffer(new FileInfo[]
            {f1, f2, f3});

        addToArrayList(line1, "1/1/1/1", "11", "12", "13");
        addToArrayList(line2, "1/1/1/2", "22", "22", "23");
        addToArrayList(line3, "1/1/1/3", "33", "32", "33");
    }
    
    private void addToArrayList(ArrayList<String> list, String... params)
    {
        for(String p : params)
        {
            list.add(p);
        }
    }
    
//    private String arrayListToString(ArrayList<String> list)
//    {
//        if(list.size() == 0)
//        {
//            return "";
//        }
//        
//        StringBuffer buf = new StringBuffer();
//        for(String s : list)
//        {
//            buf.append(s).append(",");
//        }
//        
//        return buf.substring(0, buf.length() - 1);
//    }
    
    public void testEmpty()
    {
        assertFalse(buf.hasNext(f1));
        assertFalse(buf.hasNext(f2));
        assertFalse(buf.hasNext(f3));
    }
    
    public void testAdd()
    {
        buf.addBuffer(f1, line1);
        assertTrue(buf.hasNext(f1));
        assertEquals(line1, buf.next(f1));
        assertFalse(buf.hasNext(f1));
        
        buf.addBuffer(f2, line2);
        buf.addBuffer(f2, line3);
        assertTrue(buf.hasNext(f2));
        assertEquals(line2, buf.next(f2));
        assertTrue(buf.hasNext(f2));
        assertEquals(line3, buf.next(f2));
        assertFalse(buf.hasNext(f2));
        
        assertFalse(buf.hasNext(f3));
        assertFalse(buf.hasNext(f3));
    }
    
    public void testReset()
    {
        buf.addBuffer(f2, line2);
        buf.addBuffer(f2, line3);
        buf.next(f2);
        buf.next(f2);
        assertFalse(buf.hasNext(f2));
        
        buf.resetCursor(f2);
        
        assertTrue(buf.hasNext(f2));
        assertEquals(line2, buf.next(f2));
        assertTrue(buf.hasNext(f2));
        assertEquals(line3, buf.next(f2));
        assertFalse(buf.hasNext(f2));
        
        assertFalse(buf.hasNext(f1));
        assertFalse(buf.hasNext(f3));
    }
}
