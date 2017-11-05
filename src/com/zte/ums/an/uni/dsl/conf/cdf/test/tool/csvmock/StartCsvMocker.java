package com.zte.ums.an.uni.dsl.conf.cdf.test.tool.csvmock;

import java.io.IOException;

import com.zte.ums.an.uni.dsl.conf.cdf.common.CdfTime;

/**
 * <p>�ļ�����: StartCsvMocker</p>
 * <p>�ļ�����: </p>
 * <p>��Ȩ����: ��Ȩ����(C)2001-2012</p>
 * <p>��    ˾: ����ͨѶ�ɷ����޹�˾</p>
 * <p>����ժҪ: </p>
 * <p>����˵��: </p>
 * <p>������ڣ�2011��11��21��</p>
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

public class StartCsvMocker
{
    public static void main(String[] args) throws Exception
    {
        String exportFilePath = "C:\\ftpdir\\CDF";
        int portNum = 192;
        int fileNum = 10000;
        
        CdfTime tm = new CdfTime();
        tm.markStartTime();
        
        CsvBuilderDirector dirct = new CsvBuilderDirector(exportFilePath, portNum, fileNum);
        
        try
        {
            dirct.build();
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }
        
        tm.markEndTime();
        System.out.println("Time cost :" + tm.caculateTimeCost() + "s");
        
    }
}
