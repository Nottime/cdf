package com.zte.ums.an.uni.dsl.conf.cdf.report.translate.userdefine.tt;

import com.zte.ums.an.uni.dsl.conf.cdf.common.CdfConst;
import com.zte.ums.an.uni.dsl.conf.cdf.report.translate.IFieldDataTranslate;

/**
 * <p>�ļ�����: LocationTranslateCommon.java</p>
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
public class LocationTranslateCommon implements IFieldDataTranslate
{
    /** ת������ 1/1/1-> Rack:1/Shelf:1/Slot:1 */
    @Override
    public String translate(String original)
    {
        String[] split = original.split("/");
        int size = split.length;

        switch(size)
        {
            case CdfConst.LOCATION_LENGTH_BOARD:
                return "Rack:" + split[0] + "/Shelf:" + split[1] + "/Slot:" + split[2];

            case CdfConst.LOCATION_LENGTH_XDSL:
                return "Rack:" + split[0] + "/Shelf:" + split[1] + "/Slot:" + split[2] + "/Port:" + split[3];

            case CdfConst.LOCATION_LENGTH_IGMP:
                return "Rack:" + split[1] + "/Shelf:" + split[2] + "/Slot:" + split[3] + "/Port:" + split[4];

            case CdfConst.LOCATION_LENGTH_VDSLPORTTEX:
                return "UPDWON:" + split[0] + "/Rack:" + split[1] + "/Shelf:" + split[2] + "/Slot:" + split[3] + "/Port:" + split[4];

            default:
                return original;
        }
    }

}
