package reflection;

import basic.SFunction;
import lombok.SneakyThrows;

import java.lang.invoke.SerializedLambda;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Optional;
import java.util.regex.Pattern;

/**
 * @author hq7733
 * @date 2023/2/8
 */
public class ReflectionUtils {

    private static final Pattern GET_PATTERN = Pattern.compile("^get[A-Z].*");
    private static final Pattern IS_PATTERN = Pattern.compile("^is[A-Z].*");

    @SneakyThrows
    public static <T> T newInstance(Class<T> classObject) {
        return classObject.getDeclaredConstructor().newInstance();
    }


    public static <T, S, R> void setFiledValue(T t, SFunction<S, R> action, Object value) {
        String fieldName = (String) Optional.ofNullable(getFiledName(action)).orElse("");

        try {
            Field field = t.getClass().getDeclaredField(fieldName);
            field.setAccessible(true);
            field.set(t, value);
        } catch (IllegalAccessException | NoSuchFieldException var5) {
            var5.printStackTrace();
        }
    }

    public static <T, R> R getFiledValue(T t, SFunction<T, R> action) {
        String fieldName = (String) Optional.ofNullable(getFiledName(action)).orElse("");
        Field field = null;

        try {
            field = t.getClass().getField(fieldName);
            return (R) field.get(t);
        } catch (IllegalAccessException | NoSuchFieldException var5) {
            var5.printStackTrace();
            return null;
        }
    }

    public static <T, R> String getFiledName(SFunction<T, R> action) {
        Class<?> clazz = action.getClass();
        try {
            Method method = clazz.getDeclaredMethod("writeReplace");
            method.setAccessible(true);
            SerializedLambda lambda = (SerializedLambda) method.invoke(action);
            return uncapitalize(lambda.getImplMethodName());
        } catch (Exception e) {
            if (!Object.class.isAssignableFrom(clazz.getSuperclass())) {
                return getFiledName(action);
            }
            throw new RuntimeException("current property is not exists");
        }
    }

    private static String uncapitalize(String str) {
        if (str == null || str.length() < 4) {
            return str;
        }
        String fieldName = str.startsWith("get") ?
                str.substring(3) : str.startsWith("is") ? str.substring(2) : str;
        return fieldName.substring(0, 1).toLowerCase() + fieldName.substring(1);
    }
}
