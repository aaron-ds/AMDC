package shopmanager;

import org.springframework.stereotype.Component;
import com.google.maps.GeoApiContext;
import com.google.maps.GeocodingApi;
import com.google.maps.errors.ApiException;
import com.google.maps.model.GeocodingResult;
import shopmanager.model.Location;

import java.io.IOException;
import java.util.Collection;

@Component
public class GoogleLocationService implements LocationService {

    private String apiKey = "abcde";
    GeoApiContext context;

    public GoogleLocationService() {
        context = new GeoApiContext().setApiKey(apiKey);
    }

    @Override
    public Location findLocation(String postCode) {

        try {
            GeocodingResult[] results = GeocodingApi
                .geocode(context, postCode)
                .await();
            //results[0].geometry.location;
        } catch (ApiException | InterruptedException | IOException e) {
            e.printStackTrace();
        }
        return null;

    }

    @Override
    public Location calculateClosest(Location target, Collection<Location> locations) {
        return null;
    }
}
