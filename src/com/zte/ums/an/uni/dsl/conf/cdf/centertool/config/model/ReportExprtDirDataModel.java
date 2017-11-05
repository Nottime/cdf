package com.zte.ums.an.uni.dsl.conf.cdf.centertool.config.model;

import java.io.File;

import com.zte.ums.an.uni.dsl.conf.cdf.centertool.common.CenterToolUtil;
import com.zte.ums.an.uni.dsl.conf.cdf.centertool.config.ICdfConfigDataModel;

/**
 * <p>�ļ�����: ReportExprtDirDataModel</p>
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
public class ReportExprtDirDataModel implements ICdfConfigDataModel
{
    @Override
    public String getTitle()
    {
        return "What directory do you want to keep your final report?";
    }
        
    @Override
    public String getCurrValue()
    {
        return CenterToolUtil.getReportXml().getExportDir();
    }
    
    @Override
    public boolean setNewValue(String newValue)
    {
        if(!createPathInOS(newValue))
        {
            System.out.println("Failed to set report export directory.");
            return false;
        }
        
        CenterToolUtil.getReportXml().setExportDir(newValue);
        return true;
    }

    private boolean createPathInOS(String newValue)
    {
        File ftpDir = new File(newValue);
        
        if(ftpDir.exists())
        {
            return true;
        }
        
        try
        {
            return ftpDir.mkdirs();
        }
        catch(Exception e)
        {
            e.printStackTrace();
            return false;
        }
    }
}