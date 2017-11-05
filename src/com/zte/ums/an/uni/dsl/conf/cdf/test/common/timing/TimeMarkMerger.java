package com.zte.ums.an.uni.dsl.conf.cdf.test.common.timing;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.TreeSet;

import com.zte.ums.an.uni.dsl.conf.cdf.test.common.CdfMockUtil;

/**
 * <p>�ļ�����: TimeMarkMerger.java</p>
 * <p>�ļ�����: </p>
 * <p>��Ȩ����: ��Ȩ����(C)2001-2012</p>
 * <p>��    ˾: ����ͨѶ�ɷ����޹�˾</p>
 * <p>����ժҪ: </p>
 * <p>����˵��: </p>
 * <p>������ڣ�2011-12-30</p>
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
public class TimeMarkMerger
{
    HashMap<String, MergedTimeData> map = new HashMap<String, MergedTimeData>(); 
    
    public TimeMarkMerger(ArrayList<OriginalTimeMark> markList)
    {
        HashMap<String, TreeSet<Float>> mapTimeCost = markNameToTimeCost(markList);
        
        buildMap(mapTimeCost);
    }
    
    private void buildMap(HashMap<String, TreeSet<Float>> mapTimeCost)
    {
        Iterator<Entry<String, TreeSet<Float>>> it = mapTimeCost.entrySet().iterator();
        
        while(it.hasNext())
        {
            Entry<String, TreeSet<Float>> entry = it.next();
            
            String markName = entry.getKey();
            TreeSet<Float> timeCostSet = entry.getValue();
            
            MergedTimeData data = assembleMergedData(timeCostSet, markName);
            
            map.put(markName, data);
        }
    }

    private MergedTimeData assembleMergedData(TreeSet<Float> timeCostSet, String markName)
    {
        MergedTimeData data = new MergedTimeData();
        data.setMarkName(markName);
        data.setMin(CdfMockUtil.formatFloat(timeCostSet.first()));
        data.setMax(CdfMockUtil.formatFloat(timeCostSet.last()));
        data.setAverage(CdfMockUtil.formatFloat(averageValue(timeCostSet)));
        
        return data;
    }
    
    private float averageValue(TreeSet<Float> timeCostSet)
    {
        float sum = 0;
        Iterator<Float> it = timeCostSet.iterator();
        while(it.hasNext())
        {
            sum += it.next().floatValue();
        }
        
        return sum/timeCostSet.size();
    }

    private HashMap<String, TreeSet<Float>> markNameToTimeCost(ArrayList<OriginalTimeMark> markList)
    {
       HashMap<String, TreeSet<Float>> markNameToTimeCost = new HashMap<String, TreeSet<Float>>();
       
       for(OriginalTimeMark o : markList)
       {
           String markName = o.getMark();
           
           if(!markNameToTimeCost.containsKey(markName))
           {
               markNameToTimeCost.put(markName, new TreeSet<Float>());
           }
           
           float timeCost = ((float)(o.getEndTime() - o.getStartTime()))/1000;
           markNameToTimeCost.get(markName).add(timeCost);
       }
        
       return markNameToTimeCost;
    }

    public HashMap<String, MergedTimeData> getMergedTimeData()
    {
        return map;
    }
}
