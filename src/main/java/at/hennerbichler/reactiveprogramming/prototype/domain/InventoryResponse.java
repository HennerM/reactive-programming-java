package at.hennerbichler.reactiveprogramming.prototype.domain;

/**
 * Created by markush on 1/4/17.
 */
public class InventoryResponse {

    private final Supplier supplier;
    private final OrderRequestItem orderRequestItem;
    private final boolean available;

    public InventoryResponse(Supplier supplier, OrderRequestItem orderRequestItem, boolean available) {
        this.supplier = supplier;
        this.orderRequestItem = orderRequestItem;
        this.available = available;
    }

    public Supplier getSupplier() {
        return supplier;
    }

    public OrderRequestItem getOrderRequestItem() {
        return orderRequestItem;
    }

    public boolean isAvailable() {
        return available;
    }
}
