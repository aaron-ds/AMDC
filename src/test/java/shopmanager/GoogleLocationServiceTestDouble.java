package shopmanager;

import shopmanager.model.Location;

public class GoogleLocationServiceTestDouble implements LocationService {

    @Override
    public Location findLocation(String postCode) {
        return null;
    }
}
