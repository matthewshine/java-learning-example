package string;

import java.util.Arrays;

public class TestSplit {
    public static void main(String[] args) {

        String str = "1+2-3+5-64*25+25/65";
        String[] array = str.split("\\+|-|\\*|/");
        Arrays.stream(array).forEach(System.out::println);

        String str1 = "image=dddddd&side=bbb";
        String[] array1 = str1.split("image=|&side=");
        Arrays.stream(array1).forEach(System.out::println);

    }
}
