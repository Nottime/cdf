package com.zte.ums.an.uni.dsl.conf.cdf.test.tool.csvmock.ipmock;

/**
 * <p>�ļ�����: TestIPv4Mocker</p>
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

public class TestIPv4Mocker
{
    public static void main(String[] args)
    {
        for(long i = 0; i < 65538; i++)
        {
            System.out.println(IPv4AddrMocker.getInstance().getNextIPv4Addr());
        }
    }

}
