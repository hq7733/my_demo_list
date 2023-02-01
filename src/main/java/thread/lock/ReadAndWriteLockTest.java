package thread.lock;

import thread.pool.ThreadPool;

import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * 读写锁的应用
 */
public class ReadAndWriteLockTest {
    static ReentrantReadWriteLock lock = new ReentrantReadWriteLock();

    public static void get(Thread thread) {
        lock.readLock().lock();
        System.out.println("start time:" + System.currentTimeMillis());
        for (int i = 0; i < 1500; i++) {
            try {
                Thread.sleep(20);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(thread.getName() + ":正在进行读操作……");
        }
        System.out.println(thread.getName() + ":读操作完毕！");
        System.out.println("end time:" + System.currentTimeMillis());
        lock.readLock().unlock();
    }

    public static void set(Thread thread){
        lock.writeLock().lock();
        System.out.println("start time:" + System.currentTimeMillis());
        for (int i = 0; i < 15; i++) {
            try {
                Thread.sleep(20);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(thread.getName() + ":正在进行写操作……");
        }
        System.out.println(thread.getName() + ":写操作完毕！");
        System.out.println("end time:" + System.currentTimeMillis());
        lock.writeLock().unlock();
    }

    public static void main(String[] args) {
        ThreadPool.getExecutorService().execute(()->{
            set(Thread.currentThread());
        });
        ThreadPool.getExecutorService().execute(()->{
            get(Thread.currentThread());
        });
        ThreadPool.getExecutorService().execute(()->{
            get(Thread.currentThread());
        });
    }
}
