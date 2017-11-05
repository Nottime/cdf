package com.zte.ums.an.uni.dsl.conf.cdf.report.translate.userdefine;

import com.zte.ums.an.uni.dsl.conf.cdf.common.CdfConst;
import com.zte.ums.an.uni.dsl.conf.cdf.report.translate.IFieldDataTranslate;

/**
 * <p>�ļ�����: ASCIITransalte.java</p>
 * <p>�ļ�����: </p>
 * <p>��Ȩ����: ��Ȩ����(C)2001-2012</p>
 * <p>��    ˾: ����ͨѶ�ɷ����޹�˾</p>
 * <p>����ժҪ: </p>
 * <p>����˵��: </p>
 * <p>������ڣ�2012-3-6</p>
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
public class ASCIITransalte implements IFieldDataTranslate
{
    @Override
    public String translate(String bitAscii)
    {
        if(bitAscii == null)
        {
            return CdfConst.REPORT_VALUE_INVALID;
        }
        
        return new String(toAscII(bitAscii));
    }
    
    private static StringBuffer toAscII(String bitsValue)
    {        
        StringBuffer buf = new StringBuffer();
        
        int index = 0;
        while((index + 1) < bitsValue.length())
        {
            String strValue = bitsValue.substring(index, index + 2);
            int intValue = Integer.parseInt(strValue, 16);
            if(intValue == 0)
            {
                break;
            }
            
            buf.append((char)intValue);
            index += 2;
        }
        
        return buf;
    }
}
