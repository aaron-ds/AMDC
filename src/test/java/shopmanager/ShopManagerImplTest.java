package shopmanager;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import concurrent.InstantExecutorService;
import shopmanager.model.Location;
import shopmanager.model.Shop;
import shopmanager.model.Shop.Address;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ShopManagerImplTest {

    @Mock LocationService mockLocationService;

    @Test
    public void testShopIsAdded() {
        when(mockLocationService.findLocation("W1 1AA")).thenReturn(new Location(1.1, 2.2));
        ShopManagerImpl shopManager = new ShopManagerImpl(mockLocationService, new InstantExecutorService());

//        Shop shop = new Shop();
//        shop.setShopName("My Shop");
//        Shop.Address address = new Address();
//        address.setNumber(5);
//        address.setPostCode("W1 1AA");
//        shop.setShopAddress(address);
//
//        shopManager.addShop(shop);
//
//
//        assertEquals("My Shop", shopManager.getShop("My Shop").getShopName());
    }

    @Test
    public void testShopIsReplaced() {

    }

    @Test
    public void testLocationIsUpdatedAfterShopHasBeenAdded() {

//        Location l = new Location(1.1, 2.2);
//        when(mockLocationService.findLocation("W1 1AA")).thenReturn(l);
//        ShopManagerImpl shopManager = new ShopManagerImpl(mockLocationService, new InstantExecutorService());
//
//        Shop shop = new Shop();
//        shop.setShopName("My Shop");
//        Shop.Address address = new Address();
//        address.setNumber(5);
//        address.setPostCode("W1 1AA");
//        shop.setShopAddress(address);
//
//        shopManager.addShop(shop);
//
//
//        assertEquals("My Shop", shopManager.getShop("My Shop").getShopName());
//        assertEquals(l, shopManager.getShop("My Shop").getShopAddress().getLocation());
    }

    @Test
    public void testLocationIsNotUpdatedIfShopHasChanged() {

    }

    @Test
    public void testIfAShopIsAddedAtTheSameTimeByTwoUsersOnlyOneReceivesAnUpdate() {

    }


}
