package com.zte.ums.an.uni.dsl.conf.cdf.report.translate;

import org.apache.log4j.Logger;

import com.zte.ums.an.uni.dsl.conf.cdf.common.LogPrint;
import com.zte.ums.an.uni.dsl.conf.cdf.report.translate.simplerule.SimpleRuleTranslateCenter;
import com.zte.ums.n3common.api.ZXLogger;


/**
 * <p>�ļ�����: TranslateDirector.java</p>
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
public class TranslateDirector
{
    private static Logger logger = ZXLogger.getLogger(TranslateDirector.class.getName());
    
    private TranslateDirector()
    {
    }
    
    public static IFieldDataTranslate getTranslateInstance(String translateFunctionName)
    {
        if(translateFunctionName == null)
        {
            return SimpleRuleTranslateCenter.getDefaultTranslateInstance();
        }
            
        Class<?> transClass = loadDataTranslateClass(translateFunctionName);
        
        if(transClass != null)
        { 
            return translateByUserDefineClass(transClass);
            
        }
        
        return SimpleRuleTranslateCenter.getTranslateInstance(translateFunctionName);
    }

    private static IFieldDataTranslate translateByUserDefineClass(Class<?> transClass)
    {
        try
        {
            return (IFieldDataTranslate)transClass.newInstance();
        }
        catch(Exception e)
        {
            LogPrint.logError(logger, "", e);
            return SimpleRuleTranslateCenter.getDefaultTranslateInstance();
        }
    }
    
    private static Class<?> loadDataTranslateClass(String className)
    {
        Class<?> myClass = null;
        try
        {
            myClass = ClassLoader.getSystemClassLoader().loadClass(className);
        }
        catch(ClassNotFoundException e)
        {
            return null;
        }
        
        if(!IFieldDataTranslate.class.isAssignableFrom(myClass))
        {
            throw new IllegalArgumentException("The translate class must be assignable from " + IFieldDataTranslate.class.getSimpleName());
        }
        
        return myClass;
    }
    
    public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException
    {
//        String s = "com.zte.ums.an.uni.dsl.conf.cdf.report.translate.SimpleFieldTranslate";
//        Class<?> myClass = ClassLoader.getSystemClassLoader().loadClass(s);
//        
//        System.out.println("className:" + myClass.getName());
//        
//        Class<?>[] interfaces = myClass.getInterfaces();
//        for(Class<?> cls : interfaces)
//        {
//            System.out.println("interface:" + cls.getName());
//        }
//        
//        Class<?> superClass = myClass.getSuperclass();
//        System.out.println("superClass:" + superClass.getName());
//        
//        System.out.println(IFieldDataTranslate.class.isAssignableFrom(myClass));
//        
//        System.out.println(((IFieldDataTranslate)(myClass.newInstance())).translate());
        
        loadDataTranslateClass("com.zte.ums.an.uni.dsl.conf.cdf.report.translate.SimpleFieldTranslate");
    }
}
