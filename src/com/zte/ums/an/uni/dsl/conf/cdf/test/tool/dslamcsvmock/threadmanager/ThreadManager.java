package com.zte.ums.an.uni.dsl.conf.cdf.test.tool.dslamcsvmock.threadmanager;

import java.util.Hashtable;

import org.apache.log4j.Logger;

import com.zte.ums.an.uni.dsl.conf.cdf.common.FtpInfo;
import com.zte.ums.an.uni.dsl.conf.cdf.common.LogPrint;
import com.zte.ums.an.uni.dsl.conf.cdf.test.tool.dslamcsvmock.thread.CsvUploadThread;
import com.zte.ums.an.uni.dsl.conf.cdf.test.tool.dslamcsvmock.thread.ThreadObject;
import com.zte.ums.n3common.api.ZXLogger;

/**
 * <p>�ļ�����: ThreadManager.java</p>
 * <p>�ļ�����: </p>
 * <p>��Ȩ����: ��Ȩ����(C)2001-2012</p>
 * <p>��    ˾: ����ͨѶ�ɷ����޹�˾</p>
 * <p>����ժҪ: </p>
 * <p>����˵��: </p>
 * <p>������ڣ�2011-12-22</p>
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
public class ThreadManager
{
    private Logger logger = ZXLogger.getLogger(this.getClass().getName());
    Hashtable<String, ThreadObject> threadsProcess = new Hashtable<String, ThreadObject>();
    
    public void addTask(String ipAddr, FtpInfo ftpInfo)
    {
        ThreadObject obj = new ThreadObject();
        obj.setResult(2);
        obj.setIpAddr(ipAddr);
        obj.setFtpInfo(ftpInfo);
        
        Thread csvUploadThread = new CsvUploadThread(obj);
        
        obj.setThread(csvUploadThread);
        
        csvUploadThread.start();
        
        threadsProcess.put(ipAddr, obj);
    }
    
    public Integer getResult(String ipAddr)
    {
        if(threadsProcess.containsKey(ipAddr))
        {
            return (threadsProcess.get(ipAddr)).getResult();
        }
        
        LogPrint.logError(logger, "Cannot find the uploading progresss of DSLAM " + ipAddr);
        return 4;
    }
}
