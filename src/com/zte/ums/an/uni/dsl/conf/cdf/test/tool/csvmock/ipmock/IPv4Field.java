package com.zte.ums.an.uni.dsl.conf.cdf.test.tool.csvmock.ipmock;

import com.zte.ums.an.uni.dsl.conf.cdf.test.tool.csvmock.AbstractChainField;

/**
 * <p>�ļ�����: IPv4Field</p>
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

public class IPv4Field extends AbstractChainField
{
    private static int MAX_IP_NO = 255;
    private static int MIN_IP_NO = 0;
    
    private int fieldValue = MIN_IP_NO;
    
    @Override
    public void next()
    {
        if(fieldValue >= MAX_IP_NO)
        {
            if(this.nextField != null)
            {
                this.nextField.next();
            }
        }
        
        fieldValue = (fieldValue + 1) % (MAX_IP_NO + 1);
    }

    @Override
    public int getFieldValue()
    {
        return fieldValue;
    }
}
