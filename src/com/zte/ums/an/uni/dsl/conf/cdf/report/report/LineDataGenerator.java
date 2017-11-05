package com.zte.ums.an.uni.dsl.conf.cdf.report.report;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.log4j.Logger;

import com.zte.ums.an.uni.dsl.conf.cdf.common.CdfConst;
import com.zte.ums.an.uni.dsl.conf.cdf.common.LogPrint;
import com.zte.ums.an.uni.dsl.conf.cdf.report.common.CdfLocalize;
import com.zte.ums.an.uni.dsl.conf.cdf.report.common.FieldInfo;
import com.zte.ums.n3common.api.ZXLogger;

/**
 * <p>�ļ�����: LineDataGenerator</p>
 * <p>�ļ�����: </p>
 * <p>��Ȩ����: ��Ȩ����(C)2001-2012</p>
 * <p>��    ˾: ����ͨѶ�ɷ����޹�˾</p>
 * <p>����ժҪ: </p>
 * <p>����˵��: </p>
 * <p>������ڣ�2012-3-2</p>
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
public class LineDataGenerator
{
    private Logger logger = ZXLogger.getLogger(this.getClass().getName());
    private static CdfLocalize localize = new CdfLocalize();
    
    private FieldInfo[] fields;
    private ResultSet rs;
    
    LineDataGenerator(final FieldInfo[] fields, ResultSet rs) throws SQLException
    {
        this.fields = fields;
        this.rs = rs;
    }
    
    public String getFieldI18n()
    {
        if(fields.length == 0)
        {
            return "";
        }
        
        StringBuffer strBuf = new StringBuffer();
        for(int i = 0; i < fields.length; i++)
        {
            strBuf.append(localize.findLocalString(fields[i].getI18n())).append(",");
        }
        
        return strBuf.substring(0, strBuf.length() - 1);
    }
    
    public boolean next() throws SQLException
    {
        return rs.next();
    }

    public String getLineData()
    {
        if(fields.length == 0)
        {
            return "";
        }
        
        int i = 0;
        try
        {
            StringBuffer strBuf = new StringBuffer();
            for(i = 0; i < fields.length; i++)
            {
                strBuf.append(getTranslatedFieldValue(i)).append(",");
            }
            
            return strBuf.substring(0, strBuf.length() - 1);
        }
        catch(Exception e)
        {
            LogPrint.logError(logger, "", e);
            System.out.println(fields[i]);
        }
        
        return CdfConst.REPORT_PROGRAM_ERROR;
    }

    private String getTranslatedFieldValue(int index) throws SQLException
    {
        FieldInfo field = fields[index];
        
        String fieldValue = null;
        if(field.isFromDB())
        {
            fieldValue = rs.getString(field.getFieldName());
        }
        
        return field.translate(fieldValue);
    }
}
