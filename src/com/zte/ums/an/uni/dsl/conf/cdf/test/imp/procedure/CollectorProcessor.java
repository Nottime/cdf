package com.zte.ums.an.uni.dsl.conf.cdf.test.imp.procedure;

import com.zte.ums.an.uni.dsl.conf.cdf.collect.collector.AbsCollectorProcessor;
import com.zte.ums.api.common.snmpnode.ppu.entity.SnmpNode;

/**
 * <p>文件名称: CollectionProcessor.java</p>
 * <p>文件描述: 每个采集线程任务</p>
 * <p>版权所有: 版权所有(C)2007-2012</p>
 * <p>公    司: 中兴通讯股份有限公司</p>
 * <p>内容摘要: </p>
 * <p>其他说明: </p>
 * <p>完成日期：2011年11月14日</p>
 * <p>修改记录1:</p>
 * <pre>
 *    修改日期：
 *    版 本 号：
 *    修 改 人：
 *    修改内容：
 * </pre>
 * <p>修改记录2：</p>
 * @version 1.0
 * @author jingxueshi_10118495
 */
public class CollectorProcessor extends AbsCollectorProcessor
{
    //****** 代码段: 变量定义 **********************************************************************/

    //****** 代码段: 公共方法 **********************************************************************/

    public CollectorProcessor(SnmpNode snmpNode)
    {
        super(snmpNode);
    }

    @Override
    public boolean getCSVFileFromNe()
    {
        try
        {
            Thread.sleep(((long)(Math.random() * 100000) % 2000) + 5000);
//            Thread.sleep(500);
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return true;
    }

    @Override
    public boolean isVersionInconsistent()
    {
        return false;
    }

    @Override
    public void downloadXMLWithCurrentVersion()
    {
        
    }

    @Override
    public boolean isNeedOperate()
    {
        return true;
    }
}