package jdk8.lamda;

import org.junit.Test;

import java.util.*;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/********************************************************************************
 *
 * Title: 20190104Test
 *
 * Description:
 *
 * @author chenlm
 * create date on 2019/1/4
 *
 *******************************************************************************/
public class NewTest {
    /**
     * Lambda表达式
     *
     * @throws Exception
     */
    @Test
    public void test1() throws Exception {
        //没有Lambda表达式之前的string 列表排序
        List<String> list = Arrays.asList("5", "4", "6", "3");
        // 使用比较器Comparator排序
        Collections.sort(list, new Comparator<String>() {
            @Override
            public int compare(String s, String t1) {
                return s.compareTo(t1);
            }
        });
        //使用Lambda表达式1
        Collections.sort(list, (String a, String b) -> {
            return a.compareTo(b);
        });
        //使用Lambda表达式2
        Collections.sort(list, (a, b) -> a.compareTo(b));
        //使用Comparator工具方法
        Collections.sort(list, Comparator.naturalOrder());


    }

    /**
     * Lambda之方法和构造函数引用
     *
     * @throws Exception
     */
    @Test
    public void test2() throws Exception {
        //方法的调用
        Lambda4 lambda4 = new Lambda4();
        Converter<String, Integer> converter = lambda4::startsWith;
        //构造函数的调用
        //personFactory必须只有一个抽象方法
        //personFactory的方法实现类入参必须要三个，而person的构造函数刚好是三个，可以互相调用
        personFactory<Person> personFactory = Person::new;
        Person person = personFactory.createPerson("1", "3");
        System.out.println(person.password);
    }

    /**
     * 内置函数式接口
     * Predicate的使用
     *
     * @throws Exception
     */
    @Test
    public void test3() throws Exception {

        Predicate<String> p1 = (a) -> a.length() > 0;
        System.out.println(p1.test("88"));
        Predicate<String> p2 = String::isEmpty;
        System.out.println(p2.and(p1).test("1"));


    }

    /**
     * 内置函数式接口
     * Functions的使用
     *
     * @throws Exception
     */
    @Test
    public void test4() throws Exception {
//        Function<String,Integer> toInter=Integer::valueOf;
        Function<String, Integer> toInter = Integer::valueOf;
        System.out.println(toInter.apply("10"));
        Function<String, String> other = toInter.andThen(String::valueOf);
        System.out.println(other.apply("1000"));

    }

    /**
     * Optionals
     *
     * @throws Exception
     */
    @Test
    public void test5() throws Exception {
        Optional<String> optionalS = Optional.of("99");
        System.out.println(optionalS.equals("88"));


    }

    /**
     * Streams
     *
     * @throws Exception
     */
    @Test
    public void test6() throws Exception {
        Collection<String> list = Arrays.asList("22", "33", "53", "11");
        list.stream().filter(a -> Integer.valueOf(a) > 11).forEach(System.out::println);
        list.stream().sorted().forEach(System.out::println);
        list.stream().map(a -> Integer.valueOf(a) * 3).forEach(System.out::println);
        System.out.println(list.stream().mapToInt(String::length).sum());


        //不需要集合创建
        Stream.of(list).forEach(System.out::println);

        //代替for循环
        IntStream.range(1,10).forEach(System.out::println);

        //
        list.forEach(System.out::println);
    }

    /**
     * Map新特性
     *
     * @throws Exception
     */
    @Test
    public void test7() throws Exception {
        Map<Integer, String> map = new HashMap<>();
        for (int i = 0; i < 10; i++) {
            if (map.putIfAbsent(i, "val" + i) != null) {
                throw new RuntimeException("重复添加");
            }
        }

        //不存在则添加，value接受一个function
        map.computeIfAbsent(11, a -> a + "1");
        //
        map.computeIfPresent(1, (a, b) -> null);
        map.forEach((a, val) -> System.out.println(a));

    }

    /**
     * collect
     * @throws Exception
     */
    @Test
    public void test8() throws Exception {
        Collection<String> list = Arrays.asList("22", "22", "53", "11");
        List<String> list1=list.stream().filter((s)->Integer.valueOf(s)>20).collect(Collectors.toList());

    }


    class Person {
        private String username;
        private String password;

        public Person() {

        }

        public Person(String username, String password) {
            this.password = password;
            this.username = username;
        }

    }

    interface personFactory<P extends Person> {
        P createPerson(String username, String password);

    }
}
