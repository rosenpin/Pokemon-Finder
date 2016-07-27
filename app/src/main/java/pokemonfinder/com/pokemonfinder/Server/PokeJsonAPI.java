package pokemonfinder.com.pokemonfinder.Server;

import android.content.Context;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import pokemonfinder.com.pokemonfinder.Constants;
import pokemonfinder.com.pokemonfinder.Pokemon;

public class PokeJsonAPI {

    public static boolean isValidJson(JSONObject jsonObject) {
        try {
            String status = jsonObject.getString("status");
            if (status.equals("success"))
                return true;
        } catch (JSONException e) {
            return false;
        }

        return false;
    }

    public static String getPokemonNameByID(int id) {
        JSONObject jsonObject = null;
        try {
            jsonObject = new JSONObject(Constants.nameKeyJson);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        String key = "" + id;
        try {
            return jsonObject != null ? jsonObject.getString(key) : null;
        } catch (JSONException e) {
            return "Undefined Pokemon"; // TODO: Handle this error
        } catch (NullPointerException e) {
            return "Undefined Pokemon"; // TODO: Handle this error
        }
    }

    public static ArrayList<Pokemon> getPokemonListByJsonObj(Context context, JSONObject jsonObj) {
        ArrayList<Pokemon> pokemons = new ArrayList<>();
        if (!isValidJson(jsonObj))
            return null;
        try {
            JSONArray arr = jsonObj.getJSONArray("pokemon");
            for (int i = 0; i < arr.length(); i++) {
                JSONObject pokemon = arr.getJSONObject(i);
                String name = getPokemonNameByID(pokemon.getInt("pokemonId"));
                String lat = pokemon.getString("latitude");
                String lon = pokemon.getString("longitude");
                int resourceID = Constants.pokeImageArr[pokemon.getInt("pokemonId") - 1];
                pokemons.add(new Pokemon(name, lat, lon, resourceID));
            }
            for (int i = 0; i < pokemons.size() - 1; i++) {
                int distance1 = pokemons.get(i).getDistance(context);
                int distance2 = pokemons.get(i + 1).getDistance(context);
                if (distance1 > distance2) {
                    Pokemon temp = pokemons.get(i);
                    Pokemon temp2 = pokemons.get(i + 1);
                    pokemons.set(i + 1, temp);
                    pokemons.set(i, temp2);
                    i = -1;
                } else if (distance1 == distance2 && pokemons.get(i).getName().equals(pokemons.get(i + 1).getName())) {
                    //Remove duplicate Pokemon
                    pokemons.remove(i);
                }
            }
        } catch (JSONException e) {
            return null;
        }
        return pokemons;
    }
}
