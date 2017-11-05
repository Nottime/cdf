package com.zte.ums.an.uni.dsl.conf.cdf.test.tool.customizedcsvmock;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.Set;

import org.apache.log4j.Logger;

import com.zte.ums.an.uni.dsl.conf.cdf.common.LogPrint;
import com.zte.ums.an.uni.dsl.conf.cdf.test.tool.customizedcsvmock.common.CdfMockServerXmlParser;
import com.zte.ums.an.uni.dsl.conf.cdf.test.tool.customizedcsvmock.entryidmock.EntryIdBuilder;
import com.zte.ums.an.uni.dsl.conf.cdf.test.tool.customizedcsvmock.entryidmock.EntryIdField;
import com.zte.ums.n3common.api.ZXLogger;

/**
 * <p>�ļ�����: CsvFileMocker.java</p>
 * <p>�ļ�����: </p>
 * <p>��Ȩ����: ��Ȩ����(C)2001-2012</p>
 * <p>��    ˾: ����ͨѶ�ɷ����޹�˾</p>
 * <p>����ժҪ: </p>
 * <p>����˵��: </p>
 * <p>������ڣ�2011-12-27</p>
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
public class CsvFileMocker
{
    private static final HashMap<String, String> mapBulkPoolToParams = CdfMockServerXmlParser.getInstance().getMapBulkPoolToParams();
    private static final HashMap<String, String> mapBulkPoolToEntryId = CdfMockServerXmlParser.getInstance().getMapBulkPoolToEntryId();
    private static final HashMap<String, EntryIdField[]> mapEntryId = CdfMockServerXmlParser.getInstance().getMapEntryId();
    
    private Logger logger = ZXLogger.getLogger(this.getClass().getName());
    private String ipAddr;
    private String exportPath;
    
    public CsvFileMocker(String ipAddr, String exportPath)
    {
        this.ipAddr = ipAddr;
        this.exportPath = exportPath;
    }
    
    public void mock()
    {
        init();
        
        Set<Entry<String, String>> entrySet = mapBulkPoolToParams.entrySet();
        Iterator<Entry<String, String>> it = entrySet.iterator();
        
        while(it.hasNext())
        {
            Entry<String, String> next = it.next();
            String bulkPool = next.getKey();
            String params = next.getValue();
            
            printSingleBulkPoolFile(bulkPool, params);
        }
    }
    
    private void printSingleBulkPoolFile(String bulkPool, String params)
    {
        PrintWriter writer = initPrintWriter(bulkPool);

        writer.println(params);

        int paramNum = params.split(",").length;

        String entryIdType = mapBulkPoolToEntryId.get(bulkPool);
        EntryIdBuilder entryIdBuilder = new EntryIdBuilder(mapEntryId.get(entryIdType));

        String entryId;
        while((entryId = entryIdBuilder.getNext()) != null)
        {
            writer.println(mockLine(entryId, paramNum));
        }
        
        writer.close();
    }

    private void init()
    {
        (new File(exportPath)).mkdirs();
    }

    private PrintWriter initPrintWriter(String bulkPool)
    {        
        File csv = new File(exportPath, mockFileName(bulkPool));
        PrintWriter writer = null;
        try
        {
            writer = new PrintWriter(new BufferedWriter(new FileWriter(csv)));
        }
        catch(IOException e)
        {
            LogPrint.logError(logger, "", e);
        }
        return writer;
    }

    private String mockLine(String entryId, int paramNum)
    {      
        StringBuffer line = new StringBuffer(entryId);
        
        for(int i = 0; i < paramNum - 1; i++)
        {
            line.append("," + mockData(i));
        }
        return line.toString();
    }

    private String mockFileName(String bulkPoolName)
    {
        return ipAddr + "_" + bulkPoolName + "_" + mockTimeStamp() + ".csv";
    }
    
    private static String mockTimeStamp()
    {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
        return sdf.format(new Date());
    }
    
    private static int mockData(int length)
    {
        return ((int)(Math.random() * 100000)) % 10000;
    }
    
    public static void main(String[] args)
    {
        String path = (new File(CdfMockServerXmlParser.getInstance().getTempFilePath(), "1.2.3.4")).getAbsolutePath();
        (new CsvFileMocker("1.2.3.4", path)).mock();
    }
}
