package compare;

import org.junit.Test;

import java.util.*;
import java.util.stream.Stream;

/********************************************************************************
 *
 * Title: 测试
 *
 * Description:
 *
 * @author chenlm
 * create date on 2020/3/13
 *
 *******************************************************************************/
public class CompareTest {
    @Test
    public void test1()throws Exception{
        PersonInfoA info0=new PersonInfoA();
        info0.setAge(50);
        PersonInfoA info1=new PersonInfoA();
        info1.setAge(10);
        PersonInfoA info2=new PersonInfoA();
        info2.setAge(12);
        /**
         * 内比较器的使用，对象必须要实现Comparable接口
         */
//        Stream.of(info2,info1,info0).sorted().forEach(o-> System.out.println(o.getAge()));

        /**
         * 外比较器的使用
         */
        Stream.of(info2,info1,info0).sorted(Comparator.comparingInt(PersonInfoA::getAge)).forEach(o-> System.out.println(o.getAge()));

    }

}
