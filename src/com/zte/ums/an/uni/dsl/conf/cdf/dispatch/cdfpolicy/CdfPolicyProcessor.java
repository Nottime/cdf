package com.zte.ums.an.uni.dsl.conf.cdf.dispatch.cdfpolicy;

import org.apache.log4j.Logger;

import com.zte.ums.an.uni.dsl.conf.cdf.common.LogPrint;
import com.zte.ums.an.uni.dsl.conf.cdf.common.db.CdfDbUtil;
import com.zte.ums.an.uni.dsl.conf.cdf.dispatch.CollectPoints;
import com.zte.ums.n3common.api.ZXLogger;

/**
 * <p>�ļ�����: CdfPolicyProcessor</p>
 * <p>�ļ�����: </p>
 * <p>��Ȩ����: ��Ȩ����(C)2007-2012</p>
 * <p>��    ˾: ����ͨѶ�ɷ����޹�˾</p>
 * <p>����ժҪ: </p>
 * <p>����˵��: </p>
 * <p>������ڣ�2011��11��14��</p>
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
public class CdfPolicyProcessor extends AbsCdfPolicyProcessor
{
    private Logger logger = ZXLogger.getLogger(this.getClass().getName());
    
    @Override
    public void runPolicy()
    {
        try
        {
            synchronized(CollectPoints.class)
            {
                if(CollectPoints.isEmpty())
                {
                    CollectPoints.AddNes(CdfDbUtil.getSnmpNodesListWithCP());
                    LogPrint.logInfo(logger, "Get " + CollectPoints.getUnprocessedNeCount() + " NE(s) from EMS and add to CDF task.\n");
                }
                else
                {
                    LogPrint.logInfo(logger, "Check there remains " + CollectPoints.getUnprocessedNeCount() + "NEs to collect. Do Nothing.\n");
                }
            }
        }
        catch(Exception ex)
        {
            LogPrint.logError(logger, "", ex);
        }
    }
}
