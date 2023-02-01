package thread.completable;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

public class Test {

    public static void main(String[] args) {
        Test test = new Test();
        test.getAllResult();
    }
    public void getAllResult() {
        List<Long> list = new ArrayList<>(3);
        list.add(1000l);
        list.add(10000l);
        list.add(3000l);
        FutureTaskWorker<Long,String> futureTaskWorker = new FutureTaskWorker<>(list,(Long e)->getThreadName(e));
        long begin = System.currentTimeMillis();
        List<String> allResult=futureTaskWorker.getAllResult();
        long end = System.currentTimeMillis();
        System.out.println(allResult);
        System.out.println("结束耗时:"+(end-begin));
    }

    private CompletableFuture<String> getThreadName(long sleepTime) {
        return CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(sleepTime);
                System.out.println(Thread.currentThread().getName()+"已经睡眠"+sleepTime+"毫秒");
                return Thread.currentThread().getName();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return null;
        });
    }
}
