package thread.create;

import lombok.var;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * 创建线程
 *
 * @author hq7733
 * @version 1.0
 * @date 2022/1/30 12:54 PM
 */
public class Create {

    public static void main(String[] args) throws Exception {
        test();
    }

    private static void test() throws ExecutionException, InterruptedException {
        // 方法一
        var thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 10; i++) {
                    System.out.println(Thread.currentThread().getName() + "：" + i);
                }
            }
        });
        //方法二
        var thread2 = new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                System.out.println("线程2：" + i);
            }
        });
        thread1.setName("线程1");
        thread1.start();

        // 方法三
        FutureTask futureTask = new FutureTask<Integer>((Callable<Integer>) () -> {
            return 1;
        });
        new Thread(futureTask).start();
        System.out.println(futureTask.get());
        ;

        // 方法四
        Runnable runnable = () -> {
        };
        new Thread(runnable, "线程一").start();
    }

}
