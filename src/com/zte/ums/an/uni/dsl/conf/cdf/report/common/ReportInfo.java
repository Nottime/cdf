package com.zte.ums.an.uni.dsl.conf.cdf.report.common;

import java.util.Arrays;
import java.util.HashMap;

/**
 * <p>�ļ�����: ReportInfo.java</p>
 * <p>�ļ�����: </p>
 * <p>��Ȩ����: ��Ȩ����(C)2001-2012</p>
 * <p>��    ˾: ����ͨѶ�ɷ����޹�˾</p>
 * <p>����ժҪ: </p>
 * <p>����˵��: </p>
 * <p>������ڣ�2012-3-1</p>
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
public class ReportInfo
{
    private final String name;
    private final String scheduleCron;
    private final String[] dbTables;
    
    private boolean isRunning = false;
    
    private HashMap<String, FieldInfo[]> tableToFields;//������Ϊ�� //TODO
    
    public ReportInfo(String name, String scheduleCron, String[] dbTables)
    {
        this.name = name;
        this.scheduleCron = scheduleCron;
        this.dbTables = dbTables;
        this.tableToFields = new HashMap<String, FieldInfo[]>();
    }
    
    public String getName()
    {
        return name;
    }
    
    public String getScheduleCron()
    {
        return scheduleCron;
    }
    
    public String[] getDbTables()
    {
        return dbTables;
    }
    
    public FieldInfo[] getFields(String belongedTable)
    {
        return this.tableToFields.get(belongedTable);
    }

    public void addFieldsForSingleDBTable(FieldInfo[] fields, String belongedTable)
    {
        this.tableToFields.put(belongedTable, fields);
    }
    
    public boolean isRunning()
    {
        return isRunning;
    }

    public void setRunning(boolean isRunning)
    {
        this.isRunning = isRunning;
    }

    @Override
    public String toString()
    {
        StringBuffer sb = new StringBuffer();

        sb.append("  name           = " + this.name + "\n");
        sb.append("  scheduleCron   = " + this.scheduleCron + "\n");
        sb.append("  dbTables       = " + Arrays.toString(dbTables) + "\n");
        sb.append("  isRunning      = " + this.isRunning + "\n");
        sb.append(toStringForTable());
  
        return sb.toString();
    }
    
    private String toStringForTable()
    {
        StringBuffer sb = new StringBuffer("   tables     =[\n");
        for(String table : dbTables)
        {
            sb.append("  table = " + table + ", fields = " + Arrays.toString(tableToFields.get(table)) + "\n");
        }
        
        sb.delete(sb.length() - 1, sb.length()).append("]");
        
        return sb.toString();
    }
}
