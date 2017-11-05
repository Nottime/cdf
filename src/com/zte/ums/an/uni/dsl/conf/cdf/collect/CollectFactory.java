package com.zte.ums.an.uni.dsl.conf.cdf.collect;

import java.lang.reflect.Constructor;

import org.apache.log4j.Logger;

import com.zte.ums.an.uni.dsl.conf.cdf.collect.collector.AbsCollectorProcessor;
import com.zte.ums.an.uni.dsl.conf.cdf.collect.parser.AbsParserProcessor;
import com.zte.ums.an.uni.dsl.conf.cdf.common.LogPrint;
import com.zte.ums.an.uni.dsl.conf.cdf.common.SubCollectCache;
import com.zte.ums.api.common.snmpnode.ppu.entity.SnmpNode;
import com.zte.ums.n3common.api.ZXLogger;

/**
 * <p>�ļ�����: CollectFactory</p>
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
public class CollectFactory
{    
    private static Logger logger = ZXLogger.getLogger(CollectFactory.class.getName());
    
    private static int type = SubCollectCache.currType;
    
    private static final String[][] COLLECTOR_TABLE = {
        {Integer.toString(SubCollectCache.TYPE_NORMAL), "com.zte.ums.an.uni.dsl.conf.cdf.collect.collector.CollectorProcessor"},
        {Integer.toString(SubCollectCache.TYPE_TEST_PROCEDURE), "com.zte.ums.an.uni.dsl.conf.cdf.test.imp.procedure.CollectorProcessor"},
//        {Integer.toString(SubCollectCache.TYPE_TEST_COLLECTOR), "com.zte.ums.an.uni.dsl.conf.cdf.test.collector.imp.CollectorProcessor"},
        {Integer.toString(SubCollectCache.TYPE_TEST_PARSER_DB), "com.zte.ums.an.uni.dsl.conf.cdf.test.imp.procedure.CollectorProcessor"},
        {Integer.toString(SubCollectCache.TYPE_TEST_MASSIVE_COLLECT), "com.zte.ums.an.uni.dsl.conf.cdf.test.massivecollect.imp.CollectorProcessor"}
   };
    
    private static final String[][] PARSER_TABLE = {
        {Integer.toString(SubCollectCache.TYPE_NORMAL), "com.zte.ums.an.uni.dsl.conf.cdf.collect.parser.ParserByDB"},
        {Integer.toString(SubCollectCache.TYPE_TEST_PROCEDURE), "com.zte.ums.an.uni.dsl.conf.cdf.test.imp.procedure.ParserProcessor"},
//        {Integer.toString(SubCollectCache.TYPE_TEST_COLLECTOR), "com.zte.ums.an.uni.dsl.conf.cdf.test.imp.procedure.ParserProcessor"},
        {Integer.toString(SubCollectCache.TYPE_TEST_PARSER_DB), "com.zte.ums.an.uni.dsl.conf.cdf.collect.parser.ParserByDB"},
        {Integer.toString(SubCollectCache.TYPE_TEST_MASSIVE_COLLECT), "com.zte.ums.an.uni.dsl.conf.cdf.test.massivecollect.imp.KPNParserWithTiming"}
    };
           
    public static AbsParserProcessor createParserProc(SnmpNode snmpNode)
    {
        String parserClass = getParserClass(type);
        return (AbsParserProcessor)create(snmpNode, parserClass);
    }
    
    public static AbsCollectorProcessor createCollectorProc(SnmpNode snmpNode)
    {
        String collectClass = getCollectorClass(type);
        return (AbsCollectorProcessor)create(snmpNode, collectClass);
    }
    
    public static AbsParserProcessor createParserProc(SnmpNode snmpNode, int type)
    {
        String parserClass = getParserClass(type);
        return (AbsParserProcessor)create(snmpNode, parserClass);
    }
    
    public static AbsCollectorProcessor createCollectorProc(SnmpNode snmpNode, int type)
    {
        String collectClass = getCollectorClass(type);
        return (AbsCollectorProcessor)create(snmpNode, collectClass);
    }

    private static Object create(SnmpNode snmpNode, String parserClass)
    {
        Class<?> cc = null;
        Object obj = null;
        try
        {
            cc = Class.forName(parserClass);
            Class<?>[] argClasses = new Class[] {SnmpNode.class};
            Constructor<?> constructor = cc.getConstructor(argClasses);
            Object[] argObjects = new Object[]{snmpNode};
            obj = constructor.newInstance(argObjects);
        }
        catch(Exception ex)
        {
            LogPrint.logError(logger, "create", ex);
        }
        return obj;
    }
    
    private static String getParserClass(int type)
    {
        for(int i = 0; i < PARSER_TABLE.length; i++)
        {
            if(Integer.parseInt(PARSER_TABLE[i][0]) == type)
            {
                return PARSER_TABLE[i][1];
            }
        }
        
        return "";
    }
    
    private static String getCollectorClass(int type)
    {
        for(int i = 0; i < COLLECTOR_TABLE.length; i++)
        {
            if(Integer.parseInt(COLLECTOR_TABLE[i][0]) == type)
            {
                return COLLECTOR_TABLE[i][1];
            }
        }
        
        return "";
    }
}
