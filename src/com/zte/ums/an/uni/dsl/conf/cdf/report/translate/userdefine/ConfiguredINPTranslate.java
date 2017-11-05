package com.zte.ums.an.uni.dsl.conf.cdf.report.translate.userdefine;

import java.util.HashMap;
import java.util.Map;

import com.zte.ums.an.uni.dsl.conf.cdf.report.translate.IFieldDataTranslate;

/**
 * <p>�ļ�����: ConfiguredINPTranslate</p>
 * <p>�ļ�����: </p>
 * <p>��Ȩ����: ��Ȩ����(C)2005-2015</p>
 * <p>��    ˾: ����ͨѶ�ɷ����޹�˾</p>
 * <p>����ժҪ: </p>
 * <p>����˵��: </p>
 * <p>������ڣ�2012��12��18��</p>
 * <p>�޸ļ�¼1:</p>
 * <pre>
 *    �޸����ڣ�
 *    �� �� �ţ�
 *    �� �� �ˣ�
 *    �޸����ݣ�
 * </pre>
 * <p>�޸ļ�¼2��</p>
 * @version 1.0
 * @author  ChenDuoduo_10087118
 */

public class ConfiguredINPTranslate implements IFieldDataTranslate
{   
    private static final int mib_noProtection = 1;
    private static final int mib_halfSymbol = 2;
    private static final int mib_singleSymbol = 3;
    private static final int mib_twoSymbols = 4;
    private static final int mib_threeSymbols = 5;
    private static final int mib_fourSymbols = 6;
    private static final int mib_fiveSymbols = 7;
    private static final int mib_sixSymbols = 8;
    private static final int mib_sevenSymbols = 9;
    private static final int mib_eightSymbols = 10;
    private static final int mib_nineSymbols = 11;
    private static final int mib_tenSymbols = 12;
    private static final int mib_elevenSymbols = 13;
    private static final int mib_twelveSymbols = 14;
    private static final int mib_thirteeSymbols = 15;
    private static final int mib_fourteenSymbols = 16;
    private static final int mib_fifteenSymbols = 17;
    private static final int mib_sixteenSymbols = 18;
    
    private static final Map<Integer, String> map = initMap();
    
    private static Map<Integer, String> initMap()
    {
        HashMap<Integer, String> map = new HashMap<Integer, String>();
        map.put(mib_noProtection, "0.0");
        map.put(mib_halfSymbol, "0.5");
        map.put(mib_singleSymbol, "1.0");
        map.put(mib_twoSymbols, "2.0");
        map.put(mib_threeSymbols, "3.0");
        map.put(mib_fourSymbols, "4.0");
        map.put(mib_fiveSymbols, "5.0");
        map.put(mib_sixSymbols, "6.0");
        map.put(mib_sevenSymbols, "7.0");
        map.put(mib_eightSymbols, "8.0");
        map.put(mib_nineSymbols, "9.0");
        map.put(mib_tenSymbols, "10.0");
        map.put(mib_elevenSymbols, "11.0");
        map.put(mib_twelveSymbols, "12.0");
        map.put(mib_thirteeSymbols, "13.0");
        map.put(mib_fourteenSymbols, "14.0");
        map.put(mib_fifteenSymbols, "15.0");
        map.put(mib_sixteenSymbols, "16.0");
        
        return map;
    }

    @Override
    public String translate(String original)
    {
        return map.get(new Integer(original));
    }
    
    public static void main(String[] args)
    {
        junit.framework.Assert.assertEquals("0.5", new ConfiguredINPTranslate().translate("2"));
        System.out.println("OK");
    }
    
}
