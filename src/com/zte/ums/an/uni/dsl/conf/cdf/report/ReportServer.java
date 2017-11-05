package com.zte.ums.an.uni.dsl.conf.cdf.report;

import java.io.File;

import org.apache.log4j.PropertyConfigurator;

import com.zte.ums.an.uni.dsl.conf.cdf.common.singleton.InstanceControlByFile;


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
public class ReportServer
{
    static
    {
        String userdir = System.getProperty("user.dir");
        String propFile = new File(userdir).getAbsolutePath()
            + "/conf/log4j-report.properties";
        PropertyConfigurator.configure(propFile);
    }
    
    public static void main(String[] args)
    {
        if(InstanceControlByFile.tryRunning())
        {
            System.out.println("Only one report server is allowed on the same computer.");
            System.exit(0);
        } 
        
        ReportProcessor.start();
    }
}
