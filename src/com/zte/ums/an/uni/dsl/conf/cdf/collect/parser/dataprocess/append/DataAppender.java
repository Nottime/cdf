package com.zte.ums.an.uni.dsl.conf.cdf.collect.parser.dataprocess.append;

import java.util.ArrayList;
import java.util.HashMap;

import com.zte.ums.an.uni.dsl.conf.cdf.collect.matcher.IMatcher;
import com.zte.ums.an.uni.dsl.conf.cdf.collect.parser.common.BulkPoolGroupInfo;
import com.zte.ums.an.uni.dsl.conf.cdf.collect.parser.common.FileAppendInfo;
import com.zte.ums.an.uni.dsl.conf.cdf.collect.parser.common.FileInfo;
import com.zte.ums.an.uni.dsl.conf.cdf.collect.parser.common.Record;
import com.zte.ums.an.uni.dsl.conf.cdf.collect.parser.common.SeqRecProxy;
import com.zte.ums.an.uni.dsl.conf.cdf.common.CdfConst;
import com.zte.ums.an.uni.dsl.conf.cdf.common.CdfUtil;
import com.zte.ums.an.uni.dsl.conf.cdf.common.SubCollectCache;
import com.zte.ums.an.uni.dsl.conf.cdf.common.xml.CollectDataXmlParser;

