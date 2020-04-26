package jvm;

import java.util.ArrayList;
import java.util.List;

/**
 * 在jdk1.6,1.7,1.8下执行会有不同的效果。
 *
 *
 * -XX:MetaspaceSize
 */
public class StringOomMock {
    static String  base = "string";
    public static void main(String[] args) {
        List<String> list = new ArrayList<String>();
        for (int i=0;i< Integer.MAX_VALUE;i++){
            String str = base + base;
            base = str;
            list.add(str.intern()); //如果创建的字符串在不存在，就添加入方法区
        }
    }
}