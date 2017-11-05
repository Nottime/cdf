package com.zte.ums.an.uni.dsl.conf.cdf.common.db.singconn;

/**
 * <p>�ļ�����: MSSqlDBConnector</p>
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

public class MSSqlDBConnector extends SingDBConnector
{    
    private String dbName;
    
    MSSqlDBConnector(String dbtype, String serverIp, String port, String user, String password, String dbName)
    {
        super(dbtype, serverIp, port, user, password);

        this.dbName = dbName;
        
        this.drivertitle = "jdbc:jtds:sqlserver";
        this.drivername = "net.sourceforge.jtds.jdbc.Driver";
    }
    
    @Override
    String getURL()
    {        
        if(dbName == null || dbName.equalsIgnoreCase(""))
        {
            return drivertitle + "://" + serverIp + ":" + port;
        }
        else
        {
            return drivertitle + "://" + serverIp + ":" + port + "/" + dbName;
        }
    }
}
