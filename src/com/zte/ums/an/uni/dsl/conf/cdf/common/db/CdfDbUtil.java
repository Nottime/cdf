package com.zte.ums.an.uni.dsl.conf.cdf.common.db;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import org.apache.log4j.Logger;

import com.zte.ums.an.uni.dsl.conf.cdf.common.LogPrint;
import com.zte.ums.an.uni.dsl.conf.cdf.common.db.singconn.CdfDbConnectionUtil;
import com.zte.ums.api.common.snmpnode.ppu.entity.SnmpNode;
import com.zte.ums.n3common.api.ZXLogger;

/**
 * <p>�ļ�����: CdfDbUtil</p>
 * <p>�ļ�����: ���ݿ⹤����</p>
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
public class CdfDbUtil
{
    //****** �����: �������� **********************************************************************/

    private static Logger logger = ZXLogger.getLogger(CdfDbUtil.class.getName());

    //****** �����: ���߷��� **********************************************************************/

    public static void closeDB(Statement stmt, ResultSet rs)
    {
        if(rs != null)
        {
            try
            {
                rs.close();
                rs = null;
            }
            catch(SQLException e)
            {
                LogPrint.logError(logger, "closeDB,close rs SQLException : ", e);
            }
        }

        if(stmt != null)
        {
            try
            {
                stmt.close();
                stmt = null;
            }
            catch(SQLException e)
            {
                LogPrint.logError(logger, "closeDB,close stmt SQLException : ", e);
            }
        }
    }

    public static void closeDB(Statement stmt)
    {
        if(stmt != null)
        {
            try
            {
                stmt.close();
                stmt = null;
            }
            catch(SQLException e)
            {
                LogPrint.logError(logger, "closeDB,close stmt SQLException : ", e);
            }
        }
    }

    public static void closeDB(Connection conn)
    {
        if(conn != null)
        {
            try
            {
                conn.close();
                conn = null;
            }
            catch(SQLException e)
            {
                LogPrint.logError(logger, "closeDB,close conn SQLException : ", e);
            }
        }
    }

    public static void closeDB(Statement stmt, Connection conn)
    {
        if(stmt != null)
        {
            try
            {
                stmt.close();
                stmt = null;
            }
            catch(SQLException e)
            {
                LogPrint.logError(logger, "closeDB,close stmt SQLException : ", e);
            }
        }

        if(conn != null)
        {
            try
            {
                conn.close();
                conn = null;
            }
            catch(SQLException e)
            {
                LogPrint.logError(logger, "closeDB,close conn SQLException : ", e);
            }
        }
    }

    public static void rollback(Connection conn)
    {
        try
        {
            conn.rollback();
        }
        catch(SQLException ex)
        {
            LogPrint.logError(logger, "rollback SQLException :", ex);
        }
    }

    /**
     * ���������ݿ��л�ȡ���������˲ɼ������ԪSNMPNODE��Ϣ
     * @return ArrayList
     */
    public static ArrayList<SnmpNode> getSnmpNodesListWithCP()
    {
        try
        {
            Connection conn = CdfDbConnectionUtil.getDBConnection4EmsAN();
            if(conn == null)
            {
                LogPrint.logError(logger, "getSnmpNodesListWithCP 1 conn == null");
                return null;
            }

            ArrayList<String> nesWithCPList = queryNesWithCPList(conn);
            conn.close();
            
            conn = CdfDbConnectionUtil.getDBConnection4EmsUep4x();
            if(conn == null)
            {
                LogPrint.logError(logger, "getSnmpNodesListWithCP 2 conn == null");
                return null;
            }
            ArrayList<SnmpNode> snmpNodeList = dbOperForGetSnmpNodes(conn, nesWithCPList);
            conn.close();

            return snmpNodeList;
        }
        catch(Exception ex)
        {
            LogPrint.logError(logger, "getSnmpNodesListWithCP error. ex: ", ex);
        }

        return null;
    }
    
    public static ArrayList<String> queryNesWithCPList(Connection conn)
    {
        Statement stmt = null;
        ArrayList<String> allNesList = null;
        ResultSet rs = null;

        String strSQL = "select Ne_Ip from AN_COLLECTPOINTTABLE";

        try
        {
            stmt = conn.createStatement();
            rs = stmt.executeQuery(strSQL);

            allNesList = getAlNeIpsFromRS(rs);
        }
        catch(SQLException ex)
        {
            LogPrint.logError(logger, "queryNesWithCPList SQLException :", ex);
        }
        finally
        {
            closeDB(stmt, rs);
        }

        return allNesList;
    }
    
    private static ArrayList<String> getAlNeIpsFromRS(ResultSet rs)
    {
        ArrayList<String> neIpList = new ArrayList<String>();
        String tmpNeIp = null;

        try
        {
            while(rs.next())
            {
                tmpNeIp = rs.getString("Ne_Ip");

                boolean isAdded = false;
                //ȥ���ظ�����
                for(int i = 0; i < neIpList.size(); i++)
                {
                    if(tmpNeIp.equalsIgnoreCase((String)neIpList.get(i)))
                    {
                        isAdded = true;
                        break;
                    }
                }

                if(!isAdded)
                {
                    neIpList.add(tmpNeIp);
                }
            }

            return neIpList;
        }
        catch(SQLException ex)
        {
            LogPrint.logError(logger, "getAlNeIpsFromRS SQLException : ", ex);
        }

        return null;
    }
    
    private static ArrayList<SnmpNode> dbOperForGetSnmpNodes(Connection conn, ArrayList<String> neIpList)
    {
        ArrayList<SnmpNode> snmpNodeList = new ArrayList<SnmpNode>();
        Statement stmt = null;
        ResultSet rs = null;
        try
        {

            for(int i = 0; i < neIpList.size(); i++)
            {
                String neQuerySql = "select NAME,IPADDRESS,MOC,SNMPCOMMUNITY,SNMPWRITECOMMUNITY,SNMPVERSION,SNMPPORT from SnmpNode  where IPADDRESS='"
                                    + (String)neIpList.get(i) + "'";
                stmt = conn.createStatement();
                rs = stmt.executeQuery(neQuerySql);

                if(rs.next())
                {
                    SnmpNode snmpNode = assembleSnmpNode(rs);
                    snmpNodeList.add(snmpNode);
                }
                
                closeDB(stmt);
            }

            return snmpNodeList;
        }
        catch(SQLException e)
        {
            LogPrint.logError(logger, "dbOperForGetSnmpNodes SQLException : ", e);
        }
        finally
        {
            closeDB(stmt, rs);
        }
        return snmpNodeList;
    }
    
    private static SnmpNode assembleSnmpNode(ResultSet rs) throws SQLException
    {
        SnmpNode snmpNode = new SnmpNode();

        String name = rs.getString("NAME");
        String ipAddress = rs.getString("IPADDRESS");
        String neType = rs.getString("MOC");
        String readCommunity = rs.getString("SNMPCOMMUNITY");
        String writeCommunity = rs.getString("SNMPWRITECOMMUNITY");
        String snmpVersion = rs.getString("SNMPVERSION");
        int snmpPort = rs.getInt("SNMPPORT");

        snmpNode.setName(name);
        snmpNode.setIpAddress(ipAddress);
        snmpNode.setMoc(neType);
        snmpNode.setSnmpCommunity(readCommunity);
        snmpNode.setSnmpWriteCommunity(writeCommunity);
        snmpNode.setSnmpVersion(snmpVersion);
        snmpNode.setSnmpPort(snmpPort);

        return snmpNode;
    }
    
    /**
     * �ж��Ƿ���ڼ�¼
     * @param rs
     * @return
     * @throws SQLException
     */
    public static boolean hasRecords(ResultSet rs)
    {
        try
        {
            return (rs.getRow() != 0 || rs.isBeforeFirst());
        }
        catch(SQLException e)
        {
            e.printStackTrace();
            return false;
        }
    }
}
