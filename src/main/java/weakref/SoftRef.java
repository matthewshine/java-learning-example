package weakref;
import java.lang.ref.WeakReference;
public class SoftRef {
    public static void main(String[] args) {
        A a = new A();
        B b = new B(a);
        a = null;
        System.gc(); //触发full gc
        //如果类 B 不是虚引用类 A 的话，执行 main 方法会出现内存泄漏的问题， 因为类 B 依然依赖于 A
        System.out.println(b.getA());  // null
    }

}
class A {}
class B {
    WeakReference<A> weakReference;
    //classB创建时，通过弱引用指向了a
    public B(A a) {
        weakReference = new WeakReference<>(a);
    }
    //get方法可以判断当前对象是否被清理
    public A getA() {
        return weakReference.get();
    }
}
