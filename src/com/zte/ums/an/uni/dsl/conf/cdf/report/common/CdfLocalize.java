package com.zte.ums.an.uni.dsl.conf.cdf.report.common;

import java.io.File;
import java.util.Hashtable;

import org.apache.log4j.Logger;

import com.borland.dx.dataset.Column;
import com.borland.dx.dataset.TableDataSet;
import com.borland.dx.dataset.TextDataFile;
import com.zte.ums.an.uni.dsl.conf.cdf.common.LogPrint;
import com.zte.ums.n3common.api.ZXLogger;

/**
 * <p>�ļ�����: CdfLocalize</p>
 * <p>�ļ�����: </p>
 * <p>��Ȩ����: ��Ȩ����(C)2007-2012</p>
 * <p>��    ˾: ����ͨѶ�ɷ����޹�˾</p>
 * <p>����ժҪ: </p>
 * <p>����˵��: </p>
 * <p>������ڣ�2012��01��09��</p>
 * <p>�޸ļ�¼1:</p>
 * <pre>
 *    �޸����ڣ�
 *    �� �� �ţ�
 *    �� �� �ˣ�
 *    �޸����ݣ�
 * </pre>
 * <p>�޸ļ�¼2��</p>
 * @version 1.0
 * @author jingxueshi
 */
public class CdfLocalize
{
  //****** �����: �������� **********************************************************************/

    private final String LANGEUAGE_ENGLISH = "English";
    private final String LANGEUAGE_CHINESE = "Chinese";

    //****** �����: �������� **********************************************************************/

    private Logger logger = ZXLogger.getLogger(this.getClass().getName());
    private String language = LANGEUAGE_ENGLISH;
    private String localizeParamFileName = null;

    private String nativeStringKey;
    private String nativeString;

    private Hashtable hashtable = new Hashtable();

    private TextDataFile textDataFile = new TextDataFile();
    private TableDataSet tableDataSet = new TableDataSet();
    private Column columnKey = new Column();
    private Column columnEng = new Column();
    private Column columnChi = new Column();

    //****** �����: ���췽�� **********************************************************************/

    public CdfLocalize()
    {
        this.language = LANGEUAGE_ENGLISH;
        this.localizeParamFileName = getLocalizeFile();
        loadLocalizeFiles(this.localizeParamFileName);
    }

    //****** �����: �������� **********************************************************************/

    public String findLocalString(String nativeStringKey)
    {
        String temp = (String)hashtable.get((Object)(nativeStringKey.toUpperCase()));
        if(temp == null)
        {
            temp = nativeStringKey;
        }
        
        return temp;
    }

    //****** �����: ˽�з��� **********************************************************************/

    private void loadLocalizeFiles(String fileName)
    {
        fileName = this.localizeParamFileName;

        prepareColumn();
        prepareDataSet(fileName);

        int rowCount = tableDataSet.rowCount();
        try
        {
            for(int i = 0; i < rowCount; i++)
            {
                nativeStringKey = tableDataSet.getString("ID").toUpperCase();

                nativeString = tableDataSet.getString(language);

                hashtable.put(nativeStringKey, nativeString);
                tableDataSet.next();
            }
        }
        catch(Exception ex)
        {
            LogPrint.logError(logger, "", ex);
        }

        tableDataSet.close();
    }

    private void prepareColumn()
    {
        columnKey.setCaption("ID");
        columnKey.setColumnName("ID");
        columnKey.setDataType(com.borland.dx.dataset.Variant.STRING);
        columnKey.setServerColumnName("NewcolumnKey");
        columnKey.setSqlType(0);

        columnEng.setCaption(LANGEUAGE_ENGLISH);
        columnEng.setColumnName(LANGEUAGE_ENGLISH);
        columnEng.setDataType(com.borland.dx.dataset.Variant.STRING);
        columnEng.setServerColumnName("NewcolumnEng");
        columnEng.setSqlType(0);

        columnChi.setCaption(LANGEUAGE_CHINESE);
        columnChi.setColumnName(LANGEUAGE_CHINESE);
        columnChi.setDataType(com.borland.dx.dataset.Variant.STRING);
        columnChi.setServerColumnName("NewcolumnChi");
        columnChi.setSqlType(0);
    }

    private void prepareDataSet(String fileName)
    {
        tableDataSet.setDataFile(textDataFile);
        textDataFile.setFileName(fileName);
        textDataFile.setSeparator(",");
        tableDataSet.setColumns(new Column[]
            {columnKey, columnEng, columnChi});
        try
        {
            tableDataSet.open();
            tableDataSet.atFirst();
        }
        catch(Exception ex)
        {
            tableDataSet.close();
            LogPrint.logError(logger, "", ex);
            return;
        }
    }

    private static String getLocalizeFile()
    {
        return (new File("localize/zxnm01-uni-dsl-cdf-i18n.csv")).getAbsolutePath();
    }
}
