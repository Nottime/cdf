package com.zte.ums.an.uni.dsl.conf.cdf.report.report;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.rmi.RemoteException;
import java.sql.ResultSet;
import java.util.ArrayList;

import org.apache.log4j.Logger;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import com.zte.ums.an.uni.dsl.conf.cdf.common.CdfUtil;
import com.zte.ums.an.uni.dsl.conf.cdf.common.LogPrint;
import com.zte.ums.an.uni.dsl.conf.cdf.common.SubCollectInfo;
import com.zte.ums.an.uni.dsl.conf.cdf.common.xml.ReportXmlParser;
import com.zte.ums.an.uni.dsl.conf.cdf.dispatch.dispatch.IDispatch;
import com.zte.ums.an.uni.dsl.conf.cdf.report.DispatchRemoteAgent;
import com.zte.ums.an.uni.dsl.conf.cdf.report.ReportProcessor;
import com.zte.ums.an.uni.dsl.conf.cdf.report.common.FieldInfo;
import com.zte.ums.an.uni.dsl.conf.cdf.report.common.ReportInfo;
import com.zte.ums.n3common.api.ZXLogger;

/**
 * <p>�ļ�����: CdfReport</p>
 * <p>�ļ�����: </p>
 * <p>��Ȩ����: ��Ȩ����(C)2007-2012</p>
 * <p>��    ˾: ����ͨѶ�ɷ����޹�˾</p>
 * <p>����ժҪ: </p>
 * <p>����˵��: </p>
 * <p>������ڣ�2011��12��7��</p>
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
public class CdfReport implements Job
{
  //****** �����: �������� **********************************************************************/
    
    private ReportInfo reportInfo;
    
    protected Logger logger = ZXLogger.getLogger(this.getClass().getName());
    
    @Override
    public void execute(JobExecutionContext arg0) throws JobExecutionException
    {
        if(!beforeRunning(arg0.getJobDetail().getName()))
        {
            return;
        }
        
        IDispatch dispatchInterface = DispatchRemoteAgent.getInstance().getDispatchInterface();
        if(dispatchInterface == null)
        {
            LogPrint.logInfo(logger, "Please ensure the dispatch server already startup.");
            reportInfo.setRunning(false);
            return;
        }

        ArrayList<SubCollectInfo> subCollectSvrList = getValidSubCollectSvrList(dispatchInterface);
        if(subCollectSvrList != null)
        {
            generateReport(subCollectSvrList);
        }
        
        afterRunning();
    }
    
    private boolean beforeRunning(String reportName)
    {
        reportInfo = ReportProcessor.getAllReports().get(reportName);
        if(reportInfo == null)
        {
            LogPrint.logError(logger, "Cannot find detail for " + reportName);
            return false;
        }
        
        if(reportInfo.isRunning())
        {
            LogPrint.logInfo(logger, "Cannot start " + reportName + "report due to last report is still running.");
            return false;
        }
        
        reportInfo.setRunning(true);
        LogPrint.logInfo(logger, reportInfo.getName() + " report begins at " + CdfUtil.getCurDateTime());
        return true;
    }
    
    private void afterRunning()
    {
        LogPrint.logInfo(logger, reportInfo.getName() + " report finishes at " + CdfUtil.getCurDateTime());
        reportInfo.setRunning(false);
    }
    
    private ArrayList<SubCollectInfo> getValidSubCollectSvrList(IDispatch dispatchInterface)
    {
        ArrayList<SubCollectInfo> subCollectSvrList = null;

        try
        {
            subCollectSvrList = dispatchInterface.getSubCollectSvrList();
        }
        catch(RemoteException e)
        {
            LogPrint.logError(logger, "", e);
            return null;
        }

        if(subCollectSvrList.size() == 0)
        {
            LogPrint.logWarn(logger, "No alive collect server found. Exit without generating report.");
            return null;
        }
        
        return subCollectSvrList;
    }

    private void generateReport(ArrayList<SubCollectInfo> subCollectSvrList)
    {
        DbOperGateway dbOper = new DbOperGateway(subCollectSvrList);
        
        try
        {
            String[] allDbTables = getDbTables();
                        
            for(int i = 0; i < allDbTables.length; i++)
            {
                ResultSet rs = dbOper.startQuery(allDbTables[i]);
                checkEmpty(subCollectSvrList, dbOper);
                File reportFile = assembleReportFile(allDbTables[i]);
                
                doGenerate(rs, reportFile, allDbTables[i]);
                
                afterGenerate(dbOper, reportFile);
            }
        }
        catch(Exception ex)
        {
            LogPrint.logError(logger, "", ex);
        }
        finally
        {
            dbOper.closeAll();
        }
    }

    protected void afterGenerate(DbOperGateway dbOper, File reportFile)
    {
        if(ReportXmlParser.getInstance().getIsDeletePrerecords())
        {
            dbOper.deleteQueriedRecords();
        }
            
        dbOper.closeQuery();
        
        String finalReportFile = reportFile.getAbsolutePath().substring(0, reportFile.getAbsolutePath().lastIndexOf("."));
        reportFile.renameTo(new File(finalReportFile));
        LogPrint.logInfo(logger, "Succeeded in generating reports at '" + finalReportFile + "'.");
    }

    private void checkEmpty(ArrayList<SubCollectInfo> subCollectSvrList, DbOperGateway dbOper)
    {
        for(int j = 0; j < subCollectSvrList.size(); j++)
        {
            SubCollectInfo svr = subCollectSvrList.get(j);
            if(!dbOper.isCurrQueryHasRecords(svr))
            {
                LogPrint.logWarn(logger, "The collect server: " + svr.getName() + " has no record in database.");
            }
        }
    }
    
    private void doGenerate(ResultSet rs, File reportFile, String tableName) 
    {
        PrintWriter printWriter = null;

        try
        {
            printWriter = new PrintWriter(new BufferedWriter(new FileWriter(reportFile)));
            
            FieldInfo[] fields = getFieldsInfo(tableName);
            LineDataGenerator gen = new LineDataGenerator(fields, rs);
            printWriter.println(gen.getFieldI18n());
            
            while(gen.next())
            {
                printWriter.println(gen.getLineData());
            }
        }
        catch(Exception e)
        {
            LogPrint.logError(logger, "", e);
            return;
        }
        finally
        {
            if(printWriter != null)
            {
                printWriter.close();
            }
        }
    }
    
    private FieldInfo[] getFieldsInfo(String tableName)
    {
        FieldInfo[] fields = this.reportInfo.getFields(tableName);
        if(fields == null || fields.length == 0)
        {
            throw new IllegalArgumentException("Fields of table:" + tableName + " cannot be empty.");
        }
        
        return fields;
    }

    private File assembleReportFile(String dbTableName)
    {
        String filePath = ReportXmlParser.getInstance().getExportDir();
        String fileName = dbTableName + "_" +CdfUtil.getCurDateTime("yyyyMMdd_HHmmss") + ".csv.tmp";
        
        (new File(filePath)).mkdirs();
        
        return new File(filePath, fileName);
    }
    
    private String[] getDbTables()
    {
        return this.reportInfo.getDbTables();
    }
}
