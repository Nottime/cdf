package com.zte.ums.an.uni.dsl.conf.cdf.centertool.config.model.ftp;

import com.zte.ums.an.uni.dsl.conf.cdf.centertool.common.CenterToolUtil;
import com.zte.ums.an.uni.dsl.conf.cdf.centertool.config.ICdfConfigDataModel;

/**
 * <p>�ļ�����: FTPTransferTypeDataModel</p>
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
public class FTPTransferTypeDataModel implements ICdfConfigDataModel
{
    public String getTitle()
    {
        return "CDF Transfer Protocal Type(1-FTP, 2-SFTP)";
    }
            
    public String getCurrValue()
    {
        String currValue = "";
        
        String transferType = CenterToolUtil.getCollectXml().getTransferType();
        
        if("FTP".equalsIgnoreCase(transferType))
        {
            currValue = "1";
        }
        else if("SFTP".equalsIgnoreCase(transferType))
        {
            currValue = "2";
        }
        else
        {
            currValue = "--";
        }
        
        return currValue;
    }
    
    public boolean setNewValue(String newValue)
    {
        String transferType;
        if("1".equalsIgnoreCase(newValue))
        {
            transferType = "FTP";
        }
        else if("2".equalsIgnoreCase(newValue))
        {
            transferType = "SFTP";
        }
        else
        {
            return false;
        }
        
        CenterToolUtil.getCollectXml().setTransferType(transferType);
        
        return true;
    }
}