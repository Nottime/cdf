package com.zte.ums.an.uni.dsl.conf.cdf.centertool.common;

/**
 * <p>�ļ�����: TestResult.java</p>
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
public class CdfTestResult
{
    public boolean isSuccess = false;
    public String detailStr = "";
    
    public CdfTestResult(String detailStr)
    {
        this.detailStr = detailStr;
    }
    
    public CdfTestResult(boolean isSuccess)
    {
        this.isSuccess = isSuccess;
    }
    
    @Override
    public String toString()
    {
        StringBuffer buf = new StringBuffer();
        buf.append("isSuccess      = ").append(isSuccess).append("\n");
        buf.append("detailStr      = ").append(detailStr).append("\n");
        
        return buf.toString();
    }
    
    @Override
    public int hashCode()
    {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((detailStr == null) ? 0 : detailStr.hashCode());
        result = prime * result + (isSuccess ? 1231 : 1237);
        return result;
    }

    @Override
    public boolean equals(Object obj)
    {
        if(this == obj)
        {
            return true;
        }
        
        if(!(obj instanceof CdfTestResult))
        {
            return false;
        }
        
        CdfTestResult result = (CdfTestResult)obj;
        if((detailStr == result.detailStr) && (isSuccess == result.isSuccess))
        {
            return true;
        }
        
        return false;
    }
}
