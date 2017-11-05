package com.zte.ums.an.uni.dsl.conf.cdf.collect.parser.common;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import org.apache.log4j.Logger;

import com.zte.ums.an.uni.dsl.conf.cdf.common.LogPrint;
import com.zte.ums.n3common.api.ZXLogger;

/**
 * <ul>
 * <li>�ļ�����: RecordReadProxy</li>
 * <li>�ļ�����: </li>
 * <li>��Ȩ����: ��Ȩ����(C) 2012</li>
 * <li>�� ˾: ����ͨѶ�ɷ����޹�˾</li>
 * <li>����ժҪ: </li>
 * <li>����˵��: </li>
 * <li>�������:2011-12-06 </li>
 * </ul>
 * <ul>
 * <li>�޸ļ�¼: </li>
 * <li>�� �� ��: </li>
 * <li>�޸�����: </li>
 * <li>�� �� ��:</li>
 * <li>�޸�����:</li>
 * </ul>
 * 
 * @author jingxueshi
 * @version 1.0.0
 */

public class SeqRecProxy
{
    private Logger logger = ZXLogger.getLogger(this.getClass().getName());
    
    private BufferedReader reader;
    
    private Record currRecord;
    private boolean isEndofFile = false;
    
    public SeqRecProxy(BufferedReader reader) throws IOException
    {
        this.reader = reader;
        next();
    }
    
    public SeqRecProxy(File file) throws IOException
    {
        this.reader = new BufferedReader(new FileReader(file));
        next();
    }
    
    public boolean next()
    {
        try
        {
            String line = reader.readLine();

            //�x���ջ��߿��ַ����J���ļ��Y��
            if(line == null || line.equalsIgnoreCase(""))
            {
                this.isEndofFile = true;
                this.currRecord = null;
                return false;
            }

            this.currRecord = new Record(line);
            return true;
        }
        catch(IOException e)
        {
            LogPrint.logError(logger, "", e);
            return false;
        }
    }

    public Record getRecord()
    {
        return currRecord;
    }
    
    public boolean isEndOfFile()
    {
        return isEndofFile;
    }

    public void close()
    {
        try
        {
            if(reader != null)
            {
                reader.close();
            }
        }
        catch (IOException e)
        {
            LogPrint.logError(logger, "", e);
        }
    }
}
