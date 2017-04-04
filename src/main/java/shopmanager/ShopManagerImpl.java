package shopmanager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import shopmanager.model.Location;
import shopmanager.model.Shop;
import shopmanager.model.Shop.Address;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.ExecutorService;

@Component
public class ShopManagerImpl implements ShopManager {

    private ConcurrentMap<String, Shop> shopStore = new ConcurrentHashMap();
    private LocationService locationService;
    private ExecutorService executorService;


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
        Shop closest = null;
        double closestDistance = Double.MAX_VALUE;
        for (Shop shop : shopStore.values()) {
            if (shop.getShopAddress().getLocation() != null) {
                double distance = location.distanceTo(shop.getShopAddress().getLocation());
                if (distance < closestDistance) {
                    System.out.println("distance of " + shop.getShopName() + " is " + distance);
                    closestDistance = distance;
                    closest = shop;
                }
            }
        }

        return closest;
    }

    private void getAndUpdateLocation(Shop shop) {
        executorService.execute( () -> {
            Location location = locationService.findLocation(shop.getShopAddress().getPostCode());
            if (location != null) {
//                shopWithLocation.setShopName(shop.getShopName());
                Address address = shop.getShopAddress();
                address.setLocation(location);
//                shopWithLocation.setShopAddress(address);
                Shop shopWithLocation = new Shop(shop.getShopName(), address);
                shopStore.replace(shop.getShopName(), shop, shopWithLocation);
            }
        });
    }

    Shop getShop(String shopName) {
        return shopStore.get(shopName);
    }

}
