package shopmanager.model;

import org.junit.Test;
import shopmanager.ShopManagerImpl;
import shopmanager.model.Shop;
import shopmanager.model.Shop.Address;

import static org.junit.Assert.assertEquals;

public class ShopTest {

    @Test
    public void testClone() {
        Address a = new Address();
        a.setPostCode("N1");
        a.setNumber(1);
        Shop shop = new Shop();
        shop.setShopName("a shop");
        shop.setShopAddress(a);

        a.setNumber(2);
        a.setPostCode("E1");

    }
}
