package StringEqual;

import org.junit.Test;

/********************************************************************************
 *
 * Title: String ==和equal测试
 *
 * Description:
 *
 * @author chenlm
 * create date on 2020/7/8
 *
 *******************************************************************************/
public class StringTest {
    /**
     * 首先栈区创建str引用，然后在String池（独立于栈和堆而存在，存储不可变量）中寻找其指向的内容为"abcd"的对象，
     * 如果String池中没有，则创建一个，然后str指向String池中的对象，如果有，则直接将str指向"abcd""；
     * @throws Exception
     */

    @Test
    public void test1()throws Exception{
        String str="abcd";//方法区String池创建
        String b=new String("abc");

    }
}
