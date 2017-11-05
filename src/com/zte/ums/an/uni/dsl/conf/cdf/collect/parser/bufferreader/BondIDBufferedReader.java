package com.zte.ums.an.uni.dsl.conf.cdf.collect.parser.bufferreader;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;

/**
 * <p>�ļ�����: BondIDBufferedReader.java</p>
 * <p>�ļ�����: </p>
 * <p>��Ȩ����: ��Ȩ����(C)2001-2012</p>
 * <p>��    ˾: ����ͨѶ�ɷ����޹�˾</p>
 * <p>����ժҪ: </p>
 * <p>����˵��: </p>
 * <p>������ڣ�2012-3-5</p>
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
public class BondIDBufferedReader extends BufferedReader
{
    public BondIDBufferedReader(Reader in)
    {
        super(in);
    }
    
    @Override
    public String readLine() throws IOException
    {        
        String line = super.readLine();
        
        if(line == null || line.equalsIgnoreCase(""))
        {
            return line;
        }
        
        String entryId = line.substring(0, line.indexOf(","));
        
        String cpn = entryId.substring(0, entryId.lastIndexOf("/"));
        String bondId = entryId.substring(entryId.lastIndexOf("/") + 1);
        
        StringBuffer buf = new StringBuffer();
        buf.append(cpn).append(",").append(bondId).append(",").append(line.substring(line.indexOf(",") + 1));
        
        return buf.toString();
    }
}
