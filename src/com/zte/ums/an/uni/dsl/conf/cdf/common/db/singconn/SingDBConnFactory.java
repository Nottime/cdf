package com.zte.ums.an.uni.dsl.conf.cdf.common.db.singconn;

import java.sql.Connection;

import com.zte.ums.an.uni.dsl.conf.cdf.common.CdfConst;

/**
 * <p>�ļ�����: SingDBConnFactory</p>
 * <p>�ļ�����: </p>
 * <p>��Ȩ����: ��Ȩ����(C)2001-2012</p>
 * <p>��    ˾: ����ͨѶ�ɷ����޹�˾</p>
 * <p>����ժҪ: </p>
 * <p>����˵��: </p>
 * <p>������ڣ�2011��11��25��</p>
 * <p>�޸ļ�¼1:</p>
 * <pre>
 *    �޸����ڣ�
 *    �� �� �ţ�
 *    �� �� �ˣ�
 *    �޸����ݣ�
 * </pre>
 * <p>�޸ļ�¼2��</p>
 * @version 1.0
 * @author  ChenDuoduo_10087118
 */

public class SingDBConnFactory
{
    private SingDBConnFactory()
    {
    }

    private static SingDBConnector getDBConnector(String dbtype, String serverIp, String port, String user, String password, String dbName)
                    throws Exception
    {
        if(dbtype.equalsIgnoreCase(CdfConst.DB_TYPE_ORACLE))
        {
            String oracleSid = dbName;
            return new OracleDBConnector(dbtype, serverIp, port, user, password, oracleSid);
        }
        else if(dbtype.equalsIgnoreCase(CdfConst.DB_TYPE_MSSQL))
        {
            return new MSSqlDBConnector(dbtype, serverIp, port, user, password, dbName);
        }

        throw new Exception("Invalid DB Type: " + dbtype);
    }
    
    public static Connection getDBConnect(String dbtype, String serverIp, String port, String user, String password, String dbName) throws Exception
    {
        SingDBConnector connector = getDBConnector(dbtype, serverIp, port, user, password, dbName);
        return connector.getDBConnection();
    }
}
