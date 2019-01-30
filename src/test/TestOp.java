package test;

import org.junit.Test;

import java.util.*;

/********************************************************************************
 *
 * Title: 
 *
 * Description:
 *
 * @author chenlm
 * create date on 2018/6/19
 *
 *******************************************************************************/
public class TestOp {
    private  volatile static int a = 1;
    private static  List<String> list=new Vector<>(10);
    private static  List<String> list2= Collections.synchronizedList(new ArrayList<>(9));


    public static void main(String[] args) throws Exception {
//        Class.forName("oracle.jdbc.driver.OracleDriver");
//        Connection conn= DriverManager.getConnection("jdbc:oracle:thin:@10.98.2.10:1521/lytestt","lysi","OGBBXSS5jf");
//        System.out.println(conn);
        test1();




    }

    public  static void test1() {
       List<String> list=new ArrayList<>();
       list.add("1");
       List<Integer> integerList=new ArrayList<>();
       integerList.add(9);
       show(list);
       show(integerList);


    }

    public  static void show(List<?> list) {
        for (Object o : list) {
            System.out.println(o);
        }

    }

    @Test
    public void  test12()throws Exception{
        String a="http://172.18.1.150:80/login";
        System.out.println(a.substring(0,a.lastIndexOf("/")+1));
    }






}
