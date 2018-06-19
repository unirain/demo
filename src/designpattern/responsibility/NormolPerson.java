package designpattern.responsibility;

/********************************************************************************
 *
 * Title: 
 *
 * Description:
 *
 * @author chenlm
 * create date on 2018/6/19
 *
 *******************************************************************************/
public class NormolPerson extends  Approver {
    @Override
    protected void processHandle(int money) {
        if (money<500){
            System.out.println("我是普通人，我处理了"+money);
        }else{
            this.nextOne.processHandle(money);
        }
    }
}
