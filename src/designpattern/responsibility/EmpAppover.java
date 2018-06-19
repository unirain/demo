package designpattern.responsibility;

/********************************************************************************
 *
 * Title: 处理人---员工
 *
 * Description:
 *
 * @author chenlm
 * create date on 2018/6/19
 *
 *******************************************************************************/
public class EmpAppover extends Approver {
    @Override
    protected void processHandle(int money) {
        if (money <= 1000&&500<=money) {
            System.out.println("我是员工，我处理了" + money);
        } else {
            this.nextOne.processHandle(money);
        }
    }
}
