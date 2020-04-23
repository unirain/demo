package retry;

import com.github.rholder.retry.*;
import com.google.common.base.Predicate;
import com.google.common.base.Predicates;
import org.junit.Test;

import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

/********************************************************************************
 *
 * Title: 重试
 *
 * Description:
 *
 * @author chenlm
 * create date on 2019/8/8
 *
 *******************************************************************************/
public class RetyTest {

    @Test
    public  void test1()throws Exception{
        Retryer<Boolean> retryer= RetryerBuilder.<Boolean>newBuilder().retryIfRuntimeException().retryIfException().retryIfResult(Predicates.equalTo(false))
                //等待策略：每次请求间隔1s
                .withWaitStrategy(WaitStrategies.fixedWait(1, TimeUnit.SECONDS))
                //停止策略 : 尝试请求6次
                .withStopStrategy(StopStrategies.stopAfterAttempt(2))
                //时间限制 : 某次请求不得超过2s , 类似: TimeLimiter timeLimiter = new SimpleTimeLimiter();
                .withAttemptTimeLimiter(AttemptTimeLimiters.fixedTimeLimit(2, TimeUnit.SECONDS))
                .build();
        CallPost callPot=new CallPost();
        retryer.call(callPot);


    }


    class CallPost implements Callable<Boolean>{
        int times = 1;

        @Override
        public Boolean call() throws Exception {
            System.out.println("call times="+times);
            times++;

            if (times == 2) {
                throw new NullPointerException();
            } else if (times == 3) {
                throw new Exception();
            } else if (times == 4) {
                throw new RuntimeException();
            } else if (times == 5) {
                return false;
            } else {
                return true;
            }
        }
    }
}
