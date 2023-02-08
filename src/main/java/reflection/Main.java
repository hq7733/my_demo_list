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
        ReflectionUtils.setFiledValue(student,Student::getName,"zhang san");
        String name = ReflectionUtils.getFiledName(Student::getName);
        System.out.println(name);
        System.out.println(student.getName());
    }
}
