package thread.topic;

import lombok.SneakyThrows;
import thread.pool.ThreadPool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 三个线程交替打印ABC，打印一百个
 */
public class Topic1 {
    private static volatile int index = 0;
    private static ReentrantLock reentrantLock = new ReentrantLock();

    public static void main(String[] args) {
        Condition printACondition = reentrantLock.newCondition();
        Condition printBCondition = reentrantLock.newCondition();
        Condition printCCondition = reentrantLock.newCondition();
        ExecutorService executorService = ThreadPool.getExecutorService();
        ExecutorService executorService1 = Executors.newFixedThreadPool(10);
        //======================================方法一=============================================================
        executorService.execute(new volatileTest("A", 0));
        executorService.execute(new volatileTest("B", 1));
        executorService.execute(new volatileTest("C", 2));
        //======================================方法二=============================================================
        /*executorService.execute(new conditionTest(34, "A", printACondition, printBCondition, reentrantLock));
        executorService.execute(new conditionTest(33, "B", printBCondition, printCCondition, reentrantLock));
        executorService.execute(new conditionTest(33, "C", printCCondition, printACondition, reentrantLock));*/
        //======================================方法三=============================================================
        /*Thread thread1 = new Thread(new test(34, "A", printACondition, printBCondition, reentrantLock));
        Thread thread2 = new Thread(new test(33, "B", printBCondition, printCCondition, reentrantLock));
        Thread thread3 = new Thread(new test(33, "C", printCCondition, printACondition, reentrantLock));
        thread1.start();
        thread2.start();
        thread3.start();*/
    }

    // 利用condition来进行顺序执行
    static class conditionTest implements Runnable {
        private ReentrantLock lock;
        private int count;
        private String str;
        private int index = 0;
        private Condition currentCondition;
        private Condition nextCondition;

        public conditionTest(int count, String str, Condition currentCondition, Condition nextCondition, ReentrantLock lock) {
            this.count = count;
            this.str = str;
            this.currentCondition = currentCondition;
            this.nextCondition = nextCondition;
            this.lock = lock;
        }

        @Override
        public void run() {
            while (true) {
                lock.lock();
                if (count >= index) {
                    System.out.println(str);
                    this.index++;
                }
                nextCondition.signal();
                try {
                    currentCondition.await();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                lock.unlock();
            }
        }
    }


    // 通过volatile来进行顺序执行
    static class volatileTest implements Runnable {
        private final String str;

        private int remainder;

        public volatileTest(String str, int remainder) {
            this.str = str;
            this.remainder = remainder;
        }

        @Override
        public void run() {
            while (index < 100) {
                try {
                    Thread.sleep(50);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                if (index % 3 == remainder) print(str, index);
            }
        }

        @SneakyThrows
        public void print(String str, int row) {
            System.out.println("窗口" + Thread.currentThread().getName() + "，index：" + row + "，str：" + str);
            index++;
        }
    }
}
