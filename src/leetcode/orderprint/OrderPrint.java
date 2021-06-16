package leetcode.orderprint;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Lock;
import java.util.function.Consumer;

/********************************************************************************
 *
 * Title: 按序打印
 *
 * Description:
 * 我们提供了一个类：
 *
 * public class Foo {
 *   public void first() { print("first"); }
 *   public void second() { print("second"); }
 *   public void third() { print("third"); }
 * }
 * 三个不同的线程 A、B、C 将会共用一个 Foo 实例。
 *
 * 一个将会调用 first() 方法
 * 一个将会调用 second() 方法
 * 还有一个将会调用 third() 方法
 * 请设计修改程序，以确保 second() 方法在 first() 方法之后被执行，third() 方法在 second() 方法之后被执行。
 *
 *示例 1:
 *
 * 输入: [1,2,3]
 * 输出: "firstsecondthird"
 * 解释:
 * 有三个线程会被异步启动。
 * 输入 [1,2,3] 表示线程 A 将会调用 first() 方法，线程 B 将会调用 second() 方法，线程 C 将会调用 third() 方法。
 * 正确的输出是 "firstsecondthird"。
 * 示例 2:
 *
 * 输入: [1,3,2]
 * 输出: "firstsecondthird"
 * 解释:
 * 输入 [1,3,2] 表示线程 A 将会调用 first() 方法，线程 B 将会调用 third() 方法，线程 C 将会调用 second() 方法。
 * 正确的输出是 "firstsecondthird"。

 *
 * @author chenlm
 * create date on 2021/5/12 0012
 *
 *******************************************************************************/
public class OrderPrint {
    private boolean firstIsOk;
    private boolean secondIsOk;
    private final String key="lock";

    public void first(Runnable printFirst) throws InterruptedException {
        synchronized (key){
            // printFirst.run() outputs "first". Do not change or remove this line.
            printFirst.run();
            firstIsOk=true;
            key.notifyAll();
        }

    }

    public void second(Runnable printSecond) throws InterruptedException {
       synchronized (key){
           while (!firstIsOk){
               key.wait();
           }
           // printSecond.run() outputs "second". Do not change or remove this line.
           printSecond.run();
           secondIsOk=true;
           key.notifyAll();
       }


    }

    public void third(Runnable printThird) throws InterruptedException {
        synchronized (key){
            while (!secondIsOk){
                key.wait();
            }
            // printThird.run() outputs "third". Do not change or remove this line.
            printThird.run();
            key.notifyAll();
        }
    }




}
