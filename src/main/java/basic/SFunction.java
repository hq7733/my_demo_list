package basic;

import java.io.Serializable;
import java.util.function.Function;

/**
 * @author hq7733
 * @date 2022/12/5
 */
@FunctionalInterface
public interface SFunction<T, R> extends Function<T, R>, Serializable {
}
