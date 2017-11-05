package com.zte.ums.an.uni.dsl.conf.cdf.dispatch;

import java.util.ArrayList;
import java.util.Vector;

import com.zte.ums.api.common.snmpnode.ppu.entity.SnmpNode;

/**
 * <p>�ļ�����: CollectPoints.java</p>
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
public class CollectPoints
{
    /** ���ɼ���Ԫ���� */
    private static Vector snmpNodeList = new Vector();
    
    public synchronized static void AddNes(ArrayList<SnmpNode> nes)
    {
        snmpNodeList.addAll(nes);
    }

    /** ��ȡǰN�����ɼ���Ԫ�б� */
    public synchronized static Vector getNes(int stepLength)
    {
        Vector ret = new Vector();

        int len = snmpNodeList.size() > stepLength ? stepLength : snmpNodeList.size();

        for(int i = 0; i < len; i++)
        {
            ret.add(snmpNodeList.get(i));
        }
        for(int i = 0; i < len; i++)
        {
            snmpNodeList.remove(0);
        }
        return ret;
    }

    public synchronized static boolean isEmpty()
    {
        return snmpNodeList.isEmpty();
    }

    /** ��ȡ���ɼ���Ԫ���� */
    public synchronized static int getUnprocessedNeCount()
    {
        return snmpNodeList.size();
    }
}
