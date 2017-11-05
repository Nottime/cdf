package com.zte.ums.an.uni.dsl.conf.cdf.report.translate.userdefine;

import com.zte.ums.an.uni.dsl.conf.cdf.common.CdfConst;
import com.zte.ums.an.uni.dsl.conf.cdf.report.common.CdfLocalize;
import com.zte.ums.an.uni.dsl.conf.cdf.report.translate.IFieldDataTranslate;
import com.zte.ums.n3common.api.BitMap;

/**
 * <p>�ļ�����: VectorConfModeTranslate</p>
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
public class VectorConfModeTranslate implements IFieldDataTranslate
{
    private static CdfLocalize localize = new CdfLocalize();
    
    @Override
    public String translate(String bitsValue)
    {
        if(bitsValue == null)
        {
            return CdfConst.REPORT_VALUE_INVALID;
        }
        
        byte[] bytesValue = toArrayByte(bitsValue);
        return getVectorConfMode(XDSL2TransMode.getAnnexFromBitMap(new BitMap(bytesValue)));
    }

    private String getVectorConfMode(String[] annexes)
    {
        for(String annex : annexes)
        {
            if(isG9935(annex))
            {
                return localize.findLocalString("vector_enabled");
            }
        }
        
        return localize.findLocalString("vector_disabled");
    }

    private boolean isG9935(String annex)
    {
        return "62".equals(annex);
    }
    
    private static byte[] toArrayByte(String bitsValue)
    {        
        if((bitsValue.length() % 2) == 1)
        {
//            LogPrint.logError(logger, "BitsDescSeriesTranslate :length of " + bitsValue + " must be divided to 2.");
            return new byte[0];
        }
        
        byte[] rtnBytes = new byte[bitsValue.length() / 2];
        
        for(int i = 0; i < rtnBytes.length; i++)
        {
            String strValue = bitsValue.substring(2 * i, 2 * i + 2);
            rtnBytes[i] = (byte)(Integer.parseInt(strValue, 16));
        }
        
        return rtnBytes;
    }
}
