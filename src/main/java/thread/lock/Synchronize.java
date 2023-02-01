package thread.lock;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static thread.pool.ThreadPool.getExecutorService;

/**
 * 等待线程都执行完统一返回
 */
public class Synchronize {

    public static void main(String[] args) {
        Synchronize synchronize =new Synchronize();
        synchronize.test1();
    }
    public void test1() {
        long startTime = System.currentTimeMillis();
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        getExecutorService().execute(() -> {
            for (int i = 0; i < 100; i++) {
                System.out.println("测试a：" + System.currentTimeMillis());
            }
        });
        getExecutorService().execute(() -> {
            for (int i = 0; i < 100; i++) {
                System.out.println("测试b：" + System.currentTimeMillis());
            }
        });
        getExecutorService().shutdown();
        while (true) {
            if (getExecutorService().isTerminated()) {
                System.out.println("线程执行完成，共耗时：" + (System.currentTimeMillis() - startTime));
                break;
            }
        }
    }
}
