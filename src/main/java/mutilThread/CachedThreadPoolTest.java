package mutilThread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CachedThreadPoolTest {
    /**
     * 创建一个可缓存线程池，如果线程池长度超过处理需要，可灵活回收空闲线程，若无可回收，则新建线程。示例代码如下
     *
     * @param args
     */
    public static void main(String[] args) {
        ExecutorService cachePoll = Executors.newCachedThreadPool();

        for(int i=0;i<10;i++){
            final  int index =i;

            try {
                Thread.sleep(index*1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            cachePoll.execute(()->{
                System.out.println(index);
            });


        }




    }
}
