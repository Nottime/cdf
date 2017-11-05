package com.zte.ums.an.uni.dsl.conf.cdf.report;

import java.text.ParseException;
import java.util.HashMap;

import org.apache.log4j.Logger;
import org.quartz.CronTrigger;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.Trigger;
import org.quartz.impl.StdSchedulerFactory;

import com.zte.ums.an.uni.dsl.conf.cdf.centertool.common.CronExpressParser;
import com.zte.ums.an.uni.dsl.conf.cdf.common.LogPrint;
import com.zte.ums.an.uni.dsl.conf.cdf.common.xml.ReportDataXmlParser;
import com.zte.ums.an.uni.dsl.conf.cdf.report.common.ReportInfo;
import com.zte.ums.an.uni.dsl.conf.cdf.report.report.CdfReport;
import com.zte.ums.n3common.api.ZXLogger;

/**
 * <p>�ļ�����: ReportServer.java</p>
 * <p>�ļ�����: </p>
 * <p>��Ȩ����: ��Ȩ����(C)2007-2010</p>
 * <p>��    ˾: ����ͨѶ�ɷ����޹�˾</p>
 * <p>����ժҪ: </p>
 * <p>����˵��: </p>
 * <p>������ڣ�2011��9��1��</p>
 * <p>�޸ļ�¼1:</p>
 * <pre>
 *    �޸����ڣ�
 *    �� �� �ţ�
 *    �� �� �ˣ�
 *    �޸����ݣ�
 * </pre>
 * <p>�޸ļ�¼2��</p>
 * @version 1.0
 * @author lixiaochun
 */
public class ReportProcessor
{
    private static Logger logger = ZXLogger.getLogger(ReportProcessor.class.getName());
    private static HashMap<String, ReportInfo> mapReports = new HashMap<String, ReportInfo>();
    
    private ReportProcessor()
    {
    }
    
    private static void scheduleReportJobs(ReportInfo[] allReports) throws Exception
    {
        Scheduler scheduler = (new StdSchedulerFactory()).getScheduler();

        for(ReportInfo report : allReports)
        {
            scheduleSingleReport(scheduler, report);
        }
        scheduler.start();
    }

    private static void scheduleSingleReport(Scheduler scheduler, ReportInfo report) throws ParseException, SchedulerException
    {
        CdfReport instance = new CdfReport();
        
        String name = report.getName();
        String table = report.getName() + "table";

        Trigger trigger = new CronTrigger(name, table, report.getScheduleCron());
        JobDetail job = new JobDetail(name, table, instance.getClass());

        scheduler.scheduleJob(job, trigger);
    }
    
    private static void initHashMap(ReportInfo[] allReports)
    {
        for(ReportInfo info : allReports)
        {
            mapReports.put(info.getName(), info);
        }
    }
    
    public static HashMap<String, ReportInfo> getAllReports()
    {
        return mapReports;
    }
    
    private static String getAllReportsScheduledTime(ReportInfo[] allReports)
    {
        StringBuffer buf = new StringBuffer("\n");
        for(ReportInfo report : allReports)
        {
            CronExpressParser cronExpressParser = new CronExpressParser(report.getScheduleCron());
            String reportSchedule = "Report named '" + report.getName() + "' begins to execute at " + cronExpressParser.toDisplayStr();
            buf.append(reportSchedule).append("\n");
        }
        
        return buf.toString();
    }

    static void start()
    {
        ReportInfo[] allReports = ReportDataXmlParser.getInstance().getReportInfos();

        LogPrint.logInfo(logger, "Report server has started. Please wait until the scheduled time arrives.\n"
                                 + getAllReportsScheduledTime(allReports));

        initHashMap(allReports);

        try
        {
            scheduleReportJobs(allReports);
        }
        catch(Exception e)
        {
            LogPrint.logError(logger, "", e);
        }
    }
}
