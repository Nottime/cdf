package com.zte.ums.an.uni.dsl.conf.cdf.collect;

import java.util.concurrent.Callable;

import org.apache.log4j.Logger;

import com.zte.ums.an.uni.dsl.conf.cdf.collect.collector.AbsCollectorProcessor;
import com.zte.ums.an.uni.dsl.conf.cdf.collect.listener.ListenerCollection;
import com.zte.ums.an.uni.dsl.conf.cdf.collect.parser.AbsParserProcessor;
import com.zte.ums.an.uni.dsl.conf.cdf.common.LogPrint;
import com.zte.ums.an.uni.dsl.conf.cdf.common.xml.CollectXmlParser;
import com.zte.ums.api.common.snmpnode.ppu.entity.SnmpNode;
import com.zte.ums.n3common.api.ZXLogger;

/**
 * <p>�ļ�����: ThreadTask.java</p>
 * <p>�ļ�����: </p>
 * <p>��Ȩ����: ��Ȩ����(C)2007-2010</p>
 * <p>��    ˾: ����ͨѶ�ɷ����޹�˾</p>
 * <p>����ժҪ: </p>
 * <p>����˵��: </p>
 * <p>������ڣ�2011��9��1��</p>
 * <p>�޸ļ�¼1:</p>
 * <pre>
 *    �޸����ڣ�
 *    �� �� �ţ�
 *    �� �� �ˣ�
 *    �޸����ݣ�
 * </pre>
 * <p>�޸ļ�¼2��</p>
 * @version 1.0
 * @author lixiaochun
 */
public class ThreadTask implements Callable
{
    private Logger logger = ZXLogger.getLogger(this.getClass().getName());
    
    private SnmpNode snmpNode = null;

    public ThreadTask(SnmpNode snmpNode)
    {
        this.snmpNode = snmpNode;
    }

    //****** �����: Callable�ӿڷ���ʵ�� ****************************************************************/

    public Object call()
    {
        try
        {
            AbsCollectorProcessor cp = CollectFactory.createCollectorProc(snmpNode);
            ListenerCollection.getInstance().notifyOnStartCollectSingleDSLAM(snmpNode);
            ListenerCollection.getInstance().notifyOnBeforeCollect(snmpNode);
            
            if(cp.isNeedOperate())
            {
                boolean isCollectSuccess = doCollectAndParse(cp);
                
                ListenerCollection.getInstance().notifyOnFinishCollectSingleDSLAM(isCollectSuccess, snmpNode);
            }
        }
        catch(Throwable t)
        {
            LogPrint.logError(logger, "", t);
        }
            
        return null;
    }

    private boolean doCollectAndParse(AbsCollectorProcessor cp)
    {
        boolean isCollectSuccess = true;
        
        if(isCheckXml() && cp.isVersionInconsistent())
        {
            cp.downloadXMLWithCurrentVersion();
        }
        else
        {
            isCollectSuccess = cp.getCSVFileFromNe();
            ListenerCollection.getInstance().notifyOnAfterCollect(snmpNode);
            
            if(isCollectSuccess)
            {
                parseData();
            }
        }
        return isCollectSuccess;
    }

    private static boolean isCheckXml()
    {
        return CollectXmlParser.getInstance().getCheckXml();
    }

    protected void parseData()
    {
        ListenerCollection.getInstance().notifyOnBeforeParser(snmpNode);
        
        AbsParserProcessor pp = CollectFactory.createParserProc(snmpNode);
        boolean result = pp.parseDataFromCsv();
        
        ListenerCollection.getInstance().notifyOnAfterParser(snmpNode);
        
        if(result)
        {
            LogPrint.logInfo(logger, "Succeeded in parsing data with " + snmpNode.getIpAddress() + ".");
        }
    }
}
