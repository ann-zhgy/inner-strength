package cn.ann.util;

import java.time.Duration;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

/**
 * Description： 计算程序运行时间的工具类.
 * </p>
 *
 * @author 88475
 */
public class Times {
    private Times() {
    }

    private static final DateTimeFormatter DATE_TIME_FORMATTER
            = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss", Locale.CHINA);

    @FunctionalInterface
    public interface Task {
        /**
         * 具体执行的代码
         */
        void execute();
    }

    public static void test(String title, Task task) {
        if (task == null) {
            return;
        }
        title = (title == null) ? "" : ("【" + title + "】");
        System.out.println(title);
        System.out.println("开始：" + DATE_TIME_FORMATTER.format(LocalDateTime.now()));
        Instant start = Instant.now();
        task.execute();
        Instant end = Instant.now();
        System.out.println("结束：" + DATE_TIME_FORMATTER.format(LocalDateTime.now()));
        System.out.printf("耗时：%d豪秒\n", Duration.between(start, end).toMillis());
        System.out.println("-------------------------------------");
    }
}
