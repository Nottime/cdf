package com.zte.ums.an.uni.dsl.conf.cdf.test.common;

import java.io.File;

/**
 * <p>�ļ�����: CdfMockUtil.java</p>
 * <p>�ļ�����: </p>
 * <p>��Ȩ����: ��Ȩ����(C)2001-2012</p>
 * <p>��    ˾: ����ͨѶ�ɷ����޹�˾</p>
 * <p>����ժҪ: </p>
 * <p>����˵��: </p>
 * <p>������ڣ�2011-12-28</p>
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
public class CdfMockUtil
{
    private CdfMockUtil()
    {
    }
    
    public static void setUserDirToParentPath()
    {
        String userDir = System.getProperty("user.dir");
        userDir = (new File(userDir)).getParent();
        System.setProperty("user.dir", userDir);
    }
    
    public static float formatFloat(float oldFloat)
    {
        return (float)((int)(oldFloat * 100))/100;
    }
    
    public static float caculatePercent(int i1, int i2)
    {
        return ((float)i1/i2) * 100;
    }
}
