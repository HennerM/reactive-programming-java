package at.hennerbichler.reactiveprogramming.examples;

import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by markush on 2/18/17.
 */
public class ConcurrencyExample {


    public static void main(String[] args) throws InterruptedException {

        Observable<String> stringObservable = Observable.fromArray("abc", "d", "efg");
        stringObservable
                .subscribeOn(Schedulers.io())
                .map(String::toUpperCase)
                .filter(value -> value.length() > 1)
                .observeOn(new SampleUiScheduler())
                .subscribe(System.out::println);

        Thread.sleep(1000);

    }

}
