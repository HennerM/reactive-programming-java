package at.hennerbichler.reactiveprogramming.prototype.domain;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class DeliveryRequest {

    public final Supplier supplier;
    public final List<Article> articles;

    @JsonCreator
    public DeliveryRequest(@JsonProperty("supplier") Supplier supplier, @JsonProperty("articles") List<Article> articles) {
        this.supplier = supplier;
        this.articles = articles;
    }
}
