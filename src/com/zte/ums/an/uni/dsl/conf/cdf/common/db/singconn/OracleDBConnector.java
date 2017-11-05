package com.zte.ums.an.uni.dsl.conf.cdf.common.db.singconn;

/**
 * <p>�ļ�����: OracleDBConnector</p>
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

public class OracleDBConnector extends SingDBConnector
{    
    private String oracleSid;
    
    OracleDBConnector(String dbtype, String serverIp, String port, String user, String password, String oracleSid)
    {
        super(dbtype, serverIp, port, user, password);
        
        this.oracleSid = oracleSid;
        
        this.drivername = "oracle.jdbc.driver.OracleDriver";
        this.drivertitle = "jdbc:oracle:thin";
    }
    
    @Override
    String getURL()
    {
        return drivertitle + ":@" + serverIp + ":" + port + ":" + oracleSid;
    }

}
