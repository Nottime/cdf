package com.zte.ums.an.uni.dsl.conf.cdf.report.translate.simplerule;

import com.zte.ums.an.uni.dsl.conf.cdf.common.CdfConst;
import com.zte.ums.an.uni.dsl.conf.cdf.report.translate.IFieldDataTranslate;

/**
 * <p>�ļ�����: MultiTranslate</p>
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
class MultiTranslate implements IFieldDataTranslate
{
    private double multiplier;

    public MultiTranslate(double multiplier)
    {
        this.multiplier = multiplier;
    }

    @Override
    public String translate(String original)
    {
        try
        {
            return Double.toString(Long.parseLong(original) * multiplier);
        }
        catch(NumberFormatException e)
        {
        }
        
        return CdfConst.REPORT_VALUE_INVALID;
    }

}
