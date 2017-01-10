package at.hennerbichler.reactiveprogramming.prototype;

import at.hennerbichler.reactiveprogramming.prototype.dal.SupplierRepository;
import at.hennerbichler.reactiveprogramming.prototype.domain.*;
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
import org.springframework.web.context.request.async.DeferredResult;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;


@RestController
public class OrderController {

    private InventoryChecker inventoryChecker;

    @Autowired
    public OrderController(InventoryChecker inventoryChecker) {
        this.inventoryChecker = inventoryChecker;
    }

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public DeferredResult<ResponseEntity<List<InventoryResponse>>> get(@RequestBody OrderRequest orderRequest) {
        Observable<OrderRequestItem> orderRequests = Observable.fromIterable(orderRequest.getItems()).observeOn(Schedulers.io());
        Observable<InventoryResponse> inventoryResponses = orderRequests.flatMap(orderRequestItem -> {
            return inventoryChecker.checkSupplierInStock(orderRequestItem).observeOn(Schedulers.io());
        }).filter(InventoryResponse::isAvailable);

        return new DeferredResultAdapter<>(inventoryResponses.toList().map(value -> new ResponseEntity<>(value, HttpStatus.OK)));
    }

}
