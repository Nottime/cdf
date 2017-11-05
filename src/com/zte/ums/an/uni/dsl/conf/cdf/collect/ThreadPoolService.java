package com.zte.ums.an.uni.dsl.conf.cdf.collect;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;

import com.zte.ums.an.uni.dsl.conf.cdf.common.LogPrint;
import com.zte.ums.an.uni.dsl.conf.cdf.common.xml.CollectXmlParser;
import com.zte.ums.n3common.api.ZXLogger;

/**
 * <p>�ļ�����: ThreadPoolService.java</p>
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
public class ThreadPoolService
{
    //****** �����: �������� **********************************************************************/

    private Logger logger = ZXLogger.getLogger(this.getClass().getName());
    
    public static ThreadPoolService object = new ThreadPoolService();

    private int poolSize = -1;

    private ExecutorService executorService = null;

    //****** �����: �������� **********************************************************************/

    public static ThreadPoolService instance()
    {
        return object;
    }

    public void initalize()
    {
        this.poolSize = Integer.parseInt(CollectXmlParser.getInstance().getThreadNum());

        createExecutorService();
    }

    public boolean release()
    {
        return destoryExecutorService();
    }

    public Future submit(Callable task)
    {
        return executorService.submit(task);
    }

    public boolean isBusy()
    {
        return ((ThreadPoolExecutor)executorService).getQueue().size() > 0 ? true : false;
    }

    //****** �����: ˽�з��� **********************************************************************/

    private boolean destoryExecutorService()
    {
        boolean result = true;

        if(executorService != null && !executorService.isShutdown())
        {
            executorService.shutdown();
            
            try
            {
                executorService.awaitTermination(Long.MAX_VALUE, TimeUnit.SECONDS);
            }
            catch(InterruptedException e)
            {
                LogPrint.logError(logger, "Release thread pool failed.", e);

                result = false;
            }
        }
        return result;
    }

    private void createExecutorService()
    {
        destoryExecutorService();

        executorService = Executors.newFixedThreadPool(poolSize);
    }
}
