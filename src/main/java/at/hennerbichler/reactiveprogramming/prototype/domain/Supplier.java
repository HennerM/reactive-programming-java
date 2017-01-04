package at.hennerbichler.reactiveprogramming.prototype.domain;

/**
 * Created by markush on 1/4/17.
 */
public class Supplier {

    private final String name;

    private final String inventoryApi;
    private final int id;

    public Supplier(int id,String name, String inventoryApi) {
        this.id = id;
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
