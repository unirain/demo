package designpattern.responsibility;

/********************************************************************************
 *
 * Title: 处理人--老板
 *
 * Description:
 *
 * @author chenlm
 * create date on 2018/6/19
 *
 *******************************************************************************/
public class BossAppover extends  Approver {
    @Override
    protected void processHandle(int money) {
        System.out.println("我是老板，我处理了"+money);
    }
}
