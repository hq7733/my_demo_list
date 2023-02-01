package thread.completable;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;
import java.util.function.Supplier;
import java.util.stream.Collectors;

public class Completable {

    public static void main(String[] args) throws ExecutionException, InterruptedException, TimeoutException {
        long startTime = System.currentTimeMillis();
        // 创建线程池
        ExecutorService executorService = Executors.newFixedThreadPool(10);

        List<CompletableFuture<Integer>> futures = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            CompletableFuture<Integer> future = CompletableFuture.supplyAsync(new test(), executorService);

            futures.add(future);
        }

        // 使用allOf方法来表示所有的并行任务
        CompletableFuture<Void> allFutures = CompletableFuture.allOf(futures.toArray(new CompletableFuture[futures.size()]));

        // 下面的方法可以帮助我们获得所有子任务的处理结果
        CompletableFuture<List<Integer>> finalResults = allFutures.thenApply(v ->
                futures.stream().map(CompletableFuture::join).collect(Collectors.toList())
        );
        List<Integer> resultList = finalResults.join();
        System.out.println(resultList);


        System.out.println("执行完成，耗时：" + (System.currentTimeMillis() - startTime) / 1000.0);
    }

    static class test implements Supplier<Integer> {
        @Override
        public Integer get() {
            System.out.println("有返回值的异步任务" + Thread.currentThread().getName());
            try {
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            return 1;
        }
    }
}