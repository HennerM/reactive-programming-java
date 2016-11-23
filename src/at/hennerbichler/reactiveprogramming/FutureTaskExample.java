package at.hennerbichler.reactiveprogramming;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public class FutureTaskExample {

       public static void main(String[] args) {

           FutureTask<Integer> futureTask = new FutureTask<>(() -> {
               int result = 0;
               for (int i = 0; i < 1000; i++) {
                   result = result + 1;
               }
               return result;
           });
           new Thread(futureTask).start();
           try {
               System.out.println("Ergebnis: " + futureTask.get().toString());
           } catch (InterruptedException | ExecutionException e) {
               e.printStackTrace();
           }

       }

}
