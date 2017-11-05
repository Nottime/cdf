package com.zte.ums.an.uni.dsl.conf.cdf.dispatch;

import org.apache.log4j.Logger;

import com.zte.ums.an.uni.dsl.conf.cdf.common.LogPrint;
import com.zte.ums.an.uni.dsl.conf.cdf.dispatch.cdfpolicy.AbsCdfPolicyProcessor;
import com.zte.ums.n3common.api.ZXLogger;

/**
 * <p>�ļ�����: CdfPolicy.java</p>
 * <p>�ļ�����: </p>
 * <p>��Ȩ����: ��Ȩ����(C)2007-2010</p>
 * <p>��    ˾: ����ͨѶ�ɷ����޹�˾</p>
 * <p>����ժҪ: </p>
 * <p>����˵��: </p>
 * <p>������ڣ�2011��9��1��</p>
 * <p>�޸ļ�¼1:</p>
 * <pre>
 *    �޸����ڣ�
 *    �� �� �ţ�
 *    �� �� �ˣ�
 *    �޸����ݣ�
 * </pre>
 * <p>�޸ļ�¼2��</p>
 * @version 1.0
 * @author lixiaochun
 */
public class CdfPolicy implements Runnable
{
    private Logger logger = ZXLogger.getLogger(this.getClass().getName());
    
    AbsCdfPolicyProcessor policy = null;
    
    public CdfPolicy()
    {
        this.policy = DispatchFactory.createCdfPolicyProc();
    }
    
    //****** �����: �̷߳��� **********************************************************************/

    public void run()
    {
        try
        {
            this.policy.runPolicy();
        }
        catch(Exception ex)
        {
            LogPrint.logError(logger, "", ex);
        }
    }

}
