package com.zte.ums.an.uni.dsl.conf.cdf.collect;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;

import com.zte.ums.an.uni.dsl.conf.cdf.collect.wathdog.Watchdog;
import com.zte.ums.an.uni.dsl.conf.cdf.common.LogPrint;
import com.zte.ums.an.uni.dsl.conf.cdf.common.SubCollectInfo;
import com.zte.ums.an.uni.dsl.conf.cdf.common.xml.CollectXmlParser;
import com.zte.ums.n3common.api.ZXLogger;


/**
 * <p>�ļ�����: CollectProcessor</p>
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
 *    
 *    �޸����ݣ�
 * </pre>
 * <p>�޸ļ�¼2��</p>
 * @version 1.0
 * @author lixiaochun
 */
public class CollectProcessor
{
    private Logger logger = ZXLogger.getLogger(this.getClass().getName());
    
    private static CollectProcessor instance = null;

    private CollectProcessor()
    {

    }

    public static CollectProcessor getInstance()
    {
        if(instance == null)
        {
            instance = new CollectProcessor();
        }
        return instance;
    }
 
    public void start(SubCollectInfo subCollectInfo)
    {
        try
        {
            // ��ʼ�����ַ�����������
            if(DispatchRemoteAgent.getInstance().getDispatchInterface() != null)
            {
                startConnector(subCollectInfo);
                startCollector(subCollectInfo);
            }
            else
            {
                LogPrint.logError(logger, "Please ensure the dispatch server already startup, then restart this sub-collect server.");
            }
        }
        catch(Exception e)
        {
            LogPrint.logError(logger, "", e);
        }
    }

    private void startWatchDog(Collector collector)
    {
        int timeout = 1000 * Integer.parseInt(CollectXmlParser.getInstance().getPoolReinitialTime());
        Watchdog w = new Watchdog(timeout);
        w.addTimeoutObserver(collector);
        w.start();
    }

    // �����Դ����ַ���������ȡ���ɼ���Ԫ�б�Ȼ�����Ԫ��ȡCSV�ļ���������������ݱ��������ݿ�
    private void startCollector(SubCollectInfo subCollectInfo)
    {
        Collector collector = new Collector(subCollectInfo);
        int collectPeriod = Integer.parseInt(CollectXmlParser.getInstance().getScheduledPeriod());
        final ScheduledExecutorService collectScheduler = Executors.newSingleThreadScheduledExecutor();
        collectScheduler.scheduleAtFixedRate(collector, 0, collectPeriod, TimeUnit.SECONDS);
    
        startWatchDog(collector);
    }

    // ע��ɼ��������������Է�������
    private void startConnector(SubCollectInfo subCollectInfo)
    {
        int heartBeatPeriod = Integer.parseInt(CollectXmlParser.getInstance().getHeartBeat());
        final ScheduledExecutorService connectScheduler = Executors.newSingleThreadScheduledExecutor();
        connectScheduler.scheduleAtFixedRate(new Connector(subCollectInfo), 0, heartBeatPeriod, TimeUnit.SECONDS);
    }
}
