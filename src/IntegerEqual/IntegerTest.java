package IntegerEqual;

import org.junit.Test;

/********************************************************************************
 *
 * Title: 整型包装类测试
 *
 * Description:
 *
 * @author chenlm
 * create date on 2020/7/8
 *
 *******************************************************************************/
public class IntegerTest {

    @Test
    public void test()throws Exception{
        Integer integerA=new Integer(100);
        Integer integerB=new Integer(100);
        //对象比较  false
        System.out.println(integerA==integerB);

    }
    @Test
    public void test1()throws Exception{
        Integer integerA=new Integer(100);
        int a=100;
        //自动拆箱比较
        System.out.println(a==integerA);
    }

    /**
     * 当Int值范围在-128—127之间时，会通过一个IntegerCache缓存来创建Integer对象；
     * 当Int值不在该范围时，直接调用new Integer（）来创建对象。因此会产生以下情况：
     * @throws Exception
     */
    @Test
    public void test2()throws Exception{
        Integer a=100;
        Integer b=100;
        //true 原因是缓冲   -128-128之内，使用缓存创建
        System.out.println(a==b);
        a=300;
        b=300;
        // 使用 new 创建
        System.out.println(a==b);


    }
}
