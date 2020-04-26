package jvm;

import java.util.ArrayList;
import java.util.List;

/**
 * verbose:gc -Xms20M -Xmx20M -Xmn10M -XX:+PrintGCDetails -XX:SurvivorRatio=8
 *
 *  "java.lang.OutOfMemoryError: Java heap space"
 */
public class HeapOOM {
    static class OOMOBject{}

    public static void main(String[] args) {
        List<OOMOBject> list = new ArrayList<OOMOBject>();
        while(true){
            list.add(new OOMOBject());
        }
    }
}
