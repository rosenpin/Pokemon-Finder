package pokemonfinder.com.pokemonfinder.Server;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;

import java.util.Timer;

public class GPSTracker {

    Timer timer1;
    LocationManager lm;
    LocationResult locationResult;
    boolean gps_enabled = false;
    boolean network_enabled = false;
    Context context;
    LocationListener locationListenerNetwork = new LocationListener() {
        public void onLocationChanged(Location location) {
            timer1.cancel();
            if (ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                return;
            }
            lm.removeUpdates(this);
            lm.removeUpdates(locationListenerGps);
        }

        public void onProviderDisabled(String provider) {
        }

        public void onProviderEnabled(String provider) {
        }

        public void onStatusChanged(String provider, int status, Bundle extras) {
        }
    };
    LocationListener locationListenerGps = new LocationListener() {
        @Override
        public void onLocationChanged(Location location) {
            timer1.cancel();
            if (ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                return;
            }
            lm.removeUpdates(this);
            lm.removeUpdates(locationListenerNetwork);
        }

        public void onProviderDisabled(String provider) {
        }

        public void onProviderEnabled(String provider) {
        }

        public void onStatusChanged(String provider, int status, Bundle extras) {
        }
    };

    public GPSTracker(Context context) {
        this.context = context;


    }

    public Location getLocation(Context context, LocationResult result) {
        //I use LocationResult callback class to pass location value from GPSTracker to user code.
        if (lm == null)
            lm = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);

        //exceptions will be thrown if provider is not permitted.
        try {
            gps_enabled = lm.isProviderEnabled(LocationManager.GPS_PROVIDER);
        } catch (Exception ignored) {
        }
        try {
            network_enabled = lm.isProviderEnabled(LocationManager.NETWORK_PROVIDER);
        } catch (Exception ignored) {
        }

        //Toast.makeText(context, gps_enabled+" "+network_enabled,     Toast.LENGTH_LONG).show();

        //don't start listeners if no provider is enabled
        if (!gps_enabled && !network_enabled)
            return null;

        if (gps_enabled)
            if (ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                return null;
            }
        lm.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, locationListenerGps);
        if (network_enabled)
            lm.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0, 0, locationListenerNetwork);
        timer1 = new Timer();

        return null;
    }

    public Location getLocation() {
        if (lm == null)
            lm = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
        Location gpsLocation = null;
        Location netWorkLocation = null;
        Location passiveLocation = null;

        if (lm.isProviderEnabled(LocationManager.GPS_PROVIDER))
            gpsLocation = lm.getLastKnownLocation(LocationManager.GPS_PROVIDER);

        if (lm.isProviderEnabled(LocationManager.NETWORK_PROVIDER))
            netWorkLocation = lm.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);

        if (lm.isProviderEnabled(LocationManager.PASSIVE_PROVIDER))
            passiveLocation = lm.getLastKnownLocation(LocationManager.PASSIVE_PROVIDER);

        if (gpsLocation != null)
            return gpsLocation;

        if (netWorkLocation != null)
            return netWorkLocation;

        if (passiveLocation != null)
            return passiveLocation;

        return null;
    }

    public boolean isGPSActive() {
        final LocationManager manager = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
        return manager.isProviderEnabled(LocationManager.GPS_PROVIDER);
    }


    public static abstract class LocationResult {
        public abstract void gotLocation(Location location);
    }
}
