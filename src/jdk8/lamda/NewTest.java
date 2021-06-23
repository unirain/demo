package jdk8.lamda;

import com.google.common.collect.Lists;
import org.junit.Assert;
import org.junit.Test;

import java.util.*;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
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
     * @param i
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
        Collections.sort(list, String::compareTo);
        //使用Comparator工具方法
        Collections.sort(list, Comparator.naturalOrder());
        //或者
        list.sort(Comparator.naturalOrder());


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
     * Predicate的使用
     * 20200114复习
     *
     * @throws Exception
     */
    @Test
    public void test31() throws Exception {
        //定义数值必须大于10的数据
        Predicate<Integer> p1 = a -> a > 10;
        //false
        System.out.println(p1.test(5));
        //true
        System.out.println(p1.negate().test(5));
        //or判断
        System.out.println(p1.or(a -> a < 1).test(0));
        //and 判断
        System.out.println(p1.and(a -> a <= 15).test(20));

    }

    /**
     * 内置函数式接口
     * Functions的使用
     *
     * @throws Exception
     */
    @Test
    public void test4() throws Exception {
        Function<String, Integer> toInter = Integer::valueOf;
        System.out.println(toInter.apply("10"));
        Function<String, String> other = toInter.andThen(String::valueOf);
        System.out.println(other.apply("1000"));

    }

    /**
     * * 内置函数式接口
     * * Functions的使用
     * 实现函数的引用
     * 20200114复习
     * compose 和 andThen的不同之处是函数执行的顺序不同。
     * compose 函数先执行参数，然后执行调用者，而 andThen 先执行调用者，然后再执行参数。
     *
     * @throws Exception
     */
    @Test
    public void test41() throws Exception {
        //定义一个执行方法，然后执行，泛型中一个是入参的参数类型，一个是出参的参数类型
        Function<String, String> f1 = String::toUpperCase;
        System.out.println(f1.apply("abc"));
        //先执行转为大写，之后运行字符切割
        System.out.println(f1.andThen(a -> a.substring(0, 1)).apply("aBc"));
        //很andthen执行顺序相反
        System.out.println(f1.compose(a -> ((String) a).substring(0, 1)).apply("aBc"));
        /**
         * new test
         * 执行顺序
         */
        Function<Integer, Integer> f2 = i -> i + 2;
        //16
        System.out.println(f2.andThen(i -> i * i).apply(2));
        //8
        System.out.println(f2.compose(i -> (Integer) i * (Integer) i).apply(2));


    }

    /**
     * Function
     * 执行函数的引用
     *
     * @throws Exception
     */
    @Test
    public void test411() throws Exception {
        Function<String, String> function = this::add;
        System.out.println(function.andThen(this::delete).apply("6"));

    }

    private String add(String end) {
        return end + "end";
    }

    private String delete(String delete) {
        return delete.substring(0, 2);
    }


    /**
     * Consumers接口
     * 执行一个方法，且没有返回值
     */
    @Test
    public void testConsume() {
        Consumer<String> consumer = System.out::println;
        consumer.accept("6666");
        //和funciton类似，拥有andthen
        consumer.andThen(System.out::println).accept("我是Consumers接口");

    }

    /**
     * Supplier  接口
     * 适合new 一个对象
     */
    @Test
    public void testSupplier() {
        Supplier<Exception> s1 = () -> new Exception("我是异常对象");
        System.out.println(s1.get());

        Supplier<String> s2 = () -> new String("我是字符对象");
        System.out.println(s2.get());

    }


    /**
     * Optionals
     *
     * @throws Exception
     */
    @Test
    public void test11() throws Exception {
        //空指针异常
//       Optional<String> optionalS=Optional.empty();
//       System.out.println(optionalS.get());
//        String a="ddd";
        String a = null;
        Optional<String> optionalS = Optional.ofNullable(a);
        optionalS.ifPresent(i -> Assert.assertEquals("111", i));
//        String result=Optional.ofNullable(a).orElse("bei");
        String result = Optional.ofNullable(a).orElseThrow(() -> new RuntimeException("必填"));
//        Optional.ofNullable(a).flatMap()
        System.out.println(result);
    }

    /**
     * Optionals
     * 防止空值异常
     *
     * @throws Exception
     */
    @Test
    public void test121() throws Exception {
        String notNull = "我不是空的";
        Optional<String> o1 = Optional.ofNullable(notNull);
        //是否为空，不为空的话使用Consumers 的acept执行
        o1.ifPresent(System.out::println);
        //用空值来演示
        Optional<String> o2 = Optional.empty();
        System.out.println(o2.orElse("我是代替值"));
        //空值找地方new 一个给他
        System.out.println(o2.orElseGet(() -> new String("我是使用Supplier中get出来的")));
        //空值抛异常
        o2.orElseThrow(() -> new Exception("不可为空"));

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
        IntStream.range(1, 10).forEach(System.out::println);

        //
        list.forEach(System.out::println);
    }

    /**
     * Streams
     * <p>
     * for  flatMap
     *
     * @throws Exception
     */
    @Test
    public void testMap() throws Exception {
        Person person1 = new Person();
        person1.username = "1";
        Person person2 = new Person();
        person2.username = "2";
        Stream.of(person1, person2).flatMap(person -> Stream.of(person.username)).forEach(System.out::print);

    }

    /**
     * Streams
     *
     * @throws Exception
     */
    @Test
    public void test130() throws Exception {
        Collection<String> list = Arrays.asList("22", "33", "53", "11");
        list.stream().map(a -> Integer.valueOf(a) * 2).forEach(System.out::println);
//        list.stream().flatMap()
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
     *
     * @throws Exception
     */
    @Test
    public void test8() throws Exception {
        Collection<String> list = Arrays.asList("22", "22", "53", "11");
        List<String> list1 = list.stream().filter((s) -> Integer.valueOf(s) > 20).collect(Collectors.toList());


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


    //按每3个一组分割
    private static final Integer MAX_NUMBER = 200;

    /**
     * 计算切分次数
     */
    private static Integer countStep(Integer size) {
        return (size + MAX_NUMBER - 1) / MAX_NUMBER;
    }

    public void testSplit() {
        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5, 6, 7);
        int limit = countStep(list.size());
        List<List<Integer>> mglist = new ArrayList<>();
        Stream.iterate(0, n -> n + 1).limit(limit).forEach(i -> {
            mglist.add(list.stream().skip(i * MAX_NUMBER).limit(MAX_NUMBER).collect(Collectors.toList()));
        });

        System.out.println(mglist);

        //方法二：获取分割后的集合
        List<List<Integer>> splitList = Stream.iterate(0, n -> n + 1).limit(limit).parallel().map(a -> list.stream().skip(a * MAX_NUMBER).limit(MAX_NUMBER).parallel().collect(Collectors.toList())).collect(Collectors.toList());

        System.out.println(splitList);
    }

    @Test
    public void should_09() {
        Stream.iterate(0, n -> n + 1).limit(6).forEach(System.out::println);

    }

    @Test
    public void should_reduce() {
        //Reduce Array to String.
        String[] array = {"Mohan", "Sohan", "Mahesh"};
        Arrays.stream(array).reduce((x, y) -> x + "," + y).ifPresent(s -> System.out.println("Array to String: " + s));
        //Reduce List to String.
        List<String> list = Arrays.asList("Mohan", "Sohan", "Mahesh");
        list.stream().reduce((x, y) -> x + "," + y).ifPresent(s -> System.out.println("List to String: " + s));

    }


    @Test
    public void should_sub() {
        List<String> sourceList = Lists.newArrayList();
        IntStream.range(0, 2000).mapToObj(String::valueOf).forEach(sourceList::add);
        List<List<String>> result = Lists.newArrayList();
        long timebegin=System.currentTimeMillis();

        //over time1
        int startIndex = 0;
        int endIndex;
        int limit = 200;
        int size = sourceList.size();
        while (startIndex < size) {
            endIndex = startIndex + limit;
            endIndex = Math.min(endIndex, size);
            List<String> subList = sourceList.subList(startIndex, endIndex);
            result.add(subList);
            startIndex = endIndex;
        }

        //over time10
//        int limit = countStep(sourceList.size());
//        IntStream.range(0,limit).forEach(i -> {
//            result.add(sourceList.stream().skip(i * MAX_NUMBER).limit(MAX_NUMBER).collect(Collectors.toList()));
//        });

        System.out.println("over time"+  (System.currentTimeMillis()-timebegin));


    }


}
