package designpattern.responsibility;

/********************************************************************************
 *
 * Title: 责任链模式--抽象处理人
 *
 * Description:
 *
 * @author chenlm
 * create date on 2018/6/19
 *
 *******************************************************************************/
public abstract class Approver {
    protected  Approver nextOne;
    protected void setNext( Approver next){
        this.nextOne=next;
    }
    protected  abstract void  processHandle(int money);


}
