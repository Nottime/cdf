package com.zte.ums.an.uni.dsl.conf.cdf.test.tool.csvmock.cpnmock;

import com.zte.ums.an.uni.dsl.conf.cdf.test.tool.csvmock.AbstractChainField;

/**
 * <p>�ļ�����: CPNMocker</p>
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

public class CPNMocker
{
    private static CPNMocker instance = new CPNMocker();
    
    private AbstractChainField portField = new PortField();
    private AbstractChainField slotField = new SlotField();
    
    private StringBuffer cpnStr = new StringBuffer("1/1/");
    
    private CPNMocker()
    {        
        portField.setNextField(slotField);
    }
    
    public static CPNMocker getInstance()
    {
        return instance;
    }
    
    public String getNextCPN()
    {
        cpnStr.delete(4, cpnStr.length());
        String nextCPN = (cpnStr.append(slotField.getFieldValue()).append("/").append(portField.getFieldValue())).toString();
       
        portField.next();
        return nextCPN;
    }

}
