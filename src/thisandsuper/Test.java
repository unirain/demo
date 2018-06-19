package thisandsuper;

/********************************************************************************
 *
 * Title: 
 *
 * Description:
 * 在继承多态中：
 * 1、对于方法的覆盖，new的谁就调谁，这就是多态。
 * 2、对于成员变量的覆盖，this在哪个类就指向哪个类的成员变量，没有多态。
 *
 * @author chenlm
 * create date on 2018/6/19
 *
 *******************************************************************************/
public class Test {
    public static void main(String[] args) {
        Father father = new Child();
        System.out.println(father.getUsername());

    }


}
