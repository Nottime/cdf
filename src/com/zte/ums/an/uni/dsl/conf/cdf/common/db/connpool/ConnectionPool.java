package com.zte.ums.an.uni.dsl.conf.cdf.common.db.connpool;

import java.sql.Connection;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

import org.apache.log4j.Logger;

import com.zte.ums.an.uni.dsl.conf.cdf.common.LogPrint;
import com.zte.ums.an.uni.dsl.conf.cdf.common.db.CdfDbUtil;
import com.zte.ums.an.uni.dsl.conf.cdf.common.db.singconn.CdfDbConnectionUtil;
import com.zte.ums.an.uni.dsl.conf.cdf.common.xml.CollectXmlParser;
import com.zte.ums.n3common.api.ZXLogger;

/**
 * <p>�ļ�����: ConnectionPool.java</p>
 * <p>�ļ�����: ���ݿ����ӳ�</p>
 * <p>��Ȩ����: ��Ȩ����(C)2001-2012</p>
 * <p>��    ˾: ����ͨѶ�ɷ����޹�˾</p>
 * <p>����ժҪ: </p>
 * <p>����˵��: </p>
 * <p>������ڣ�2011��11��21��</p>
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
public class ConnectionPool
{
    //****** �����: �������� **********************************************************************/

    private Logger logger = ZXLogger.getLogger(this.getClass().getName());
    
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

    /** ��ʵ�� */
    private static ConnectionPool instance = new ConnectionPool();

    //****** �����: ���췽�� **********************************************************************/

    /**
     * ˽�еĹ��췽������ֹ���ⲿֱ��ʵ����
     */
    private ConnectionPool()
    {
        max = Integer.parseInt(CollectXmlParser.getInstance().getDBMaxConnect());
    }

    //****** �����: �������� **********************************************************************/

    public static ConnectionPool getInstance()
    {
        return instance;
    }

    /**
     * �����ӳ���ȡ��һ���������ݿ����ӣ������������û�п���������������û�г������ֵ���򴴽������ӣ�
     * ����һֱ����ֱ����ȡ�ɹ���
     * ע�⣬ʹ����Ϻ���Ҫ����freeConnection�����ͷš�
     * @return
     */
    public Connection getConnection(String threadName)
    {
        Connection conn = null;
        do
        {
            synchronized(poolLock)
            {
                if(!pool.isEmpty())
                {
                    conn = (Connection)pool.pop();
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
     * ����һ�����ݿ����ӵ����У������̷߳������ӳ�
     * @return void
     */
    public void freeConnection(Connection conn)
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
        instance = new ConnectionPool();

        /* �ͷ��������ӣ�����Using��Using������ʹ�õ��һ����µĳ��ﱻ�ͷ� */
        int poolSize = -1;
        synchronized(poolLock)
        {
            poolSize = pool.size();
            ;
            while(!pool.isEmpty())
            {
                Connection conn = (Connection)pool.pop();
                CdfDbUtil.closeDB(conn);
            }
        }
        synchronized(createdLock)
        {
            created -= poolSize;
        }
        
        LogPrint.logInfo(logger, "Success in releasing DB pool. Now in DB pool, created == " + created + ". pool == " + pool.size() + ". using == "
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

    private Connection createNewConn()
    {
        try
        {
            synchronized(createdLock)
            {
                if(created < max)
                {
                    Connection conn = CdfDbConnectionUtil.getDBConnection4Cdf();

                    if(conn != null)
                    {
                        created++;
                    }
                    else
                    {
                        LogPrint.logError(logger, "Failed to get db connection, please check the parameters.");
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
