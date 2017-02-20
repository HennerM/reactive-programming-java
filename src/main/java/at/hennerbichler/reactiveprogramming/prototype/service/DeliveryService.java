package at.hennerbichler.reactiveprogramming.prototype.service;

import at.hennerbichler.reactiveprogramming.prototype.domain.Article;
import at.hennerbichler.reactiveprogramming.prototype.domain.DeliveryOrder;
import at.hennerbichler.reactiveprogramming.prototype.domain.DeliveryRequest;
import at.hennerbichler.reactiveprogramming.prototype.domain.InventoryResponse;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import io.reactivex.Observable;
import io.reactivex.Single;
import org.springframework.stereotype.Service;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by markush on 1/10/17.
 */
@Service
public class DeliveryService {

    public Observable<DeliveryOrder> requestDelivery(Observable<InventoryResponse> inventoryResponses) {

        DeliveryRestService deliveryRestService = buildDeliveryService();
        return inventoryResponses.groupBy(InventoryResponse::getSupplier)
                .map(supplierGroup -> {
                    DeliveryRequest deliveryRequest = new DeliveryRequest(supplierGroup.getKey(), new ArrayList<>());
                    return supplierGroup.reduce(deliveryRequest, (DeliveryRequest acc, InventoryResponse current) -> {
                        String product = current.getOrderRequestItem().getProduct();
                        int amount = current.getOrderRequestItem().getAmount();
                        acc.articles.add(new Article(current.getSupplier(), product, amount));
                        return acc;
                    });
                })
                .flatMap(Single::toObservable)
                .flatMap(deliveryRequest -> deliveryRestService.doDeliveryOrder(deliveryRequest).toObservable());
    }

    private DeliveryRestService buildDeliveryService() {
        Retrofit.Builder retrofitBuilder = new Retrofit.Builder();
        Retrofit retrofit = retrofitBuilder.baseUrl(DeliveryRestService.DELIVERY_SERVICE_URL)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(JacksonConverterFactory.create())
                .build();
        return retrofit.create(DeliveryRestService.class);
    }

}
