package com.zte.ums.an.uni.dsl.conf.cdf.common.db.initdb;

import java.io.File;
import java.sql.Connection;
import java.util.Properties;

import com.zte.ums.an.uni.dsl.conf.cdf.common.db.singconn.CdfDbConnectionUtil;
import com.zte.ums.an.uni.dsl.conf.cdf.common.xml.CollectXmlParser;

/**
 * <p>�ļ�����: InitDbTables</p>
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

public class KPNInitDbTables extends InitDbTemplate
{
    @Override
    String getOperName()
    {
        return "Initializing CDF database";
    }
    
    @Override
    boolean isNeedInitWholeDatabase()
    {
        return true;
    }
    
    @Override
    Connection getDBConnection4WholeDatabase()
    {
        return CdfDbConnectionUtil.getDBConnection();
    }
    
    @Override
    String getSqlFile4InitWholeDatabase()
    {
        String userdir = System.getProperty("user.dir");
        String dbtype = CollectXmlParser.getInstance().getDbType();
        
        String sqlFile = null;
        if(dbtype.equalsIgnoreCase("oracle"))
        {
            sqlFile = new File(userdir).getAbsolutePath() + "/db/scripts/cdf/cdf_createdb_oracle.sql";
        }
        else
        {
            sqlFile = new File(userdir).getAbsolutePath() + "/db/scripts/cdf/cdf_createdb_mssql.sql";
        }

        return sqlFile;
    }
    
    @Override
    Connection getDBConnection4Table()
    {
        return CdfDbConnectionUtil.getDBConnection4Cdf();
    }
    
    @Override
    String getSqlFile4InitTable()
    {
        String userdir = System.getProperty("user.dir");
        String dbtype = CollectXmlParser.getInstance().getDbType();
        
        String sqlFile = null;
        if(dbtype.equalsIgnoreCase("oracle"))
        {
            sqlFile = new File(userdir).getAbsolutePath() + "/db/scripts/cdf/cdf_oracle.sql";
        }
        else
        {
            sqlFile = new File(userdir).getAbsolutePath() + "/db/scripts/cdf/cdf_mssql.sql";
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
    
    private static boolean executeDirectly(String[] args)
    {
        if(args != null && args.length >= 1)
        {
            return "-f".equals(args[0]);
        }
            
        return false;
    }
    
    private static void initDBDirectly_NoPrompt()
    {
        (new KPNInitDbTables()).doInitDB();
    }

    private static void initDBWithPrompt()
    {
        (new KPNInitDbTables()).mainProcess();
    }
    
    public static void main(String[] args)
    {
        customUserDir();
        if(executeDirectly(args))
        {
            initDBDirectly_NoPrompt();
        }
        else
        {
            initDBWithPrompt();
        }
    }
}
