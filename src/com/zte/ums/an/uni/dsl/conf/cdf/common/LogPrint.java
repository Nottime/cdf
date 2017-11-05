package com.zte.ums.an.uni.dsl.conf.cdf.common;

import org.apache.log4j.Logger;

/**
 * Error���𣬴��ں���־����¼��
 * Info���𣬴��ں���־����¼��
 * Debug���𣬽���־��¼��
 * printStep������Info�����һ������ʵ�֡�
 * @author chenduoduo
 *
 */
public class LogPrint
{
    public static void logError(Logger logger, String str, Throwable ex)
    {
        ex.printStackTrace();
        logger.error(str, ex);
    }
    
    public static void logError(Logger logger, String str)
    {
        printInfo("Error:" + str);
        logger.error(str);
    }
    
    public static void logWarn(Logger logger, String str)
    {
        printInfo("Warning:" + str);
        logger.warn(str);
    }

    public static void logDebug(Logger logger, String info)
    {       
        logger.debug(info);
    }
    
    public static void logInfo(Logger logger, String info)
    {
        printInfo(info);
        logger.info(info);
    }
    
    private static void printInfo(String info)
    {
        System.out.println(CdfUtil.getCurDateTime() + ":" + info);
    }

    private LogPrint()
    {
    }
}
