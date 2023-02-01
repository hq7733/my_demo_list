package thread.lock;

import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * 读写锁
 */
public class LockTest {
    static ReentrantReadWriteLock readWriteLock = new ReentrantReadWriteLock();
    private ReentrantLock reentrantLock = new ReentrantLock();

    public void readPrint(Thread thread) {
        readWriteLock.writeLock().lock();
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
        readWriteLock.writeLock().unlock();
    }

    public void writePrint(Thread thread) {
        readWriteLock.readLock().lock();
        System.out.println("start time:" + System.currentTimeMillis());
        for (int i = 0; i < 15; i++) {
            try {
                Thread.sleep(20);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(thread.getName() + ":正在进行读操作……");
        }
        System.out.println(thread.getName() + ":读操作完毕！");
        System.out.println("end time:" + System.currentTimeMillis());
        readWriteLock.readLock().unlock();
    }
}
