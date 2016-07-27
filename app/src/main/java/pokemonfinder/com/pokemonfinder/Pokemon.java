package pokemonfinder.com.pokemonfinder;

import android.content.Context;
import android.location.Location;

import pokemonfinder.com.pokemonfinder.Server.GPSTracker;

public class Pokemon {

    private String name;
    private String lon;
    private String lat;
    private int resourceID;

    public Pokemon(String name, String lat, String lon, int respurceId) {
        this.name = name;
        this.lat = lat;
        this.lon = lon;
        this.resourceID = respurceId;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLng() {
        return lon;
    }

    public String getLat() {
        return lat;
    }

    public void setLon(String lon) {
        this.lon = lon;
    }

    public int getResourceID() {
        return resourceID;
    }

    public void setResourceID(int resourceID) {
        this.resourceID = resourceID;
    }

    public int getDistance(Context context) {
        GPSTracker gpsTracker = new GPSTracker(context);
        Location pokemonLocation = new Location("");
        pokemonLocation.setLatitude(Double.parseDouble(lat));
        pokemonLocation.setLongitude(Double.parseDouble(lon));
        return (int) gpsTracker.getLocation().distanceTo(pokemonLocation);
    }
}
