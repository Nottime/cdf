package com.zte.ums.an.uni.dsl.conf.cdf.test.tool.customizedcsvmock.common;

import java.io.File;

import com.zte.ums.an.uni.dsl.conf.cdf.common.xml.XmlIO;

/**
 * <p>�ļ�����: CollectXmlParser</p>
 * <p>�ļ�����: </p>
 * <p>��Ȩ����: ��Ȩ����(C)2001-2012</p>
 * <p>��    ˾: ����ͨѶ�ɷ����޹�˾</p>
 * <p>����ժҪ: </p>
 * <p>����˵��: </p>
 * <p>������ڣ�2011��11��25��</p>
 * <p>�޸ļ�¼1:</p>
 * <pre>
 *    �޸����ڣ�
 *    �� �� �ţ�
 *    �� �� �ˣ�
 *    �޸����ݣ�
 * </pre>
 * <p>�޸ļ�¼2��</p>
 * @version 1.0
 * @author  ChenDuoduo_10087118
 */

public class CdfMockDslamXmlParser
{
    private static CdfMockDslamXmlParser instance = new CdfMockDslamXmlParser();
    private XmlIO xmlIo;
    
    private CdfMockDslamXmlParser()
    {
        File file = new File(System.getProperty("user.dir"), "conf/cdf-mock-dslam.xml");
        xmlIo = new XmlIO(file);
    }

    public static CdfMockDslamXmlParser getInstance() 
    {
        return instance;
    }
    
    public String getServerIP()
    {
        return xmlIo.getRootElement().getChild("Mock_DSLAM").getChild("Remote_Mocker_Server").getChild("Server_IP").getText();
        
    }
    
    public String getServerPort()
    {
        return xmlIo.getRootElement().getChild("Mock_DSLAM").getChild("Remote_Mocker_Server").getChild("Server_Port").getText();
    }
    
    public String getRemoteObjectName()
    {
        return xmlIo.getRootElement().getChild("Mock_DSLAM").getChild("Remote_Mocker_Server").getChild("Remote_Object_Name").getText();
    }
   
 
    public static void main(String[] args) throws Exception
    {
        System.out.println(CdfMockDslamXmlParser.getInstance().getRemoteObjectName());
        System.out.println(CdfMockDslamXmlParser.getInstance().getServerIP());
        System.out.println(CdfMockDslamXmlParser.getInstance().getServerPort());
    }
}
