package com.zte.ums.an.uni.dsl.conf.cdf.centertool.config.model.db;

import com.zte.ums.an.uni.dsl.conf.cdf.centertool.common.CenterToolUtil;
import com.zte.ums.an.uni.dsl.conf.cdf.centertool.config.ICdfConfigDataModel;

/**
 * <p>�ļ�����: DbIpDataModel.java</p>
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
public class SubCollectDbIpDataModel implements ICdfConfigDataModel
{
    @Override
    public String getTitle()
    {
        return "CDF Database Server IP";
    }
           
    @Override
    public String getCurrValue()
    {
        return CenterToolUtil.getCollectXml().getDbServerIp();
    }
    
    @Override
    public boolean setNewValue(String newIP)
    {
        if(!CenterToolUtil.isValidIPAddr(newIP))
        {
            return false;
        }

        CenterToolUtil.getCollectXml().setDbServerIp(newIP);
        
        return true;
    }
}
