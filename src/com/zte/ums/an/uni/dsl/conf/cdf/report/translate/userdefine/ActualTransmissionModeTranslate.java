package com.zte.ums.an.uni.dsl.conf.cdf.report.translate.userdefine;

import com.zte.ums.an.uni.dsl.conf.cdf.common.CdfConst;
import com.zte.ums.an.uni.dsl.conf.cdf.report.translate.IFieldDataTranslate;

/**
 * <p>�ļ�����: ActualTransmissionModeTranslate.java</p>
 * <p>�ļ�����: </p>
 * <p>��Ȩ����: ��Ȩ����(C)2001-2012</p>
 * <p>��    ˾: ����ͨѶ�ɷ����޹�˾</p>
 * <p>����ժҪ: </p>
 * <p>����˵��: </p>
 * <p>������ڣ�2012-6-28</p>
 * <p>�޸ļ�¼1:</p>
 * <pre>
 *    �޸����ڣ�
 *    �� �� �ţ�
 *    �� �� �ˣ�
 *    �޸����ݣ�
 * </pre>
 * <p>�޸ļ�¼2��</p>
 * @version 1.0
 * @author ljy
 */
public class ActualTransmissionModeTranslate implements IFieldDataTranslate
{
    public static final int LENGTH_TRANSLATE = 8;
    
    @Override
    public String translate(String bitsValue)
    {
        if(bitsValue == null || bitsValue.length() != 16)
        {
            return CdfConst.REPORT_VALUE_INVALID;
        }

        return getBinary(bitsValue.substring(0, LENGTH_TRANSLATE)) + getBinary(bitsValue.substring(LENGTH_TRANSLATE));
    }
    
    private static String getBinary(String bitsValue)
    {
        long longValue = Long.parseLong(bitsValue, 16);
        String binaryStr = Long.toBinaryString(longValue);

        int addCount = LENGTH_TRANSLATE * 4 - binaryStr.length();
        if(addCount > 0)
        {
            StringBuffer buf = new StringBuffer();
            for(int i = 0; i < addCount; i++)
            {
                buf.append("0");
            }
            buf.append(binaryStr);
            return buf.toString();
        }
        return binaryStr;
    }
    
    
    
//    public static void main(String[] args)
//    {
//        IFieldDataTranslate t = new ActualTransmissionModeTranslate();
//        String bitsValue = "1000000000000000";
//        Assert.assertEquals("0001000000000000000000000000000000000000000000000000000000000000", t.translate(bitsValue));
//        
//        bitsValue = "0000000000800000";
//        Assert.assertEquals("0000000000000000000000000000000000000000100000000000000000000000", t.translate(bitsValue));
//        
//        bitsValue = "1000000000000001";
//        Assert.assertEquals("0001000000000000000000000000000000000000000000000000000000000001", t.translate(bitsValue));
//        
//        bitsValue = "8000000000000000";
//        Assert.assertEquals("1000000000000000000000000000000000000000000000000000000000000000", t.translate(bitsValue));
//        
//        bitsValue = "0000000000000000";
//        Assert.assertEquals("0000000000000000000000000000000000000000000000000000000000000000", t.translate(bitsValue));
//        
//        bitsValue = "FFFFFFFFFFFFFFFF";
//        Assert.assertEquals("1111111111111111111111111111111111111111111111111111111111111111", t.translate(bitsValue));
//        System.out.println("OK");
//    }
    

}
