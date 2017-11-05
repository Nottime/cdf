package com.zte.ums.an.uni.dsl.conf.cdf.report.translate.userdefine;

import junit.framework.Assert;

import com.zte.ums.an.uni.dsl.conf.cdf.common.CdfConst;
import com.zte.ums.an.uni.dsl.conf.cdf.report.translate.IFieldDataTranslate;

/**
 * <p>�ļ�����: NetworkToPortIDTranslate</p>
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
public class NetworkToPortIDTranslate implements IFieldDataTranslate
{
    /**
     * ����"1/1/7/1/4093"ת��Ϊ"R1.S1.LT01.01"
     */
    @Override
    public String translate(String networkID)
    {
        if(!isValid(networkID))
        {
            return CdfConst.REPORT_VALUE_INVALID;
        }
        
        String[] cpnArray = networkID.split("/");
        
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
        
        if(arr.length != 5)
        {
            return false;
        }
        
        return true;
    }
    
  public static void main(String[] args)
  {
      IFieldDataTranslate t = new NetworkToPortIDTranslate();
      
      String networkID = "1/1/7/1/4093";
      Assert.assertEquals("R1.S1.LT07.01", t.translate(networkID));
      
      System.out.println("OK");
  }
}
