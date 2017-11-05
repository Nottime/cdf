package com.zte.ums.an.uni.dsl.conf.cdf.test.tool.csvmock.cpnmock;

/**
 * <p>�ļ�����: SlotField</p>
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

public class SlotField extends CPNField
{
    private static int MAX_PORT_NO = 6;
    private static int MIN_PORT_NO = 1;
    
    @Override
    int getMaxValue()
    {
        return MAX_PORT_NO;
    }
    
    @Override
    int getMinValue()
    {
        return MIN_PORT_NO;
    }
    
}
