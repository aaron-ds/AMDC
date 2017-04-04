package shopmanager.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import shopmanager.ShopManager;
import shopmanager.model.Location;
import shopmanager.model.Shop;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;


@RestController
public class ShopManagerController {

    private static final Logger log = LoggerFactory.getLogger(ShopManagerController.class);

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
    }

    @RequestMapping(method=GET, name="/shop")
    public Shop handleFindNearestShop(@RequestParam("lat") String latitude, @RequestParam("long") String longitude) {
        Shop shop = shopManager.findClosestShop(new Location(Double.valueOf(latitude), Double.valueOf(longitude)));
        return shop;
    }
}
