package com.zte.ums.an.uni.dsl.conf.cdf.dispatch;

import java.io.File;

import org.apache.log4j.PropertyConfigurator;

import com.zte.ums.an.uni.dsl.conf.cdf.common.SubCollectCache;
import com.zte.ums.an.uni.dsl.conf.cdf.common.singleton.InstanceControlByFile;

/**
 * <p>文件名称: DispatchServer.java</p>
 * <p>文件描述: </p>
 * <p>版权所有: 版权所有(C)2007-2010</p>
 * <p>公    司: 中兴通讯股份有限公司</p>
 * <p>内容摘要: </p>
 * <p>其他说明: </p>
 * <p>完成日期：2011年9月1日</p>
 * <p>修改记录1:</p>
 * <pre>
 *    修改日期：
 *    版 本 号：
 *    修 改 人：
 *    修改内容：
 * </pre>
 * <p>修改记录2：</p>
 * @version 1.0
 * @author lixiaochun
 */
public class DispatchServer
{
    static
    {
        String userdir = System.getProperty("user.dir");
        String propFile = new File(userdir).getAbsolutePath()
            + "/conf/log4j-dispatch.properties";
        PropertyConfigurator.configure(propFile);
    }
    
    public static void main(String[] args)
    {
        if(args.length != 1)
        {
            System.out.println("Wrong args length");
            return;
        }
        
        if(InstanceControlByFile.tryRunning())
        {
            System.out.println("Only one dispatch server is allowed on the same computer.");
            System.exit(0);
        }
        
        int collectType = Integer.parseInt(args[0]);
        SubCollectCache.currType = collectType;
     
//        SubCollectCache.initParams();    
        DispatchProcessor.getInstance().start();
    }
}
