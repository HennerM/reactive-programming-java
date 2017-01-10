package at.hennerbichler.reactiveprogramming.prototype.domain;

import java.time.LocalDateTime;
import java.util.List;

public class DeliveryOrder {

    public final LocalDateTime estimatedArrival;
    public final List<Article> articles;

    public DeliveryOrder(LocalDateTime estimatedArrival, List<Article> articles) {
        this.estimatedArrival = estimatedArrival;
        this.articles = articles;
    }


}
