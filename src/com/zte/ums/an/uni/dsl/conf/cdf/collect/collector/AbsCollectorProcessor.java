package com.zte.ums.an.uni.dsl.conf.cdf.collect.collector;

import com.zte.ums.api.common.snmpnode.ppu.entity.SnmpNode;

/**
 * <p>�ļ�����: AbsCollectorProcessor</p>
 * <p>�ļ�����: </p>
 * <p>��Ȩ����: ��Ȩ����(C)2007-2012</p>
 * <p>��    ˾: ����ͨѶ�ɷ����޹�˾</p>
 * <p>����ժҪ: </p>
 * <p>����˵��: </p>
 * <p>������ڣ�2011��11��14��</p>
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
public abstract class AbsCollectorProcessor
{
    protected SnmpNode snmpNode = null;
    
    public AbsCollectorProcessor(SnmpNode snmpNode)
    {
        this.snmpNode = snmpNode;
    }
    
    public abstract boolean isNeedOperate();
    
    public abstract boolean getCSVFileFromNe();

    public abstract boolean isVersionInconsistent();

    public abstract void downloadXMLWithCurrentVersion();
    
}