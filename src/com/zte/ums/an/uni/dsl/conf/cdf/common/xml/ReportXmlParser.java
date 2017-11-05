package com.zte.ums.an.uni.dsl.conf.cdf.common.xml;

import java.io.File;

/**
 * <p>�ļ�����: ReportXmlParser</p>
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

public class ReportXmlParser
{
    private static ReportXmlParser instance = new ReportXmlParser();
    private XmlIO xmlIo;
    
    private ReportXmlParser()
    {
        File file = new File(System.getProperty("user.dir"), "conf/cdf-report.xml");
        xmlIo = new XmlIO(file);
    }

    public static ReportXmlParser getInstance() 
    {
        return instance;
    }
    
    public String getServerIP()
    {
        return xmlIo.getRootElement().getChild("Remote_Dispatch_Server").getChild("Server_IP").getText();
    }
    
    public void setServerIP(String text)
    {
        xmlIo.getRootElement().getChild("Remote_Dispatch_Server").getChild("Server_IP").setText(text);
    }
    
    public String getServerPort()
    {
        return xmlIo.getRootElement().getChild("Remote_Dispatch_Server").getChild("Server_Port").getText();
    }
    
    public void setServerPort(String text)
    {
        xmlIo.getRootElement().getChild("Remote_Dispatch_Server").getChild("Server_Port").setText(text);
    }
    
    public String getRemoteObjectName()
    {
        return xmlIo.getRootElement().getChild("Remote_Dispatch_Server").getChild("Remote_Object_Name").getText();
    }
    
    public void setRemoteObjectName(String text)
    {
        xmlIo.getRootElement().getChild("Remote_Dispatch_Server").getChild("Remote_Object_Name").setText(text);
    }
    
    //Report    
    public String getExportDir()
    {
        return xmlIo.getRootElement().getChild("Report").getChild("Export_Dir").getText();
    }
    
    public void setExportDir(String text)
    {
        xmlIo.getRootElement().getChild("Report").getChild("Export_Dir").setText(text);
    }
    
    public boolean getIsDeletePrerecords()
    {
        String text = xmlIo.getRootElement().getChild("Report").getChild("Delete_PreRecords").getText();
        
        return "true".equalsIgnoreCase(text);
    }
    
    public void setIsDeletePrerecords(String text)
    {
        xmlIo.getRootElement().getChild("Report").getChild("Delete_PreRecords").setText(text);
    }

    public boolean save()
    {
        return xmlIo.save();
    }
    
    public static void main(String[] args)
    {
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
