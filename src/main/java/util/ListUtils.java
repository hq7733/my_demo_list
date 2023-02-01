package util;


import java.util.Iterator;
import java.util.List;
import java.util.function.Predicate;

/**
 * list工具类
 *
 * @author hq7733
 * @date 2023/2/1
 */
public class ListUtils {

    public static <T> void remove(List<T> list, Predicate<T> predicate) {
        Iterator<T> it = list.iterator();
        while (it.hasNext()) {
            boolean result = predicate.test(it.next());
            if (result) {
                it.remove();
            }
        }
    }
}
