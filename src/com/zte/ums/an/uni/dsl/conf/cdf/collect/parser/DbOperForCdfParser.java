package com.zte.ums.an.uni.dsl.conf.cdf.collect.parser;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

import org.apache.log4j.Logger;

import com.zte.ums.an.uni.dsl.conf.cdf.collect.parser.common.BulkPoolGroupInfo;
import com.zte.ums.an.uni.dsl.conf.cdf.collect.parser.dataprocess.DataProcess;
import com.zte.ums.an.uni.dsl.conf.cdf.collect.parser.dataprocess.fixedarea.FixedFieldCenter;
import com.zte.ums.an.uni.dsl.conf.cdf.collect.parser.dataprocess.fixedarea.IFixedAreaGetter;
import com.zte.ums.an.uni.dsl.conf.cdf.common.LogPrint;
import com.zte.ums.an.uni.dsl.conf.cdf.common.db.CdfDbUtil;
import com.zte.ums.an.uni.dsl.conf.cdf.common.db.connpool.ConnectionPool;
import com.zte.ums.an.uni.dsl.conf.cdf.common.xml.CollectDataXmlParser;
import com.zte.ums.api.common.snmpnode.ppu.entity.SnmpNode;
import com.zte.ums.n3common.api.ZXLogger;

/**
 * <p>�ļ�����: DbOperForCdfParser</p>
 * <p>�ļ�����: </p>
 * <p>��Ȩ����: ��Ȩ����(C)2001-2012</p>
 * <p>��    ˾: ����ͨѶ�ɷ����޹�˾</p>
 * <p>����ժҪ: </p>
 * <p>����˵��: </p>
 * <p>������ڣ�2012-3-5</p>
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
public class DbOperForCdfParser
{
    private SnmpNode snmpNode = null;
    private BulkPoolGroupInfo group;
    private IFixedAreaGetter[] fieldValueGetters = null;
    private Logger logger = ZXLogger.getLogger(this.getClass().getName());
    
    public DbOperForCdfParser(BulkPoolGroupInfo group, SnmpNode snmpNode) throws SecurityException, NoSuchMethodException
    {
        this.group = group;
        this.snmpNode = snmpNode;
        
        String[] fieldMethodNames = CollectDataXmlParser.getInstance().getGroupToFixedArea().get(group.getGroupName());
        fieldValueGetters = new IFixedAreaGetter[fieldMethodNames.length];
        for(int i = 0; i < fieldMethodNames.length; i++)
        {
            fieldValueGetters[i] = FixedFieldCenter.getFixedAreaGetter(fieldMethodNames[i]);
        }
    }
    
    public boolean dbOperForInsertRecords(DataProcess dataProcess)
    {
        String sql = null;
        PreparedStatement ps4Insert = null;
        Connection conn = null;

        try
        {
            ArrayList<String> firstLine = dataProcess.readNextLineParams();
            if(firstLine != null)
            {
                sql = assembleSql4Insert(group.getGroupName(), firstLine);

                conn = ConnectionPool.getInstance().getConnection("");
                ps4Insert = conn.prepareStatement(sql);

                conn.setAutoCommit(false);
                addBatchRecord(ps4Insert, firstLine, group);

                ArrayList<String> line = null;
                while((line = dataProcess.readNextLineParams()) != null)
                {
                    addBatchRecord(ps4Insert, line, group);
                }
                ps4Insert.executeBatch();
                conn.commit();
            }
            return true;
        }
        catch(Exception ex)
        {
            LogPrint.logError(logger, "", ex);
            LogPrint.logError(logger, sql);
            return false;
        }
        finally
        {
            CdfDbUtil.closeDB(ps4Insert);
            ConnectionPool.getInstance().freeConnection(conn);
        }
    }

    public String assembleSql4Insert(String dbTableName,ArrayList<String> line)
    {
        int questionMarkNumber = 2 + fieldValueGetters.length + line.size();
        StringBuffer sql = new StringBuffer();
                
        sql.append("insert into ").append(dbTableName).append(" values(");
        for(int i = 0; i < questionMarkNumber; i++)
        {
            sql.append("?,");
        }
        sql.deleteCharAt(sql.length() - 1);
        sql.append(")");

        return sql.toString();
    }

    public void addBatchRecord(PreparedStatement ps4Insert,  ArrayList<String> line, BulkPoolGroupInfo group) throws SQLException
    {
        int i = 1;
        
        //��һ���ֶι̶���񼯕r�g
        ps4Insert.setTimestamp(i++, group.getTime());
        
        //���fixedArea���ֶ�
        for(IFixedAreaGetter field : fieldValueGetters)
        {
            ps4Insert.setString(i++, field.getFieldValue(snmpNode));
        }

        //���merge�� append���ֶ�
        for(int j = 0; j < line.size(); j++)
        {
            ps4Insert.setString(i++, line.get(j));
        }
        
        //���һ���ֶι̶������ʱ��
        ps4Insert.setLong(i++, System.nanoTime());

        ps4Insert.addBatch();
    }
}
