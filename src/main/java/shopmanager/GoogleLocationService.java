package shopmanager;

import org.springframework.stereotype.Component;
import com.google.maps.DistanceMatrixApi;
import com.google.maps.GeoApiContext;
import com.google.maps.GeocodingApi;
import com.google.maps.errors.ApiException;
import com.google.maps.model.DistanceMatrix;
import com.google.maps.model.GeocodingResult;
import shopmanager.model.Location;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class GoogleLocationService implements LocationService {

    private String apiKey = "AIzaSyCSJxPgsqD4N8Ji9qDj87lQqmkL9aI8KSw";
    GeoApiContext context;

    public GoogleLocationService() {
        context = new GeoApiContext().setApiKey(apiKey);
    }

    @Override
    public Location findLocation(String postCode) {

        try {
            GeocodingResult[] results = GeocodingApi.geocode(context, postCode).await();
            System.out.println(results[0].geometry.location);
            return new Location(results[0].geometry.location.lat, results[0].geometry.location.lng);
        } catch (ApiException | InterruptedException | IOException e) {
            e.printStackTrace();
        }
        return null;

    }

    @Override
    public Location calculateClosest(Location target, Collection<Location> locations) {
        String[] origin = extractCoOrdinates(Arrays.asList(target));
        String[] destinations = extractCoOrdinates(locations);
        System.out.println(origin);
        System.out.println(destinations);
        try {
            DistanceMatrix distanceMatrix = DistanceMatrixApi
                .getDistanceMatrix(context, origin, destinations)
                .await();
            System.out.println(distanceMatrix.rows[0].elements[0].distance.inMeters);
//            System.out.println(distanceMatrix.rows[0].elements[0].);

        } catch (ApiException | InterruptedException | IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private String[] extractCoOrdinates(Collection<Location> locations) {
        List<String> list = locations.stream()
                                     .map(l -> l.getLatitude() + "," + l.getLongitude())
                                     .collect(Collectors.toList());
        return list.toArray(new String[list.size()]);
    }

    private Location getClosest(String[] destinations) {

        return null;
    }
}
