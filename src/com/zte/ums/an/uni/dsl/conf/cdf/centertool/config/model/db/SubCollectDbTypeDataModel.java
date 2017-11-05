package com.zte.ums.an.uni.dsl.conf.cdf.centertool.config.model.db;

import com.zte.ums.an.uni.dsl.conf.cdf.centertool.common.CenterToolUtil;
import com.zte.ums.an.uni.dsl.conf.cdf.centertool.config.ICdfConfigDataModel;

/**
 * <p>�ļ�����: DbTypeInfo.java</p>
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
public class SubCollectDbTypeDataModel implements ICdfConfigDataModel
{
    public String getTitle()
    {
        return "CDF Database Type(1-mssql, 2-oracle)";
    }
            
    public String getCurrValue()
    {
        String currValue = "";
        
        String dbType = CenterToolUtil.getCollectXml().getDbType();
        
        if("mssql".equalsIgnoreCase(dbType))
        {
            currValue = "1";
        }
        else if("oracle".equalsIgnoreCase(dbType))
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
        String dbType;
        if("1".equalsIgnoreCase(newValue))
        {
            dbType = "mssql";
        }
        else if("2".equalsIgnoreCase(newValue))
        {
            dbType = "oracle";
        }
        else
        {
            return false;
        }
        
        CenterToolUtil.getCollectXml().setDbType(dbType);
        
        return true;
    }
}