package com.zte.ums.an.uni.dsl.conf.cdf.collect.parser.dataprocess.append;

import java.util.ArrayList;
import java.util.HashMap;

import com.zte.ums.an.uni.dsl.conf.cdf.collect.parser.common.FileInfo;

/**
 * <p>�ļ�����: DataAppenderBuffer.java</p>
 * <p>�ļ�����: </p>
 * <p>��Ȩ����: ��Ȩ����(C)2001-2012</p>
 * <p>��    ˾: ����ͨѶ�ɷ����޹�˾</p>
 * <p>����ժҪ: </p>
 * <p>����˵��: </p>
 * <p>������ڣ�2012-3-1</p>
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
public class CopyOfDataAppenderBuffer
{
    private HashMap<String, ArrayList<ArrayList<String>>> fileToLine = null;
    private HashMap<String, Integer> fileToCursor = null;
    
    public CopyOfDataAppenderBuffer(FileInfo[] appendFileInfoList)
    {
        initHashMap(appendFileInfoList);
    }

    private void initHashMap(FileInfo[] appendFileInfoList)
    {
        initFileToLine(appendFileInfoList);
        
        initFileToCursor(appendFileInfoList);
    }

    private void initFileToCursor(FileInfo[] appendFileInfoList)
    {
        fileToCursor = new HashMap<String, Integer>();
        for(FileInfo fileInfo : appendFileInfoList)
        {
            fileToCursor.put(getKey(fileInfo), -1);
        }
    }

    private void initFileToLine(FileInfo[] appendFileInfoList)
    {
        fileToLine = new HashMap<String, ArrayList<ArrayList<String>>>();
        for(FileInfo fileInfo : appendFileInfoList)
        {
            fileToLine.put(getKey(fileInfo), new ArrayList<ArrayList<String>>());   
        }
    }
    
    private String getKey(FileInfo fileInfo)
    {
        return fileInfo.getbulkPoolName();
    }

    public synchronized void addBuffer(FileInfo fileInfo, ArrayList<String> tmpAppendLine)
    {
        fileToLine.get(getKey(fileInfo)).add(tmpAppendLine);
    }

    public synchronized boolean hasNext(FileInfo fileInfo)
    {
        return fileToCursor.get(getKey(fileInfo)) < fileToLine.get(getKey(fileInfo)).size() - 1;
    }

    public void resetCursor(FileInfo fileInfo)
    {
        fileToCursor.put(getKey(fileInfo), -1);
    }

    public synchronized ArrayList<String> next(FileInfo fileInfo)
    {
        int next = 1 + fileToCursor.get(getKey(fileInfo));
        fileToCursor.put(getKey(fileInfo), next);
        
        return fileToLine.get(getKey(fileInfo)).get(next);
    }
}
