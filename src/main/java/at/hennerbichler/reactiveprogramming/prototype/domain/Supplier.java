package at.hennerbichler.reactiveprogramming.prototype.domain;

/**
 * Created by markush on 1/4/17.
 */
public class Supplier {

    private final String name;

    private final String inventoryApi;

    public Supplier(String name, String inventoryApi) {
        this.name = name;
        this.inventoryApi = inventoryApi;
    }

    public String getName() {
        return name;
    }

    public String getInventoryApi() {
        return inventoryApi;
    }

    @Override
    public String toString() {
        return "Supplier{" +
                "name='" + name + '\'' +
                ", inventoryApi='" + inventoryApi + '\'' +
                '}';
    }
}
