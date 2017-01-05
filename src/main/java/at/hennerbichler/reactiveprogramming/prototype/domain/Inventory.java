package at.hennerbichler.reactiveprogramming.prototype.domain;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Inventory {

    private final String product;
    private final int stock;

    @JsonCreator
    public Inventory(@JsonProperty("product") String product, @JsonProperty("stock") int stock) {
        this.product = product;
        this.stock = stock;
    }

    public String getProduct() {
        return product;
    }

    public int getStock() {
        return stock;
    }
}
