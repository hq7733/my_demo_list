package queue;


import lombok.SneakyThrows;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.DelayQueue;
import java.util.concurrent.TimeUnit;

/**
 * @author hq7733
 * @date 2023/2/14
 */
public class Main {
    @SneakyThrows
    public static void main(String[] args) {
        Item item1 = new Item("item1", 5, TimeUnit.SECONDS);
        Item item2 = new Item("item2", 1, TimeUnit.MINUTES);
        Item item3 = new Item("item3", 15, TimeUnit.SECONDS);
        DelayQueue<Item> queue = new DelayQueue<Item>();
        queue.put(item1);
        queue.add(item2);
        queue.add(item3);
        while (!queue.isEmpty()) {
            Item take = queue.take();
            System.out.format("name:{%s}, time:{%s}\n", take.name, LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss")));
        }
    }
}
