package thread.lock;

import lombok.SneakyThrows;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 买票：超卖demo
 */
public class SellTicket03 {

    private static ReentrantLock reentrantLock = new ReentrantLock();

    public static void main(String[] args) {
        Condition condition1 = reentrantLock.newCondition();
        Condition condition2 = reentrantLock.newCondition();
        // 卖票
        Ticket ticket = new Ticket();
        new Thread(() -> {
            while (true) {
                try {
                    Thread.sleep(50);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                ticket.sale();
            }
        }, "线程一").start();
        new Thread(() -> {
            while (true) {
                try {
                    Thread.sleep(50);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                ticket.sale();
            }
        }, "线程二").start();
        new Thread(() -> {
            while (true) {
                try {
                    Thread.sleep(50);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                ticket.sale();
            }
        }, "线程三").start();
    }
}

class Ticket {
    private int number = 100;

    @SneakyThrows
    public void sale() {
        synchronized (Ticket.class) {
            Thread.sleep(50);
            if (number > 0) {
                System.out.println(Thread.currentThread().getName() + "剩余:" + --number + "张票");
            }
        }
    }
}