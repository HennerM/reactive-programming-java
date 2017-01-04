package at.hennerbichler.reactiveprogramming.prototype.service;

import at.hennerbichler.reactiveprogramming.prototype.dal.SupplierRepository;
import at.hennerbichler.reactiveprogramming.prototype.domain.InventoryResponse;
import at.hennerbichler.reactiveprogramming.prototype.domain.OrderRequestItem;
import at.hennerbichler.reactiveprogramming.prototype.domain.Supplier;
import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created by markush on 1/4/17.
 */
public class InventoryChecker {

    private final SupplierRepository supplierRepository;

    @Autowired
    public InventoryChecker(SupplierRepository supplierRepository) {
        this.supplierRepository = supplierRepository;
    }

    public Observable<InventoryResponse> checkSupplierInStock(OrderRequestItem orderRequestItem) {
        List<Supplier> suppliers = this.supplierRepository.findForProduct(orderRequestItem.getProduct());
        return Observable.fromIterable(suppliers)
                .observeOn(Schedulers.io())
                .flatMap(supplier -> checkInventoryFor(orderRequestItem, supplier));
    }

    private Observable<InventoryResponse> checkInventoryFor(OrderRequestItem orderRequestItem, Supplier supplier) {
        return Observable.empty();
    }


}
