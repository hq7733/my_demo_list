package thread.pool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadFixedPoll {
    private static ExecutorService executorService;

    private ThreadFixedPoll() {
    }

    public static synchronized ExecutorService getExecutorService() {
        if (executorService == null) {
            executorService = Executors.newFixedThreadPool(10);
        }
        return executorService;
    }
}
