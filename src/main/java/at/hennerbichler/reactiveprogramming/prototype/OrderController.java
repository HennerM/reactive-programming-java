package at.hennerbichler.reactiveprogramming.prototype;

import at.hennerbichler.reactiveprogramming.prototype.dal.SupplierRepository;
import at.hennerbichler.reactiveprogramming.prototype.domain.InventoryResponse;
import at.hennerbichler.reactiveprogramming.prototype.domain.OrderRequest;
import at.hennerbichler.reactiveprogramming.prototype.domain.OrderRequestItem;
import at.hennerbichler.reactiveprogramming.prototype.domain.Supplier;
import at.hennerbichler.reactiveprogramming.prototype.service.InventoryChecker;
import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
public class OrderController {

    private InventoryChecker inventoryChecker;

    @Autowired
    public OrderController(InventoryChecker inventoryChecker) {
        this.inventoryChecker = inventoryChecker;
    }

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public ResponseEntity<List<InventoryResponse>> get(@RequestBody OrderRequest orderRequest) {
        Observable<OrderRequestItem> orderRequests = Observable.fromIterable(orderRequest.getItems()).observeOn(Schedulers.io());
        Observable<InventoryResponse> inventoryRespones = orderRequests.flatMap(orderRequestItem -> {
            return inventoryChecker.checkSupplierInStock(orderRequestItem);
        }).filter(inventoryResponse -> inventoryResponse.isAvailable());

        return new ResponseEntity<>(inventoryRespones.toList().blockingGet(), HttpStatus.OK);
    }

}
