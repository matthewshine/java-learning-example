package test;


public class Test1 {
    public static void main(String[] args) {

        int nums [] = {0,1,2,3,4,5,6,7,8,9,10,10,9,8,7,6,5,4,1,2,3,3,0};

        int i = solution(nums);
        System.out.println(i);
    }

    public static int  solution (int[] nums) {
        int res = 0;
        for (int value: nums
        ) {
            res ^= value;
        }
        return res;
    }



}
