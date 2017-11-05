package com.zte.ums.an.uni.dsl.conf.cdf.collect.parser.dataprocess;

import java.util.ArrayList;

import com.zte.ums.an.uni.dsl.conf.cdf.collect.parser.common.BulkPoolGroupInfo;
import com.zte.ums.an.uni.dsl.conf.cdf.collect.parser.common.FileInfo;
import com.zte.ums.an.uni.dsl.conf.cdf.collect.parser.common.Record;
import com.zte.ums.an.uni.dsl.conf.cdf.collect.parser.common.SeqRecProxy;
import com.zte.ums.an.uni.dsl.conf.cdf.collect.parser.dataprocess.append.DataAppender;
import com.zte.ums.an.uni.dsl.conf.cdf.collect.parser.dataprocess.filter.DataFilter;
import com.zte.ums.an.uni.dsl.conf.cdf.collect.parser.dataprocess.merge.DataMerger;
import com.zte.ums.an.uni.dsl.conf.cdf.common.CdfUtil;

/**
 * <p>�ļ�����: DataExecuter</p>
 * <p>�ļ�����: </p>
 * <p>��Ȩ����: ��Ȩ����(C)2001-2012</p>
 * <p>��    ˾: ����ͨѶ�ɷ����޹�˾</p>
 * <p>����ժҪ: </p>
 * <p>����˵��: </p>
 * <p>������ڣ�2012-2-28</p>
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
public class DataProcess
{
    private BulkPoolGroupInfo group;
    private DataMerger dm;
    private DataAppender dataAppend;
    private DataFilter dataFilter;

    public DataProcess(BulkPoolGroupInfo group) throws Exception
    {
        this.group = group;
        initialize();
    }
    
    private void initialize() throws Exception
    {
        ArrayList<SeqRecProxy> proxyList = buildProxylist(group.getMergedFileInfoList());
        dm = new DataMerger(proxyList);
        
        dataAppend = new DataAppender(group);
        dataFilter = new DataFilter(group);
    }

    private ArrayList<SeqRecProxy> buildProxylist(FileInfo[] fileInfoList) throws Exception
    {
        ArrayList<SeqRecProxy> proxyList = new ArrayList<SeqRecProxy>();
        
        for(FileInfo fileInfo : fileInfoList)
        {
            proxyList.add(CdfUtil.createProxy(fileInfo.getFile(), fileInfo.getReadProxy()));
        }
        
        return proxyList;
    }
    
    public ArrayList<String> readNextLineParams() throws Exception
    {
        Record mergedRecord = dm.readMergedData();
        
        if(mergedRecord == null)
        {
            return null;
        }
        
        ArrayList<String> appendedRecord = dataAppend.afterAppend(mergedRecord);
        
        if(!dataFilter.isNeedReserve(appendedRecord))
        {
            appendedRecord = readNextLineParams();
        }
        
        return appendedRecord;
    }
    
    public void close()
    {
        dm.close();
    }
}
