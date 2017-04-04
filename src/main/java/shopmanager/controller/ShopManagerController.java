package shopmanager.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;
import shopmanager.ShopManager;
import shopmanager.model.Location;
import shopmanager.model.Shop;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;


@RestController
public class ShopManagerController {

    private ShopManager shopManager;

    @Autowired
    public ShopManagerController(ShopManager shopManager) {
        this.shopManager = shopManager;
    }

    @RequestMapping(method=POST, name="/shop", produces = "application/json")
    public Shop handleCreateShop(@RequestBody Shop shop) {
        System.out.println("POST called with " + shop.getShopName());
        Shop existingShop = shopManager.addShop(shop);
        return existingShop;
//        return existingShop != null ? existingShop.getShopName() : "{\"response\": \"success\"}";
    }

    @RequestMapping(method=GET, name="/shop")
    public String handleFindNearestShop(@RequestParam("lat") String latitude, @RequestParam("long") String longitude) {
        //do some validation of the parameters

        Shop shop = shopManager.findClosestShop(new Location(Double.valueOf(latitude), Double.valueOf(longitude)));
        System.out.println("GET called");
        System.out.println(shop.getShopName());
        return "lat = " + latitude + ", long = " + longitude;
    }
}
