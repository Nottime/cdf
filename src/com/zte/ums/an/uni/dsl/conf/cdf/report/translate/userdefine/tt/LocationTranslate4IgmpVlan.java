package com.zte.ums.an.uni.dsl.conf.cdf.report.translate.userdefine.tt;

import com.zte.ums.an.uni.dsl.conf.cdf.common.CdfConst;
import com.zte.ums.an.uni.dsl.conf.cdf.report.translate.IFieldDataTranslate;

/**
 * <p>�ļ�����: LocationTranslate4IgmpVlan.java</p>
 * <p>�ļ�����: </p>
 * <p>��Ȩ����: ��Ȩ����(C)2001-2012</p>
 * <p>��    ˾: ����ͨѶ�ɷ����޹�˾</p>
 * <p>����ժҪ: </p>
 * <p>����˵��: </p>
 * <p>������ڣ�2012-6-7</p>
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
public class LocationTranslate4IgmpVlan implements IFieldDataTranslate
{
    /** ת������ 1/1/1-> Rack:1/Shelf:1/Slot:1 */
    @Override
    public String translate(String original)
    {
        String[] split = original.split("/");

        if(CdfConst.LOCATION_LENGTH_IGMP == split.length)
        {
            return "MVLAN:" + split[0];
        }

        return original;
    }

}
