package com.zte.ums.an.uni.dsl.conf.cdf.centertool.common;

/**
 * <p>�ļ�����: RmiInfo.java</p>
 * <p>�ļ�����: </p>
 * <p>��Ȩ����: ��Ȩ����(C)2001-2012</p>
 * <p>��    ˾: ����ͨѶ�ɷ����޹�˾</p>
 * <p>����ժҪ: </p>
 * <p>����˵��: </p>
 * <p>������ڣ�2012-3-12</p>
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
public class RmiInfo
{
    public String port;
    public String ipAddr = "";
    public String remoteObject = "";

    @Override
    public int hashCode()
    {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((ipAddr == null) ? 0 : ipAddr.hashCode());
        result = prime * result + ((port == null) ? 0 : port.hashCode());
        result = prime * result + ((remoteObject == null) ? 0 : remoteObject.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj)
    {
        if(this == obj)
        {
            return true;
        }
        
        if(!(obj instanceof RmiInfo))
        {
            return false;
        }
        
        RmiInfo other = (RmiInfo)obj;
        
        if((ipAddr.equals(other.ipAddr)) &&(remoteObject.equals(other.remoteObject)) && (port.equalsIgnoreCase(other.port)))
        {
            return true;
        }
        
        return false;
    }

    @Override
    public String toString()
    {
        return "port=" + port + ", ipAddr=" + ipAddr + ", remoteObject=" + remoteObject;
    }
}
