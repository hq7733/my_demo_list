package stream;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author hq7733
 * @date 2023/2/6
 */
public class Main {

    public static void main(String[] args) {
        List<Integer> list = Arrays.asList(2, 4, 1, 2, 10, 250, 10, 12);
        System.out.println(list);
        // 排序
        List<Integer> listSort = list.stream().sorted().collect(Collectors.toList());
        List<Integer> listSort1 = list.stream().sorted((b, a) -> a.compareTo(b)).collect(Collectors.toList());
        System.out.println(listSort);
        System.out.println(listSort1);
        // 过滤
        List<Integer> listFilter = list.stream().filter(item -> item == 10).collect(Collectors.toList());
        System.out.println(listFilter);
        // 去重
        List<Integer> listDistinct = list.stream().distinct().collect(Collectors.toList());
        System.out.println(listDistinct);
        // 分组
        Map<Integer, List<Integer>> listGroup = list.stream().collect(Collectors.groupingBy(integer -> integer));
        System.out.println(listGroup);
        // 返回指定个数
        List<Integer> listLimit = list.stream().limit(3).collect(Collectors.toList());
        System.out.println(listLimit);
        // 去除前n条
        List<Integer> listSkip = list.stream().skip(3).collect(Collectors.toList());
        System.out.println(listSkip);
        // 实现类似于分页操作
        int page = 1;
        int size = 2;
        List<Integer> listPage = list.stream().skip(page * size).limit(size).collect(Collectors.toList());
        System.out.println(listPage);
        // 判断是否存在
        boolean listMatch = list.stream().anyMatch(action -> action == 1);
        System.out.println(listMatch);
    }
}
