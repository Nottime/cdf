package com.zte.ums.an.uni.dsl.conf.cdf.report.translate.userdefine.tt;

import com.zte.ums.an.uni.dsl.conf.cdf.common.CdfConst;
import com.zte.ums.an.uni.dsl.conf.cdf.report.translate.IFieldDataTranslate;

/**
 * <p>文件名称: LocationTranslate4IgmpVlan.java</p>
 * <p>文件描述: </p>
 * <p>版权所有: 版权所有(C)2001-2012</p>
 * <p>公    司: 中兴通讯股份有限公司</p>
 * <p>内容摘要: </p>
 * <p>其他说明: </p>
 * <p>完成日期：2012-6-7</p>
 * <p>修改记录1:</p>
 * <pre>
 *    修改日期：
 *    版 本 号：
 *    修 改 人：
 *    修改内容：
 * </pre>
 * <p>修改记录2：</p>
 * @version 1.0
 * @author ljy
 */
public class LocationTranslate4IgmpVlan implements IFieldDataTranslate
{
    /** 转换形如 1/1/1-> Rack:1/Shelf:1/Slot:1 */
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
