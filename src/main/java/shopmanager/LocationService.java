package shopmanager;

import shopmanager.model.Location;

import java.util.Collection;

public interface LocationService {

    Location findLocation(String postCode);

    Location calculateClosest(Location target, Collection<Location> locations);
}
