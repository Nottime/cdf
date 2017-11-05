package com.zte.ums.an.uni.dsl.conf.cdf.centertool.common;



/**
 * <p>�ļ�����: CdfTestResultConst.java</p>
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
public class CdfTestResultConst
{
    public static final String CREATE_TEST_FILE_FAIL = "Cannot create test file in current directory."; 
    public static final String CREATE_TEST_REPORTFILE_FAIL = "Cannot create file in report directory, please ensure you have permission"; 
    public static final String DELETE_TEST_REPORTFILE_FAIL = "Cannot delete file in report directory, please ensure you have permission";
    
    public static final String CREATE_TEST_FTPFILE_FAIL = "Cannot create file in ftp directory, please ensure you have permission"; 
    public static final String DELETE_TEST_FTPFILE_FAIL = "Cannot delete file in ftp directory, please ensure you have permission";
    
    public static final String CREATE_FTP_CONN_FAIL = "Cannot connect to FTP";
    public static final String FTP_MKDIR_FAIL = "Failed to create new directory to FTP, please ensure you have permission";
    public static final String FTP_DELDIR_FAIL = "Failed to delete temp directory from FTP, please ensure you have permission";
    public static final String FTP_UPLOAD_FAIL = "Failed to upload to FTP, please ensure you have permission";
    public static final String FTP_ROOTDIR_INVALID = "Invalid FTP root dir";
    
    public static final String DB_CONNECT_CLOSE_FAIL = "Failed to close database";
    public static final String DB_CONNECT_EMSAN_FAIL = "Failed to connect to EMS AN database";
    public static final String DB_CONNECT_EMSUEP4X_FAIL = "Failed to connect to EMS UEP4X database";
    public static final String DB_CONNECT_CDF_FAIL = "Failed to connect to CDF database";
    public static final String DB_CONNECT_SUBCOLLECT_FAIL = "Failed to connect to sub-collect database";

    public static final String DB_QUERY_ANCOLLECTPOINT_FAIL = "Failed to query AN_CollectPointTable from AN database";
    public static final String DB_QUERY_COLLECTNELIST_FAIL = "Failed to query NE list to collect from database";
    public static final String DB_INSTALL_CDF_FAIL = "Failed to installing CDF database";
    
    public static final String RMI_CONF_INVALID_port = "RMI port is invalid";
    public static final String IPV4_NOT_LOCAL = "The IPv4 Address is not local host IP.";
    public static final String RMI_INCONSISTENT = "RMI inconsistent with dispatch server.";
    
    public static final String RMI_START_FAIL = "Failed to start dispatch server RMI.";
    public static final String RMI_CONNECT_FAIL = "Failed to connect to dispatch server RMI.";
    
    public static final String INCONSISTENT_VERSION = "Inconsistent CDF version, please install CDF database.";
    
//    public static final HashMap<String, ICdfConfigDataModel[]> errorRelatedToConfig = initHashMap();

//    private static HashMap<String, ICdfConfigDataModel[]> initHashMap()
//    {
//        HashMap<String, ICdfConfigDataModel[]> map = new HashMap<String, ICdfConfigDataModel[]>();
//        
//        map.put(CREATE_FTP_CONN_FAIL, new ICdfConfigDataModel[] {});
//    }
//    
    
}
