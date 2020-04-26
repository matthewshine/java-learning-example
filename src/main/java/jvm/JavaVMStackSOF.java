package jvm;

/**
 * -Xss128k
 */
public class JavaVMStackSOF {
    private int stackLength =1;
    public void statckLeak(){
        stackLength++;
        statckLeak();
    }

    public static void main(String[] args) {
        JavaVMStackSOF oom = new JavaVMStackSOF();
        try {
            oom.statckLeak();
        } catch (Exception e) {
            System.out.println("stack length="+oom.stackLength);
            e.printStackTrace();
        }

    }
}
