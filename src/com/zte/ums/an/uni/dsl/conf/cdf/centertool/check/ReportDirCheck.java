package com.zte.ums.an.uni.dsl.conf.cdf.centertool.check;

import java.io.File;
import java.io.IOException;

import com.zte.ums.an.uni.dsl.conf.cdf.centertool.common.CdfTestResult;
import com.zte.ums.an.uni.dsl.conf.cdf.centertool.common.CdfTestResultConst;
import com.zte.ums.an.uni.dsl.conf.cdf.centertool.common.CenterToolUtil;

/**
 * <p>�ļ�����: ReportDirCheck</p>
 * <p>�ļ�����: </p>
 * <p>��Ȩ����: ��Ȩ����(C)2001-2012</p>
 * <p>��    ˾: ����ͨѶ�ɷ����޹�˾</p>
 * <p>����ժҪ: </p>
 * <p>����˵��: </p>
 * <p>������ڣ�2012-3-12</p>
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
public class ReportDirCheck implements ICdfCheck
{
    private String reportDir;
    
    public ReportDirCheck()
    {
    }
    
    private String getReportDir()
    {
        return CenterToolUtil.getReportXml().getExportDir();
    }
    
    @Override
    public String getName()
    {
        return "final report directory";
    }
    
    @Override
    public void presentTitle()
    {
        CenterToolUtil.printGroupTile("Start final report directory write permission test");
    }
    
    @Override
    public void presentEnd()
    {
        CenterToolUtil.printGroupTile("Final report directory write permission test.");
    }
    
    @Override
    public CdfTestResult doCheck()
    {
        this.reportDir = getReportDir();
        
        try
        {
            File tmpFile = createTestFile();

            cleanTestTemp(tmpFile);
            return new CdfTestResult(true);
        }
        catch(Exception e)
        {
            return new CdfTestResult(e.getMessage());
        }
    }

    private void cleanTestTemp(File tmpFile) throws Exception
    {
        CenterToolUtil.printCheckTile("Deleting test file from report directory");
        
        try
        {
            if(tmpFile.delete())
            {
                CenterToolUtil.printSucess();
            }
            else
            {
                throw new Exception(CdfTestResultConst.DELETE_TEST_REPORTFILE_FAIL);
            }
        }
        catch(IOException e)
        {
            throw new Exception(CdfTestResultConst.DELETE_TEST_REPORTFILE_FAIL);
        }
    }
    
    private File createTestFile() throws Exception
    {
        CenterToolUtil.printCheckTile("Creating test file to report directory");
        
        File testFile;
        try
        {
            testFile = new File(this.reportDir, "tmp.tst");
            if(testFile.createNewFile())
            {
                CenterToolUtil.printSucess();
            }
            else
            {
                throw new Exception(CdfTestResultConst.CREATE_TEST_REPORTFILE_FAIL);
            }
        }
        catch(IOException e)
        {
            throw new Exception(CdfTestResultConst.CREATE_TEST_REPORTFILE_FAIL);
        }
        
        return testFile.getAbsoluteFile();
    }
}
