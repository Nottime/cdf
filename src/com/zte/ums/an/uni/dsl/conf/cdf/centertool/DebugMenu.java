package com.zte.ums.an.uni.dsl.conf.cdf.centertool;

/**
 * <p>�ļ�����: DebugMenu</p>
 * <p>�ļ�����: </p>
 * <p>��Ȩ����: ��Ȩ����(C)2001-2012</p>
 * <p>��    ˾: ����ͨѶ�ɷ����޹�˾</p>
 * <p>����ժҪ: </p>
 * <p>����˵��: </p>
 * <p>������ڣ�2012-3-13</p>
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
public class DebugMenu extends CenterToolMenu implements IMenuAction
{    
    private final static String[][] debugMenuList = new String[][] {
      {"1", "1. Run Configuration Tests", "com.zte.ums.an.uni.dsl.conf.cdf.centertool.action.ActionConfigCheck"},
      {"2", "2. Delete Temporary Files", "com.zte.ums.an.uni.dsl.conf.cdf.centertool.action.ActionDeleteTmp"},
      {"3", "3. Zip Log", "com.zte.ums.an.uni.dsl.conf.cdf.centertool.action.ActionZipLog"},
      {"4", "4. Back to Main Menu", "com.zte.ums.an.uni.dsl.conf.cdf.centertool.action.ActionBacktoMainMenu"}};
      
    @Override
    public String[][] getMenuList()
    {
        return debugMenuList;
    }

    @Override
    public void presentTitle()
    {
        System.out.println("=======CDF Test and Maintenance Menu=======");
        System.out.println();
    }
    
    @Override
    public void doAction()
    {
        runMenu();
    }
}
