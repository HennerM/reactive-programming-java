package at.hennerbichler.reactiveprogramming.prototype.controller;

import at.hennerbichler.reactiveprogramming.prototype.domain.Inventory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class InventoryController {

    @RequestMapping(value = "/{supplierName}/inventory", method = RequestMethod.GET)
    public ResponseEntity<Inventory[]> get(@PathVariable String supplierName) {
        Inventory[] result;
//        try {
//            Thread.sleep(1000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
        switch (supplierName) {
            case "acme":
                result = new Inventory[]{new Inventory("Schnitzel", 2), new Inventory("Kebap", 1)};
                break;

            case "hofwirt":
                result = new Inventory[]{new Inventory("Schnitzel", 1), new Inventory("Kebap", 0)};
                break;

            default:
                result = new Inventory[]{};
        }
        return new ResponseEntity<>(result, HttpStatus.OK);

    }
}
