package com.zte.ums.an.uni.dsl.conf.cdf.centertool.config.model.cron;

import com.zte.ums.an.uni.dsl.conf.cdf.centertool.common.CenterToolUtil;
import com.zte.ums.an.uni.dsl.conf.cdf.centertool.common.CronExpressParser;
import com.zte.ums.an.uni.dsl.conf.cdf.report.common.ReportInfo;

/**
 * <p>�ļ�����: ReportCronDataModel</p>
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
public class ReportCronModeDataModel extends AbsCdfConfigDataModelWithSequence
{
    private String userInputMode = "";

    public ReportCronModeDataModel(ReportInfo reportInfo)
    {
        super(reportInfo);
    }
        
    @Override
    public String getTitle()
    {
        String currStartInfo = (new CronExpressParser(reportInfo.getScheduleCron())).toDisplayStr();
        
        String generalInfo = "The report named '" + reportInfo.getName() + "' is scheduled as + (" + currStartInfo + ")\n"
        + "Rechedule the time when this report begin to generate.";
        
        String modeTitle = "Reconfigure Report Frequence(1-everyday, 2-once a week, 3-once a month)";
        
        return generalInfo + "\n" + modeTitle;
    }
           
    @Override
    public String getCurrValue()
    {
        CronExpressParser parse = new CronExpressParser(reportInfo.getScheduleCron());
        
        if(parse.getIsDaily())
        {
            return CenterToolUtil.CRON_MODE_DAILY + "";
        }
        
        if(parse.getIsWeekly())
        {
            return CenterToolUtil.CRON_MODE_WEEKLY + "";
        }
        
        if(parse.getIsMonthly())
        {
            return CenterToolUtil.CRON_MODE_MONTHLY + "";
        }
        
        return "--";
    }
    
    @Override
    public boolean setNewValue(String newMode)
    {
        if(CenterToolUtil.isValidCronMode(newMode))
        {
            this.userInputMode = newMode;
            return true;
        }
        
        return false;
    }
    
    @Override
    public String getUserInput()
    {
        return userInputMode;
    }
}
