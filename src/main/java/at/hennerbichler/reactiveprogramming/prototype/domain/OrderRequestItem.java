package at.hennerbichler.reactiveprogramming.prototype.domain;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by markush on 1/4/17.
 */
public class OrderRequestItem {

    private final String product;

    private final int amount;

    @JsonCreator
    public OrderRequestItem(@JsonProperty("product") String product,@JsonProperty("amount") int amount) {
        this.product = product;
        this.amount = amount;
    }

    public String getProduct() {
        return product;
    }

    public int getAmount() {
        return amount;
    }
}
