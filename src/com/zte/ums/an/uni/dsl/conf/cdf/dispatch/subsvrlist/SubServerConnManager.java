package com.zte.ums.an.uni.dsl.conf.cdf.dispatch.subsvrlist;

import java.util.ArrayList;

import org.apache.log4j.Logger;

import com.zte.ums.an.uni.dsl.conf.cdf.common.LogPrint;
import com.zte.ums.an.uni.dsl.conf.cdf.common.SubCollectInfo;
import com.zte.ums.an.uni.dsl.conf.cdf.common.xml.DispatchXmlParser;
import com.zte.ums.n3common.api.ZXLogger;

/**
 * <p>�ļ�����: SubServerConnManager</p>
 * <p>�ļ�����: </p>
 * <p>��Ȩ����: ��Ȩ����(C)2007-2012</p>
 * <p>��    ˾: ����ͨѶ�ɷ����޹�˾</p>
 * <p>����ժҪ: </p>
 * <p>����˵��: </p>
 * <p>������ڣ�2011��12��01��</p>
 * <p>�޸ļ�¼1:</p>
 * <pre>
 *    �޸����ڣ�
 *    �� �� �ţ�
 *    �� �� �ˣ�
 *    �޸����ݣ�
 * </pre>
 * <p>�޸ļ�¼2��</p>
 * @version 1.0
 * @author jingxueshi_10118495
 */

public class SubServerConnManager extends Thread
{
  //****** �����: �������� *******************************************************************************/

    private Logger logger = ZXLogger.getLogger(this.getClass().getName());
    
    private ArrayList<SubCollectInfo> subserverList = new ArrayList<SubCollectInfo>();
    private static SubServerConnManager instance = new SubServerConnManager();
    private static final int expiredTime = Integer.parseInt(DispatchXmlParser.getInstance().getSubServerTimeout());
    

    private SubServerConnManager()
    {
    }

    public static SubServerConnManager getInstance()
    {
        return instance;
    }
    
  //****** �����:�������� *******************************************************************************/
  
    @Override
    public void run()
    {
        LogPrint.logInfo(logger, "Periodically checking: total " + subserverList.size() + " alive subserver(s).");
        
        try
        {
            removeExpiredSubservers();
        }
        catch(Exception e)
        {
            LogPrint.logError(logger, "", e);
        }
    }
    
    public synchronized void updateSubServerList(SubCollectInfo subServer)
    {
        subServer.hearBeatTime = System.currentTimeMillis();
        
        int size = this.subserverList.size();
        for(int i = 0; i < this.subserverList.size(); i++)
        {
            SubCollectInfo tmpInfo = subserverList.get(i);
            if(tmpInfo.equals(subServer))
            {
                subserverList.set(i, subServer);
                LogPrint.logDebug(logger, "update subServer " + subServer.getName());
                return;
            }
        }

        subserverList.add(size, subServer);
        LogPrint.logInfo(logger, "The subServer :" + subServer.getName() + "has connected to this dispach server.\n");
    }
    
    public synchronized ArrayList<SubCollectInfo> getSubServerList()
    {
        return this.subserverList;
    }

    private synchronized void removeExpiredSubservers()
    {
        for(int i = subserverList.size() - 1; i >= 0; i--)
        {
            long now = System.currentTimeMillis();
            long recorded = subserverList.get(i).hearBeatTime;
            long inactivity = now - recorded;

            LogPrint.logDebug(logger, subserverList.get(i).getName()  + " inactivity == " + inactivity);

            if(isExpired(expiredTime, inactivity))
            {
                LogPrint.logInfo(logger, subserverList.get(i).getName() + " has lost its connection.");
                subserverList.remove(i);
            }
        }
    }

    private static boolean isExpired(int expiredTime, long inactivity)
    {
        return inactivity > expiredTime * 1000;
    }
    
    private static SubCollectInfo mockSubCollectInfo(String alias, int hearBeatTime, String ipAddr)
    {
        SubCollectInfo subCollectInfo1 = new SubCollectInfo();
        subCollectInfo1.alias = alias;
        subCollectInfo1.hearBeatTime = hearBeatTime;
        subCollectInfo1.ipAddr = ipAddr;
        return subCollectInfo1;
    }
    
    public static void main(String[] args)
    {
        SubCollectInfo subCollectInfo1 = mockSubCollectInfo("sh_no1", 10, "1.1.1.1");
        SubCollectInfo subCollectInfo2 = mockSubCollectInfo("sh_no2", 20, "2.2.2.2");
        ArrayList<SubCollectInfo> subserverList = SubServerConnManager.getInstance().getSubServerList();
        subserverList.add(subCollectInfo1);
        subserverList.add(subCollectInfo2);
        
        System.out.println("default : ");
        System.out.println(subserverList);
        
        //test add
        SubCollectInfo subCollectInfo3 = mockSubCollectInfo("sh_no3", 30, "3.3.3.3");
        SubServerConnManager.getInstance().updateSubServerList(subCollectInfo3);
        System.out.println("after add : ");
        System.out.println(subserverList);
        
        //test update
        SubCollectInfo testSubServer = mockSubCollectInfo("sh_no4", 40, "2.2.2.2");
        
        SubServerConnManager.getInstance().updateSubServerList(testSubServer);
        System.out.println("after update : ");
        System.out.println(subserverList);
        
        //test remove
        SubServerConnManager.getInstance().run();
        System.out.println("after remove : ");
        System.out.println(subserverList);
    }

}
