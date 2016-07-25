package pokemonfinder.com.pokemonfinder;

/**
 * Created by Yoav on 7/24/2016.
 */
public class Pokemon {

    private String name;
    private String lon;
    private String lat;

    public Pokemon(String name, String lat, String lon)
    {
        this.name = name;
        this.lat = lat;
        this.lon = lon;
    }

    public String getLat() {
        return lat;
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

    public void setLon(String lon) {
        this.lon = lon;
    }
}
