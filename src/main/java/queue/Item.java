package queue;

import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

/**
 * @author hq7733
 * @date 2023/2/14
 */
public class Item implements Delayed {

    /* 触发时间*/
    private long time;
    String name;

    public Item(String name, long time, TimeUnit unit) {
        this.name = name;
        this.time = System.currentTimeMillis() + (time > 0 ? unit.toMillis(time) : 0);
    }

    /**
     * 需要实现的接口，获得延迟时间   用过期时间-当前时间
     * @param unit
     * @return
     */
    @Override
    public long getDelay(TimeUnit unit) {
        return unit.convert((time) - System.currentTimeMillis(), TimeUnit.MILLISECONDS);
    }
    @Override
    public int compareTo(Delayed o) {
        Item item = (Item) o;
        return (int) (this.time - item.time);
    }
}
