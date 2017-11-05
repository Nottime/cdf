package com.zte.ums.an.uni.dsl.conf.cdf.centertool.action;

import com.zte.ums.an.uni.dsl.conf.cdf.centertool.IMenuAction;
import com.zte.ums.an.uni.dsl.conf.cdf.centertool.common.CenterToolUtil;

/**
 * <p>�ļ�����: ActionShutDown.java</p>
 * <p>�ļ�����: </p>
 * <p>��Ȩ����: ��Ȩ����(C)2001-2012</p>
 * <p>��    ˾: ����ͨѶ�ɷ����޹�˾</p>
 * <p>����ժҪ: </p>
 * <p>����˵��: </p>
 * <p>������ڣ�2012-3-13</p>
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
public class ActionShutDown implements IMenuAction
{
    @Override
    public void doAction()
    {
        shutdownCDF();
        System.out.println("CDF which is run by current user has been stopped.");
    }
    
    private void shutdownCDF()
    {
        CenterToolUtil.resetDir();
        
        if(!CenterToolUtil.isWindowsOS())
        {
            if(CenterToolUtil.isLinux())
            {
                CenterToolUtil.runProcess("sh conf/QuickRemoteStop_RedHat.sh");
            }
            else
            {
                CenterToolUtil.runProcess("sh conf/QuickRemoteStop.sh");
            }
        }
        
    }
}
