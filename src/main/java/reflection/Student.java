package reflection;

import lombok.Data;

/**
 * @author hq7733
 * @date 2023/2/8
 */
@Data
public class Student {
    private String id;
    private String name;
    private String age;

    private static String test() {
        return "调用成功";
    }
}
