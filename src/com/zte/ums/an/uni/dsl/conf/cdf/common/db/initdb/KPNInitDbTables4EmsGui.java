package com.zte.ums.an.uni.dsl.conf.cdf.common.db.initdb;

import java.io.File;
import java.sql.Connection;
import java.util.Properties;

import com.zte.ums.an.uni.dsl.conf.cdf.common.db.singconn.CdfDbConnectionUtil;
import com.zte.ums.an.uni.dsl.conf.cdf.common.xml.DispatchXmlParser;

/**
/**
 * <p>�ļ�����: InitDbTables4EmsGui</p>
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

public class KPNInitDbTables4EmsGui extends InitDbTemplate
{
    @Override
    String getOperName()
    {
        return "Initializing CDF in AN database";
    }
    
    @Override
    boolean isNeedInitWholeDatabase()
    {
        return false;
    }
    
    @Override
    Connection getDBConnection4WholeDatabase()
    {
        return null;
    }
    
    @Override
    public String getSqlFile4InitWholeDatabase()
    {
        return null;
    }
    
    @Override
    Connection getDBConnection4Table()
    {
        return CdfDbConnectionUtil.getDBConnection4EmsAN();
    }
    
    @Override
    String getSqlFile4InitTable()
    {
        String userdir = System.getProperty("user.dir");
        String dbtype = DispatchXmlParser.getInstance().getDbType();
        String sqlFile = null;

        if(dbtype.equalsIgnoreCase("oracle"))
        {
            sqlFile = new File(userdir).getAbsolutePath() + "/db/scripts/ems/ems_oracle.sql";
        }
        else
        {
            sqlFile = new File(userdir).getAbsolutePath() + "/db/scripts/ems/ems_mssql.sql";
        }

        return sqlFile;
    }
    
    private static void customUserDir()
    {
        String userdir = System.getProperty("user.dir");
        String newUserDir = (new File(userdir)).getParent();
        
        Properties p = System.getProperties();
        p.setProperty("user.dir", newUserDir);
    }
    
    public static void main(String[] args)
    {
        customUserDir();
        (new KPNInitDbTables4EmsGui()).mainProcess();
    }
}
