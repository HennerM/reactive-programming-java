package at.hennerbichler.reactiveprogramming.examples;

import io.reactivex.Observable;

public class TwitterExample {

    class Tweet {
        public String userUrl;
        public String user;
        public String body;
    }

    private static Observable<Tweet> getTweetsByHash(String url) {
        // TODO
        return Observable.fromArray(null);
    }
    private static Observable<Tweet> getTweetsByUser(String url) {
        // TODO
        return Observable.fromArray(null);
    }

    public static void main(String[] args) {

        Observable.fromArray("wuhu", "WuzlHub")
                .flatMap(hash -> getTweetsByHash(hash))
                .distinct(tweet -> tweet.user)
                .flatMap(tweet -> getTweetsByUser(tweet.user).take(1))
                .map(tweet -> tweet.toString())
                .subscribe(text -> System.out.println(text));
        // prints POTUS:  It was an honor...

    }
}
