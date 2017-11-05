package com.zte.ums.an.uni.dsl.conf.cdf.test.tool.csvmock;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.zte.ums.an.uni.dsl.conf.cdf.test.tool.csvmock.ipmock.IPv4AddrMocker;

/**
 * <p>�ļ�����: FileNameMocker</p>
 * <p>�ļ�����: </p>
 * <p>��Ȩ����: ��Ȩ����(C)2001-2012</p>
 * <p>��    ˾: ����ͨѶ�ɷ����޹�˾</p>
 * <p>����ժҪ: </p>
 * <p>����˵��: </p>
 * <p>������ڣ�2011��11��21��</p>
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

public class FileNameMocker
{
    private static FileNameMocker instance = new FileNameMocker();
    private String ip;
    
    
    private FileNameMocker()
    {
    }
    
    public static FileNameMocker getInstance()
    {
        return instance;
    }
    
    private static String mockTimeStamp()
    {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
        return sdf.format(new Date());
    }
    
    public String getNextFileName()
    {
        ip = IPv4AddrMocker.getInstance().getNextIPv4Addr();
        return ip + "_VDSLPORT_TRUNK_" + mockTimeStamp() + ".csv";
    }
    
    public String getIPAddrFromCurrFileName()
    {
        return ip;
    }
}
