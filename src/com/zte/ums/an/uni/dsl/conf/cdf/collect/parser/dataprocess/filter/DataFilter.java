package com.zte.ums.an.uni.dsl.conf.cdf.collect.parser.dataprocess.filter;

import java.util.ArrayList;

import org.apache.log4j.Logger;

import com.zte.ums.an.uni.dsl.conf.cdf.collect.parser.common.BulkPoolGroupInfo;
import com.zte.ums.an.uni.dsl.conf.cdf.collect.parser.common.FilterInfo;
import com.zte.ums.an.uni.dsl.conf.cdf.common.LogPrint;
import com.zte.ums.an.uni.dsl.conf.cdf.common.xml.CollectDataXmlParser;
import com.zte.ums.n3common.api.ZXLogger;

/**
 * <p>�ļ�����: DataFilter.java</p>
 * <p>�ļ�����: </p>
 * <p>��Ȩ����: ��Ȩ����(C)2001-2012</p>
 * <p>��    ˾: ����ͨѶ�ɷ����޹�˾</p>
 * <p>����ժҪ: </p>
 * <p>����˵��: </p>
 * <p>������ڣ�2012-7-12</p>
 * <p>�޸ļ�¼1:</p>
 * <pre>
 *    �޸����ڣ�
 *    �� �� �ţ�
 *    �� �� �ˣ�
 *    �޸����ݣ�
 * </pre>
 * <p>�޸ļ�¼2��</p>
 * @version 1.0
 * @author ljy
 */
public class DataFilter
{
    private Logger logger = ZXLogger.getLogger(this.getClass().getName());
    
    private String groupName = null;
    private FilterInfo[] filterInfos = null;
    
    public DataFilter(BulkPoolGroupInfo group)
    {
        this.groupName = group.getGroupName();
        filterInfos = CollectDataXmlParser.getInstance().getGroupToFilterInfos().get(this.groupName);
    }
    
    public boolean isNeedReserve(ArrayList<String> record)
    {
        if(null == filterInfos || filterInfos.length == 0)
        {
            return true;
        }
        
        for(int i = 0; i < filterInfos.length; i++)
        {
            int index = filterInfos[i].getIndexFilter();
            String[] values = filterInfos[i].getValuesFilter();
            
            if(isIndexInRange(index, record.size()) && isNeedReverse(index, values, record))
            {
                return true;
            }
        }
        
        return false;
    }
    
    // TODO this method is only for test.
    public DataFilter()
    {

    }
    
    // TODO this method is only for test.
    public void setFilterInfos(FilterInfo[] infos)
    {
        this.filterInfos = infos;
    }
    
    private boolean isIndexInRange(int index, int recordLen)
    {
        boolean isInRange = recordLen > 0 && index >= 0 && index < recordLen;
        
        if(!isInRange)
        {
            LogPrint.logError(logger, "The index of filter is out of range. index = " + index + ", record length = " + recordLen);
        }
        
        return isInRange;
    }
    
    private boolean isNeedReverse(int index, String[] filterValues, ArrayList<String> record)
    {
        for(int i = 0; i < filterValues.length; i++)
        {
            if(record.get(index).matches(filterValues[i]))
            {
                return true;
            }
        }
        return false;
    }
}
