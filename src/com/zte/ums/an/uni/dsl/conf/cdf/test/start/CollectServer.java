package com.zte.ums.an.uni.dsl.conf.cdf.test.start;

import com.zte.ums.an.uni.dsl.conf.cdf.collect.listener.ListenerCollection;
import com.zte.ums.an.uni.dsl.conf.cdf.test.massivecollect.imp.MassiveCollectorListener;

/**
 * <p>�ļ�����: DispatchServer.java</p>
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
public class CollectServer
{
    public static void main(String[] args)
    {
//        CdfMockUtil.setUserDirToParentPath();
        ListenerCollection.getInstance().addListener(new MassiveCollectorListener());

        com.zte.ums.an.uni.dsl.conf.cdf.collect.CollectServer.main(args);
    }
}
