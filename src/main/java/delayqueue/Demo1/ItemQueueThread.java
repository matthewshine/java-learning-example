package delayqueue.Demo1;

import java.util.Date;
import java.util.Map;
import java.util.concurrent.DelayQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;

/**
 * 延迟队列 存放有效期对象
 *
 */
public class ItemQueueThread
{
    private static final Logger logger = Logger.getLogger(ItemQueueThread.class);

    private ItemQueueThread()
    {
    }

    /**
     * 延迟加载(线程安全)
     *
     */
    private static class LazyHolder
    {
        private static ItemQueueThread itemQueueThread = new ItemQueueThread();
    }

    //返回一个ItemQueue的单例
    public static ItemQueueThread getInstance()
    {
        return LazyHolder.itemQueueThread;
    }

    /**
     * 缓存线程池
     */
    ExecutorService executor = Executors.newCachedThreadPool();

    /**
     * 线程
     */
    private Thread daemonThread;

    /**
     * 初始化线程
     */
    public void init()
    {
        daemonThread = new Thread(() -> {
            try
            {
                execute();
            }
            catch (InterruptedException e)
            {
                e.printStackTrace();
                logger.info(e.getMessage());
            }
        });
        System.out.println("init------------------start");
        daemonThread.start();
    }

    private void execute()
            throws InterruptedException
    {
        while (true)
        {
            Map<Thread, StackTraceElement[]> map = Thread.getAllStackTraces();
            System.out.println("线程数--------------" + map.size());
            System.out.println(System.currentTimeMillis());
            System.out.println(item.size());
            System.out.println("线程状态---------" + Thread.currentThread().getState());
            boolean flag=false;
            try
            {
                // 从延迟队列中取值,如果没有对象过期则队列一直等待，
                DelayItem<?> t1 = item.take();
                if (t1 != null)
                {
                    Object task = t1.getTask();
                    if (task == null)
                    {
                        continue;
                    }
                    if ( task instanceof WxMessage){
                        if(((WxMessage) task).getToName().equals("gaoliang")){
                            System.out.println("这是一个要被取消的任务"+((WxMessage) task).getFromname());
                            flag=true;
                        }else {

                            System.out.printf("%s发送消息--%s-->%sat %s%n" , ((WxMessage) task).getFromname(), ((WxMessage) task).getContent()
                                    , ((WxMessage) task).getToName(), new Date());
                        }

                    }

                }
            }
            catch (Exception e)
            {
                e.printStackTrace();
                logger.info(e.getMessage());
            }
        }
    }

    /**
     * 创建空的延迟队列
     */
    private DelayQueue<DelayItem<?>> item = new DelayQueue<>();

    /**
     * 往队列中添加任务
     *
     * @param time 延迟时间
     * @param task 任务
     * @param timeUnit 时间单位
     *
     */
    @SuppressWarnings({"unchecked", "rawtypes"})
    public void put(long time, Object task, TimeUnit timeUnit)
    {
        // 转换成ns
        long nanoTime = TimeUnit.NANOSECONDS.convert(time, timeUnit);
        DelayItem<?> k = new DelayItem(nanoTime, task);
        item.put(k);
    }

    /**
     * 结束任务
     *
     * @param task
     */
    public boolean endTask(DelayItem<Runnable> task)
    {
        return item.remove(task);
    }

}

