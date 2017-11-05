package com.zte.ums.an.uni.dsl.conf.cdf.test.tool.csvmock.cpnmock;

import com.zte.ums.an.uni.dsl.conf.cdf.test.tool.csvmock.AbstractChainField;

/**
 * <p>�ļ�����: CPNField</p>
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

public abstract class CPNField extends AbstractChainField
{        
    private int fieldValue = getMinValue();
    
    @Override
    public void next()
    {
        if(fieldValue >= getMaxValue())
        {
            if(this.nextField != null)
            {
                this.nextField.next();
            }
        }
        
        fieldValue = (fieldValue + 1) % (getMaxValue() + 1);
        
        if(fieldValue < getMinValue())
        {
            fieldValue = getMinValue();
        }
    }

    @Override
    public int getFieldValue()
    {
        return fieldValue;
    }
    
    abstract int getMaxValue();
    abstract int getMinValue();
}
