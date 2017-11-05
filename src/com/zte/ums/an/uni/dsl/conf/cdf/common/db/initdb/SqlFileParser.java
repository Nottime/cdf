package com.zte.ums.an.uni.dsl.conf.cdf.common.db.initdb;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Properties;

/**
 * <p>�ļ�����: SqlFileParser.java</p>
 * <p>�ļ�����: </p>
 * <p>��Ȩ����: ��Ȩ����(C)2001-2010</p>
 * <p>��    ˾: ����ͨѶ�ɷ����޹�˾</p>
 * <p>����ժҪ: </p>
 * <p>����˵��: </p>
 * <p>������ڣ�2010��7��6��</p>
 * <p>�޸ļ�¼1:</p>
 * <pre>
 *    �޸����ڣ�
 *    �� �� �ţ�
 *    �� �� �ˣ�
 *    �޸����ݣ�
 * </pre>
 * <p>�޸ļ�¼2��</p>
 * @version 1.0
 * @author  Chenduoduo_10087118
 */
public class SqlFileParser
{
    private Properties p = null;
    
    private static final String START_PATTEN = "%$";
    private static final String END_PATTEN = "$%";

    private String filename;
    private BufferedReader reader;
    
    private String[] allStatements;
    private int currStatementIndex = 0;
    
    public SqlFileParser(String filename, Properties p) throws Exception
    {
        this.filename = filename;
        this.p = p;
        if(null == this.p)
        {
            this.p = new Properties();
        }
        
        openFile();
        
        try
        {
            parseFile();
        }
        catch(Exception e)
        {
            throw new Exception(e.getMessage() + " of File:" + filename);
        }
    }
    
    public String nextStatement()
    {
        if(currStatementIndex == allStatements.length)
        {
            return null;
        }
        
        return allStatements[currStatementIndex++];
    }

    private void parseFile() throws Exception
    {
        ArrayList<String> statements = new ArrayList<String>();
        String statement;
        while((statement = getNextStatement()) != null)
        {
            statements.add(statement);
        }
        
        allStatements = statements.toArray(new String[0]);
    }

    private String getNextStatement() throws Exception
    {
        StringBuffer sqlStatement = new StringBuffer();
        String line;

        while((line = this.reader.readLine()) != null)
        {
            line = parseSqlLine(line);

            if(line.endsWith("/"))
            {
                return sqlStatement.toString().trim();
            }
            sqlStatement.append(line + " ");
        }
        String statement = sqlStatement.toString();
        if(statement == null || statement.trim().equals(""))
        {
            return null;
        }
        else
        {
            throw new Exception("Error - unexpected EOF");
        }
    }
    
    
    private String parseSqlLine(String sqlLine) throws Exception
    {
        sqlLine = sqlLine.trim();
        int indexPattenStart = sqlLine.indexOf(START_PATTEN);
        int indexPattenEnd = sqlLine.indexOf(END_PATTEN);
        
        if(indexPattenStart == -1 && indexPattenEnd == -1)
        {
            return sqlLine;
        }
        else if(indexPattenStart != -1 && indexPattenEnd > indexPattenStart)
        {
            String toBeReplaced = sqlLine.substring(indexPattenStart + START_PATTEN.length(), indexPattenEnd);

            return sqlLine.substring(0, indexPattenStart) + getReplacedStr(toBeReplaced) + sqlLine.substring(indexPattenEnd + END_PATTEN.length());
        }
        else
        {
            throw new Exception("Invalid %$ or $% in line \"" + sqlLine + "\"");
        }
    }

    private String getReplacedStr(String toBeReplaced) throws Exception
    {
        if (p.containsKey(toBeReplaced))
        {
            return (String)p.get(toBeReplaced);
        }
        
        throw new Exception("Cannot find parameter " + START_PATTEN + toBeReplaced + END_PATTEN);
    }

    //****** �����: ˽�з��� **********************************************************************/

    private void openFile() throws FileNotFoundException
    {
        this.reader = new BufferedReader(new InputStreamReader(new FileInputStream(filename)));
    }

}
