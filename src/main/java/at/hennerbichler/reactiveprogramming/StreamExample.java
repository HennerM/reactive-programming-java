package at.hennerbichler.reactiveprogramming;

import java.util.Arrays;
import java.util.stream.Stream;

/**
 * Created by markush on 12/2/16.
 */
public class StreamExample {

    public static void main(String[] args) {
        Stream<String> stream = Arrays.stream(new String[] {"abc", "d", "efg"});
        stream.map(String::toUpperCase)
              .filter(value -> value.length() > 1)
              .forEach(System.out::println);
    }
}
