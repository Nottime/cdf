package com.zte.ums.an.uni.dsl.conf.cdf.test.tool.dslamcsvmock;

import java.io.File;

import org.apache.log4j.PropertyConfigurator;

import com.zte.ums.an.uni.dsl.conf.cdf.test.tool.dslamcsvmock.rmi.RMIStarter;

/**
 * <p>�ļ�����: StartDslamCsvMocker.java</p>
 * <p>�ļ�����: </p>
 * <p>��Ȩ����: ��Ȩ����(C)2001-2012</p>
 * <p>��    ˾: ����ͨѶ�ɷ����޹�˾</p>
 * <p>����ժҪ: </p>
 * <p>����˵��: </p>
 * <p>������ڣ�2011-12-22</p>
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
public class StartDslamCsvMocker
{
    static
    {
        String userdir = System.getProperty("user.dir");
        String propFile = new File(userdir).getAbsolutePath()
            + "/conf/log4j-massive-dslams-mocker.properties";
        PropertyConfigurator.configure(propFile);
    }
    
    public static void main(String[] args)
    {
//        CdfMockUtil.setUserDirToParentPath();
        
        RMIStarter.start();
    }

}
