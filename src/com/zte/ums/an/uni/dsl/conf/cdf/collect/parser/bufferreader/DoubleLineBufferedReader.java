package com.zte.ums.an.uni.dsl.conf.cdf.collect.parser.bufferreader;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;

import com.zte.ums.an.uni.dsl.conf.cdf.collect.parser.common.Record;


/**
 * <p>�ļ�����: DoubleLineBufferedReader</p>
 * <p>�ļ�����: </p>
 * <p>��Ȩ����: ��Ȩ����(C)2001-2012</p>
 * <p>��    ˾: ����ͨѶ�ɷ����޹�˾</p>
 * <p>����ժҪ: </p>
 * <p>����˵��: </p>
 * <p>������ڣ�2011��12��14��</p>
 * <p>�޸ļ�¼1:</p>
 * <pre>
 *    �޸����ڣ�
 *    �� �� �ţ�
 *    �� �� �ˣ�
 *    �޸����ݣ�
 * </pre>
 * <p>�޸ļ�¼2��</p>
 * @version 1.0
 * @author  Chenduoduo_10087118
 */
public class DoubleLineBufferedReader extends BufferedReader
{
    private int lineNumber = 0;
    
    public DoubleLineBufferedReader(Reader in)
    {
        super(in);
    }
    
    @Override
    public String readLine() throws IOException
    {
        //TODO У��������
        if(lineNumber == 0)
        {
            lineNumber++;
            return super.readLine();
        }
        
        String line1 = super.readLine();
        String line2 = super.readLine();
        
        if(line1 == null || line2 == null)
        {
            return null;
        }
        
        Record r1 = new Record(line1);
        Record r2 = new Record(line2);
        
        String entryId = r1.getEntryId().substring(0, r1.getEntryId().length() - 2);
        
        return entryId + "," + r1.getLineRec() + "," + r2.getLineRec();
    }
}
