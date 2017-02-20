package at.hennerbichler.reactiveprogramming.prototype.controller;

import at.hennerbichler.reactiveprogramming.prototype.DeferredResultAdapter;
import at.hennerbichler.reactiveprogramming.prototype.dal.SupplierRepository;
import at.hennerbichler.reactiveprogramming.prototype.domain.*;
import at.hennerbichler.reactiveprogramming.prototype.service.DeliveryService;
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

    private DeliveryService deliveryService;

    @Autowired
    public OrderController(InventoryChecker inventoryChecker, DeliveryService deliveryService) {
        this.inventoryChecker = inventoryChecker;
        this.deliveryService = deliveryService;
    }

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public DeferredResult<ResponseEntity<List<DeliveryOrder>>> makeOrder(@RequestBody OrderRequest orderRequest) {

        Observable<OrderRequestItem> orderRequests = Observable.fromIterable(orderRequest.getItems());

        Observable<InventoryResponse> inventoryResponses = orderRequests
                .flatMap(orderRequestItem -> inventoryChecker.checkSupplierInStock(orderRequestItem)
                        .filter(InventoryResponse::isAvailable)
                        .take(1));

        Observable<DeliveryOrder> deliveryOrders = deliveryService.requestDelivery(inventoryResponses);
        return new DeferredResultAdapter<>(deliveryOrders.toList().map(value -> new ResponseEntity<>(value, HttpStatus.CREATED)));
    }

}
