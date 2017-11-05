package com.zte.ums.an.uni.dsl.conf.cdf.centertool.config;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import com.zte.ums.an.uni.dsl.conf.cdf.centertool.config.model.cron.AbsCdfConfigDataModelWithSequence;

/**
 * <p>�ļ�����: ConfigCheckRunner.java</p>
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
public class ConfigCheckRunner
{
    InputStreamReader systemInReader = null;
    BufferedReader reader = null;

    public ConfigCheckRunner()
    {
        this.systemInReader = new InputStreamReader(System.in);
        this.reader = new BufferedReader(systemInReader);
    }
    
    public void runCheck(ICdfConfigDataModel[] checkList)
    {
        for(ICdfConfigDataModel model : checkList)
        {
            runCheck(model);
        }
    }

    private void runCheck(ICdfConfigDataModel model)
    {
        while(true)
        {
            String input = getValidInput(model);
            if(model.setNewValue(input))
            {
                break;
            }
        }
    }
    
    public String runSingleCheck(AbsCdfConfigDataModelWithSequence model)
    {
        runCheck(model);
        return model.getUserInput();
    }

    private String getValidInput(ICdfConfigDataModel info)
    {
        try
        {
            while(true)
            {
                System.out.print(info.getTitle() + "[" + info.getCurrValue() + "]:");
                return getUserInput(info);
            }
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }

        return null;
    }

    private String getUserInput(ICdfConfigDataModel info) throws IOException
    {
        String input = reader.readLine();
        if(input != null)
        {
            input = input.trim();
            
            if(input.equalsIgnoreCase(""))
            {
                input = info.getCurrValue();
            }
        }
        
        return input;
    }
    
    
    public void close()
    {
        if(systemInReader != null)
        {
            try
            {
                systemInReader.close();
            }
            catch(IOException e)
            {
                e.printStackTrace();
            }
        }
        
        if(reader != null)
        {
            try
            {
                reader.close();
            }
            catch(IOException e)
            {
                e.printStackTrace();
            }
        }
    }
}
