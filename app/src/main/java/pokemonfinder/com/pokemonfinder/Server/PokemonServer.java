package pokemonfinder.com.pokemonfinder.Server;

import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.provider.MediaStore;
import android.util.Log;
import android.widget.Toast;

import org.apache.commons.io.IOUtils;
import org.apache.http.HttpResponse;
import org.apache.http.HttpVersion;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.conn.ClientConnectionManager;
import org.apache.http.conn.scheme.PlainSocketFactory;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.tsccm.ThreadSafeClientConnManager;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpParams;
import org.apache.http.params.HttpProtocolParams;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;
import java.net.Authenticator;
import java.net.InetSocketAddress;
import java.net.MalformedURLException;
import java.net.PasswordAuthentication;
import java.net.Proxy;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

import javax.net.ssl.HttpsURLConnection;

import pokemonfinder.com.pokemonfinder.Consts;


/**
 * Created by Yoav on 8/23/2015.
 */
public class PokemonServer {

    public static JSONObject getNearPokemonsByCordinates(String lat, String lng) {

        JSONObject jsonObject = null;

        try {
            jsonObject = new Request("/map/data", lat, lng).execute().get();
            return jsonObject;
        } catch (Exception e) {
            Log.d(Consts.TAG, "Error in getNearPokemonsByCordinates function" + e.getMessage());
            return null;
        }
    }

    private static class Request extends AsyncTask<String, String, JSONObject> {

        String request;
        String lat, lng;

        public Request(String request, String lat, String lng) {
            this.request = request;
            this.lat = lat;
            this.lng = lng;
        }

        @Override
        protected JSONObject doInBackground(String... args) {
            JSONParser json = new JSONParser();
            String myUrl = Consts.REQUEST_PREFIX + "" + request + "/" + lat + "/" + lng;
            try {
                URL url = new URL(myUrl);
                URLConnection urlConnection = url.openConnection();
                InputStream in = urlConnection.getInputStream();

                String result = IOUtils.toString(in, StandardCharsets.UTF_8);

                try {
                    JSONObject obj = new JSONObject(result);
                    Log.d(Consts.TAG, obj.toString());
                    return obj;
                } catch (Throwable t) {
                    Log.e(Consts.TAG, "Could not parse malformed JSON: \"" + json + "\"");
                }
            } catch (ClientProtocolException e) {
                Log.d(Consts.TAG , e.toString() + myUrl);
            } catch (IOException e) {
                Log.d(Consts.TAG , e.toString() + myUrl);
            }

            return null;

        }



        @Override
        protected void onPostExecute(JSONObject json) {


        }
    }
}