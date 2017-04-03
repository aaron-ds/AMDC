import org.junit.Test;
import com.google.gson.Gson;
import shopmanager.model.Shop;

import static org.junit.Assert.assertTrue;

public class GsonTest {


    @Test
    public void testDeserialization() {

        String json = "{  \n"
            + "   \"shopName\":\"My Shop\",\n"
            + "   \"shopAddress\":{  \n"
            + "      \"number\":1,\n"
            + "      \"postCode\":\"N1 1EH\"\n"
            + "   }\n"
            + "}";

        Gson gson = new Gson();

        Shop shop = gson.fromJson(json, Shop.class);
        assertTrue(shop.getShopName().equals("My Shop"));
    }



}
