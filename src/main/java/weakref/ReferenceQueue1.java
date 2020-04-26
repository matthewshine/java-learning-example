package weakref;

import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;
import java.util.concurrent.TimeUnit;

public class ReferenceQueue1 {
    public static void main(String[] args) throws InterruptedException {
        ReferenceQueue<Man> referenceQueue= new ReferenceQueue<Man>();
        WeakReference<Man> weak=new WeakReference<Man>(new Man(),referenceQueue);
        System.out.println("man对应的虚引用对象："+weak.hashCode());
        System.gc();
        TimeUnit.SECONDS.sleep(1);
        System.out.println("垃圾回收后 man"+weak.get());
        System.out.println("引用队列里面的对象"+referenceQueue.poll().hashCode());
    }
}
