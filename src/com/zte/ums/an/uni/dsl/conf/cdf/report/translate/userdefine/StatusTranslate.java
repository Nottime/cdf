package com.zte.ums.an.uni.dsl.conf.cdf.report.translate.userdefine;

import com.zte.ums.an.uni.dsl.conf.cdf.common.CdfConst;
import com.zte.ums.an.uni.dsl.conf.cdf.report.common.CdfLocalize;
import com.zte.ums.an.uni.dsl.conf.cdf.report.translate.IFieldDataTranslate;

/**
 * <p>�ļ�����: StatusTranslate</p>
 * <p>�ļ�����: </p>
 * <p>��Ȩ����: ��Ȩ����(C)2001-2012</p>
 * <p>��    ˾: ����ͨѶ�ɷ����޹�˾</p>
 * <p>����ժҪ: </p>
 * <p>����˵��: </p>
 * <p>������ڣ�2012-10-29</p>
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
public class StatusTranslate implements IFieldDataTranslate
{
    private static CdfLocalize localize = new CdfLocalize();
    
    @Override
    public String translate(String value)
    {
        if(value == null)
        {
            return CdfConst.REPORT_VALUE_INVALID;
        }
        
        if("1".equals(value))
        {
            return localize.findLocalString("Enabled");
        }
        else if("2".equals(value))
        {
            return localize.findLocalString("Disabled");
        }
        
        return value;
    }
}
