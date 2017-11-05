package com.zte.ums.an.uni.dsl.conf.cdf.centertool.config.model.ftp;

import com.zte.ums.an.uni.dsl.conf.cdf.centertool.common.CenterToolUtil;
import com.zte.ums.an.uni.dsl.conf.cdf.centertool.config.ICdfConfigDataModel;

/**
 * <p>�ļ�����: SubCollectDbUserDataModel</p>
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
public class FTPPasswordDataModel implements ICdfConfigDataModel
{
    @Override
    public String getTitle()
    {
        return "FTP Password";
    }
        
    @Override
    public String getCurrValue()
    {
        return CenterToolUtil.getCollectXml().getFTPPassword();
    }
    
    @Override
    public boolean setNewValue(String newValue)
    {
        CenterToolUtil.getCollectXml().setFTPPassword(newValue);
        return true;
    }
}