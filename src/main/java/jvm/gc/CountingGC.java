package jvm.gc;

public class CountingGC {
    public Object instance =null;
    private static final int _1Mb = 1024*1024;
    private byte [] bigSize = new byte[2* _1Mb];

    public static void main(String[] args) {
        CountingGC objA = new CountingGC();
        CountingGC objB = new CountingGC();

        objA.instance = objB;
        objB.instance = objA;

    System.gc();
    }
}
