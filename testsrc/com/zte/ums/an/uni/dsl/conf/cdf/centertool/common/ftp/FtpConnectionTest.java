package com.zte.ums.an.uni.dsl.conf.cdf.centertool.common.ftp;

import java.io.File;
import java.io.IOException;

import junit.framework.TestCase;

import com.zte.ums.an.uni.dsl.conf.cdf.centertool.common.CdfTestResultConst;
import com.zte.ums.an.uni.dsl.conf.cdf.common.FtpInfo;
import com.zte.ums.uep.psl.jsch.jcraft.sftp.api.SftpClientException;

/**
 * <p>�ļ�����: FtpConnectionTest</p>
 * <p>�ļ�����: </p>
 * <p>��Ȩ����: ��Ȩ����(C)2005-2015</p>
 * <p>��    ˾: ����ͨѶ�ɷ����޹�˾</p>
 * <p>����ժҪ: </p>
 * <p>����˵��: </p>
 * <p>������ڣ�2012-12-12</p>
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
public class FtpConnectionTest extends TestCase
{
    private FtpInfo getFtpInfoFromWindows()
    {
        FtpInfo ftpInfo = new FtpInfo();
        ftpInfo.ftpServerIp = "10.63.204.112";
        ftpInfo.ftpUser = "webnms";
        ftpInfo.ftpUserPasswd = "webnms";

        return ftpInfo;
    }
    
    private FtpInfo getFtpInfoFromRedHat()
    {
        FtpInfo ftpInfo = new FtpInfo();
        ftpInfo.ftpServerIp = "10.63.174.204";
        ftpInfo.ftpUser = "webnms";
        ftpInfo.ftpUserPasswd = "webnms";

        return ftpInfo;
    }

    public static void testSftp() throws IOException, SftpClientException
    {
        //�yԇ��ҪFTP�h������ˌ�test�������Ğ�test����Ҫ�yԇ�r���_��

        com.zte.ums.uep.psl.jsch.jcraft.sftp.SftpClientImpl sftpClient = new com.zte.ums.uep.psl.jsch.jcraft.sftp.SftpClientImpl("10.63.204.112",
                        22, 60000, "webnms", "swss");
        // ���ļ�
//        String[] x = sftpClient.dir("/", true);
//        
//        for (String s : x){
//            System.out.println(s);
//        }
//        System.out.println(x.length);
        File tmpFile = new File("test.txt");
        tmpFile.createNewFile();
        sftpClient.putFile(tmpFile.getName(), "/myTest.txt");
    }

//    public void testFtpFromOSWindows() throws Exception
//    {
//        FtpInfo ftpInfo = getFtpInfoFromWindows();
//        FtpConnection ftpConn = new FtpConnection(FtpConnection.PROTOCAL_TYPE_FTP);
//        ftpConn.connect(ftpInfo.ftpServerIp, ftpInfo.ftpUser, ftpInfo.ftpUserPasswd);
//        
//        doTest(ftpConn, "/", "AAA", "1.1.1.1");
//    }
//
//    public void testFtpFromOSRedHat() throws Exception
//    {
//        FtpInfo ftpInfo = getFtpInfoFromRedHat();
//        FtpConnection ftpConn = new FtpConnection(FtpConnection.PROTOCAL_TYPE_FTP);
//        ftpConn.connect(ftpInfo.ftpServerIp, ftpInfo.ftpUser, ftpInfo.ftpUserPasswd);
//        
//        doTest(ftpConn, "/home/ftpdir/", "AAA", "1.1.1.1");
//    }
    
//    public void testSftpFromOSWindows() throws Exception
//    {
//        FtpInfo ftpInfo = getFtpInfoFromWindows();
//        FtpConnection ftpConn = new FtpConnection(FtpConnection.PROTOCAL_TYPE_SFTP);
//        ftpConn.connect(ftpInfo.ftpServerIp, ftpInfo.ftpUser, "dddd");
//        
//        doTest(ftpConn, "/", "AAA", "1.1.1.1");
//    }
    
//    public void testSftpFromOSRedHat() throws Exception
//    {
//        FtpInfo ftpInfo = getFtpInfoFromRedHat();
//        ftpInfo.isSFTP = true;
//        FtpConnection ftpConn = new FtpConnection(FtpConnection.PROTOCAL_TYPE_SFTP);
//        ftpConn.connect(ftpInfo.ftpServerIp, ftpInfo.ftpUser, ftpInfo.ftpUserPasswd);
//        
//        doTest(ftpConn, "/home/ftpdir/", "AAA", "1.1.1.1");
//    }
    
    private void doTest(FtpConnection ftpConn, String expectedDisplayedRootDir, String dir1, String dir2) throws IOException, Exception
    {
        assertEquals(expectedDisplayedRootDir, ftpConn.getDisplayedRootDir());
        assertEquals(expectedDisplayedRootDir, ftpConn.pwd());

        ftpConn.mkDirs(dir1 + "/" + dir2);
        assertEquals(expectedDisplayedRootDir, ftpConn.pwd());

        File tmpFile = createTestFile();
        ftpConn.upload(dir1 + "/" + dir2, tmpFile.getAbsolutePath());
        assertEquals(expectedDisplayedRootDir, ftpConn.pwd());
        
        ftpConn.delFile(ftpConn.getDisplayedRootDir() + dir1 + "/" + dir2 + "/" + tmpFile.getName());
        ftpConn.delDir(ftpConn.getDisplayedRootDir() + dir1 + "/" + dir2 + "/");
        ftpConn.delDir(ftpConn.getDisplayedRootDir() + dir1 + "/");
    }
    
    private File createTestFile() throws Exception
    {
        File testFile;
        try
        {
            testFile = new File("tmp.tst");
            testFile.createNewFile();
            return testFile;
        }
        catch(IOException e)
        {
            throw new Exception(CdfTestResultConst.CREATE_TEST_FILE_FAIL);
        }
    }
}
