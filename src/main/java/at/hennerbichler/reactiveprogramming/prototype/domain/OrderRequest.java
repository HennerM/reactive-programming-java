package at.hennerbichler.reactiveprogramming.prototype.domain;

import at.hennerbichler.reactiveprogramming.prototype.domain.OrderRequestItem;
import at.hennerbichler.reactiveprogramming.prototype.domain.UserLocation;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * Created by markush on 1/4/17.
 */
public class OrderRequest {

    private final long customerId;
    private final List<OrderRequestItem> items;
    private final UserLocation location;

    @JsonCreator
    public OrderRequest(@JsonProperty("customerId") long customerId,
                        @JsonProperty("items") List<OrderRequestItem> items,
                        @JsonProperty("location") UserLocation location) {
        this.customerId = customerId;
        this.items = items;
        this.location = location;
    }

    public long getCustomerId() {
        return customerId;
    }

    public List<OrderRequestItem> getItems() {
        return items;
    }

    public UserLocation getLocation() {
        return location;
    }
}
