package at.hennerbichler.reactiveprogramming.prototype.domain;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by markush on 1/4/17.
 */
public class UserLocation {

    private final double lat;
    private final double lng;

    @JsonCreator
    public UserLocation(@JsonProperty("lat") double lat, @JsonProperty("lng") double lng) {
        this.lat = lat;
        this.lng = lng;
    }

    public double getLat() {
        return lat;
    }

    public double getLng() {
        return lng;
    }
}
