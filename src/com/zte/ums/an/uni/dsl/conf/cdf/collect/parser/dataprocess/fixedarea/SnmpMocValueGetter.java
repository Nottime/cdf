package com.zte.ums.an.uni.dsl.conf.cdf.collect.parser.dataprocess.fixedarea;

import com.zte.ums.api.common.snmpnode.ppu.entity.SnmpNode;

/**
 * <p>�ļ�����: SnmpMocValueGetter.java</p>
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
public class SnmpMocValueGetter implements IFixedAreaGetter
{
    @Override
    public String getFieldValue(SnmpNode snmpNode)
    {
        return snmpNode.getMoc();
    }
}
