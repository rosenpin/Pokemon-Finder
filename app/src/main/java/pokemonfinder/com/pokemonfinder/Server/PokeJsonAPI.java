package pokemonfinder.com.pokemonfinder.Server;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import pokemonfinder.com.pokemonfinder.Pokemon;
import pokemonfinder.com.pokemonfinder.R;

/**
 * Created by Yoav on 7/24/2016.
 */
public class PokeJsonAPI {

    public static int[] pokeImageArr = new int[] { R.drawable.a1,R.drawable.a2,R.drawable.a3,R.drawable.a4,R.drawable.a5,R.drawable.a6,R.drawable.a7,R.drawable.a8,R.drawable.a9,R.drawable.a10,R.drawable.a11,R.drawable.a12,R.drawable.a13,R.drawable.a14,R.drawable.a15,R.drawable.a16,R.drawable.a17,R.drawable.a18,R.drawable.a19,R.drawable.a20,R.drawable.a21,R.drawable.a22,R.drawable.a23,R.drawable.a24,R.drawable.a25,R.drawable.a26,R.drawable.a27,R.drawable.a28,R.drawable.a29,R.drawable.a30,R.drawable.a31,R.drawable.a32,R.drawable.a33,R.drawable.a34,R.drawable.a35,R.drawable.a36,R.drawable.a37,R.drawable.a38,R.drawable.a39,R.drawable.a40,R.drawable.a41,R.drawable.a42,R.drawable.a43,R.drawable.a44,R.drawable.a45,R.drawable.a46,R.drawable.a47,R.drawable.a48,R.drawable.a49,R.drawable.a50,R.drawable.a51,R.drawable.a52,R.drawable.a53,R.drawable.a54,R.drawable.a55,R.drawable.a56,R.drawable.a57,R.drawable.a58,R.drawable.a59,R.drawable.a60,R.drawable.a61,R.drawable.a62,R.drawable.a63,R.drawable.a64,R.drawable.a65,R.drawable.a66,R.drawable.a67,R.drawable.a68,R.drawable.a69,R.drawable.a70,R.drawable.a71,R.drawable.a72,R.drawable.a73,R.drawable.a74,R.drawable.a75,R.drawable.a76,R.drawable.a77,R.drawable.a78,R.drawable.a79,R.drawable.a80,R.drawable.a81,R.drawable.a82,R.drawable.a83,R.drawable.a84,R.drawable.a85,R.drawable.a86,R.drawable.a87,R.drawable.a88,R.drawable.a89,R.drawable.a90,R.drawable.a91,R.drawable.a92,R.drawable.a93,R.drawable.a94,R.drawable.a95,R.drawable.a96,R.drawable.a97,R.drawable.a98,R.drawable.a99,R.drawable.a100,R.drawable.a101,R.drawable.a102,R.drawable.a103,R.drawable.a104,R.drawable.a105,R.drawable.a106,R.drawable.a107,R.drawable.a108,R.drawable.a109,R.drawable.a110,R.drawable.a111,R.drawable.a112,R.drawable.a113,R.drawable.a114,R.drawable.a115,R.drawable.a116,R.drawable.a117,R.drawable.a118,R.drawable.a119,R.drawable.a120,R.drawable.a121,R.drawable.a122,R.drawable.a123,R.drawable.a124,R.drawable.a125,R.drawable.a126,R.drawable.a127,R.drawable.a128,R.drawable.a129,R.drawable.a130,R.drawable.a131,R.drawable.a132,R.drawable.a133,R.drawable.a134,R.drawable.a135,R.drawable.a136,R.drawable.a137,R.drawable.a138,R.drawable.a139,R.drawable.a140,R.drawable.a141,R.drawable.a142,R.drawable.a143,R.drawable.a144,R.drawable.a145,R.drawable.a146,R.drawable.a147,R.drawable.a148,R.drawable.a149,R.drawable.a150,R.drawable.a151 };
    public static String nameKeyJson = "{\"1\": \"Bulbasaur\",\"2\": \"Ivysaur\",\"3\": \"Venusaur\",\"4\": \"Charmander\",\"5\": \"Charmeleon\",\"6\": \"Charizard\",\"7\": \"Squirtle\",\"8\": \"Wartortle\",\"9\": \"Blastoise\",\"10\": \"Caterpie\",\"11\": \"Metapod\",\"12\": \"Butterfree\",\"13\": \"Weedle\",\"14\": \"Kakuna\",\"15\": \"Beedrill\",\"16\": \"Pidgey\",\"17\": \"Pidgeotto\",\"18\": \"Pidgeot\",\"19\": \"Rattata\",\"20\": \"Raticate\",\"21\": \"Spearow\",\"22\": \"Fearow\",\"23\": \"Ekans\",\"24\": \"Arbok\",\"25\": \"Pikachu\",\"26\": \"Raichu\",\"27\": \"Sandshrew\",\"28\": \"Sandslash\",\"29\": \"Nidoran (F)\",\"30\": \"Nidorina\",\"31\": \"Nidoqueen\",\"32\": \"Nidoran (M)\",\"33\": \"Nidorino\",\"34\": \"Nidoking\",\"35\": \"Clefairy\",\"36\": \"Clefable\",\"37\": \"Vulpix\",\"38\": \"Ninetales\",\"39\": \"Jigglypuff\",\"40\": \"Wigglytuff\",\"41\": \"Zubat\",\"42\": \"Golbat\",\"43\": \"Oddish\",\"44\": \"Gloom\",\"45\": \"Vileplume\",\"46\": \"Paras\",\"47\": \"Parasect\",\"48\": \"Venonat\",\"49\": \"Venomoth\",\"50\": \"Diglett\",\"51\": \"Dugtrio\",\"52\": \"Meowth\",\"53\": \"Persian\",\"54\": \"Psyduck\",\"55\": \"Golduck\",\"56\": \"Mankey\",\"57\": \"Primeape\",\"58\": \"Growlithe\",\"59\": \"Arcanine\",\"60\": \"Poliwag\",\"61\": \"Poliwhirl\",\"62\": \"Poliwrath\",\"63\": \"Abra\",\"64\": \"Kadabra\",\"65\": \"Alakazam\",\"66\": \"Machop\",\"67\": \"Machoke\",\"68\": \"Machamp\",\"69\": \"Bellsprout\",\"70\": \"Weepinbell\",\"71\": \"Victreebel\",\"72\": \"Tentacool\",\"73\": \"Tentacruel\",\"74\": \"Geodude\",\"75\": \"Graveler\",\"76\": \"Golem\",\"77\": \"Ponyta\",\"78\": \"Rapidash\",\"79\": \"Slowpoke\",\"80\": \"Slowbro\",\"81\": \"Magnemite\",\"82\": \"Magneton\",\"83\": \"Farfetch'd\",\"84\": \"Doduo\",\"85\": \"Dodrio\",\"86\": \"Seel\",\"87\": \"Dewgong\",\"88\": \"Grimer\",\"89\": \"Muk\",\"90\": \"Shellder\",\"91\": \"Cloyster\",\"92\": \"Gastly\",\"93\": \"Haunter\",\"94\": \"Gengar\",\"95\": \"Onix\",\"96\": \"Drowzee\",\"97\": \"Hypno\",\"98\": \"Krabby\",\"99\": \"Kingler\",\"100\": \"Voltorb\",\"101\": \"Electrode\",\"102\": \"Exeggcute\",\"103\": \"Exeggutor\",\"104\": \"Cubone\",\"105\": \"Marowak\",\"106\": \"Hitmonlee\",\"107\": \"Hitmonchan\",\"108\": \"Lickitung\",\"109\": \"Koffing\",\"110\": \"Weezing\",\"111\": \"Rhyhorn\",\"112\": \"Rhydon\",\"113\": \"Chansey\",\"114\": \"Tangela\",\"115\": \"Kangaskhan\",\"116\": \"Horsea\",\"117\": \"Seadra\",\"118\": \"Goldeen\",\"119\": \"Seaking\",\"120\": \"Staryu\",\"121\": \"Starmie\",\"122\": \"Mr. Mime\",\"123\": \"Scyther\",\"124\": \"Jynx\",\"125\": \"Electabuzz\",\"126\": \"Magmar\",\"127\": \"Pinsir\",\"128\": \"Tauros\",\"129\": \"Magikarp\",\"130\": \"Gyarados\",\"131\": \"Lapras\",\"132\": \"Ditto\",\"133\": \"Eevee\",\"134\": \"Vaporeon\",\"135\": \"Jolteon\",\"136\": \"Flareon\",\"137\": \"Porygon\",\"138\": \"Omanyte\",\"139\": \"Omastar\",\"140\": \"Kabuto\",\"141\": \"Kabutops\",\"142\": \"Aerodactyl\",\"143\": \"Snorlax\",\"144\": \"Articuno\",\"145\": \"Zapdos\",\"146\": \"Moltres\",\"147\": \"Dratini\",\"148\": \"Dragonair\",\"149\": \"Dragonite\",\"150\": \"Mewtwo\",\"151\": \"Mew\"}";

