package com.zte.ums.an.uni.dsl.conf.cdf.centertool.config.model.cron;

import com.zte.ums.an.uni.dsl.conf.cdf.centertool.common.CronExpressGenerator;
import com.zte.ums.an.uni.dsl.conf.cdf.centertool.common.CronExpressParser;
import com.zte.ums.an.uni.dsl.conf.cdf.report.common.ReportInfo;

/**
 * <p>�ļ�����: ReportCronDateDataModel</p>
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
public class ReportCronDateDataModel extends AbsCdfConfigDataModelWithSequence
{
    private String userInputDate = "";

    public ReportCronDateDataModel(ReportInfo reportInfo)
    {
        super(reportInfo);
    }
    
    @Override
    public String getTitle()
    {
        return "Reconfigure Start day(1-31)";
    }
        
    @Override
    public String getCurrValue()
    {
        CronExpressParser parse = new CronExpressParser(reportInfo.getScheduleCron());
        return parse.getDay();
    }
    
    @Override
    public boolean setNewValue(String newValue)
    {
        CronExpressGenerator gen = new CronExpressGenerator();
        
        if(gen.setDate(newValue))
        {
            this.userInputDate = newValue;
            return true;
        }
        
        return false;
    }
    
    @Override
    public String getUserInput()
    {
        return userInputDate;
    }
}