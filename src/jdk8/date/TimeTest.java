package jdk8.date;

import org.junit.Assert;
import org.junit.Test;
import org.junit.internal.runners.statements.Fail;

import java.time.*;
import java.time.temporal.TemporalAdjuster;
import java.time.temporal.TemporalAdjusters;
import java.time.temporal.TemporalQuery;
import java.util.Date;

/********************************************************************************
 *
 * Title: jdk8时间测试
 *
 * Description:
 *
 * @author chenlm
 * create date on 2020/8/14
 *
 *******************************************************************************/
public class TimeTest {

    /**
     * 日期运算
     * <p>
     * plusXXX和minusXXX方法可以帮助我们计算一段时间之前/之后的日期和时间。
     * withXXX方法需要TemporalAdjuster类型的对象，这个方法可以帮助我们确定今年的第几天这样的问题。
     * TemporalAdjusters类包含了很多现成的实例，可供我们使用
     *
     * @throws Exception
     */
    @Test
    public void test1() throws Exception {
        LocalDate nowDate = LocalDate.now();
//        LocalDate birthday = LocalDate.of(1995, Month.MAY, 8);
        System.out.println(nowDate);
        //十天之后
        LocalDate after10D = nowDate.plusDays(10);
        System.out.println(after10D);
        //十天之前
        LocalDate before10D = nowDate.minusDays(10);
        System.out.println(before10D);
        //一个月前
        System.out.println(nowDate.minusMonths(1));
        //这个月的最后一天
        System.out.println(nowDate.with(TemporalAdjusters.lastDayOfMonth()));

    }

    /**
     * 使用TemporalQuery 可以自定义 查询
     * 下面的例子利用这个接口和lambda表达式实现了一个查询到年底还有几天的查询对象
     *
     * @throws Exception
     */
    @Test
    public void test2() throws Exception {
        TemporalQuery<Integer> remainDaysOfYear = temporal -> {
            //转换时间
            LocalDate tDate = LocalDate.from(temporal);
            //一年的最后一天
            LocalDate lastDate = tDate.with(TemporalAdjusters.lastDayOfYear());
            return Math.toIntExact(lastDate.toEpochDay() - tDate.toEpochDay());
        };
        System.out.println(LocalDate.now().query(remainDaysOfYear));

    }

    /**
     * 使用TemporalQuery 可以自定义 查询
     * <p>
     * 查询当前时间在第几个季度
     *
     * @throws Exception
     */
    @Test
    public void test4() throws Exception {
        TemporalQuery<Integer> quartOfYear = temporal -> {
            Month month = Month.from(temporal);
            return month.getValue() / 3 + 1;
        };
        System.out.println(LocalDate.now().query(quartOfYear));
    }


    /**
     * 新老日期的转换
     * --Instant
     * <p>
     * Instant类一般不单独使用，比较常见的用法就是在新老日期键进行转换。
     * Java 8为旧日期类型全部添加了toInstant()方法，可以将日期转换为Instant实例，
     * 然后将Instant实例转换为新日期类型。反过来也是一样的
     *
     * @throws Exception
     */
    @Test
    public void test5() throws Exception {
        //新日期转老日期
//        Date now=Date.from(Instant.from(LocalDate.now()));//允许接收一个temporal为入参
        Date now = Date.from(Instant.now());

        //旧转新
        Date t = new Date();
        LocalDateTime localDateTime = LocalDateTime.ofInstant(t.toInstant(), ZoneId.systemDefault());
        System.out.println(localDateTime);
    }


    /*两个类表示时间量或两个日期之间的差，两者之间的差异为：Period基于日期值，而Duration基于时间值*/
    /**
     * Period 类
     * Period 类表示一段时间的年、月、日
     *
     * @throws Exception
     */
    @Test
    public void test10() throws Exception {
        LocalDate beginDate=LocalDate.of(2010,Month.JULY,30);
        LocalDate endDate=LocalDate.of(2020,Month.JUNE,30);
        //转换为时间差
        Period period=Period.between(beginDate,endDate);
        //返回false ,说明  每一个值都会大于0
        Assert.assertFalse(period.isNegative());
        //当然 我们也可以对这个period进行增加  减少
        Assert.assertEquals(10,period.plusYears(1).getYears());

    }

    /**
     *
     * Duration
     *
     * Duration类表示秒或纳秒时间间隔，适合处理较短的时间，需要更高的精确性
     * @throws Exception
     */
    @Test
    public void test101() throws Exception {
        LocalTime beginTime=LocalTime.of(20,30,10);
        LocalTime endTime=LocalTime.of(23,20,17);
        Duration duration= Duration.between(beginTime,endTime);
        Assert.assertFalse(duration.isNegative());
        //当然 我们也可以对这个duration进行增加  减少



    }
}
