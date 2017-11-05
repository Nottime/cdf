package com.zte.ums.an.uni.dsl.conf.cdf.common.db.initdb;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.Statement;
import java.util.Properties;

import com.zte.ums.an.uni.dsl.conf.cdf.common.CdfConst;
import com.zte.ums.an.uni.dsl.conf.cdf.common.CdfUtil;
import com.zte.ums.an.uni.dsl.conf.cdf.common.db.CdfDbUtil;
import com.zte.ums.an.uni.dsl.conf.cdf.common.xml.CollectXmlParser;

/**
 * <p>�ļ�����: InitDbTemplate</p>
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

public abstract class InitDbTemplate
{
    private final static String ACTION_INITIALIZE = "1";
    private final static String ACTION_EXIT = "2";

    protected boolean initWholeDatabase()
    {
        Connection conn = getDBConnection4WholeDatabase();
        if(conn == null)
        {
            System.out.println("initWholeDatabase error. conn == null");
            return false;
        }

        String sqlFile = getSqlFile4InitWholeDatabase();

        return doInitBySqlFile(conn, sqlFile);
    }

    private boolean initTables()
    {
        Connection conn = getDBConnection4Table();
        if(conn == null)
        {
            System.out.println("initTables error. conn == null");
            return false;
        }

        String sqlFile = getSqlFile4InitTable();

        return doInitBySqlFile(conn, sqlFile);
    }
    
    private boolean doInitBySqlFile(Connection conn, String sqlFile)
    {
        Statement stmt = null;
        String sql = null;
        
        try
        {
            stmt = conn.createStatement();

            SqlFileParser sqlFileParser = new SqlFileParser(sqlFile, getProperty());
            while((sql = sqlFileParser.nextStatement()) != null)
            {
                stmt.execute(sql);
            }
            return true;
        }
        catch(Exception ex)
        {
            System.out.println("initDB error on sql:" + sql);
            System.out.println("Detail:" + ex.getMessage());
        }
        finally
        {
            CdfDbUtil.closeDB(stmt, conn);
        }

        return false;
    }

    private static Properties getProperty()
    {
        Properties p = new Properties();
        
        String dbPath = CollectXmlParser.getInstance().getDBPath();
        p.put(CdfConst.DB_PATH, formatDbPath(dbPath));
        
        p.put(CdfConst.VERSION_ID, getVersion());
        
        return p;
    }

    private static String getVersion()
    {
        String dir = System.getProperty("user.dir");
        System.setProperty("user.dir", new File(dir).getParent());
        String version =  CdfUtil.getVersion();
        
        System.setProperty("user.dir", dir);
        
        return version;
    }

    private static String formatDbPath(String dbPath)
    {
        dbPath = dbPath.replace('\\', '/');
        
        if(dbPath == "" || dbPath.endsWith("/"))
        {
            return dbPath;
        }
        
        return dbPath + "/";
    }
    
    private String waitForCorrectInput(BufferedReader reader) throws IOException
    {
        String action = "";
        while(action == null || (!ACTION_INITIALIZE.equals(action.trim()) && !ACTION_EXIT.equals(action.trim())))
        {
            System.out.println("Please select your operation:");
            System.out.println("1." + getOperName());
            System.out.println("2.Exit");
            action = reader.readLine();
        }
        return action;
    }
    
    void mainProcess()
    {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        try
        {
            String action = waitForCorrectInput(reader);
            
            if(ACTION_EXIT.equals(action))
            {
                System.exit(1);
            }
            else if(ACTION_INITIALIZE.equals(action))
            {
                System.out.println(getOperName() + ", please wait...");
                
                if(doInitDB())
                {
                    System.out.println(getOperName() + " is complete successfully.");
                    System.exit(0);
                }
            }
        }
        catch(IOException e1)
        {
            System.out.println("Exception occur: " + e1);
            System.exit(5);
        }
    }

    public boolean doInitDB()
    {
        if(isNeedInitWholeDatabase())
        {
            if(!initWholeDatabase())
            {
                return false;
            }
        }
        
        if(!initTables())
        {
            return false;
        }
        
        return true;
    }
    
    abstract String getOperName();
    
    /** �Ƿ���Ҫ��ʼ����ռ䣬���Ϊ��getDBConnection4WholeDatabase��getSqlFile4InitWholeDatabase����ʵ��*/
    abstract boolean isNeedInitWholeDatabase();
    abstract Connection getDBConnection4WholeDatabase();
    abstract String getSqlFile4InitWholeDatabase();
    
    abstract Connection getDBConnection4Table();
    abstract String getSqlFile4InitTable();
}
