package com.zte.ums.an.uni.dsl.conf.cdf.collect.matcher;

/**
 * <p>�ļ�����: DefaultMatcher.java</p>
 * <p>�ļ�����: </p>
 * <p>��Ȩ����: ��Ȩ����(C)2001-2012</p>
 * <p>��    ˾: ����ͨѶ�ɷ����޹�˾</p>
 * <p>����ժҪ: </p>
 * <p>����˵��: </p>
 * <p>������ڣ�2012-7-18</p>
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
public class DefaultMatcher implements IMatcher
{
    @Override
    public boolean isMatch(String mergeKey, String appendKey)
    {
        if(mergeKey == null || appendKey == null)
        {
            return false;
        }
        return mergeKey.equals(appendKey);
    }
}
