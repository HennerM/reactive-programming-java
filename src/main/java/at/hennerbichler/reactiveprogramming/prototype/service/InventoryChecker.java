package at.hennerbichler.reactiveprogramming.prototype.service;

import at.hennerbichler.reactiveprogramming.prototype.dal.SupplierRepository;
import at.hennerbichler.reactiveprogramming.prototype.domain.Inventory;
import at.hennerbichler.reactiveprogramming.prototype.domain.InventoryResponse;
import at.hennerbichler.reactiveprogramming.prototype.domain.OrderRequestItem;
import at.hennerbichler.reactiveprogramming.prototype.domain.Supplier;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import io.reactivex.Observable;
import io.reactivex.Single;
import io.reactivex.functions.BiFunction;
import io.reactivex.schedulers.Schedulers;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.Pair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;

@Service
public class InventoryChecker {

    private final SupplierRepository supplierRepository;


    @Autowired
    public InventoryChecker(SupplierRepository supplierRepository) {
        this.supplierRepository = supplierRepository;
    }

    public Observable<InventoryResponse> checkSupplierInStock(OrderRequestItem orderRequestItem) {
        Observable<Supplier> suppliers = this.supplierRepository.findForProduct(orderRequestItem.getProduct());
        Observable<OrderRequestItem> oriObservable = Observable.just(orderRequestItem);

        return suppliers
                .withLatestFrom(oriObservable, ImmutablePair::new)
                .flatMap(requestPair -> checkInventoryFor(requestPair.getRight(), requestPair.getLeft()).toObservable());
    }

    private Single<InventoryResponse> checkInventoryFor(OrderRequestItem orderRequestItem, Supplier supplier) {
        InventoryResponse inventoryResponse = new InventoryResponse(supplier, orderRequestItem, false);

        SupplierService supplierService = buildSupplierService(supplier.getInventoryApi());
        Single<Boolean> availableObservable = productIsAvailable(supplierService.getInventory().subscribeOn(Schedulers.io()), orderRequestItem);

        return Single.zip(Single.just(inventoryResponse), availableObservable, (invResponse, available) -> {
            return new InventoryResponse(invResponse.getSupplier(), invResponse.getOrderRequestItem(), available);
        });

    }

    private static boolean productInStock(Pair<Inventory, OrderRequestItem> pair) {
        return pair.getLeft().getProduct().equals(pair.getRight().getProduct())
                && pair.getLeft().getStock() >= pair.getRight().getAmount();
    }

    private Single<Boolean> productIsAvailable(Observable<List<Inventory>> inventoryListObservable, final OrderRequestItem orderRequestItem) {
        return inventoryListObservable.flatMap(Observable::fromIterable)
                .withLatestFrom(Observable.just(orderRequestItem), ImmutablePair::new)
                .any(InventoryChecker::productInStock);
    }

    private SupplierService buildSupplierService(String inventoryApi) {
        Retrofit.Builder retrofitBuilder = new Retrofit.Builder();
        Retrofit retrofit = retrofitBuilder.baseUrl(inventoryApi)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(JacksonConverterFactory.create())
                .build();
        return retrofit.create(SupplierService.class);
    }


}
