package at.hennerbichler.reactiveprogramming.prototype;

import at.hennerbichler.reactiveprogramming.prototype.domain.OrderRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by markush on 1/4/17.
 */

@RestController
public class OrderController {

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public ResponseEntity<OrderRequest> get(@RequestBody OrderRequest orderRequest) {



        return new ResponseEntity<OrderRequest>(orderRequest, HttpStatus.OK);
    }

}
