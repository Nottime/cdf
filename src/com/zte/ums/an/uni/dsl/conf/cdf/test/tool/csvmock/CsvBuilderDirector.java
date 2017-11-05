package com.zte.ums.an.uni.dsl.conf.cdf.test.tool.csvmock;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import com.zte.ums.an.uni.dsl.conf.cdf.common.CdfUtil;

/**
 * <p>�ļ�����: CsvBuilderDirector</p>
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

public class CsvBuilderDirector
{
    private static final int DEFAULT_CSV_FILE_NUM = 1;
    private static final int DEFAULT_PORT_NUM = 192;
    
    private String exportPathBase = "g:\\";
    private int portNum = DEFAULT_PORT_NUM;
    private int csvFileNum = DEFAULT_CSV_FILE_NUM;
        
    private PrintWriter printWriter;
    
    public CsvBuilderDirector()
    {
    }
    
    public CsvBuilderDirector(String exportPath, int portNum, int csvFileNum)
    {
        this.exportPathBase = CdfUtil.formatPath(exportPath);        
        this.portNum = portNum;
        this.csvFileNum = csvFileNum;
    }
    
    public void build() throws IOException
    {
        CdfUtil.createdDirIfNoExist(this.exportPathBase);

        for(int i = 0; i < csvFileNum; i++)
        {
            generateOneCsv();
            System.out.println("Remaining " + (csvFileNum - i) + " files.");
        }
    }
    
    private void generateOneCsv() throws IOException
    {
        String fileName = FileNameMocker.getInstance().getNextFileName();
        String filepath = exportPathBase + FileNameMocker.getInstance().getIPAddrFromCurrFileName() + File.separatorChar;
        (new File(filepath)).mkdirs();
        
        String exportFile = filepath + fileName;
        
        printWriter = new PrintWriter(new BufferedWriter(new FileWriter(exportFile)));

        printWriter.println(MockedDataBuilder.mockHeader());
        
        for(int i = 0; i < portNum; i++)
        {
            printWriter.println(MockedDataBuilder.mockDataLine());
        }
        
        printWriter.close();
        System.out.print("succeded in generate CSV at " + exportFile + ".");
    }
}
