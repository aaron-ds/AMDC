package shopmanager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import shopmanager.model.Location;
import shopmanager.model.Shop;
import shopmanager.model.Shop.Address;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Component
public class ShopManagerImpl implements ShopManager {

    private ConcurrentMap<String, Shop> shopStore = new ConcurrentHashMap();
    private LocationService locationService;
    private ExecutorService executorService; // = Executors.newFixedThreadPool(10);


    @Autowired
    public ShopManagerImpl(LocationService locationService, ExecutorService executorService) {
        this.locationService = locationService;
        this.executorService = executorService;
    }


    @Override
    public Shop addShop(Shop shop) {
        //does the shop exist with the same details?
        Shop existingShop = shopStore.put(shop.getShopName(), shop);
        //if exising shop has the same postcode and number as the exising shop then don't bother do
        getAndUpdateLocation(shop);
        return existingShop;
    }

    @Override
    public Shop findClosestShop(Location location) {
        //get all of the locations of the shops
        return null;
    }

    public Shop getShop(String shopName) {
        return shopStore.get(shopName);
    }

    private void getAndUpdateLocation(Shop shop) {
        executorService.execute( () -> {
            Location location = locationService.findLocation(shop.getShopAddress().getPostCode());
            if (location != null) {
                Shop shopWithLocation = new Shop();
                shopWithLocation.setShopName(shop.getShopName());
                Address address = shop.getShopAddress();
                address.setLocation(location);
                shopWithLocation.setShopAddress(address);
                shopStore.replace(shop.getShopName(), shop, shopWithLocation);
            }
        });
    }

}
