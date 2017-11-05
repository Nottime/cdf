package com.zte.ums.an.uni.dsl.conf.cdf.centertool.check;

import java.sql.Connection;

import com.zte.ums.an.uni.dsl.conf.cdf.centertool.common.CdfTestResult;
import com.zte.ums.an.uni.dsl.conf.cdf.centertool.common.CdfTestResultConst;
import com.zte.ums.an.uni.dsl.conf.cdf.centertool.common.CenterToolUtil;
import com.zte.ums.an.uni.dsl.conf.cdf.common.db.singconn.CdfDbConnectionUtil;

/**
 * <p>�ļ�����: DbConnOfSubcollectCheck.java</p>
 * <p>�ļ�����: </p>
 * <p>��Ȩ����: ��Ȩ����(C)2001-2013</p>
 * <p>��    ˾: ����ͨѶ�ɷ����޹�˾</p>
 * <p>����ժҪ: </p>
 * <p>����˵��: </p>
 * <p>������ڣ�2012-7-11</p>
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
public class DbConnOfSubcollectCheck implements ICdfCheck
{
    @Override
    public String getName()
    {
        return "sub-collect server database configuration";
    }
    
    @Override
    public void presentTitle()
    {
        CenterToolUtil.printGroupTile("Start sub-collect server database configuration test");
    }
    
    @Override
    public void presentEnd()
    {
        CenterToolUtil.printGroupTile("Sub-collect server database configuration test finished.");
    }
    
    @Override
    public CdfTestResult doCheck()
    {
        try
        {
            testConn();
            return new CdfTestResult(true);

        }
        catch(Exception e)
        {
            return new CdfTestResult(e.getMessage());
        }
    }
    
    private void testConn() throws Exception
    {
        CenterToolUtil.printCheckTile("Connecting to sub-collect server Database");
        Connection conn = getConn();
        closeConn(conn);
        CenterToolUtil.printSucess();
    }
    
    private Connection getConn() throws Exception
    {
        CenterToolUtil.changeToSubCollectDir();
        Connection conn = CdfDbConnectionUtil.getDBConnection();
        if(conn == null)
        {
            throw new Exception(CdfTestResultConst.DB_CONNECT_SUBCOLLECT_FAIL);
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
}
