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
public class Test {
    public static void main(String[] args) {
        Approver bossAppover = new BossAppover();
        Approver empAppover = new EmpAppover();
        Approver person = new NormolPerson();
        person.setNext(empAppover);
        empAppover.setNext(bossAppover);
        person.processHandle(600);

    }


}
