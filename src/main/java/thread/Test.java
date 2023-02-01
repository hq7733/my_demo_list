package thread;

import thread.lock.LockTest;
import thread.pool.ThreadPool;

/**
 * @author hq7733
 * @version 1.0
 * @date 2022/2/2 1:34 PM
 */
public class Test {

    volatile static Integer index = 0;

    public static void main(String[] args) {
        Test test = new Test();
        test.lockTest();
    }

    private void lockTest(){
        LockTest lockTest = new LockTest();
        ThreadPool.getExecutorService().execute(() -> {
            lockTest.writePrint(Thread.currentThread());
        });
        ThreadPool.getExecutorService().execute(() -> {
            lockTest.readPrint(Thread.currentThread());
        });
        ThreadPool.getExecutorService().execute(() -> {
            lockTest.readPrint(Thread.currentThread());
        });
    }

    static void test(int name,int t){
        synchronized (Test.class){
            System.out.println("thread:"+name+"；index："+t);
        }
    }


}

