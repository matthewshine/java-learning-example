package mutilThread;

import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 单线程池模型，同一时刻只能有一个线程，保证线程是顺序执行的
 */
public class SingleThreadExecutorTest {
    public static void main(String[] args) {

        ExecutorService singlePool = Executors.newSingleThreadExecutor();

        for(int i=0;i<10;i++){
            final  int index = i;

            singlePool.execute(()->{
                System.out.println(new Date()+"---"+index);
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });

        }
    }
}
