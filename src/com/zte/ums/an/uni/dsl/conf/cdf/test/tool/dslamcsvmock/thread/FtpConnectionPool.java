package com.zte.ums.an.uni.dsl.conf.cdf.test.tool.dslamcsvmock.thread;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

import org.apache.log4j.Logger;

import com.zte.ums.an.uni.dsl.conf.cdf.common.FtpInfo;
import com.zte.ums.an.uni.dsl.conf.cdf.common.LogPrint;
import com.zte.ums.an.uni.dsl.conf.cdf.test.tool.customizedcsvmock.common.CdfMockServerXmlParser;
import com.zte.ums.n3common.api.ZXLogger;

/**
 * <p>�ļ�����: FtpConnectionPool</p>
 * <p>�ļ�����: FTP���ӳ�</p>
 * <p>��Ȩ����: ��Ȩ����(C)2001-2012</p>
 * <p>��    ˾: ����ͨѶ�ɷ����޹�˾</p>
 * <p>����ժҪ: </p>
 * <p>����˵��: </p>
 * <p>������ڣ�2012��1��5��</p>
 * <p>�޸ļ�¼1:</p>
 * <pre>
 *    �޸����ڣ�
 *    �� �� �ţ�
 *    �� �� �ˣ�
 *    �޸����ݣ�
 * </pre>
 * <p>�޸ļ�¼2��</p>
 * @version 1.0
 * @author  Chenduoduo_10087118
 */
public class FtpConnectionPool
{
    //****** �����: �������� **********************************************************************/

    /** ���ӳأ�����Ѵ�����δʹ�õ����� */
    private Stack pool = new Stack();

    /** �ѱ�ȡ�ߡ�����ʹ�õ����� */
    private Map using = new HashMap();

    /** �Ѿ��������ӳؼ��� */
    private int created = 0;

    /** ���ӳ������ */
    private int max = 100;

    /** ����ͬ��������byte[]���󴴽�ʱ����̣�ռ�������� */
    private final byte[] createdLock = new byte[0];
    private final byte[] usingLock = new byte[0];
    private final byte[] poolLock = new byte[0];
    private final byte[] waitNofityLock = new byte[0];

    /** ��־��¼ */
    private Logger logger = ZXLogger.getLogger(this.getClass().getName());

    /** ��ʵ�� */
    private static FtpConnectionPool instance = new FtpConnectionPool();
    
    private FtpInfo ftpInfo;

    //****** �����: ���췽�� **********************************************************************/

    /**
     * ˽�еĹ��췽������ֹ���ⲿֱ��ʵ����
     */
    private FtpConnectionPool()
    {
        try
        {
            max = CdfMockServerXmlParser.getInstance().getFtpConnPoolNum();
        }
        catch(Exception ex)
        {
            LogPrint.logError(logger, "", ex);
        }
    }

    //****** �����: �������� **********************************************************************/

    public static FtpConnectionPool getInstance()
    {
        return instance;
    }
    
    //TODO  Ŀǰ��֧�ֵ���Collect Svr
    public void setFtpInfo(FtpInfo ftpInfo)
    {
        this.ftpInfo = ftpInfo;
    }

    /**
     * �����ӳ���ȡ��һ���������ݿ����ӣ������������û�п���������������û�г������ֵ���򴴽������ӣ�
     * ����һֱ����ֱ����ȡ�ɹ���
     * ע�⣬ʹ����Ϻ���Ҫ����freeConnection�����ͷš�
     * @return
     */
    public FtpConnection getConnection(String threadName)
    {
        FtpConnection conn = null;
        do
        {
            synchronized(poolLock)
            {
                if(!pool.isEmpty())
                {
                    conn = (FtpConnection)pool.pop();
                }
                else
                {
                    conn = createNewConn();
                }
            }

            if(conn == null)
            {
                waitUntilNotify();
            }
        }
        while(conn == null);

        synchronized(usingLock)
        {
            using.put(conn, threadName);
        }

        return conn;
    }

    /**
     * ����һ��FTP���ӵ����У������̷߳������ӳ�
     * @return void
     */
    public void freeConnection(FtpConnection conn)
    {
        if(conn == null)
        {
            return;
        }

        try
        {
            synchronized(usingLock)
            {
                using.remove(conn);
            }
            synchronized(poolLock)
            {
                pool.add(conn);
            }
            synchronized(waitNofityLock)
            {
                waitNofityLock.notify();
            }
        }
        catch(Exception ex)
        {
            LogPrint.logError(logger, "", ex);
        }
    }

    /**
     * �ͷ���������,�����³�
     * return void
     */
    public void releasePool()
    {
        instance = new FtpConnectionPool();

        /* �ͷ��������ӣ�����Using��Using������ʹ�õ��һ����µĳ��ﱻ�ͷ� */
        int poolSize = -1;
        synchronized(poolLock)
        {
            poolSize = pool.size();
            ;
            while(!pool.isEmpty())
            {
                FtpConnection conn = (FtpConnection)pool.pop();
                conn.disconnect();
            }
        }
        synchronized(createdLock)
        {
            created -= poolSize;
        }

        LogPrint.logInfo(logger, "Success in releasing ftp pool. Now in ftp pool, created == " + created + ". pool == " + pool.size() + ". using == "
            + using.size() + ".");
    }

    //****** �����: ˽�з��� **********************************************************************/

    private void waitUntilNotify()
    {
        synchronized(waitNofityLock)
        {
            try
            {
                waitNofityLock.wait();
            }
            catch(InterruptedException e)
            {
                LogPrint.logError(logger, "", e);
            }
        }
    }

    private FtpConnection createNewConn()
    {
        try
        {
            synchronized(createdLock)
            {
                if(created < max)
                {
                    FtpConnection conn = new FtpConnection(ftpInfo.ftpServerIp, ftpInfo.ftpUser, ftpInfo.ftpUserPasswd);

                    if(conn != null)
                    {
                        created++;
                    }
                    
                    return conn;
                }
            }
        }
        catch(Exception ex)
        {
            LogPrint.logError(logger, "", ex);
        }

        return null;
    }
}
