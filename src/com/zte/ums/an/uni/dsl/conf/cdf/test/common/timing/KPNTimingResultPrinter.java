package com.zte.ums.an.uni.dsl.conf.cdf.test.common.timing;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;

import org.apache.log4j.Logger;

import com.zte.ums.an.uni.dsl.conf.cdf.common.CdfUtil;
import com.zte.ums.an.uni.dsl.conf.cdf.common.LogPrint;
import com.zte.ums.an.uni.dsl.conf.cdf.common.xml.CollectXmlParser;
import com.zte.ums.an.uni.dsl.conf.cdf.test.common.CdfMockUtil;
import com.zte.ums.n3common.api.ZXLogger;

/**
 * <p>�ļ�����: KPNResultPrinter.java</p>
 * <p>�ļ�����: </p>
 * <p>��Ȩ����: ��Ȩ����(C)2001-2012</p>
 * <p>��    ˾: ����ͨѶ�ɷ����޹�˾</p>
 * <p>����ժҪ: </p>
 * <p>����˵��: </p>
 * <p>������ڣ�2011-12-30</p>
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
public class KPNTimingResultPrinter
{
    private static Logger logger = ZXLogger.getLogger(KPNTimingResultPrinter.class.getName());
    
    private static final String[] resultSequence = new String[] {
    KPNTimeMarkConst.COLLECT, KPNTimeMarkConst.PARSE, KPNTimeMarkConst.DB
    };
    
    private static final int COLUMN_LENGTH = 18;
    
    private static PrintWriter pw;
    private static File output = null;
    
    public static void exportResult(int round, HashMap<String, MergedTimeData> allData, int totalNE, int failedNE)
    {
        try
        {
            initExportFile();
            
            initBufferedWriter();
            for(String s : resultSequence)
            {
                if(allData.containsKey(s))
                {
                    String dataStr = prepareDataStr(round, allData.get(s));
                    pw.write(dataStr);
                }
            }
            
            printNeNum(totalNE, failedNE);
            printTotal(allData);
            
            LogPrint.logInfo(logger, "Completing in reporting with " + output.getAbsolutePath());
        }
        catch(IOException e)
        {
            LogPrint.logError(logger, "", e);
        }
        finally
        {
            endPrint();
        }
    }

    private static void printTotal(HashMap<String, MergedTimeData> allData)
    {
        if(allData.containsKey(KPNTimeMarkConst.TOTAL))
        {
            //����TOTAL��average��min��max���ߵ�ֵ����ʵ��һ���ġ�
            pw.write("Time cost                  :    " + allData.get(KPNTimeMarkConst.TOTAL).getAverage() + "s\n");
        }
        pw.write("============================================ End of Round ======================================================\n");
    }

    private static void printNeNum(int totalNE, int failedNE)
    {
        int sucess = totalNE - failedNE;

        pw.write("DSLAMS Number Success/Total:    " + sucess + "/" + totalNE + "("
                 + CdfMockUtil.formatFloat(CdfMockUtil.caculatePercent(sucess, totalNE)) + "%)  " + CdfUtil.getCurDateTime() + "\n");
    }

    private static void initBufferedWriter() throws IOException
    {
        pw = new PrintWriter(new BufferedWriter(new FileWriter(output, true)));
    }
    
    private static void initExportFile() throws IOException
    {
        if(output == null)
        {
            output = (new File("report", "report" + CdfUtil.getCurDateTime("yyyyMMddHHmmss") + ".txt" )).getAbsoluteFile();
            output.getParentFile().mkdirs();
            
            initBufferedWriter();
            pw.write(assembleFirstLine());
            pw.write(formatStr(MergedTimeData.getHeader()));
            pw.close();
        }
    }

    private static String assembleFirstLine()
    {
        return "========================= Start (Thread Number of Sub-collect Server == " + CollectXmlParser.getInstance().getThreadNum()
               + ") ===================================\n\n";
    }
    
    private static String formatStr(String[] data)
    {
        StringBuffer buf = new StringBuffer();
        for(int i = 0; i < data.length; i++)
        {
            buf.append(data[i]);

            int spaceLength = getSpaceLength(i, data[i]);
            buf.append(createSpaceArrChar(spaceLength)).append(" | ");
        }
        
        buf.append("\n");
        
        return buf.toString();
    }

    private static int getSpaceLength(int index, String str)
    {
        int columnlength = COLUMN_LENGTH;

        if(index == 1)
        {
            columnlength = 27;
        }
        
        while(columnlength - str.length() <= 0)
        {
            columnlength = (int)(columnlength * 1.2);
        }
        
        return columnlength - str.length();
    }

    private static char[] createSpaceArrChar(int length)
    {
        char[] initChars = new char[length];
        int i = -1;
        while(++i < initChars.length)
        {
            initChars[i] = ' ';
        }
        
        return initChars;
    }

    private static String prepareDataStr(int round, MergedTimeData datas)
    {
        datas.setRound(round);
        
        return formatStr(datas.getData());
    }
        
    private static void endPrint()
    {
        if(pw != null)
        {
            pw.close();
        }
    }
    
    //TEST
    public static void main(String[] args)
    {
        String[] data = new String[] {"Collect From DSLAM", "298", "ddd", "123"};
        String[] data2 = new String[] {"Insert DB", "29118", "111", "122223"};
        System.out.print(formatStr(data));
        System.out.print(formatStr(data2));
    }
}
