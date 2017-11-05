package com.zte.ums.an.uni.dsl.conf.cdf.centertool.action;

import com.zte.ums.an.uni.dsl.conf.cdf.centertool.common.CenterToolUtil;
import com.zte.ums.an.uni.dsl.conf.cdf.centertool.common.CronExpressGenerator;
import com.zte.ums.an.uni.dsl.conf.cdf.centertool.config.ConfigCheckRunner;
import com.zte.ums.an.uni.dsl.conf.cdf.centertool.config.model.cron.ReportCronDateDataModel;
import com.zte.ums.an.uni.dsl.conf.cdf.centertool.config.model.cron.ReportCronDayTimeDataModel;
import com.zte.ums.an.uni.dsl.conf.cdf.centertool.config.model.cron.ReportCronModeDataModel;
import com.zte.ums.an.uni.dsl.conf.cdf.centertool.config.model.cron.ReportCronWeekDayDataModel;
import com.zte.ums.an.uni.dsl.conf.cdf.report.common.ReportInfo;


/**
 * <p>�ļ�����: ReportCronConfig</p>
 * <p>�ļ�����: </p>
 * <p>��Ȩ����: ��Ȩ����(C)2001-2012</p>
 * <p>��    ˾: ����ͨѶ�ɷ����޹�˾</p>
 * <p>����ժҪ: </p>
 * <p>����˵��: </p>
 * <p>������ڣ�2012-3-14</p>
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
public class ReportCronConfig
{        
    private ReportCronConfig()
    {
    }
    
    public static void runCheck(ConfigCheckRunner runner)
    {
        ReportInfo[] allReports = CenterToolUtil.getReportDataXml().getReportInfos();
        for(int i = 0; i < allReports.length; i++)
        {
            ReportInfo reportInfo = allReports[i];
            CronExpressGenerator gen = new CronExpressGenerator();
            
            String userInputMode = runner.runSingleCheck(new ReportCronModeDataModel(reportInfo));  
            
            String userInputDayTime = runner.runSingleCheck(new ReportCronDayTimeDataModel(reportInfo));  
            gen.setDayTime(userInputDayTime, ":");
            
            if(userInputMode.equalsIgnoreCase(CenterToolUtil.CRON_MODE_WEEKLY + ""))
            {
                String userInputWeekDay = runner.runSingleCheck(new ReportCronWeekDayDataModel(reportInfo));  
                gen.setWeekDay(userInputWeekDay);
            }
            else if(userInputMode.equalsIgnoreCase(CenterToolUtil.CRON_MODE_MONTHLY + ""))
            {
                String userInputDate = runner.runSingleCheck(new ReportCronDateDataModel(reportInfo));  
                gen.setDate(userInputDate);
            }
            
            String cronExpression = gen.getCronExpress();
            CenterToolUtil.getReportDataXml().setSchedule(cronExpression, reportInfo.getName());
            System.out.println();
        }
        
        CenterToolUtil.saveAllXml();
    }
    
//    public static void main(String[] args)
//    
//    {
//        ReportCronConfig.runCheck(runner);
//    }
}
