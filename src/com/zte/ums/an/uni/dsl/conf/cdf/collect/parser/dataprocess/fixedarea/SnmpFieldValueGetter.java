package com.zte.ums.an.uni.dsl.conf.cdf.collect.parser.dataprocess.fixedarea;

import java.lang.reflect.Method;

import org.apache.log4j.Logger;

import com.zte.ums.an.uni.dsl.conf.cdf.common.CdfConst;
import com.zte.ums.an.uni.dsl.conf.cdf.common.LogPrint;
import com.zte.ums.api.common.snmpnode.ppu.entity.SnmpNode;
import com.zte.ums.n3common.api.ZXLogger;

/**
 * <p>�ļ�����: SnmpFieldValueGetter.java</p>
 * <p>�ļ�����: </p>
 * <p>��Ȩ����: ��Ȩ����(C)2001-2012</p>
 * <p>��    ˾: ����ͨѶ�ɷ����޹�˾</p>
 * <p>����ժҪ: </p>
 * <p>����˵��: </p>
 * <p>������ڣ�2012-3-5</p>
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
public class SnmpFieldValueGetter implements IFixedAreaGetter
{
    private Logger logger = ZXLogger.getLogger(this.getClass().getName());
    private Method myMethod;
    
    public SnmpFieldValueGetter(String methodName) throws SecurityException, NoSuchMethodException
    {
        myMethod = SnmpNode.class.getMethod(methodName);
    }
    
    @Override
    public String getFieldValue(SnmpNode snmpNode)
    {
        if(myMethod != null)
        {
            try
            {
                return (String)(myMethod.invoke(snmpNode));
            }
            catch(Exception e)
            {
                LogPrint.logError(logger, "", e);
            }
        }
        
        return CdfConst.REPORT_PROGRAM_ERROR;
    }
    
}
