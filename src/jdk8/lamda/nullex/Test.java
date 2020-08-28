package jdk8.lamda.nullex;

import java.util.Optional;
import java.util.function.Supplier;

/********************************************************************************
 *
 * Title: 
 *
 * Description: 使用Optional预防空指针
 *
 * @author chenlm
 * create date on 2020/8/14
 *
 *******************************************************************************/
public class Test {

    /**
     * 我们可以通过利用 Java 8 的 Optional 类型来摆脱所有这些 null 检查。
     * map 方法接收一个 Function 类型的 lambda 表达式，
     * 并自动将每个 function 的结果包装成一个 Optional 对象。
     * 这使我们能够在一行中进行多个 map 操作。Null 检查是在底层自动处理的。
     *
     * @throws Exception
     */
    @org.junit.Test
    public void test1() throws Exception {
        Outer outer = new Outer();
        //空指针
//        System.out.println(outer.getInner().getFoo());
        Optional.of(outer).map(Outer::getInner).map(Inner::getFoo).ifPresent(System.out::println);
    }

    /**
     * 还有一种实现相同作用的方式就是通过利用一个 supplier 函数来解决嵌套路径的问题
     * @throws Exception
     */
    @org.junit.Test
    public void test2() throws Exception {
        Outer outer = new Outer();
        resolve(()->outer.getInner().getFoo()).ifPresent(System.out::println);

    }

    /**
     * 解决嵌套调用空指针
     *
     * 调用 obj.getInner().getFoo())
     * 可能会抛出一个 NullPointerException 异常。在这种情况下，该异常将会被捕获，而该方法会返回 Optional.empty()。
     * @param supplier
     * @param <T>
     * @return
     */
    public <T> Optional<T> resolve(Supplier<T> supplier) {
        try {
            T t = supplier.get();
            return Optional.ofNullable(t);
        } catch (Exception e) {
            return Optional.empty();
        }
    }
}
