package at.hennerbichler.reactiveprogramming.prototype;

import io.reactivex.Single;
import org.springframework.web.context.request.async.DeferredResult;

public class DeferredResultAdapter<T> extends DeferredResult<T> {

    private static final long DEFAULT_TIMEOUT = 50000;

    public DeferredResultAdapter(Single<T> observable) {
        this(observable, DEFAULT_TIMEOUT);
    }

    public DeferredResultAdapter(Single<T> observable, long timeout) {
        super(timeout);
        observable.subscribe(this::setResult, this::setErrorResult);
    }


}
