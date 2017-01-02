package at.hennerbichler.reactiveprogramming;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 * Created by markush on 12/3/16.
 */
public class CompleteableFutureExample {


    public static void main(String[] args) throws ExecutionException, InterruptedException {
        CompletableFuture<Integer> cf = CompletableFuture
                .supplyAsync(() -> 20)
                .thenApplyAsync(x -> x * 2)
                .thenApplyAsync(x -> x + 2);
    }


}
