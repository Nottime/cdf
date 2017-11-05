package com.zte.ums.an.uni.dsl.conf.cdf.test.tool.csvmock;

/**
 * <p>�ļ�����: AbstractChainField</p>
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

public abstract class AbstractChainField
{
    protected AbstractChainField nextField;
    
    public void setNextField(AbstractChainField nextField)
    {
        this.nextField = nextField;
    }
    
    public abstract void next();
    
    public abstract int getFieldValue();
}
