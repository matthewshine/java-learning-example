package string;


public class Test4 {
    public static void main(String[] args) {
        long starttime = System.currentTimeMillis();
        Long sum =0l;
        for(long i=0;i<Integer.MAX_VALUE;i++){
            sum+=i;
        }
        long endtime = System.currentTimeMillis();
        System.out.println(sum);
        System.out.println((endtime-starttime));
    }
}
