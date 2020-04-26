package jvm.ref;

import java.lang.ref.WeakReference;

public class WeekRef {

    public static void main(String[] args) {
        WeakReference<M> m = new WeakReference<M>(new M());
        System.out.println(m.get());
        System.gc(); //只要发生垃圾回收就会被回收
        System.out.println(m.get());
    }
}
