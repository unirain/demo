package designpattern.builder;

/********************************************************************************
 *
 * Title: 
 *
 * Description:
 *
 * @author chenlm
 * create date on 2019/1/31
 *
 *******************************************************************************/
public class Test {

    @org.junit.Test
    public void test1(){
        Person person=PersonBuilder.create().age(19).name("chenlm").sex("男").build();
        System.out.println(person.toString());

    }
    @org.junit.Test
    public void test2(){
        PersonV2 v2=PersonV2.create().age(3).name("chenlm").sex("男").build();
        System.out.println(v2.toString());

    }
}
