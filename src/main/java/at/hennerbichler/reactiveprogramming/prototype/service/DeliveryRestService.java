package at.hennerbichler.reactiveprogramming.prototype.service;

import at.hennerbichler.reactiveprogramming.prototype.domain.DeliveryOrder;
import at.hennerbichler.reactiveprogramming.prototype.domain.DeliveryRequest;
import at.hennerbichler.reactiveprogramming.prototype.domain.InventoryResponse;
import io.reactivex.Observable;
import io.reactivex.Single;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * Created by markush on 1/10/17.
 */
public interface DeliveryRestService {

    String DELIVERY_SERVICE_URL = "http://localhost:8080/delivery/";

    @POST("order")
    Observable<DeliveryOrder> doDeliveryOrder(@Body DeliveryRequest deliveryRequest);
}