    public static boolean vertifyJson(JSONObject jsonObject)
    {
        try
        {
            String status = jsonObject.getString("status");
            if(status.equals("success"))
                return true;
        } catch (JSONException e)
        {
            return false;
        }

        return false;
    }

    public static String getPokemonNameByID(int id)
    {
        JSONObject jsonObject = null;
        try {
            jsonObject = new JSONObject(nameKeyJson);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        String key = "" + id;
        try {
            String str =  jsonObject.getString(key);
            return str;
        } catch (JSONException e) {
            return "Undefined Pokemon"; // TODO: Handle this error
        } catch (NullPointerException e)
        {
            return "Undefined Pokemon"; // TODO: Handle this error
        }
    }

    public static ArrayList<Pokemon> getPokemonListByJsonObj(JSONObject jsonObj)
    {
        ArrayList<Pokemon> pokemons = new ArrayList<>();

        if(!vertifyJson(jsonObj))
            return null;

        try {
            JSONArray arr = jsonObj.getJSONArray("pokemon");
            for(int i = 0; i < arr.length(); i++)
            {
                JSONObject pokemon = arr.getJSONObject(i);
                String name = getPokemonNameByID(pokemon.getInt("pokemonId"));
                String lat = pokemon.getString("latitude");
                String lon = pokemon.getString("longitude");
                int resourceID = pokeImageArr[pokemon.getInt("pokemonId")];
                pokemons.add(new Pokemon(name, lat, lon, resourceID));
            }


        } catch (JSONException e) {
            return null;
        }

        return pokemons;
    }
}