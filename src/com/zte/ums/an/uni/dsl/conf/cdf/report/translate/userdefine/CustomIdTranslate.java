package com.zte.ums.an.uni.dsl.conf.cdf.report.translate.userdefine;

import com.zte.ums.an.uni.dsl.conf.cdf.report.translate.IFieldDataTranslate;

/**
 * <p>�ļ�����: CustomIdTranslate.java</p>
 * <p>�ļ�����: </p>
 * <p>��Ȩ����: ��Ȩ����(C)2001-2013</p>
 * <p>��    ˾: ����ͨѶ�ɷ����޹�˾</p>
 * <p>����ժҪ: </p>
 * <p>����˵��: </p>
 * <p>������ڣ�2012-12-10</p>
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
public class CustomIdTranslate implements IFieldDataTranslate
{
    @Override
    public String translate(String ifAlias)
    {
        return quoteMark(null == ifAlias ? "" : ifAlias);
    }
    
    private static String quoteMark(String str)
    {
        return "\"" + str + "\"";
    }

    public static void main(String[] args)
    {
        CustomIdTranslate translate = new CustomIdTranslate();
        System.out.println(translate.translate("ddd"));
        System.out.println(translate.translate(null));
        System.out.println(translate.translate(""));
        System.out.println(translate.translate("dfs\"fd"));
    }
}
