package shopmanager;

import shopmanager.model.Location;

import java.util.Collection;

public interface LocationService {

    Location findLocation(String postCode);
}
