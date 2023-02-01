package util;

import java.util.Arrays;

/**
 * @author hq7733
 * @version 1.0
 * @date 2022/1/17 5:07 PM
 */
public class ArrayUtils {

    /**
     * 合并Integer数组后排序
     *
     * @param integers1 数组1
     * @param integers2 数组2
     * @return Integer[]
     */
    public static Integer[] t(Integer[] integers1, Integer[] integers2) {
        Integer[] integers = new Integer[integers1.length + integers2.length];
        System.arraycopy(integers1, 0, integers, 0, integers1.length);
        System.arraycopy(integers2, 0, integers, integers1.length, integers2.length);
        Arrays.sort(integers);
        return integers;
    }

}

