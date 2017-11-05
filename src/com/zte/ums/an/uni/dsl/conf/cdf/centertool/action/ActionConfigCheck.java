package com.zte.ums.an.uni.dsl.conf.cdf.centertool.action;

import com.zte.ums.an.uni.dsl.conf.cdf.centertool.IMenuAction;
import com.zte.ums.an.uni.dsl.conf.cdf.centertool.check.DbConnOfDispachCheck;
import com.zte.ums.an.uni.dsl.conf.cdf.centertool.check.DbConnForCdfCheck;
import com.zte.ums.an.uni.dsl.conf.cdf.centertool.check.FtpConnCheck;
import com.zte.ums.an.uni.dsl.conf.cdf.centertool.check.ICdfCheck;
import com.zte.ums.an.uni.dsl.conf.cdf.centertool.check.RMIConnCheck;
import com.zte.ums.an.uni.dsl.conf.cdf.centertool.check.ReportDirCheck;
import com.zte.ums.an.uni.dsl.conf.cdf.centertool.common.CdfTestResult;
import com.zte.ums.an.uni.dsl.conf.cdf.centertool.common.CenterToolUtil;

/**
 * <p>�ļ�����: ActionConfigCheck.java</p>
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
public class ActionConfigCheck implements IMenuAction
{
    private final ICdfCheck[] checkList = new ICdfCheck[] {
        new DbConnOfDispachCheck(),
        new DbConnForCdfCheck(),
        new DbConnForCdfCheck(),
        new FtpConnCheck(),
        new RMIConnCheck(),
        new ReportDirCheck()
    };
    
    @Override
    public void doAction()
    {
        if(CenterToolUtil.isCDFRunning())
        {
            System.out.println(CenterToolUtil.getSvrRunningDiscripStr() + " Please shutdown CDF first before running test.");
            return;
        }
        else
        {
            doCheck();
        }
    }

    private void doCheck()
    {
        CdfTestResult[] results = new CdfTestResult[checkList.length];
        
        for(int i = 0; i < this.checkList.length; i++)
        {
            this.checkList[i].presentTitle();
            results[i] = doCheck(this.checkList[i]);
            this.checkList[i].presentEnd();
            System.out.println();
        }
        
        afterFinish(results);
    }
    
    public static CdfTestResult doCheck(ICdfCheck check)
    {
        CdfTestResult result = check.doCheck();
        presentError(result);
        return result;
    }

    private void afterFinish(CdfTestResult[] results)
    {
        if(isAllSuccess(results))
        {
            System.out.println("Congratulations!!! All the tests passed.");
        }
        else
        {
            System.out.println("Failure exists during the test. See result details above and re-configure again.");
        }
    }

    private boolean isAllSuccess(CdfTestResult[] results)
    {
        for(CdfTestResult result : results)
        {
            if(!result.isSuccess)
            {
                return false;
            }
        }
        
        return true;
    }

    private static void presentError(CdfTestResult result)
    {
        if(!result.isSuccess)
        {
            System.out.println("FAIL!!!!!!!!!!!!!!!!!!!!");
            System.out.println("ERROR: " + result.detailStr);
        }
    }
}
