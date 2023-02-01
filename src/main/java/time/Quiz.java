package time;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;

public class Quiz {
    public static void main(String[] args) {
        // 获取当前时间
        LocalDateTime now = LocalDateTime.now();
        // 获取指定时间
        LocalDateTime specify = LocalDateTime.of(2018, 5, 20, 5, 20, 0);
        // 比较大小，前者是否大于后者
        System.out.println(specify.isAfter(now));
        // 格式化输出,yyyy-MM-dd HH:mm:ss
        System.out.println(now.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH-mm-ss")));
        // 获取年
        System.out.println(now.getYear());
        // 获取月
        System.out.println(now.getMonthValue());
        // 获取日
        System.out.println(now.getDayOfMonth());
        // 获取小时
        System.out.println(now.getHour());
        // 获取分钟
        System.out.println(now.getMinute());
        // 获取秒
        System.out.println(now.getSecond());
        // 获取星期
        System.out.println(now.getDayOfWeek().getValue());
        // 获取一个星期后的日期
        System.out.println(now.plusWeeks(1));
        // 获取一个星期钱的日期
        System.out.println(now.minusWeeks(1));
        // 获取时间戳
        System.out.println(now.toInstant(ZoneOffset.UTC));

    }
}
