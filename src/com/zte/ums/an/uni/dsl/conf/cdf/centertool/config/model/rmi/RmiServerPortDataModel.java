package com.zte.ums.an.uni.dsl.conf.cdf.centertool.config.model.rmi;

import com.zte.ums.an.uni.dsl.conf.cdf.centertool.common.CenterToolUtil;
import com.zte.ums.an.uni.dsl.conf.cdf.centertool.config.ICdfConfigDataModel;

/**
 * <p>�ļ�����: DbPortInfo.java</p>
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
public class RmiServerPortDataModel implements ICdfConfigDataModel
{
    @Override
    public String getTitle()
    {
        return "RMI Server Port";
    }
        
    @Override
    public String getCurrValue()
    {
        return CenterToolUtil.getDispatchXml().getServerPort();
    }
    
    @Override
    public boolean setNewValue(String newValue)
    {
        try
        {
            int portNo = Integer.parseInt(newValue);
            if(portNo <= 0 || portNo >= 65536)
            {
                System.out.println("Invalid port number.");
                return false;
            }
        }
        catch(NumberFormatException ex)
        {
            System.out.println("Invalid port number.");
            return false;
        }
        
        CenterToolUtil.getDispatchXml().setServerPort(newValue);
        CenterToolUtil.getReportXml().setServerPort(newValue);
        CenterToolUtil.getCollectXml().setRemoteServerPort(newValue);
        
        return true;
    }
}