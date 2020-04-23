package test;

/********************************************************************************
 *
 * Title: 测试 静态代码和静态代码块的运行 顺序
 *
 * Description:
 *
 * @author chenlm
 * create date on 2019/12/24
 *
 *******************************************************************************/
public class gzTest {
    private static  String a="66";
    static {
        a="152";
        System.out.println();
        System.out.println("我是静态代码块");
    }

    public gzTest(){
        System.out.println("我是构造函数");
    }
    public static void show(){
        System.out.println("我是静态方法块");
    }
    void play(){
        System.out.println("玩耍");
    }
}
