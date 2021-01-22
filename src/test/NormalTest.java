package test;

import com.google.common.collect.Lists;
import designpattern.builder.Person;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.math.BigDecimal;
import java.net.InetSocketAddress;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/********************************************************************************
 *
 * Title: 日常编程测试
 *
 * Description: 用与自己测试实线可行性
 *
 * @author chenlm
 * create date on 2019/2/14
 *
 *******************************************************************************/
public class NormalTest {
    int m = 0;    //统计正面次数
    int n = 0;    //统计反面次数

    private void playCoin() {
        for (int i = 0; i < 1000; i++) {
            double result = Math.random();    //某次抛硬币的结果[0,1)
            if (result >= 0.5) {
                m++;
            } else {
                n++;
            }
        }
        System.out.println("正面：" + m);
        System.out.println("反面：" + n);
    }

    @Test
    public void test1() throws Exception {
        FileReader fileReader = new FileReader(new File(""));
        BufferedReader bufferedReader = new BufferedReader(fileReader);
    }

    @Test
    public void test2() throws Exception {
        Map<String, Integer> map = new HashMap<>();
        map.put("1", 1);
        map.put("2", 2);
        map.put("3", 3);
        System.out.println(map.values());
        Collection collection = map.values();
        List<Integer> list = new ArrayList<>(map.values());

        System.out.println();


    }

    @Test
    public void test3() throws Exception {
        Map<String, Object> map = new HashMap<>();
        map.put("aac001", 1);
        map.put("aac003", 1);
        map.put("aac004", 1);
        System.out.println(transforMapParam(map));


    }

    private String transforMapParam(Map map) {
        StringBuilder sb = new StringBuilder();
        for (Object o : map.keySet()) {
            String temp = o + "=" + map.get(o);
            sb.append(temp);
            sb.append("&");
        }
        sb.deleteCharAt(sb.lastIndexOf("&"));
        return sb.toString();
    }

    @Test
    public void test4() throws Exception {
        String callResult = "成功发送10条个人账目,   发送失败11条个人账目！";
        String sucessMessage = callResult.substring(0, callResult.indexOf(","));
        String sucessCount = sucessMessage.substring(sucessMessage.indexOf("送") + 1, sucessMessage.lastIndexOf("条"));
        System.out.println(sucessCount);


    }

    @Test
    public void test5() throws Exception {
        String b = "123";
        String result = geta(b);
        System.out.println(b);
        System.out.println(result);


    }

    @Test
    public void test61() throws Exception {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMddhhmmss");
        System.out.println(simpleDateFormat.format(new Date()));


    }

    @Test
    public void test62() throws Exception {
        Map<String, String> map = new HashMap<>();
        IntStream.range(0, 1000).forEach((a) -> map.put(String.valueOf(a), String.valueOf(a)));
        List<String> keyList = new ArrayList<>(map.keySet());
        List<String> valuesList = new ArrayList<>(map.values());
        System.out.println(11);


    }

    private String geta(String b) {
        b = "q";
        return b;
    }

    @Test
    public void test6() throws Exception {
        try {

            System.out.println(UUID.randomUUID().toString());
            Person person = getI(Person.class);

        } catch (Exception e) {

        } finally {
            System.out.println("程序中断！");
        }

    }

    private <T> T getI(Class<T> tClass) throws Exception {
        return tClass.newInstance();
    }


    private String get() {
        StringBuilder sb = new StringBuilder();
        sb.append("<?xml version='1.0' encoding='UTF-8'?>                    ");
        sb.append("<Package>                                                 ");
        sb.append("  <PackageHead>                                           ");
        sb.append("    <SJBBH>20190321144738350000293862</SJBBH>             ");
        sb.append("    <JGBS>312000A101</JGBS>                               ");
        sb.append("    <SJBLX>SSHZ101</SJBLX>                                ");
        sb.append("    <DWDM>312000A101</DWDM>                               ");
        sb.append("    <DWMC>国家税务总局福建省税务局</DWMC>                 ");
        sb.append("    <SCRQ>20190321144740</SCRQ>                           ");
        sb.append("    <RECODE_ZT>0</RECODE_ZT>                              ");
        sb.append("    <REMSG_ZT>成功</REMSG_ZT>                             ");
        sb.append("    <SJBBH_SRC>201903180101010000000000000000</SJBBH_SRC> ");
        sb.append("  </PackageHead>                                          ");
        sb.append("  <Data>                                                  ");
        sb.append("    <Record tablename='SWSB_JF_CXJMJFMX' index='1'>       ");
        sb.append("      <XH>1</XH>                                          ");
        sb.append("      <SBYWXTBM>1</SBYWXTBM>                              ");
        sb.append("      <TCQBM_DM>111111</TCQBM_DM>                         ");
        sb.append("      <SBUUID>1</SBUUID>                                  ");
        sb.append("      <ZJTZLSH>1</ZJTZLSH>                                ");
        sb.append("      <DZSPHM>1</DZSPHM>                                  ");
        sb.append("      <SBJBJG_DM>43</SBJBJG_DM>                           ");
        sb.append("      <SSQXXZQH_DM>135</SSQXXZQH_DM>                      ");
        sb.append("      <SQCBH_DM>43211dfa</SQCBH_DM>                       ");
        sb.append("      <RYBH>dfadsh</RYBH>                                 ");
        sb.append("      <ZJHM>34fds</ZJHM>                                  ");
        sb.append("      <ZJLX>34f</ZJLX>                                    ");
        sb.append("      <XM>成分大方</XM>                                   ");
        sb.append("      <XZLX_DM>333</XZLX_DM>                              ");
        sb.append("      <JFLX_DM>22</JFLX_DM>                               ");
        sb.append("      <FKSSQQ>201212</FKSSQQ>                             ");
        sb.append("      <FKSSQZ>201212</FKSSQZ>                             ");
        sb.append("      <GRJFJE>112.55</GRJFJE>                             ");
        sb.append("      <JFZMID>134</JFZMID>                                ");
        sb.append("      <ZCJFRQ>20190319000000</ZCJFRQ>                     ");
        sb.append("      <SBRQ>20190319000000</SBRQ>                         ");
        sb.append("      <JKFSRQ>20190319000000</JKFSRQ>                     ");
        sb.append("      <SJSJ>20190327000000</SJSJ>                         ");
        sb.append("      <JFBS>11</JFBS>                                     ");
        sb.append("      <CXBS>111</CXBS>                                    ");
        sb.append("      <JBSJ>20190319000000</JBSJ>                         ");
        sb.append("      <SJCSLX>1</SJCSLX>                                  ");
        sb.append("      <CSPCH>201903180101010000000000000000</CSPCH>       ");
        sb.append("      <CSSJC>20190319153955</CSSJC>                       ");
        sb.append("    </Record>                                             ");
        sb.append("  </Data>                                                 ");
        sb.append("</Package>                                                ");
        return sb.toString();


    }

