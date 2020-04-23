package gc;

import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

/********************************************************************************
 *
 * Title: 内存回收日志
 *
 * Description:
 *
 * @author chenlm
 * create date on 2019/12/26
 *
 *******************************************************************************/
public class GcLog {
    public static void main(String[] args) throws Exception{
        Thread t1=new Thread(GcLog::p);
        Thread t2=new Thread(GcLog::p2);
        t1.start();
        t2.start();
        TimeUnit.SECONDS.sleep(30L);


    }
    public static void p(){
        IntStream.range(1,1000).forEach((i)->{
            Person person=new Person();
            person.setName("陈黎明"+i);
            System.out.println(person.getName());
        });

    }
    public static void p2(){
        IntStream.range(1,100).forEach((i)->{
            Person person=new Person();
            person.setName("陈黎明"+i);
            System.out.println(person.getName());
        });

    }


}
