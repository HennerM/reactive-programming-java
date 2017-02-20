package at.hennerbichler.reactiveprogramming.prototype.controller;

import at.hennerbichler.reactiveprogramming.prototype.domain.DeliveryOrder;
import at.hennerbichler.reactiveprogramming.prototype.domain.DeliveryRequest;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

@RestController
public class DeliveryController {

    @RequestMapping(value = "/delivery/order", method = RequestMethod.POST)
    public DeliveryOrder orderDelivery(@RequestBody DeliveryRequest request) {
        return new DeliveryOrder(LocalDateTime.now().plus(30, ChronoUnit.MINUTES), request.articles);
    }
}
