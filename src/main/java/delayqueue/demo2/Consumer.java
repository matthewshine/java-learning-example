package delayqueue.demo2;

import java.util.concurrent.DelayQueue;

public class Consumer implements  Runnable{

    private final DelayQueue<Data> queue;
    public Consumer(DelayQueue<Data> queue){
        this.queue=queue;
    }

    @Override
    public void run() {

        while (true){
            Data data = null;
            try {
                data = queue.take();
                System.out.println(Thread.currentThread().getName()+":get" +data);
                Thread.yield();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }


        }
    }
}
