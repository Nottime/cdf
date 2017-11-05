package com.zte.ums.an.uni.dsl.conf.cdf.centertool.common.ftp;

import java.io.File;
import java.io.IOException;

import com.zte.ums.uep.psl.jsch.jcraft.sftp.api.SftpClientException;

/**
 * <p>�ļ�����: SftpClientImpl</p>
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
public class SftpClientImpl implements FTPClient
{
    private com.zte.ums.uep.psl.jsch.jcraft.sftp.SftpClientImpl sftpClient;
    
    private String displayedHomePath = null;//��ʾ��FTP�ͻ���(��FileZilla)�ϵĳ�ʼĿ¼
    
    @Override
    public void connect(String serverIP, String userName, String password) throws IOException
    {
        try
        {
            sftpClient = new com.zte.ums.uep.psl.jsch.jcraft.sftp.SftpClientImpl(serverIP, 22, 60000, userName, password);
            initDisplayedHomePath();
        }
        catch(SftpClientException e)
        {
            throw new IOException(e);
        }
    }
    
    private void initDisplayedHomePath() throws IOException
    {
        try
        {
            String homePath = sftpClient.getHomePath();
            homePath = homePath.endsWith("/") ? homePath : homePath + "/";
            homePath = "//".equals(homePath) ? "/" : homePath;//windows��Ϊ"//"
            
            displayedHomePath = homePath;
        }
        catch(SftpClientException e)
        {
            throw new IOException(e);
        }
    }

    @Override
    public void disConnect() throws IOException
    {
        if (this.sftpClient != null)
        {
            try
            {
                this.sftpClient.quit();
                this.sftpClient = null;
            }
            catch(SftpClientException e)
            {
                throw new IOException(e);
            }
        }
    }

    @Override
    public String getDisplayedRootDir() throws IOException
    {
        return displayedHomePath;
    }

    @Override
    public void cd(String dir) throws IOException
    {
        try
        {
            sftpClient.chdir(dir);
        }
        catch(SftpClientException e)
        {
            throw new IOException(e);
        }
    }

    @Override
    public String pwd() throws IOException
    {
        String pwd;
        try
        {
            pwd = sftpClient.pwd();//��ƽ̨������ؼ��У�SftpClientImpl.pwd()������ʾ��FTP�ͻ���(��FileZilla)�ϵĳ�ʼĿ¼����ʼ��¼ʱ��pwd��Զ��"/"
            pwd = pwd.startsWith("/") ? pwd.substring(1) : pwd;
            
            return displayedHomePath + pwd;
        }
        catch(SftpClientException e)
        {
            throw new IOException(e);
        }
    }

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
        try
        {
            sftpClient.mkdir(dir);//SftpClientImpl.mkdir���Դ������Ŀ¼
            sftpClient.dir(dir, false);//SftpClientImpl.dir���Լ��Ŀ¼�Ƿ����
        }
        catch(SftpClientException e)
        {
            throw new IOException(e);
        }
    }

    @Override
    public void delDir(String dir) throws IOException
    {
        doDelete(dir);
        
        try
        {
            sftpClient.dir(dir, false);//SftpClientImpl.dir���Լ��Ŀ¼�Ƿ����
        }
        catch(SftpClientException e)
        {
            return;
        }
        
        throw new IOException("dir still exists after execute cmd:" + dir);
    }

    @Override
    public void delFile(String file) throws IOException
    {
        doDelete(file);
    }
    
    private void doDelete(String dir) throws IOException
    {
        try
        {
            sftpClient.delete(dir);//delete API - windows�¿���ɾ���ǿ�Ŀ¼��soliars��ֻ���ǿ�Ŀ¼���������쳣
        }
        catch(SftpClientException e)
        {
            throw new IOException(e);
        }
    }

    @Override
    public void upload(String remotePath, String localFile) throws IOException
    {
        String localFileName = new File(localFile).getName();
        String remoteFile = remotePath.endsWith("/") ? remotePath + localFileName : remotePath + "/" + localFileName;
        try
        {
            sftpClient.putFile(localFile, remoteFile);//putFile API֧��Ŀ¼������ʱ�Զ�����Ŀ¼
        }
        catch(SftpClientException e)
        {
            throw new IOException(e);
        }
    }

}
