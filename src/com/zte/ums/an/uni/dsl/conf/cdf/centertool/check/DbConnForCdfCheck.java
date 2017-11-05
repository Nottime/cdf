package com.zte.ums.an.uni.dsl.conf.cdf.centertool.check;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.Arrays;

import com.zte.ums.an.uni.dsl.conf.cdf.centertool.common.CdfTestResult;
import com.zte.ums.an.uni.dsl.conf.cdf.centertool.common.CdfTestResultConst;
import com.zte.ums.an.uni.dsl.conf.cdf.centertool.common.CenterToolUtil;
import com.zte.ums.an.uni.dsl.conf.cdf.common.db.singconn.CdfDbConnectionUtil;
import com.zte.ums.api.common.snmpnode.ppu.entity.SnmpNode;

/**
 * <p>�ļ�����: DbConnCheck.java</p>
 * <p>�ļ�����: </p>
 * <p>��Ȩ����: ��Ȩ����(C)2001-2012</p>
 * <p>��    ˾: ����ͨѶ�ɷ����޹�˾</p>
 * <p>����ժҪ: </p>
 * <p>����˵��: </p>
 * <p>������ڣ�2012-3-12</p>
 * <p>�޸ļ�¼1:</p>
 * <pre>
 *    �޸����ڣ�
 *    �� �� �ţ�
 *    �� �� �ˣ�
 *    �޸����ݣ�
 * </pre>
 * <p>�޸ļ�¼2��</p>
 * @version 1.0
 * @author ChenDuoduo_10087118
 */
public class DbConnForCdfCheck implements ICdfCheck
{
    @Override
    public String getName()
    {
        return "CDF database configuration and data version for sub-collect server";
    }
    
    @Override
    public void presentTitle()
    {
        CenterToolUtil.printGroupTile("Start CDF database for sub-collect server test");
    }
    
    @Override
    public void presentEnd()
    {
        CenterToolUtil.printGroupTile("CDF database for sub-collect server test finished.");
    }
    
    @Override
    public CdfTestResult doCheck()
    {
        try
        {
            testConn4Cdf();
            testDataVersion4CdfDB();
            return new CdfTestResult(true);

        }
        catch(Exception e)
        {
            return new CdfTestResult(e.getMessage());
        }
    }

    /**
     * @throws Exception 
     * 
     */
    private void testDataVersion4CdfDB() throws Exception
    {
        CenterToolUtil.printCheckTile("Test data version");
        
        String dataVersion = CenterToolUtil.getCdfDBDataVersion();
        
        
        String softwareVersion = CenterToolUtil.getVersion();
        
        if(!softwareVersion.equalsIgnoreCase(dataVersion))
        {
            throw new Exception(CdfTestResultConst.INCONSISTENT_VERSION + " CDF softerware version: " + softwareVersion + "; current data version: "
                                + dataVersion);
        }
        
        CenterToolUtil.printSucess();
    }

    private static void showAdditionInfo(ArrayList<SnmpNode> neListToCollect)
    {
        if(neListToCollect.size() > 0)
        {
            final int threathod = 100;
            if(neListToCollect.size() <= threathod)
            {
                System.out.println("Detailed NE IP Address:" + getIPList(neListToCollect));
            }
        }
        
        System.out.println("Note: The collecting NE list is added or deleted in the EMS.");
    }
    
    private static String getIPList(ArrayList<SnmpNode> neListToCollect)
    {
        String[] ipList = new String[neListToCollect.size()];
        for(int i = 0; i < neListToCollect.size(); i++)
        {
            ipList[i] = neListToCollect.get(i).getIpAddress();
        }
        
        return Arrays.toString(ipList);
    }

    private void testConn4Cdf() throws Exception
    {
        CenterToolUtil.printCheckTile("Connecting to CDF Database");
        Connection conn = getConn4Cdf();
        closeConn(conn);
        CenterToolUtil.printSucess();
    }
    
    private Connection getConn4Cdf() throws Exception
    {
        CenterToolUtil.changeToSubCollectDir();
        Connection conn = CdfDbConnectionUtil.getDBConnection4Cdf();
        if(conn == null)
        {
            throw new Exception(CdfTestResultConst.DB_CONNECT_CDF_FAIL);
        }
        
        return conn;
    }
    
    private void closeConn(Connection conn) throws Exception
    {
        try
        {
            conn.close();
        }
        catch(Exception e)
        {
            throw new Exception(CdfTestResultConst.DB_CONNECT_CLOSE_FAIL);
        }
    }
    
    public static void main(String[] args)
    {
        ArrayList<SnmpNode> list = new ArrayList<SnmpNode>();
        
        SnmpNode ne1 = new SnmpNode();
        ne1.setIpAddress("1.1.1.1");
        
        SnmpNode ne2 = new SnmpNode();
        ne2.setIpAddress("1.1.1.2");
        
        list.add(ne1);
        list.add(ne2);
        
        showAdditionInfo(list);
    }
}
