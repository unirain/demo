package designpattern.pattern.example2;

/********************************************************************************
 *
 * Title: 
 *
 * Description:
 *
 * @author chenlm
 * create date on 2018/12/29
 *
 *******************************************************************************/
public class Test {
    @org.junit.Test
    public void test1()throws Exception{
        Call phone=new Phone();
        phone.tell();
//        //增加功能
//        DecoratePhone bell=new PhoneBell(phone);
//        bell.tell();
//        //增加功能
//        DecoratePhone color=new ColorPhone(phone);
//        color.tell();
        //两个都要
        DecoratePhone double1=new PhoneBell(new ColorPhone(phone));
        double1.tell();
    }
}
