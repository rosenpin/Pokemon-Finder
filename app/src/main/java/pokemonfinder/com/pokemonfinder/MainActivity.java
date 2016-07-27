package pokemonfinder.com.pokemonfinder;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONObject;

import java.util.ArrayList;

import pokemonfinder.com.pokemonfinder.Server.GPSTracker;
import pokemonfinder.com.pokemonfinder.Server.PokeJsonAPI;
import pokemonfinder.com.pokemonfinder.Server.PokemonServer;
import pokemonfinder.com.pokemonfinder.Views.PokemonAdapter;

public class MainActivity extends AppCompatActivity {
    private static final String[] INITIAL_PERMS = {
            Manifest.permission.ACCESS_FINE_LOCATION,
    };
    private static final int INITIAL_REQUEST = 1337;
    private SwipeRefreshLayout refreshLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        refreshLayout = (SwipeRefreshLayout) findViewById(R.id.swiperefresh);
        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                refresh();
            }
        });
        if (!canAccessLocation()) {
            ActivityCompat.requestPermissions(this, INITIAL_PERMS, INITIAL_REQUEST);
        } else {
            refresh();
        }

    }

    public void refresh() {
        GPSTracker gpsTracker = new GPSTracker(MainActivity.this);
        if (gpsTracker.isGPSActive()) {
            gpsTracker.getLocation(getApplicationContext(), null);
            Location loc = gpsTracker.getLocation();
            if (loc != null) {
                double longitude = loc.getLongitude();
                double latitude = loc.getLatitude();
                JSONObject responseJson = PokemonServer.getNearPokemonsByCordinates(latitude + "", longitude + "");
                if (responseJson != null) {
                    ArrayList<Pokemon> pokemons = PokeJsonAPI.getPokemonListByJsonObj(this,responseJson);
                    if (pokemons == null)
                        Log.d(Constants.TAG, "Error in server resopnse");

                    else if (pokemons.size() == 0) {
                        Log.d(Constants.TAG, "There's no pokemons around you :(");

                    } else {
                        ((ListView) findViewById(R.id.poke_list)).setAdapter(new PokemonAdapter(this, pokemons));
                    }
                }
            } else {
                Log.d(Constants.TAG, "Can't get location for unknown reason");
            }
        } else {
            Log.d(Constants.TAG, "GPS is not active!");
        }
        refreshLayout.setRefreshing(false);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case 1337:
                if (canAccessLocation()) {
                    refresh();
                }
                break;
        }
    }

    private boolean canAccessLocation() {
        return (hasPermission(Manifest.permission.ACCESS_FINE_LOCATION));
    }

    private boolean hasPermission(String perm) {
        return (PackageManager.PERMISSION_GRANTED == ContextCompat.checkSelfPermission(this, perm));
    }
}
