package com.zte.ums.an.uni.dsl.conf.cdf.centertool.common;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;


/**
 * <p>�ļ�����: SysOutRediction.java</p>
 * <p>�ļ�����: </p>
 * <p>��Ȩ����: ��Ȩ����(C)2001-2012</p>
 * <p>��    ˾: ����ͨѶ�ɷ����޹�˾</p>
 * <p>����ժҪ: </p>
 * <p>����˵��: </p>
 * <p>������ڣ�2012-3-17</p>
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
public class SysOutRediction
{
    private static PrintStream sysOut = null;
    private static PrintStream sysErrOut = null;
    private static PrintStream newOut = null;
    private File targetFile;
    
    public SysOutRediction(File targetFile)
    {
        sysOut = System.out;
        sysErrOut = System.err;
        
        this.targetFile = targetFile.getAbsoluteFile();
    }

    public void startRediction()
    {
        try
        {
            newOut = new PrintStream(new BufferedOutputStream(new FileOutputStream(targetFile)));
            System.setErr(newOut);
            System.setOut(newOut);
        }
        catch(FileNotFoundException e)
        {
            e.printStackTrace();
        }
    }
    
    public void endRecdition()
    {
        if(newOut != null)
        {
            newOut.close();
        }
        
        System.setErr(sysErrOut);
        System.setOut(sysOut);
        
        this.targetFile.delete();
    }
}
