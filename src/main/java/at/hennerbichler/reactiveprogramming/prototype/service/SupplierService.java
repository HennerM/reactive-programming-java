package at.hennerbichler.reactiveprogramming.prototype.service;

import at.hennerbichler.reactiveprogramming.prototype.domain.Inventory;
import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Url;

import java.util.List;

public interface SupplierService {

    @GET("inventory")
    Observable<List<Inventory>> getInventory();

}
