package at.hennerbichler.reactiveprogramming.examples;

import io.reactivex.Observable;

/**
 * Created by markush on 1/2/17.
 */
public class ReactiveStreamExample {

    public static void main(String[] args) {

        Observable<String> stringObservable = Observable.fromArray("abc", "d", "efg");
        stringObservable
                .map(String::toUpperCase)
                .filter(value -> value.length() > 1)
                .subscribe(System.out::println);

    }

}
