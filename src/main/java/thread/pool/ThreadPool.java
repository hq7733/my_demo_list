package thread.pool;

import java.util.concurrent.*;

public class ThreadPool {

    private static ExecutorService executorService = new ThreadPoolExecutor(3, 10,
            1, TimeUnit.MINUTES, new ArrayBlockingQueue<>(5, true),
            Executors.defaultThreadFactory(), new ThreadPoolExecutor.AbortPolicy());

    private ThreadPool(){}
    /**
     * 获取线程池对象
     * @return ExecutorService
     */
    public static ExecutorService getExecutorService(){
        return executorService;
    }
}
