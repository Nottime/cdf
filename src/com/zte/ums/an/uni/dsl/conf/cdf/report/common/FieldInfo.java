package com.zte.ums.an.uni.dsl.conf.cdf.report.common;

import com.zte.ums.an.uni.dsl.conf.cdf.report.translate.IFieldDataTranslate;
import com.zte.ums.an.uni.dsl.conf.cdf.report.translate.TranslateDirector;

/**
 * <p>�ļ�����: FieldFormatInfo.java</p>
 * <p>�ļ�����: </p>
 * <p>��Ȩ����: ��Ȩ����(C)2001-2012</p>
 * <p>��    ˾: ����ͨѶ�ɷ����޹�˾</p>
 * <p>����ժҪ: </p>
 * <p>����˵��: </p>
 * <p>������ڣ�2012-3-1</p>
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
public class FieldInfo
{
    private final String fieldName;
    private final boolean isFromDb;
    private final String translateFunctionName;//����Ϊ��
    private final String i18n;
    
    private IFieldDataTranslate translateFunc;
    
    public FieldInfo(String i18n, String fieldName, boolean isFromDb, String translateFunctionName)
    {
        this.i18n = i18n;
        this.fieldName = fieldName;
        this.isFromDb = isFromDb;
        this.translateFunctionName = translateFunctionName;
        
        translateFunc = TranslateDirector.getTranslateInstance(translateFunctionName);
    }
    
    public String getI18n()
    {
        return i18n;
    }
    
    public String translate(String oriData)
    {
        return translateFunc.translate(oriData);
    }
    
    public String getFieldName()
    {
        return fieldName;
    }
    public boolean isFromDB()
    {
        return isFromDb;
    }
    public String getTranslate()
    {
        return translateFunctionName;
    }

    @Override
    public String toString()
    {
        return "FieldInfo [fieldName=" + fieldName + ", isFromDb=" + isFromDb + ", translateFunctionName=" + translateFunctionName + ", i18n="
               + i18n + ", translateFunc=" + translateFunc + "]";
    }
}
