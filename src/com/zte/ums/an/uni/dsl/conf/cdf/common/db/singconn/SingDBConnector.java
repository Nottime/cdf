package com.zte.ums.an.uni.dsl.conf.cdf.common.db.singconn;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 * <p>�ļ�����: SingDBConnector</p>
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

public abstract class SingDBConnector
{
    String dbtype;
    String serverIp;
    String port;
    
    String drivername;
    String user;
    String password;
    String drivertitle;
    
    SingDBConnector(String dbtype, String serverIp, String port, String user, String password)
    {
        this.dbtype = dbtype;
        this.serverIp = serverIp;
        this.port = port;
        this.user = user;
        this.password = password;
    }
        
    Connection getDBConnection() throws Exception 
    {        
        Class.forName(drivername);
        return DriverManager.getConnection(getURL(), user, password);
    }
    
    abstract String getURL() throws Exception;
}
