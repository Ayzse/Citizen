package land.macner.citizen;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONObject;

import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;




/**
 * Created by doug on 6/1/17.
 *
 * This class contains methods that access and return data from Google Civic Information API
 *
 */

public class GCIAAccess {

    private static final String TAG = "GCIA";

    static final String base_url = "https://www.googleapis.com/civicinfo/v2";

    static final String elect = "/elections";
    static final String rep = "/representatives";
    static final String location_endpoint = "";

    static final String key_holder = "?key=";

    HttpURLConnection nt;


    static JSONObject get_reps(String address){

        try {
            URL url = new URL("https", base_url + rep, "?key=" + Constants.GCIA_key + "&address=" + address);
            Log.i(TAG, url.toString());
        }catch(MalformedURLException mue){
            Log.e(TAG, mue.toString());
        }

        return null;
    }

    static JSONObject get_elections(){
        try{
            URL url = new URL("https", base_url + elect, "?key=" + Constants.GCIA_key);
            Log.i(TAG, url.toString());
        }catch(MalformedURLException mue){
            Log.e(TAG, mue.toString());
        }

        return null;
    }


}
