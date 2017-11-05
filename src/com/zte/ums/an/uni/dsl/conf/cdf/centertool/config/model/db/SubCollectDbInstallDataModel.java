package com.zte.ums.an.uni.dsl.conf.cdf.centertool.config.model.db;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import com.zte.ums.an.uni.dsl.conf.cdf.centertool.common.CenterToolUtil;
import com.zte.ums.an.uni.dsl.conf.cdf.centertool.config.ICdfConfigDataModel;
import com.zte.ums.an.uni.dsl.conf.cdf.common.db.initdb.KPNInitDbTables;

/**
 * <p>�ļ�����: SubCollectDbInstallDataModel</p>
 * <p>�ļ�����: </p>
 * <p>��Ȩ����: ��Ȩ����(C)2001-2012</p>
 * <p>��    ˾: ����ͨѶ�ɷ����޹�˾</p>
 * <p>����ժҪ: </p>
 * <p>����˵��: </p>
 * <p>������ڣ�2012-3-13</p>
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
public class SubCollectDbInstallDataModel implements ICdfConfigDataModel
{
    private static final String DEFAULT_DBPATH = "DEFAULT";
    
    @Override
    public String getTitle()
    {
        return "Do you want to install CDF database? (Needed for the first time or CDF upgrade)(y/n)";
    }
           
    @Override
    public String getCurrValue()
    {
        return "n";
    }
    
    @Override
    public boolean setNewValue(String input)
    {
        if("y".equalsIgnoreCase(input))
        {
            return doInstall();
        }
        else if("n".equalsIgnoreCase(input) || "".equalsIgnoreCase(input))
        {
            return true;
        }

            return false;
    }
    
    private boolean doInstall()
    {
        if(!inputDbPath())
        {
            System.out.println();
            return false;
        }
        
        CenterToolUtil.changeToSubCollectDir();
        CenterToolUtil.printCheckTile("Installing CDF database");
        
        boolean isSuccess = (new KPNInitDbTables()).doInitDB();
        
        if(isSuccess)
        {
            CenterToolUtil.printSucess();
            return true;
        }
        else
        {
            System.out.println("Failed.");
            return false;
        }
    }

    private boolean inputDbPath()
    {
        if(CenterToolUtil.getCollectXml().getDbType().equalsIgnoreCase("oracle"))
        {
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

            try
            {
                System.out.print("Input database server's diretory for CDF database file (type \"DEFAULT\" to used default path) ["
                                 + getDisplayCurrentDbPath() + "]:");
                setDbPathByUserInput(reader);

                if(askContinueInstall())
                {
                    return true;
                }
            }
            catch(IOException e)
            {
                e.printStackTrace();
            }

            return false;
        }
        
       return true;
    }
    
    private void setDbPathByUserInput(BufferedReader reader) throws IOException
    {
        String input = reader.readLine();
        
        if(input != null)
        {
            input = input.trim();
            if(!input.equalsIgnoreCase(""))
            {
                setDbPath(input);
            }
        }
    }

    private static String getDisplayCurrentDbPath()
    {
        String displayedCurrPath = CenterToolUtil.getCollectXml().getDBPath();
        if(displayedCurrPath.equals(""))
        {
            displayedCurrPath = DEFAULT_DBPATH;
        }
        
        return displayedCurrPath;
    }
    
    private static void setDbPath(String inputDbPath)
    {
        if(inputDbPath.equalsIgnoreCase(DEFAULT_DBPATH))
        {
            CenterToolUtil.getCollectXml().setDBPath("");
        }
        else
        {
            CenterToolUtil.getCollectXml().setDBPath(inputDbPath);
        }
    }
    
    private static boolean askContinueInstall()
    {
        String dbServerIP = CenterToolUtil.getCollectXml().getDbServerIp();
        if(CenterToolUtil.isLocalHostIP(dbServerIP))
        {
            return true;
        }

        System.out.print("The database(" + dbServerIP
                         + ") is not local, please ensure the remote path exists. Confirm the path and continue to install cdf database?(y/n)[y]");
        String input = CenterToolUtil.readValidInput(true, "y", "n", "");

        if("y".equalsIgnoreCase(input) || "".equalsIgnoreCase(input))
        {
            return true;
        }
        else
        {
            return false;
        }
    }
    
}
