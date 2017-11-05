package com.zte.ums.an.uni.dsl.conf.cdf.common.xml;

import java.io.File;
import java.util.ArrayList;

import org.jdom.Element;

import com.zte.ums.an.uni.dsl.conf.cdf.report.common.FieldInfo;
import com.zte.ums.an.uni.dsl.conf.cdf.report.common.ReportInfo;

/**
 * <p>�ļ�����: ReportDataXmlParser</p>
 * <p>�ļ�����: </p>
 * <p>��Ȩ����: ��Ȩ����(C)2001-2012</p>
 * <p>��    ˾: ����ͨѶ�ɷ����޹�˾</p>
 * <p>����ժҪ: </p>
 * <p>����˵��: </p>
 * <p>������ڣ�2011��11��28��</p>
 * <p>�޸ļ�¼1:</p>
 * <pre>
 *    �޸����ڣ�
 *    �� �� �ţ�
 *    �� �� �ˣ�
 *    �޸����ݣ�
 * </pre>
 * <p>�޸ļ�¼2��</p>
 * @version 1.0
 * @author  jingxueshi_10118495
 */

public class ReportDataXmlParser
{
    private static ReportDataXmlParser instance = new ReportDataXmlParser();
    private XmlIO xmlIo;
    
    private ReportDataXmlParser()
    {
        File file = new File(System.getProperty("user.dir"), "conf/cdf-report-data.xml");
        xmlIo = new XmlIO(file);
    }

    public static ReportDataXmlParser getInstance() 
    {
        return instance;
    }
  
    //Schedule
    public void setSchedule(String text, String reportName)
    {
        Element uplinkPortScheduleCronElement = getScheduleCronElement(reportName);
        uplinkPortScheduleCronElement.setText(text);
    }
    
    private Element getScheduleCronElement(String reportName)
    {
        ArrayList<Element> elements = XmlIO.getAllElements(xmlIo.getRootElement(), "Schedule", "Report");
        
        for(Element e: elements)
        {
            if(e.getAttributeValue("name").equalsIgnoreCase(reportName))
            {
                return e.getChild("Schedule_Cron");
            }
        }
        
        return null;
    }
    
    
    public String[] getOrderBy(String tableName)
    {
        String strOrderBy = getStrOrderBy(tableName);

        if(strOrderBy == null || strOrderBy.equalsIgnoreCase(""))
        {
            return new String[0];
        }

        return strOrderBy.split(",");
    }

    private String getStrOrderBy(String reportName)
    {
        ArrayList<Element> rulesOfDbTable = XmlIO.getAllElements(xmlIo.getRootElement(), "Report", "TranslateRule", "table");
        
        for(Element e : rulesOfDbTable)
        {
            if(e.getAttributeValue("name").equals(reportName))
            {
                return e.getAttributeValue("DBOrderBy");
            }
        }
        
        return null;
    }
        
    public synchronized ReportInfo[] getReportInfos()
    {
        ArrayList<Element> elements = XmlIO.getAllElements(xmlIo.getRootElement(), "Schedule", "Report");
        ReportInfo[] allReports = new ReportInfo[elements.size()];
        
        for(int i = 0; i < elements.size(); i++)
        {
            Element reportE = elements.get(i);
            
            String name = reportE.getAttributeValue("name");
            String scheduleCron = reportE.getChildText("Schedule_Cron");
            String[] tables = getDbTables(reportE);
            
            allReports[i] = new ReportInfo(name, scheduleCron, tables);
        }
        
        appendFieldTranslateRuleInfos(allReports);
        
        return allReports;
    }

    private void appendFieldTranslateRuleInfos(ReportInfo[] allReports)
    {
        ArrayList<Element> rulesOfTables = XmlIO.getAllElements(xmlIo.getRootElement(), "Report", "TranslateRule", "table");
        for(Element rulesSingleTable : rulesOfTables)
        {
            String tableNameE = rulesSingleTable.getAttributeValue("name");
            for(ReportInfo reportInfo : allReports)
            {
                for(String table : reportInfo.getDbTables())
                {
                    if(table.equals(tableNameE))
                    {
                        appendFieldTranslateRuleInfos(rulesSingleTable, reportInfo);
                    }
                }
            }
        }
    }

    private void appendFieldTranslateRuleInfos(Element tableE, ReportInfo reportInfo)
    {
        ArrayList<Element> rulesOfReports = XmlIO.getAllElements(tableE, "sequence");
        
        FieldInfo[] fieldInfos = new FieldInfo[rulesOfReports.size()];
        
        for(int i = 0; i < rulesOfReports.size(); i++)
        {
            Element sequenceE = rulesOfReports.get(i);
            
            String i18n = sequenceE.getAttributeValue("i18n");
            String field = sequenceE.getAttributeValue("field");
            boolean fromDB = sequenceE.getAttributeValue("fromDB").equalsIgnoreCase("true");
            String translate = sequenceE.getAttributeValue("translate");
            
            fieldInfos[i] = new FieldInfo(i18n, field, fromDB, translate);
        }
        
        reportInfo.addFieldsForSingleDBTable(fieldInfos, tableE.getAttributeValue("name"));
    }

    private String[] getDbTables(Element reportE)
    {
        ArrayList<Element> tablesE = XmlIO.getAllElements(reportE, "DbTables", "table");
        String[] tables = new String[tablesE.size()];
        for(int j = 0; j < tablesE.size(); j++)
        {
            tables[j] = tablesE.get(j).getText();
        }
        return tables;
    }
    
    public boolean save()
    {
        return xmlIo.save();
    }
    
    public static void main(String[] args)
    {
//        ReportInfo[] arrs = ReportDataXmlParser.getInstance().getReportInfos();
//        
//        int i = 3;
//        
//        HashMap<String, String[]> map = ReportXmlParser.getInstance().getReportToGroupMapInfo();
//        
//        Iterator<Entry<String, String[]>> it = map.entrySet().iterator();
//        while(it.hasNext())
//        {
//            Entry<String, String[]> one = it.next();
//            System.out.println(one.getKey() + "|" + Arrays.deepToString(one.getValue()));
//        }
//        
//        System.out.println("------------------------");
//     
//        System.out.println(ReportXmlParser.getInstance().getUserPortSchedule());
        
//        ReportInfo[] allReports = ReportXmlParser.getInstance().getReportInfos();
//        System.out.println(Arrays.deepToString(allReports));
        
//        System.out.println(Arrays.toString(ReportXmlParser.getInstance().getOrderBy("UplinkPort")));
//        System.out.println(Arrays.toString(ReportXmlParser.getInstance().getOrderBy("UserPort")));
//        System.out.println(ReportXmlParser.getInstance().getScheduleCronElement("UserPort").getText());
//        ReportXmlParser.getInstance().setSchedule("bbb", "UserPort");
//        ReportXmlParser.getInstance().save();
//        System.out.println(ReportXmlParser.getInstance().getScheduleCronElement("UserPort").getText());
        
    }
}
