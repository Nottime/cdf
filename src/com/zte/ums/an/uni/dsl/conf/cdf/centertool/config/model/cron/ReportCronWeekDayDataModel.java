package com.zte.ums.an.uni.dsl.conf.cdf.centertool.config.model.cron;

import java.util.HashMap;

import com.zte.ums.an.uni.dsl.conf.cdf.centertool.common.CenterToolUtil;
import com.zte.ums.an.uni.dsl.conf.cdf.centertool.common.CronExpressGenerator;
import com.zte.ums.an.uni.dsl.conf.cdf.centertool.common.CronExpressParser;
import com.zte.ums.an.uni.dsl.conf.cdf.report.common.ReportInfo;

/**
 * <p>�ļ�����: ReportCronWeekDayDataModel</p>
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
public class ReportCronWeekDayDataModel extends AbsCdfConfigDataModelWithSequence
{
    private String userInputWeekDay = "";
    private static final HashMap<String ,String> weekDisplayStrToNo = buildWeekDisplayStrToNo();

    public ReportCronWeekDayDataModel(ReportInfo reportInfo)
    {
        super(reportInfo);
    }
    
    private static HashMap<String, String> buildWeekDisplayStrToNo()
    {
        HashMap<String, String> weekMap = new HashMap<String, String>();
        
        for(int i = 0; i< CenterToolUtil.WEEKDAY.length; i++)
        {
            weekMap.put(CenterToolUtil.WEEKDAY[i][1], CenterToolUtil.WEEKDAY[i][0]);
        }
        
        return weekMap;
    }
    
    @Override
    public String getTitle()
    {
        return "on which day(1-SUN, 2-MON, 3-TUE, 4-WED, 5-THU, 6-FRI, 7-SAT)";
    }
        
    @Override
    public String getCurrValue()
    {
        CronExpressParser parse = new CronExpressParser(reportInfo.getScheduleCron());
        String currWeekDay = parse.getWeekDay();
        return currWeekDay == null ? "--" : currWeekDay;
    }
    
    @Override
    public boolean setNewValue(String newValue)
    {
        String weekDay;
        
        if(isNumber(newValue))
        {
            weekDay = Integer.parseInt(newValue) + "";
        }
        else
        {
            weekDay = weekDisplayStrToNo.containsKey(newValue) ? weekDisplayStrToNo.get(newValue) : "--";
        }
        
        CronExpressGenerator gen = new CronExpressGenerator();
        
        if(gen.setWeekDay(weekDay))
        {
            this.userInputWeekDay = weekDay;
            return true;
        }
        
        return false;
    }
    
    private boolean isNumber(String newValue)
    {
        try
        {
            Integer.parseInt(newValue);
            return true;
        }
        catch(NumberFormatException e)
        {
            return false;
        }
    }

    @Override
    public String getUserInput()
    {
        return userInputWeekDay;
    }
}