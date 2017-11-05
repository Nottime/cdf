package com.zte.ums.an.uni.dsl.conf.cdf.test.tool.csvmock.ipmock;

import com.zte.ums.an.uni.dsl.conf.cdf.test.tool.csvmock.AbstractChainField;

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
    
    private AbstractChainField ipv4Field1 = new IPv4Field();
    private AbstractChainField ipv4Field2 = new IPv4Field();
    private AbstractChainField ipv4Field3 = new IPv4Field();
    private AbstractChainField ipv4Field4 = new IPv4Field();
    
    private StringBuffer ipAddr = new StringBuffer();
    
    private IPv4AddrMocker()
    {        
        ipv4Field4.setNextField(ipv4Field3);
        ipv4Field3.setNextField(ipv4Field2);
        ipv4Field2.setNextField(ipv4Field1);
        ipv4Field1.setNextField(ipv4Field4);
    }
    
    public static IPv4AddrMocker getInstance()
    {
        return instance;
    }
    
    public String getNextIPv4Addr()
    {
        ipAddr.delete(0, ipAddr.length());
        String nextIp = (ipAddr.append(ipv4Field1.getFieldValue()).append(".").append(ipv4Field2.getFieldValue()).append(".")
                        .append(ipv4Field3.getFieldValue()).append(".").append(ipv4Field4.getFieldValue())).toString();

        ipv4Field4.next();

        return nextIp;
    }

}
