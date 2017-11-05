package com.zte.ums.an.uni.dsl.conf.cdf.common.db.singconn;

import java.sql.Connection;

import org.apache.log4j.Logger;

import com.zte.ums.an.uni.dsl.conf.cdf.common.CdfConst;
import com.zte.ums.an.uni.dsl.conf.cdf.common.LogPrint;
import com.zte.ums.an.uni.dsl.conf.cdf.common.xml.CollectXmlParser;
import com.zte.ums.an.uni.dsl.conf.cdf.common.xml.DispatchXmlParser;
import com.zte.ums.n3common.api.ZXLogger;

/**
 * <p>�ļ�����: ConnectionFactory</p>
 * <p>�ļ�����: </p>
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
public class CdfDbConnectionUtil
{
    private static Logger logger = ZXLogger.getLogger(CdfDbConnectionUtil.class.getName());
    
    private CdfDbConnectionUtil()
    {
    }
    
    public static Connection getDBConnection()
    {
        try
        {
            String dbtype = CollectXmlParser.getInstance().getDbType();
            String serverIp = CollectXmlParser.getInstance().getDbServerIp();
            String port = CollectXmlParser.getInstance().getDbPort();
            String user = CollectXmlParser.getInstance().getDbSuperUser();
            String password = CollectXmlParser.getInstance().getDbSuperUserPassword();
            String dbName = null;
            if(dbtype.equalsIgnoreCase(CdfConst.DB_TYPE_ORACLE))
            {
                dbName = CollectXmlParser.getInstance().getDbName();
            }
            
            return SingDBConnFactory.getDBConnect(dbtype, serverIp, port, user, password, dbName);
        }

        catch(Exception ex)
        {
            LogPrint.logError(logger, "getDBConnection", ex);
            return null;
        }
    }
    
    public static Connection getDBConnection4Cdf()
    {
        try
        {
            String dbtype = CollectXmlParser.getInstance().getDbType();
            String serverIp = CollectXmlParser.getInstance().getDbServerIp();
            String port = CollectXmlParser.getInstance().getDbPort();
            String user = "cdf";
            String password = CdfConst.DB_PASSWORD_FOR_CDF;
            String dbName = null;
            if(dbtype.equalsIgnoreCase(CdfConst.DB_TYPE_ORACLE))
            {
                dbName = CollectXmlParser.getInstance().getDbName();
            }
            
            return SingDBConnFactory.getDBConnect(dbtype, serverIp, port, user, password, dbName);
        }
        
        catch(Exception ex)
        {
            LogPrint.logError(logger, "getDBConnection4Cdf", ex);
            return null;
        }
    }

    public static Connection getDBConnection4EmsAN()
    {
        try
        {
            String dbtype = DispatchXmlParser.getInstance().getDbType();
            String serverIp = DispatchXmlParser.getInstance().getDbServerIp();
            String port = DispatchXmlParser.getInstance().getDbPort();
            String user = "an";
            String password = "U_tywg_2008";
            String dbName = null;
            if(dbtype.equalsIgnoreCase(CdfConst.DB_TYPE_ORACLE))
            {
                dbName = DispatchXmlParser.getInstance().getDbName();
            }
            
            return SingDBConnFactory.getDBConnect(dbtype, serverIp, port, user, password, dbName);
        }
        catch(Exception ex)
        {
            LogPrint.logError(logger, "getDBConnection4EmsAN", ex);
            return null;
        }
    }
   
    public static Connection getDBConnection4EmsUep4x()
    {
        try
        {
            String dbtype = DispatchXmlParser.getInstance().getDbType();
            String serverIp = DispatchXmlParser.getInstance().getDbServerIp();
            String port = DispatchXmlParser.getInstance().getDbPort();
            String user = "uep4x";
            String password = "U_tywg_2008";
            String dbName = null;
            if(dbtype.equalsIgnoreCase(CdfConst.DB_TYPE_ORACLE))
            {
                dbName = DispatchXmlParser.getInstance().getDbName();
            }

            return SingDBConnFactory.getDBConnect(dbtype, serverIp, port, user, password, dbName);
        }
        catch(Exception ex)
        {
            LogPrint.logError(logger, "getDBConnection4EmsUep4x", ex);
            return null;
        }
    }
}
