package quote;

import lombok.SneakyThrows;

import java.lang.ref.PhantomReference;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;
import java.util.concurrent.TimeUnit;

/**
 * @author hq7733
 * @date 2023/2/8
 */
public class Main {
    @SneakyThrows
    public static void main(String[] args) {
        Obj obj = new Obj();
        obj.setMessage("message");
//        SoftReference<Obj> soft = new SoftReference<Obj>(obj);
//        System.out.println("gc回收之后soft：" + soft);
//        System.gc();
//        TimeUnit.SECONDS.sleep(3L);
//        System.out.println("gc回收之后soft：" + soft);
        WeakReference<Obj> weak = new WeakReference<Obj>(obj);
        System.out.println("gc回收之后weak：" + weak.get());
        // 实际上发生gc也不一定会立刻回收。
        System.gc();
        TimeUnit.SECONDS.sleep(3L);
        System.out.println("gc回收之后weak：" + weak.get());
        Main main = new Main();
        ReferenceQueue<Main> mainReferenceQueue = new ReferenceQueue<Main>();
        PhantomReference<Main> prA = new PhantomReference<Main>(main, mainReferenceQueue);

    }
}
