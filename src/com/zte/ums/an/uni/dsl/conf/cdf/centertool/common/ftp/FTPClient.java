package com.zte.ums.an.uni.dsl.conf.cdf.centertool.common.ftp;

import java.io.IOException;

/**
 * <p>�ļ�����: FTPClient</p>
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
public interface FTPClient
{
    public void connect(String serverIP, String userName, String password) throws IOException;
    
    public void disConnect() throws IOException;
    
    /** ��ʾ��FTP�ͻ���(��FileZilla)�ϵĳ�ʼĿ¼��ʼ����"/"��β */
    public String getDisplayedRootDir() throws IOException;

    public void cd(String dir) throws IOException;

    /** ��ȡ��ǰĿ¼����Ŀ¼��"/"��β */
    public String pwd() throws IOException;

    /** ����Ŀ¼,������Ŀ¼�Ƿ�ɹ����� */
    public void mkDirs(String dir) throws IOException;

    /** ɾ����Ŀ¼�������z��Ŀ��Ƿ�ɹ��h�� */
    public void delDir(String dir) throws IOException;
    
    /** ɾ�������ļ� */
    public void delFile(String dir) throws IOException;

    /** ����Ŀ¼�µĵ����ļ����ϴ���FTPԶ���ļ����� */
    public void upload(String remotePath, String localFile) throws IOException;
}
