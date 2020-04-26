package jvm;

import java.util.ArrayList;
import java.util.List;

/**
 * -XX:PermSize=100M -XX:MaxPermSize=100M
 * JDK 1.8中 PermSize 和 MaxPermGen 已经无效。因此，
 * 可以大致验证 JDK 1.7 和 1.8 将字符串常量由永久代转移到堆中，并且 JDK 1.8 中已经不存在永久代的结论。
 */
public class RuntimeConstantPoolOOM {
    public static void main(String[] args) {
        List<String> list = new ArrayList<String>();
        int i =0;
        while(true){
            list.add(String.valueOf(i++).intern());
        }
    }
}
