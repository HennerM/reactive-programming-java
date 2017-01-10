package at.hennerbichler.reactiveprogramming.prototype.domain;


import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Article {

    public final Supplier supplier;
    public final String product;
    public final int amount;

    @JsonCreator
    public Article(@JsonProperty("supplier") Supplier supplier,@JsonProperty("product") String product,@JsonProperty("amount") int amount) {
        this.supplier = supplier;
        this.product = product;
        this.amount = amount;
    }
}
