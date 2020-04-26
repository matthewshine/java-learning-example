package mutilThread;

import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class FixedThreadPoolTest {

    /**
     * 线程池大小固定只有3个，同一时刻只能由3个线程执行，其他线程等待
     * 所以每隔3秒会有3个线程启动
     */
    public static void main(String[] args) {

        ExecutorService fixPoll = Executors.newFixedThreadPool(3);
        for(int i=0;i<10;i++){
            final  int index = i;
            fixPoll.execute(()->{
                System.out.println(new Date() +"当前线程"+index);
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });

        }


    }
}
