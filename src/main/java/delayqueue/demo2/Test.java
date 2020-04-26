package delayqueue.demo2;

import java.util.concurrent.DelayQueue;

public class Test {

    /**
     * 生产者不断产生对象放入延时队列，消费者不断尝试读取延时队列的对象
     *
     * 问题：获取过程的线程是阻塞的
     * @param args
     */
    public static void main(String[] args) {

        DelayQueue<Data> queue = new DelayQueue<>();
        Thread thread1 = new Thread(new Producer(queue),"producer-1");
        Thread thread2 = new Thread(new Consumer(queue),"consumer-1");

        thread1.start();
        thread2.start();
    }
}
