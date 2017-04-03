package shopmanager.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;
import shopmanager.ShopManager;
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

    @RequestMapping(method=POST, name="/shop")
    public String handleCreateShop(@RequestBody String shop) {
        //do some validation on the shop json
        Gson gson = new Gson();
        Shop s = gson.fromJson(shop, Shop.class);
        System.out.println("POST called with " + s.getShopName());
        return "shop was created";
    }

    @RequestMapping(method=GET, name="/shop")
    public String handleFindNearestShop() {
        //do some validation of the location json
        System.out.println("GET called");
        return "found a shop";
    }
}
