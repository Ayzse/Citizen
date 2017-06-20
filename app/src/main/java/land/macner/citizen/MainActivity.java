package land.macner.citizen;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.AssetManager;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import org.json.JSONObject;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    static Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = this;

        AssetManager am = context.getApplicationContext().getAssets();
        Typeface typeface = Typeface.createFromAsset(am,
                String.format(Locale.US, "fonts/%s", "fibon_sans_regular.ttf"));

        TextView tv = (TextView)findViewById(R.id.hw);
        tv.setTypeface(typeface);

        /*
        SharedPreferences sp = context.getSharedPreferences("land.macner.citizen.PREFERENCE", Context.MODE_PRIVATE);
        boolean init_required = sp.getInt("init", 0) == 0;
        */

        setContentView(R.layout.activity_main);


        JSONObject reps = GCIAAccess.get_reps("1064FultonDrStreamwoodIllinois60107");
    }
}
