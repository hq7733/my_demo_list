package reflection;

/**
 * @author hq7733
 * @date 2023/2/8
 */
public class Main {
    public static void main(String[] args) {
        Student student = new Student();
        student.setId("1");
        student.setName("hq7733");
        student.setId("18");
        String s = (String) ReflectionUtils.invokeMethodStatic(Student.class, "test", null, null);
        System.out.println(s);
    }
}
