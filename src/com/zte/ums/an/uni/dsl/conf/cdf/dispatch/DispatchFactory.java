package com.zte.ums.an.uni.dsl.conf.cdf.dispatch;

import org.apache.log4j.Logger;

import com.zte.ums.an.uni.dsl.conf.cdf.common.LogPrint;
import com.zte.ums.an.uni.dsl.conf.cdf.common.SubCollectCache;
import com.zte.ums.an.uni.dsl.conf.cdf.dispatch.cdfpolicy.AbsCdfPolicyProcessor;
import com.zte.ums.an.uni.dsl.conf.cdf.dispatch.dispatch.IDispatch;
import com.zte.ums.n3common.api.ZXLogger;

/**
 * <p>�ļ�����: DispatchFactory</p>
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

public class DispatchFactory
{    
    private static Logger logger = ZXLogger.getLogger(DispatchFactory.class.getName());
    
    private static int type = SubCollectCache.currType;
    
    private static final String[][] DISPATCH_TABLE = {
        {Integer.toString(SubCollectCache.TYPE_NORMAL), "com.zte.ums.an.uni.dsl.conf.cdf.dispatch.dispatch.MainWorker"},
        {Integer.toString(SubCollectCache.TYPE_TEST_PROCEDURE), "com.zte.ums.an.uni.dsl.conf.cdf.test.imp.procedure.MainWorker"},
//        {Integer.toString(SubCollectCache.TYPE_TEST_COLLECTOR), "com.zte.ums.an.uni.dsl.conf.cdf.test.imp.procedure.MainWorker"},
        {Integer.toString(SubCollectCache.TYPE_TEST_PARSER_DB), "com.zte.ums.an.uni.dsl.conf.cdf.dispatch.dispatch.MainWorker"},
        {Integer.toString(SubCollectCache.TYPE_TEST_MASSIVE_COLLECT), "com.zte.ums.an.uni.dsl.conf.cdf.test.imp.procedure.MainWorker"}
    };
    
    private static final String[][] CDFPOLICY_TABLE = {
        {Integer.toString(SubCollectCache.TYPE_NORMAL), "com.zte.ums.an.uni.dsl.conf.cdf.dispatch.cdfpolicy.CdfPolicyProcessor"},
        {Integer.toString(SubCollectCache.TYPE_TEST_PROCEDURE), "com.zte.ums.an.uni.dsl.conf.cdf.test.imp.procedure.CdfPolicyProcessor"},
//        {Integer.toString(SubCollectCache.TYPE_TEST_COLLECTOR), "com.zte.ums.an.uni.dsl.conf.cdf.test.collector.imp.CdfPolicyProcessor"},
        {Integer.toString(SubCollectCache.TYPE_TEST_PARSER_DB), "com.zte.ums.an.uni.dsl.conf.cdf.test.imp.parserdb.CdfPolicyProcessor"},
        {Integer.toString(SubCollectCache.TYPE_TEST_MASSIVE_COLLECT), "com.zte.ums.an.uni.dsl.conf.cdf.test.massivecollect.imp.CdfPolicyProcessor"}
    };
    
    public static IDispatch createDispatchProc()
    {
        String dispatchClass = getDispatchClass();
        return (IDispatch)create(dispatchClass);
    }
    
    public static AbsCdfPolicyProcessor createCdfPolicyProc()
    {
        String collectClass = getCdfPolicyClass();
        return (AbsCdfPolicyProcessor)create(collectClass);
    }

    private static Object create(String dispatchClass)
    {
        Class<?> cc = null;
        Object newObj = null;

        try
        {  
            cc = Class.forName(dispatchClass);
            newObj = cc.newInstance();
        }
        catch(Exception ex)
        {
            LogPrint.logError(logger, "", ex);
        }
        
        return newObj;
    }
    
    private static String getDispatchClass()
    {
        for(int i = 0; i < DISPATCH_TABLE.length; i++)
        {
            if(Integer.parseInt(DISPATCH_TABLE[i][0]) == type)
            {
                return DISPATCH_TABLE[i][1];
            }
        }
        
        return "";
    }
    
    private static String getCdfPolicyClass()
    {
        for(int i = 0; i < CDFPOLICY_TABLE.length; i++)
        {
            if(Integer.parseInt(CDFPOLICY_TABLE[i][0]) == type)
            {
                return CDFPOLICY_TABLE[i][1];
            }
        }
        
        return "";
    }
}
