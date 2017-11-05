package com.zte.ums.an.uni.dsl.conf.cdf.centertool.common.ftp;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import sun.net.TelnetOutputStream;
import sun.net.ftp.FtpClient;

/**
 * <p>�ļ�����: FtpClientImpl</p>
 * <p>�ļ�����: </p>
 * <p>��Ȩ����: ��Ȩ����(C)2005-2015</p>
 * <p>��    ˾: ����ͨѶ�ɷ����޹�˾</p>
 * <p>����ժҪ: </p>
 * <p>����˵��: </p>
 * <p>������ڣ�2012-12-12</p>
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
public class FtpClientImpl implements FTPClient
{
    private FtpClient ftpClient;
    private String displayedHomePath;//��ʾ��FTP�ͻ���(��FileZilla)�ϵĳ�ʼĿ¼

    @Override
    public void connect(String serverIP, String userName, String password) throws IOException
    {
        this.ftpClient = new FtpClient();
        this.ftpClient.setConnectTimeout(Integer.MAX_VALUE - 1);
        this.ftpClient.openServer(serverIP);
        this.ftpClient.login(userName, password);
        
        this.displayedHomePath = getDisplayedHomePath();
    }
    
    private String getDisplayedHomePath() throws IOException
    {
        String displayedHomePath = ftpClient.pwd();
        if(!displayedHomePath.endsWith("/"))
        {
            displayedHomePath = displayedHomePath + "/";
        }
        
        return displayedHomePath;
    }

    @Override
    public void disConnect() throws IOException
    {
        if (this.ftpClient != null)
        {
            this.ftpClient.closeServer();
            this.ftpClient = null;
        }
    }

    @Override
    public void cd(String dir) throws IOException
    {
        ftpClient.cd(dir);
    }

    @Override
    public String pwd() throws IOException
    {
        String pwd = ftpClient.pwd();
        pwd = pwd.endsWith("/") ? pwd : pwd + "/";
        
        return pwd;
    }

    /** dirsֻ��Ϊ���·��,����AAA����AAA/SUBAAA����AAA/SUBAAA/ */
    @Override
    public void mkDirs(String dirs) throws IOException
    {
        String[] arrDirs = dirs.split("/");
        
        String pwd = pwd();
        for(String dir : arrDirs)
        {
            mkDir(dir);
            cd(dir);
        }
        
        cd(pwd);
    }
    
    private void mkDir(String dir) throws IOException
    {
        ftpClient.sendServer("XMKD " + dir + "\r\n");
        ftpClient.readServerResponse();
    }

    //ɾ��Ŀ¼��ֻ���ڵ�ǰĿ¼�´���һ��Ŀ¼���޷��ݹ�
    @Override
    public void delDir(String dir) throws IOException
    {
        ftpClient.sendServer("XRMD " + dir + "\r\n");
        ftpClient.readServerResponse();
        
        try
        {
            cd(dir);
        }
        catch(IOException ex)
        {
            return;
        }
        
        throw new IOException("dir still exists after execute cmd:" + dir);
    }

    @Override
    public void delFile(String dir) throws IOException
    {
        ftpClient.sendServer("DELE " + dir + "\r\n");
        ftpClient.readServerResponse();
    }

    @Override
    public void upload(String remotePath, String localFile) throws IOException
    {
        String pwd = ftpClient.pwd();
        
        TelnetOutputStream out = null;
        BufferedInputStream bis = null;

        try
        {
            cd(remotePath);
            File file = new File(localFile);
            bis = new BufferedInputStream(new FileInputStream(file));
            byte[] bytes = new byte[1024000];
            int read;
            out = this.ftpClient.put(file.getName());
            while((read = bis.read(bytes)) != -1)
            {
                out.write(bytes, 0, read);
            }
        }
        finally
        {
            closeInputStream(bis);
            closeOutputStream(out);
            ftpClient.cd(pwd);
        }
    }
    
    private static void closeInputStream(InputStream is)
    {
        try
        {            
            if(is != null)
            {
                is.close();
            }
        }
        catch(IOException ex)
        {
            ex.printStackTrace();
        }
    }

    private static void closeOutputStream(OutputStream os)
    {
        try
        {
            if(os != null)
            {
                os.close();
            }
        }
        catch(IOException ex)
        {
            ex.printStackTrace();
        }
    }
    
    @Override
    public String getDisplayedRootDir() throws IOException
    {
        return displayedHomePath;
    }
}
