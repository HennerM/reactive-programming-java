package at.hennerbichler.reactiveprogramming.examples;

import io.reactivex.Scheduler;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by markush on 2/18/17.
 */
public class SampleUiScheduler extends io.reactivex.Scheduler {
    @Override
    public Worker createWorker() {
        return Schedulers.computation().createWorker();
    }
}
