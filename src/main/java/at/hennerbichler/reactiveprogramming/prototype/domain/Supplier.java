package at.hennerbichler.reactiveprogramming.prototype.domain;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by markush on 1/4/17.
 */
public class Supplier {

    private final String name;

    private final String inventoryApi;
    private final int id;

    @JsonCreator
    public Supplier(@JsonProperty("id") int id, @JsonProperty("string") String name, @JsonProperty("inventoryApi") String inventoryApi) {
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
