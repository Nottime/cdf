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
public class ActionStartup implements IMenuAction
{
    private static final boolean isWindowsOS = CenterToolUtil.isWindowsOS();
    
    @Override
    public void doAction()
    {
        if(CenterToolUtil.isCDFRunning())
        {
            System.out.println(CenterToolUtil.getSvrRunningDiscripStr() + " Please shutdown CDF manually.");
            return;
        }
            
        startCDF();
        afterStart();
        System.exit(0);
    }

    private void afterStart()
    {      
        if(!isWindowsOS)
        {
            if(CenterToolUtil.isAllCDFRunning())
            {
                System.out.println("CDF system(Dispatch, Sub-Collect and Report Server) has started.");
            }
            else
            {
                System.out.println("Cannot start " + CenterToolUtil.getNotRunningSvrStr() + ".");
            }

            System.out.println("See logs in 'remoteDispatchServer.log', "
                               + "'remoteSubCollectServer.log' and 'remoteReportServer.log' for the CDF system.");
        }
    }

    private void startCDF()
    {
        CenterToolUtil.resetDir();
        CenterToolUtil.runProcess(getCDFScript());
    }
    
    private String getCDFScript()
    {
        if(isWindowsOS)
        {
            return ".\\conf\\QuickStart.bat";
        }
        else
        {
            return "sh conf/QuickRemoteStart.sh";
        }
    }
}
