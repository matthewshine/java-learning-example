package mutilThread;

import java.util.Date;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * 可以延迟程序执行时间，这里程序启动3秒后执行
 */
public class ScheduledThreadPoolTest {
    public static void main(String[] args) {

        ScheduledExecutorService scheduledPoll = Executors.newScheduledThreadPool(5);
        System.out.println("程序点击"+new Date());
        //延迟3秒执行
        scheduledPoll.schedule(()->System.out.println("delay for 3 seconds "+new Date()),3, TimeUnit.SECONDS);


        //定期每隔3秒执行一次，第一次执行延期1s
        scheduledPoll.scheduleAtFixedRate(()->{
            System.out.println("定期执行"+new Date());
        },1,3,TimeUnit.SECONDS);
    }

}


