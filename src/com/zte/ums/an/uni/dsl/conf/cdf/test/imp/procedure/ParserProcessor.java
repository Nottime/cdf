package com.zte.ums.an.uni.dsl.conf.cdf.test.imp.procedure;

import com.zte.ums.an.uni.dsl.conf.cdf.collect.parser.AbsParserProcessor;
import com.zte.ums.api.common.snmpnode.ppu.entity.SnmpNode;

/**
 * <p>�ļ�����: ParserProcessor.java</p>
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
 * @author jingxueshi_10118495
 */
public class ParserProcessor extends AbsParserProcessor
{    
    public ParserProcessor(SnmpNode snmpNode)
    {
        super(snmpNode);
    }

    @Override
    public boolean parseDataFromCsv()
    {
        try
        {
            Thread.sleep(((long)(Math.random() * 100000) % 2000));
//            Thread.sleep(800);
        }
        catch(InterruptedException e)
        {
            e.printStackTrace();
        }
        
        return true;
    }
}
