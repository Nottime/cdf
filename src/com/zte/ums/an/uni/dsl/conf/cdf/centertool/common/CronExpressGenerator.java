package com.zte.ums.an.uni.dsl.conf.cdf.centertool.common;



/**
 * <p>�ļ�����: CronTimeGenerator.java</p>
 * <p>�ļ�����: </p>
 * <p>��Ȩ����: ��Ȩ����(C)2001-2012</p>
 * <p>��    ˾: ����ͨѶ�ɷ����޹�˾</p>
 * <p>����ժҪ: </p>
 * <p>����˵��: </p>
 * <p>������ڣ�2012-3-14</p>
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
public class CronExpressGenerator
{
    public static final int CRON_WEEKDAY_SUN = 1;
    public static final int CRON_WEEKDAY_MON = 2;
    public static final int CRON_WEEKDAY_TUE = 3;
    public static final int CRON_WEEKDAY_WED = 4;
    public static final int CRON_WEEKDAY_THU = 5;
    public static final int CRON_WEEKDAY_FRI = 6;
    public static final int CRON_WEEKDAY_SAT = 7;
    
    private String second = "*";
    private String minute = "*";
    private String hour = "*";
    private String day = "?";
    private String month = "*";
    private String weekDay = "*";

    /** ��������13:59:59���ֶ� */
    public boolean setDayTime(String dayTime, String splitStr)
    {
        if(dayTime == null)
        {
            return false;
        }
        
        if(dayTime.startsWith(splitStr) || dayTime.endsWith(splitStr))
        {
            return false;
        }
        
        String[] split = dayTime.split(splitStr);
        if(split.length != 3)
        {
            return false;
        }
        
        if(CenterToolUtil.isValidHour(split[0]) && CenterToolUtil.isValidMinute(split[1]) && CenterToolUtil.isValidSecond(split[2]))
        {
            this.hour = Integer.parseInt(split[0]) + "";
            this.minute = Integer.parseInt(split[1]) + "";
            this.second = Integer.parseInt(split[2]) + "";
            return true;
        }

        return false;
    }
    
    public boolean setWeekDay(String value)
    {
        if(CenterToolUtil.isValidWeekDay(value))
        {
            this.weekDay = value;
            return true;
        }
        
        return false;
    }
    
    public boolean setDate(String value)
    {
        if(CenterToolUtil.isValidDay(value))
        {
            this.day = value;
            this.weekDay = "?";
            return true;
        }
        
        return false;
    }

    public String getCronExpress()
    {
        return this.second + " " + this.minute + " " + this.hour + " " + this.day + " " + this.month + " " + this.weekDay;
    }
}
