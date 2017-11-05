package com.zte.ums.an.uni.dsl.conf.cdf.centertool.config.model.ftp;

import com.zte.ums.an.uni.dsl.conf.cdf.centertool.common.CenterToolUtil;
import com.zte.ums.an.uni.dsl.conf.cdf.centertool.config.ICdfConfigDataModel;

/**
 * <p>�ļ�����: FTPServerIpDataModel</p>
 * <p>�ļ�����: </p>
 * <p>��Ȩ����: ��Ȩ����(C)2001-2012</p>
 * <p>��    ˾: ����ͨѶ�ɷ����޹�˾</p>
 * <p>����ժҪ: </p>
 * <p>����˵��: </p>
 * <p>������ڣ�2012-3-13</p>
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
public class FTPServerIpDataModel implements ICdfConfigDataModel
{
    @Override
    public String getTitle()
    {
        return "FTP Server IP" + CenterToolUtil.getAllHosts();
    }
           
    @Override
    public String getCurrValue()
    {
        return CenterToolUtil.getCollectXml().getFTPServerIp();
    }
    
    @Override
    public boolean setNewValue(String newIP)
    {
        if(!CenterToolUtil.isValidIPAddr(newIP))
        {
            return false;
        }
        
        if(!CenterToolUtil.isLocalHostIP(newIP))
        {
            System.out.println("Not a host IP address");
            return false;
        }
        
        CenterToolUtil.getCollectXml().setFTPServerIp(newIP);
        CenterToolUtil.getCollectXml().setSubServerIP(newIP);
        
        //����RMI IP���߼��Ƶ�����
        CenterToolUtil.getDispatchXml().setServerIP(newIP);
        CenterToolUtil.getReportXml().setServerIP(newIP);
        CenterToolUtil.getCollectXml().setRemoteMainServerIp(newIP);
        
        return true;
    }
}