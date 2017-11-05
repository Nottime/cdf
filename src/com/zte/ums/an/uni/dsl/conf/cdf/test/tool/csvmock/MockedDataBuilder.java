package com.zte.ums.an.uni.dsl.conf.cdf.test.tool.csvmock;

import com.zte.ums.an.uni.dsl.conf.cdf.test.tool.csvmock.cpnmock.CPNMocker;

/**
 * <p>�ļ�����: MockedDataBuilder</p>
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

public class MockedDataBuilder
{
    public static final String HEADER = "rack/shelf/slot/port,"
            + "zxDslTrunkingOutOctets,zxDslTrunkingInOctets,zxDslTrunkingOutUCastPkts,zxDslTrunkingInUCastPkts,"
            + "zxDslTrunkingOutMulticastPkts,zxDslTrunkingInMulitcastPkts,zxDslTrunkingOutBroadcastPkts,zxDslTrunkingInBroadcastPkts," 
            + "zxDslTrunkingOutDiscardRatio,zxDslTrunkingInDiscardRatio,zxDslTrunkingInErrorRatio,zxDslTrunkingOutDiscards," 
            + "zxDslTrunkingInDiscards,zxDslTrunkingInErrors";

    private static int paramNum = HEADER.split(",").length - 1;;
        
    public static String mockHeader()
    {
        return HEADER;
    }
    
    public static String mockDataLine()
    {
        StringBuffer line = new StringBuffer(CPNMocker.getInstance().getNextCPN());
        
        for(int i = 0; i < paramNum - 1; i++)
        {
            line.append("," + mockData(i));
        }
        return line.toString();
    }
            
    private static int mockData(int length)
    {
        return ((int)(Math.random() * 100000)) % 10000;
    }
}
