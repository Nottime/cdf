package com.zte.ums.an.uni.dsl.conf.cdf.test.massivecollect.imp;

import com.zte.ums.an.uni.dsl.conf.cdf.collect.parser.ParserByDB;
import com.zte.ums.an.uni.dsl.conf.cdf.test.common.timing.KPNTimeMarkConst;
import com.zte.ums.an.uni.dsl.conf.cdf.test.common.timing.OriginalTimeMark;
import com.zte.ums.api.common.snmpnode.ppu.entity.SnmpNode;

/**
 * <p>�ļ�����: KPNParserWithTiming.java</p>
 * <p>�ļ�����: </p>
 * <p>��Ȩ����: ��Ȩ����(C)2001-2012</p>
 * <p>��    ˾: ����ͨѶ�ɷ����޹�˾</p>
 * <p>����ժҪ: </p>
 * <p>����˵��: </p>
 * <p>������ڣ�2011-12-31</p>
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
public class KPNParserWithTiming extends ParserByDB
{
    public KPNParserWithTiming(SnmpNode snmpNode)
    {
        super(snmpNode);
    }
    
    @Override
    public boolean parseDataFromCsv()
    {
        OriginalTimeMark tMark = new OriginalTimeMark(KPNTimeMarkConst.PARSE);
        tMark.markStart();
        
        boolean result = super.parseDataFromCsv();
        
        tMark.markEnd();
        
        return result;
    }
}