/**
 * <p>�ļ�����: DataAppender.java</p>
 * <p>�ļ�����: </p>
 * <p>��Ȩ����: ��Ȩ����(C)2001-2012</p>
 * <p>��    ˾: ����ͨѶ�ɷ����޹�˾</p>
 * <p>����ժҪ: </p>
 * <p>����˵��: </p>
 * <p>������ڣ�2012-2-27</p>
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
public class DataAppender
{
    private FileInfo[] appendFileInfoList;    
    
    private HashMap<String, IMatcher> matchersList = null;
    
    /** �ļ���ȡ���� */
    private DataAppenderBuffer buf;
    
    public DataAppender(BulkPoolGroupInfo group)
    {
        appendFileInfoList = group.getAppendFileInfoList();
        buf = new DataAppenderBuffer(appendFileInfoList);
        matchersList = CollectDataXmlParser.getInstance().getMatchers();
    }
    
    public ArrayList<String> afterAppend(Record mergedRecord) throws Exception
    {
        ArrayList<String> oriData = mergedRecord.lineToArrayList();
        
        for(FileInfo fileInfo : appendFileInfoList)
        {
            if(SubCollectCache.isReduceIO)
            {
                appendToMergeLineThroughMemory(oriData, fileInfo);
            }
            else
            {
                appendToMergeLineThroughFileIO(oriData, fileInfo);
            }
        }
        
        return oriData;
    }

    private void appendToMergeLineThroughMemory(ArrayList<String> oriData, FileInfo fileInfo)
    {
        if(buf.isEmplty(fileInfo))
        {
            initBuffer(fileInfo);
        }
        
        for(FileAppendInfo appendInfo : fileInfo.getAppendInfos())
        {
            if(!appendByBuffer(oriData, fileInfo, appendInfo))
            {
                appendDefault(oriData, appendInfo);
            }
        }
    }

    protected void initBuffer(FileInfo fileInfo)
    {
        SeqRecProxy appendFileReadProxy = null;
        
        try
        {
            appendFileReadProxy = CdfUtil.createProxy(fileInfo.getFile(), fileInfo.getReadProxy());

            while(appendFileReadProxy.next())
            {
                ArrayList<String> tmpAppendLine = appendFileReadProxy.getRecord().lineToArrayList();
                buf.addBuffer(fileInfo, tmpAppendLine);
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        finally
        {
            if(appendFileReadProxy != null)
            {
                appendFileReadProxy.close();
            }
        }
    }

    private void appendToMergeLineThroughFileIO(ArrayList<String> mainMergedLine, FileInfo fileInfo) throws Exception
    {
        for(FileAppendInfo appendInfo : fileInfo.getAppendInfos())
        {
            boolean operByBuffer = appendByBuffer(mainMergedLine, fileInfo, appendInfo);

            if(!operByBuffer)
            {
                appendByFileIO(mainMergedLine, fileInfo, appendInfo);
            }
        }
    }

    private boolean appendByBuffer(ArrayList<String> mainMergedLine, FileInfo fileInfo, FileAppendInfo appendInfo)
    {
        buf.resetCursor(fileInfo);
        
        while(buf.hasNext(fileInfo))
        {
            ArrayList<String> tmpAppendLine = buf.next(fileInfo);
            
            if(isMatch(fileInfo.getMatcherName(), mainMergedLine.get(appendInfo.getIndexMerge()), tmpAppendLine.get(appendInfo.getIndexAppend())))
            {
                appendTmpLineToMergeLine(mainMergedLine, tmpAppendLine, appendInfo.getValueAppend());
                return true;
            }
        }
        
        return false;
    }

    private void appendByFileIO(ArrayList<String> mainMergedLine, FileInfo fileInfo, FileAppendInfo appendInfo) throws Exception
    {
        SeqRecProxy appendFileReadProxy = null;

        try
        {
            appendFileReadProxy = CdfUtil.createProxy(fileInfo.getFile(), fileInfo.getReadProxy());

            while(appendFileReadProxy.next())
            {
                ArrayList<String> tmpAppendLine = appendFileReadProxy.getRecord().lineToArrayList();

                if(isMatch(fileInfo.getMatcherName(), mainMergedLine.get(appendInfo.getIndexMerge()), tmpAppendLine.get(appendInfo.getIndexAppend())))
                {
                    appendTmpLineToMergeLine(mainMergedLine, tmpAppendLine, appendInfo.getValueAppend());
                    buf.addBuffer(fileInfo, tmpAppendLine);
                    return;
                }
            }
            
            appendDefault(mainMergedLine, appendInfo);
        }
        catch(IndexOutOfBoundsException ex)
        {
            throw new RuntimeException("fileInfo : " + fileInfo, ex);
        }
        finally
        {
            if(appendFileReadProxy != null)
            {
                appendFileReadProxy.close();
            }
        }
    }

    private void appendDefault(ArrayList<String> mainMergedLine, FileAppendInfo appendInfo)
    {
        int count = appendInfo.getValueAppend().length;
        
        for(int i = 0; i < count; i++)
        {
            mainMergedLine.add(CdfConst.REPORT_VALUE_NOTSET);
        }
    }
    
    private boolean isMatch(String matcherName, String mergeKey, String appendKey)
    {
        return matchersList.get(matcherName).isMatch(mergeKey, appendKey);
    }
    
    private static void appendTmpLineToMergeLine(ArrayList<String> mainMergedLine, ArrayList<String> appendLine, int[] valueAppend)
    {
        for(int targeIndex : valueAppend)
        {
            mainMergedLine.add(appendLine.get(targeIndex));
        }
    }

    public static void main(String[] args)
    {
        //TEST1 
//        ArrayList<String> oriLine = new ArrayList<String>();
//        oriLine.add("1/1/1/1");
//        oriLine.add("1");
//        oriLine.add("11");
//        
//        ArrayList<String> targetLine = new ArrayList<String>();
//        targetLine.add("1/1/1/1");
//        targetLine.add("target1");
//        targetLine.add("target11");
//        
//        matchAndAppendTmpLineToMergeLine(oriLine, 0, targetLine, 0, new int[] {1, 2});
//
//        Assert.assertEquals(5, oriLine.size());
//        int i = 0;
//        Assert.assertEquals("1/1/1/1", oriLine.get(i++));
//        Assert.assertEquals("1", oriLine.get(i++));
//        Assert.assertEquals("11", oriLine.get(i++));
//        Assert.assertEquals("target1", oriLine.get(i++));
//        Assert.assertEquals("target11", oriLine.get(i++));
//        
//        //TEST2 
//        oriLine = new ArrayList<String>();
//        oriLine.add("1/1/1/1");
//        oriLine.add("1");
//        oriLine.add("11");
//        
//        targetLine = new ArrayList<String>();
//        targetLine.add("2221/1/1/1");
//        targetLine.add("target1");
//        targetLine.add("target11");
//        
//        matchAndAppendTmpLineToMergeLine(oriLine, 0, targetLine, 0, new int[] {1, 2});
//
//        Assert.assertEquals(3, oriLine.size());
//        i = 0;
//        Assert.assertEquals("1/1/1/1", oriLine.get(i++));
//        Assert.assertEquals("1", oriLine.get(i++));
//        Assert.assertEquals("11", oriLine.get(i++));
        
        System.out.println("OK");
    }
}
