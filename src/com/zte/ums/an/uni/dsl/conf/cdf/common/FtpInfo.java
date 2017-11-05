package com.zte.ums.an.uni.dsl.conf.cdf.common;

import java.io.Serializable;

/**
 * <p>�ļ�����: FtpInfo.java</p>
 * <p>�ļ�����: FTP������Ϣ��</p>
 * <p>��Ȩ����: ��Ȩ����(C)2001-2012</p>
 * <p>��    ˾: ����ͨѶ�ɷ����޹�˾</p>
 * <p>����ժҪ: </p>
 * <p>����˵��: </p>
 * <p>������ڣ�2011��11��15��</p>
 * <p>�޸ļ�¼1:</p>
 * <pre>
 *    �޸����ڣ�
 *    �� �� �ţ�
 *    �� �� �ˣ�
 *    �޸����ݣ�
 * </pre>
 * <p>�޸ļ�¼2��</p>
 * @version 1.0
 * @author  jingxueshi_10118495
 */
public class FtpInfo implements Serializable, Cloneable
{
    //****** �����: �������� **********************************************************************/

    /** ftp��ַ */
    public String ftpServerIp = null;

    /** ftp�û� */
    public String ftpUser = null;

    /** ftp�û����� */
    public String ftpUserPasswd = null;

    /** ftp�û���Ŀ¼ */
    public String ftpRootDir = null;

    /** �Ƿ�ʹ��SFTPЭ�飬true��ʾSFTP��false��ʾFTP */
    public boolean isSFTP;

    //****** �����: �������� **********************************************************************/

    public String toString()
    {
        StringBuffer sb = new StringBuffer();

        sb.append("  ftpIp                 = " + this.ftpServerIp + "\n");
        sb.append("  ftpUser               = " + this.ftpUser + "\n");
        sb.append("  ftpUserPasswd         = " + this.ftpUserPasswd + "\n");
        sb.append("  ftpRootDir            = " + this.ftpRootDir + "\n");
        sb.append("  isSFTP                = " + this.isSFTP + "\n");
        return sb.toString();
    }

    public Object clone()
    {
        FtpInfo co = null;
        try
        {
            co = (FtpInfo)super.clone();
        }
        catch(CloneNotSupportedException e)
        {
            e.printStackTrace();
        }
        return co;
    }
}
