package com.zte.ums.an.uni.dsl.conf.cdf.test.tool.dslamcsvmock.thread;

import java.io.File;
import java.io.IOException;

import org.apache.log4j.Logger;

import com.zte.ums.an.uni.dsl.conf.cdf.common.CdfUtil;
import com.zte.ums.an.uni.dsl.conf.cdf.common.LogPrint;
import com.zte.ums.an.uni.dsl.conf.cdf.test.tool.customizedcsvmock.CsvFileMocker;
import com.zte.ums.an.uni.dsl.conf.cdf.test.tool.customizedcsvmock.common.CdfMockServerXmlParser;
import com.zte.ums.n3common.api.ZXLogger;


/**
 * <p>�ļ�����: CsvUploadThread.java</p>
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
public class CsvUploadThread extends Thread
{
    private Logger logger = ZXLogger.getLogger(this.getClass().getName());
    private ThreadObject obj;

    public CsvUploadThread(ThreadObject obj)
    {
        this.obj = obj;
    }
    
    @Override
    public void run()
    {
        File path = new File(CdfMockServerXmlParser.getInstance().getTempFilePath(), obj.getIpAddr());
        
        CdfUtil.deleteFiles(path.getAbsoluteFile());
        (new CsvFileMocker(obj.getIpAddr(), path.getAbsolutePath())).mock();
        
        File[] files = path.getAbsoluteFile().listFiles();
        
        uploadByFTP(files);
    }

    private void uploadByFTP(File[] files)
    {
        if(files == null)
        {
            obj.setResult(4);
            return;
        }
        
        try
        {
            for(int i = 0; i < files.length; i++)
            {
                doUpload(files, i);
            }
            
            obj.setResult(3);
        }
        catch(IOException e)
        {
            LogPrint.logError(logger, "FTP connect fail", e);
            obj.setResult(4);
        }
    }

//    private void doUpload(File[] files, int i) throws IOException
//    {
//        FtpConnection ftp = null;
//        
//        try
//        {
//            //ģ����Ԫʵ�֣�һ��csv�ļ�һ������
//            FtpInfo ftpInfo = this.obj.getFtpInfo();
//            
//            ftp = new FtpConnection(ftpInfo.ftpServerIp, ftpInfo.ftpUser, ftpInfo.ftpUserPasswd);
//
//            if(!ftp.upload("/CDF/" + obj.getIpAddr(), files[i].getAbsolutePath()))
//            {
//                LogPrint.logError(logger, "upload fail: " + files[i].getAbsolutePath());
//            }
//        }
//        finally
//        {
//            if(ftp != null)
//            {
//                ftp.disconnect();
//            }
//        }
//    }
    
    private void doUpload(File[] files, int i) throws IOException
    {
        FtpConnection ftp = null;
        
        try
        {
            //ģ����Ԫʵ�֣�һ��csv�ļ�һ������
            FtpConnectionPool.getInstance().setFtpInfo(this.obj.getFtpInfo());
            
            ftp = FtpConnectionPool.getInstance().getConnection(null);

            if(!ftp.upload("/CDF/" + obj.getIpAddr(), files[i].getAbsolutePath()))
            {
                
                LogPrint.logError(logger, "upload fail: " + files[i].getAbsolutePath());
            }
        }
        finally
        {
            if(ftp != null)
            {
                FtpConnectionPool.getInstance().freeConnection(ftp);
            }
        }
    }
}
