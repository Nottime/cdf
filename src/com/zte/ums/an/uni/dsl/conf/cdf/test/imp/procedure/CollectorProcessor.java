package com.zte.ums.an.uni.dsl.conf.cdf.test.imp.procedure;

import com.zte.ums.an.uni.dsl.conf.cdf.collect.collector.AbsCollectorProcessor;
import com.zte.ums.api.common.snmpnode.ppu.entity.SnmpNode;

/**
 * <p>�ļ�����: CollectionProcessor.java</p>
 * <p>�ļ�����: ÿ���ɼ��߳�����</p>
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
public class CollectorProcessor extends AbsCollectorProcessor
{
    //****** �����: �������� **********************************************************************/

    //****** �����: �������� **********************************************************************/

    public CollectorProcessor(SnmpNode snmpNode)
    {
        super(snmpNode);
    }

    @Override
    public boolean getCSVFileFromNe()
    {
        try
        {
            Thread.sleep(((long)(Math.random() * 100000) % 2000) + 5000);
//            Thread.sleep(500);
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return true;
    }

    @Override
    public boolean isVersionInconsistent()
    {
        return false;
    }

    @Override
    public void downloadXMLWithCurrentVersion()
    {
        
    }

    @Override
    public boolean isNeedOperate()
    {
        return true;
    }
}