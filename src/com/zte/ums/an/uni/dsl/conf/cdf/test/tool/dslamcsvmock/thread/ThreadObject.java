package com.zte.ums.an.uni.dsl.conf.cdf.test.tool.dslamcsvmock.thread;

import com.zte.ums.an.uni.dsl.conf.cdf.common.FtpInfo;

/**
 * <p>�ļ�����: ThreadObject.java</p>
 * <p>�ļ�����: </p>
 * <p>��Ȩ����: ��Ȩ����(C)2001-2012</p>
 * <p>��    ˾: ����ͨѶ�ɷ����޹�˾</p>
 * <p>����ժҪ: </p>
 * <p>����˵��: </p>
 * <p>������ڣ�2011-12-23</p>
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
public class ThreadObject
{    
    private Thread thread;
    private int result;
    private String ipAddr;
    private FtpInfo ftpInfo;

    public ThreadObject()
    {
    }
    
    public Thread getThread()
    {
        return thread;
    }

    public void setThread(Thread thread)
    {
        this.thread = thread;
    }
    
    public int getResult()
    {
        return result;
    }

    public void setResult(int result)
    {
        this.result = result;
    }
    
    public String getIpAddr()
    {
        return ipAddr;
    }
    
    public void setIpAddr(String ipAddr)
    {
        this.ipAddr = ipAddr;
    }
    
    public FtpInfo getFtpInfo()
    {
        return ftpInfo;
    }

    public void setFtpInfo(FtpInfo ftpInfo)
    {
        this.ftpInfo = ftpInfo;
    }
}

