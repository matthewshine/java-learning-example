package test;

public class Test3 {

    public static void main(String[] args) {

        String str1 ="abcde";
        String str2 ="a12de";
        for(int i =str2.length();i>0;i--){
            for(int j=0;j<str2.length()-i+1;j++){
                String temp = str2.substring(j,j+i);
                if(str1.indexOf(temp)>0){
                    System.out.println(temp);
                    return;
                }
            }
        }
    }

}
