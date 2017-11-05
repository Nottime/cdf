package com.zte.ums.an.uni.dsl.conf.cdf.collect.parser.dataprocess.fixedarea;


/**
 * <p>�ļ�����: FixedFieldCenter.java</p>
 * <p>�ļ�����: </p>
 * <p>��Ȩ����: ��Ȩ����(C)2001-2012</p>
 * <p>��    ˾: ����ͨѶ�ɷ����޹�˾</p>
 * <p>����ժҪ: </p>
 * <p>����˵��: </p>
 * <p>������ڣ�2012-3-5</p>
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
public class FixedFieldCenter
{
    public static IFixedAreaGetter getFixedAreaGetter(String methodName) throws SecurityException, NoSuchMethodException
    {
        if(methodName.equals("getName"))
        {
            return new SnmpNameValueGetter();
        }
        else if(methodName.equals("getMoc"))
        {
            return new SnmpMocValueGetter();
        }
        else if(methodName.equals("getIpAddress"))
        {
            return new SnmpIPValueGetter();
        }

        return new SnmpFieldValueGetter(methodName);
    }
}
