package com.zte.ums.an.uni.dsl.conf.cdf.test.tool.customizedcsvmock.ipmock;

import com.zte.ums.an.uni.dsl.conf.cdf.test.tool.customizedcsvmock.common.CdfMockServerXmlParser;
import com.zte.ums.an.uni.dsl.conf.cdf.test.tool.customizedcsvmock.entryidmock.EntryIdBuilder;
import com.zte.ums.an.uni.dsl.conf.cdf.test.tool.customizedcsvmock.entryidmock.EntryIdField;

/**
 * <p>�ļ�����: IPv4AddrMocker</p>
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

public class IPv4AddrMocker
{
    private static IPv4AddrMocker instance = new IPv4AddrMocker();
    private static final int TOP = CdfMockServerXmlParser.getInstance().getMockedDSLAMNumber();
    
    private EntryIdBuilder builder;
    private String[] allMockedIP = null;
        
    private IPv4AddrMocker()
    {   
        EntryIdField f1 = new EntryIdField(10, 254);
        EntryIdField f2 = new EntryIdField(10, 254);
        EntryIdField f3 = new EntryIdField(10, 254);
        EntryIdField f4 = new EntryIdField(1, 254);
        
        builder = new EntryIdBuilder(new EntryIdField[] {f1, f2, f3, f4}, ".");
    }
    
    public static IPv4AddrMocker getInstance()
    {
        return instance;
    }
    
    public synchronized String[] getAllIp()
    {
        if(allMockedIP == null)
        {
            initAllMockedIP();
        }
        
        return allMockedIP;
    }
    
    private void initAllMockedIP()
    {
        allMockedIP = new String[TOP];
        
        int i = 0;
        while(i < TOP)
        {
            allMockedIP[i++] = builder.getNext();
        }
    }

    public static void main(String[] args)
    {
        String[] all = IPv4AddrMocker.getInstance().getAllIp();
        
        for(String ip : all)
        {
            System.out.println(ip);
        }
    }
}
