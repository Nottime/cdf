package com.zte.ums.an.uni.dsl.conf.cdf.report.translate.userdefine;

import com.zte.ums.an.uni.dsl.conf.cdf.common.CdfConst;
import com.zte.ums.an.uni.dsl.conf.cdf.report.translate.IFieldDataTranslate;

/**
 * <p>�ļ�����: CpnTranslate.java</p>
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
public class CpnTranslate implements IFieldDataTranslate
{
    /**
     * ����"1/1/7/1"ת��Ϊ"R1.S1.LT07.01"
     */
    @Override
    public String translate(String strCPN)
    {
        if(!isValid(strCPN))
        {
            return CdfConst.REPORT_VALUE_INVALID;
        }
        
        String[] cpnArray = strCPN.split("/");
        
        StringBuffer buf = new StringBuffer();
        
        buf.append("R").append(cpnArray[0]).append(".");
        
        buf.append("S").append(cpnArray[1]).append(".");
        
        buf.append("LT");
        if(cpnArray[2].length() == 1)
        {
            buf.append("0");
        }
        buf.append(cpnArray[2]).append(".");
        
        if(cpnArray[3].length() == 1)
        {
            buf.append("0");
        }
        buf.append(cpnArray[3]);
        
        return buf.toString();
    }
    
    private boolean isValid(String strCPN)
    {
        if(strCPN == null || !strCPN.contains("/"))
        {
            return false;
        }
        
        String[] arr = strCPN.split("/");
        
        if(arr.length != 4)
        {
            return false;
        }
        
        return true;
    }
    
//    public static void main(String[] args)
//    {
//        IFieldDataTranslate t = new CpnTranslate();
//        
//        String cpn = "1/1/1/1";
//        Assert.assertEquals("R1.S1.LT01.01", t.translate(cpn));
//        
//        cpn = "1/1/1/10";
//        Assert.assertEquals("R1.S1.LT01.10", t.translate(cpn));
//        
//        cpn = "100/222/333/444";
//        Assert.assertEquals("R100.S222.LT333.444", t.translate(cpn));
//        
//        cpn = "100/90/8/";
//        Assert.assertEquals(CdfConst.REPORT_VALUE_INVALID, t.translate(cpn));
//        
//        cpn = "1/1/1 1";
//        Assert.assertEquals(CdfConst.REPORT_VALUE_INVALID, t.translate(cpn));
//        
//        System.out.println("OK");
//    }
}
