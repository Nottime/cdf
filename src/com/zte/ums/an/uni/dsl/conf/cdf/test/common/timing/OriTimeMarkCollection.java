package com.zte.ums.an.uni.dsl.conf.cdf.test.common.timing;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * <p>�ļ�����: OriTimeMarkCollection</p>
 * <p>�ļ�����: </p>
 * <p>��Ȩ����: ��Ȩ����(C)2001-2012</p>
 * <p>��    ˾: ����ͨѶ�ɷ����޹�˾</p>
 * <p>����ժҪ: </p>
 * <p>����˵��: </p>
 * <p>������ڣ�2011-12-29</p>
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
public class OriTimeMarkCollection
{
    private static OriTimeMarkCollection instance = new OriTimeMarkCollection();
    
    ArrayList<OriginalTimeMark> markList = new ArrayList<OriginalTimeMark>();
    private int round = 0;
    
    public synchronized void addMark(OriginalTimeMark timeMark)
    {
        this.markList.add(timeMark);
    }

    public static OriTimeMarkCollection getInstance()
    {
        return instance;
    }
    
    public void reInistialize()
    {
        this.round++;
        this.markList.clear();
    }

    public synchronized int size()
    {
        return markList.size();
    }
    
    public synchronized ArrayList<OriginalTimeMark> getTimeMarkList()
    {
        return markList;
    }
    
    public void mergeAndPrint(int totalNE, int failedNE)
    {
        TimeMarkMerger merger = new TimeMarkMerger(markList);
        HashMap<String, MergedTimeData> allData = merger.getMergedTimeData();

        print(allData, totalNE, failedNE);
    }

    private void print(HashMap<String, MergedTimeData> allData, int totalNE, int failedNE)
    {
        KPNTimingResultPrinter.exportResult(round, allData, totalNE, failedNE);
    }
    
    //TEST
    private static void mockTimeMark2()
    {
        createNamedTimeMark(KPNTimeMarkConst.COLLECT);
        createNamedTimeMark(KPNTimeMarkConst.COLLECT);
        createNamedTimeMark(KPNTimeMarkConst.COLLECT);
    }
    
    private static void mockTimeMark()
    {
        createNamedTimeMark(KPNTimeMarkConst.PARSE);
        createNamedTimeMark(KPNTimeMarkConst.PARSE);
        createNamedTimeMark(KPNTimeMarkConst.PARSE);
        createNamedTimeMark(KPNTimeMarkConst.COLLECT);
        createNamedTimeMark(KPNTimeMarkConst.COLLECT);
        createNamedTimeMark(KPNTimeMarkConst.COLLECT);
        createNamedTimeMark(KPNTimeMarkConst.COLLECT);
        createNamedTimeMark(KPNTimeMarkConst.DB);
        createNamedTimeMark(KPNTimeMarkConst.DB);
        createNamedTimeMark("useless");
    }
    
    private static void createNamedTimeMark(String name)
    {
        OriginalTimeMark tMark = new OriginalTimeMark(name);
        tMark.markStart();
        sleep();
        tMark.markEnd();
    }
    
    private static void sleep()
    {
        try
        {
            Thread.sleep((long)(Math.random() * 300));
        }
        catch(InterruptedException e)
        {
            e.printStackTrace();
        }
    }
    
    public static void main(String[] args)
    {
//        mockTimeMark();
//        OriTimeMarkCollection.getInstance().mergeAndPrint(2);
//        
//        OriTimeMarkCollection.getInstance().reInistialize();
//        mockTimeMark();
//        OriTimeMarkCollection.getInstance().mergeAndPrint(3);
    }
}