    @Test
    public void test9() throws Exception {
        float d = 8.81f;
        System.out.println(d * 100);
    }

    @Test
    public void test10() throws Exception {
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6);

        System.out.println("skip:");
        numbers.stream().skip(2).forEach(System.out::println);

        System.out.println("limit:");
        numbers.stream().limit(2).forEach(System.out::println);


    }

    @Test
    public void test11() throws Exception {

        Double spgz00 = new BigDecimal("6261.08").multiply(new BigDecimal("12")).setScale(0, BigDecimal.ROUND_HALF_UP).doubleValue();
        System.out.println(spgz00);
    }

    @Test
    public void test12() throws Exception {
        String a = "123";
        int b = Integer.parseInt(a);
        Map<Object, String> map = new HashMap<>();
        map.put(null, "fds");
        System.out.println(map.get(null));


    }

    @Test
    public void test13() throws Exception {
        Class c = Class.forName("test.gzTest");
        System.out.println("---");
        c.newInstance();


    }

    @Test
    public void test14() throws Exception {
        Scanner scan = new Scanner(System.in);
        while (scan.hasNextLine()) {
            System.out.println("444");
            //读取键盘输入
            String line = scan.nextLine();
            //将键盘输入的内容输出到SocketChannel中
            System.out.println(line);

        }
    }

    public static void main(String[] args) throws Exception {
        SocketChannel sc = SocketChannel.open(new InetSocketAddress("127.0.0.1", 32299));
        //设置该sc以非阻塞方式工作
        sc.configureBlocking(false);
        //创建键盘输入流
        Scanner scan = new Scanner(System.in);
        while (scan.hasNextLine()) {
            //读取键盘输入
            String line = scan.nextLine();
            //将键盘输入的内容输出到SocketChannel中
            sc.write(Charset.forName("UTF-8").encode(line));
        }
    }

    @Test
    public void test36() {
        Stream.of(1, 2, 3, 4).collect(Collectors.toList()).removeIf(integer -> integer == 4);

//        Stream.of(1, 2, 3, 4).collect(Collectors.toList()).iterator()
    }


    @Test
    public void test15(){
        List<String> list= Lists.newLinkedList();
        list.add("1");
        list.add("2");
        list.add("3");
        System.out.println(list.get(2));

    }
    @Test
    public void  test()throws Exception{
        System.out.println(1.13-0.82);
    }

    @Test
    public void test17(){
        String a=new String("abc");
        String b=new String("abc");
        String c="abc";
        System.out.println(a==c);

    }
    @Test
    public void test27(){
        int a=10;
        Integer b=new Integer(10);
        Integer c=new Integer(10);
        System.out.println(a==b);
        System.out.println(b==c);
        System.out.println(b.getClass());



    }





    @Test
    public void test87()throws Exception{
        System.out.println(this.getClass());
        System.out.println(this.getClass().getClassLoader());
        System.out.println(this.getClass().getClassLoader().getParent());
        System.out.println(this.getClass().getClassLoader().getParent().getParent());

    }

    @Test
    public void t1est87()throws Exception{
        System.out.println(10*24*60*60*1000);
    }
    @Test
    public void should_1(){
        long time=System.currentTimeMillis();
        System.out.println(String.valueOf(time).length());

    }
    @Test
    public void should_map(){
        Map<String,String> map=new HashMap<>();
        System.out.println(map.putIfAbsent("1","1"));
        System.out.println(map.putIfAbsent("1","1"));

    }


}
