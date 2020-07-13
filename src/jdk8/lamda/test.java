package jdk8.lamda;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

/********************************************************************************
 *
 * Title: 
 *
 * Description:
 *
 * @author chenlm
 * create date on 2018/8/17
 *
 *******************************************************************************/
public class test {
    private static int count = 123;

    public static void main(String[] args) {

        Lambda4 lambda4=new Lambda4();
        lambda4.testScopes();

//        Lambda4.test test=lambda4.new test();
//        System.out.println(test.convert(55));
        System.out.println(lambda4.outerStaticNum);

    }

    @Test
    public  void test1() {
        Converter<Integer, String> converter = String::valueOf;
        int a = 1234;
        System.out.println(converter.convert(a));
        System.out.println(converter.sort(19,39));
    }
    private static void test2(){
        Converter<Integer,String> converter=(from)->{
            count=35;
            return  String.valueOf(from);
        };
        System.out.println(converter.convert(555));
        System.out.println(count);
    }

    @Test
    public void test3()throws Exception{
        Predicate<String> predicate=(s)->s.length()>1;
        predicate.test("12");
        System.out.println();
        System.out.println(predicate.negate().test("12222"));
        Runnable runnable=this::test1;
    }

    @Test
    public void test4()throws Exception{
        String name= "ee";
        change(name);
        System.out.println(name);
    }
    private void change(String name){
        name="888";
//        System.out.println(name);
    }

    @Test
    public void test41()throws Exception{
        List<String> testlist=new ArrayList<>(1);
        testlist.add("1");
        testlist.add("2");
    }
}
