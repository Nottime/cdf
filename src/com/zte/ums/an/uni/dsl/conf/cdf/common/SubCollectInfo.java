package com.zte.ums.an.uni.dsl.conf.cdf.common;

import java.io.Serializable;

/**
 * <p>�ļ�����: SubCollectInfo</p>
 * <p>�ļ�����: </p>
 * <p>��Ȩ����: ��Ȩ����(C)2001-2012</p>
 * <p>��    ˾: ����ͨѶ�ɷ����޹�˾</p>
 * <p>����ժҪ: </p>
 * <p>����˵��: </p>
 * <p>������ڣ�2011��11��29��</p>
 * <p>�޸ļ�¼1:</p>
 * <pre>
 *    �޸����ڣ�
 *    �� �� �ţ�
 *    �� �� �ˣ�
 *    �޸����ݣ�
 * </pre>
 * <p>�޸ļ�¼2��</p>
 * @version 1.0
 * @author  ChenDuoduo_10087118
 */
public class SubCollectInfo implements Serializable
{
    private static final long serialVersionUID = 4341544030205670808L;
    
    public String alias;
    public long hearBeatTime;
    public long nanoTime;
    public String ipAddr;
    public String dbType;
    public String dbPort;
    public String dbServerIp;
    public String dbUser;
    public String dbPassword;
    public String dbName;
    public String dbMaxConnect;
    public SubCollectInfo subCollectInfo;
    public FtpInfo ftpInfo;
    
    public String toString()
    {
        StringBuffer sb = new StringBuffer();

        sb.append("  alias                          = " + this.alias + "\n");
        sb.append("  hearBeatTime                   = " + this.hearBeatTime + "\n");
        sb.append("  nanoTime                       = " + this.nanoTime + "\n");
        sb.append("  ipAddr                         = " + this.ipAddr + "\n");
        sb.append("  dbType                         = " + this.dbType + "\n");
        sb.append("  dbPort                         = " + this.dbPort + "\n");
        sb.append("  dbServerIp                     = " + this.dbServerIp + "\n");
        sb.append("  dbUser                         = " + this.dbUser + "\n");
        sb.append("  dbPassword                     = " + this.dbPassword + "\n");
        sb.append("  dbName                         = " + this.dbName + "\n");
        sb.append("  dbMaxConnect                   = " + this.dbMaxConnect + "\n");
        sb.append("  ftpInfo                        = " + this.ftpInfo + "\n");
        return sb.toString();
    }
    
    public String getName()
    {
        return ipAddr + "(" + alias + ")";
    }
    
    @Override
    public boolean equals(Object obj)
    {   
        if(!(obj instanceof SubCollectInfo))
        {
            return false;
        }
        
        SubCollectInfo info = (SubCollectInfo)obj;
        
        if(info.ipAddr == null)
        {
            return false;
        }
        
        return info.ipAddr.equalsIgnoreCase(this.ipAddr);
    }
}
