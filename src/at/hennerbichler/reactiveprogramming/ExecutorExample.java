package at.hennerbichler.reactiveprogramming;

import java.util.concurrent.*;

/**
 * Created by markush on 11/30/16.
 */
public class ExecutorExample {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        int nrOfCores = Runtime.getRuntime().availableProcessors();
        ExecutorService executor = Executors.newFixedThreadPool(nrOfCores);
        Callable<String> longOperation = () -> "42";
        Future<String> answerToEverything = executor.submit(longOperation);
        System.out.print(answerToEverything.get());
    }
}
