package Date;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Test {

    public static void main(String[] args) throws ParseException {

        String date_str = "2020-04-1";
        // 获取当月第一天和最后一天
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String firstday;
        // 获取前月的第一天
        Calendar cale = Calendar.getInstance();
        cale.setTime(format.parse(date_str));
        cale.add(Calendar.MONTH, -3);
        cale.set(Calendar.DAY_OF_MONTH, 1);
        firstday = format.format(cale.getTime());
    }
}
