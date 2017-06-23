package land.macner.citizen;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.AssetManager;
import android.graphics.Typeface;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.transition.Fade;
import android.transition.Scene;
import android.transition.Transition;
import android.transition.TransitionManager;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.json.JSONObject;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "main";

    Scene welcome_scene;
    Scene login_scene;
    Scene name_scene;
    Scene address_scene;
    Scene homepage_scene;

    Context context;
    Typeface fibon_sans;

    String usernameS;
    String passwordS;
    String conf_passwordS;

    String firstNameS;
    String lastNameS;
    String preferredNameS;

    String street_addr_1S;
    String street_addr_2S;
    String cityS;
    String stateS;
    String zipcodeS;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = this;

        AssetManager am = context.getApplicationContext().getAssets();
        fibon_sans = Typeface.createFromAsset(am,
                String.format(Locale.US, "font/%s", "fibon_sans_regular.ttf"));

        /*
        SharedPreferences sp = context.getSharedPreferences("land.macner.citizen.PREFERENCE", Context.MODE_PRIVATE);
        boolean init_required = sp.getInt("init", 0) == 0;
        */
        boolean init_required = true;
        if(init_required){
            setContentView(R.layout.welcome);

            usernameS = "username";
            passwordS = "password";
            conf_passwordS = "password";

            firstNameS = "First name";
            lastNameS = "Last name";
            preferredNameS = "Preferred name";

            street_addr_1S = "Street Address 1";
            street_addr_2S = "Street Address 2";
            cityS = "City";
            stateS = "State";
            zipcodeS = "Zipcode";

            welcome();
        }else {

            setContentView(R.layout.activity_main);

            TextView tv = (TextView) findViewById(R.id.hw);
            tv.setTypeface(fibon_sans);

        }


        JSONObject reps = GCIAAccess.get_reps("1064FultonDrStreamwoodIllinois60107");
    }

    void welcome(){

        TextView welcome = (TextView) findViewById(R.id.Welcome);
        welcome.setTypeface(fibon_sans);

        TextView to = (TextView) findViewById(R.id.to);
        to.setTypeface(fibon_sans);

        TextView citizen = (TextView) findViewById(R.id.citizen);
        citizen.setTypeface(fibon_sans);

        ViewGroup sceneRoot = (ViewGroup) findViewById(R.id.scene_root);
        if(Build.VERSION.SDK_INT >= 19) {
            welcome_scene = Scene.getSceneForLayout(sceneRoot, R.layout.welcome, context);
            login_scene = Scene.getSceneForLayout(sceneRoot, R.layout.create_login, context);
        }

        Button begin = (Button) findViewById(R.id.begin);
        begin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i(TAG, "beginning view transition");
                if(Build.VERSION.SDK_INT >= 19) {
                    Transition mTransition = new Fade();
                    TransitionManager.go(login_scene, mTransition);
                    createLogin();
                }

            }
        });
    }

    void createLogin(){
        TextView greeting = (TextView) findViewById(R.id.usepass);
        final EditText username = (EditText) findViewById(R.id.username);
        final EditText password = (EditText) findViewById(R.id.password);
        final EditText confirmPassword = (EditText) findViewById(R.id.confirm_password);
        Button next = (Button) findViewById(R.id.login_next);

        username.setText(usernameS);
        password.setText(passwordS);
        password.setText(conf_passwordS);

        greeting.setTypeface(fibon_sans);
        username.setTypeface(fibon_sans);
        password.setTypeface(fibon_sans);
        confirmPassword.setTypeface(fibon_sans);
        next.setTypeface(fibon_sans);

        final ViewGroup sceneRoot = (ViewGroup) findViewById(R.id.cl_scene_root);
        /*if(Build.VERSION.SDK_INT >= 19) {
            welcome_scene = Scene.getSceneForLayout(sceneRoot, R.layout.welcome, context);
            login_scene = Scene.getSceneForLayout(sceneRoot, R.layout.create_login, context);
        }*/

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                usernameS = username.getText().toString();
                passwordS = password.getText().toString();
                conf_passwordS = confirmPassword.getText().toString();

                if(Build.VERSION.SDK_INT >= 19) {
                    Transition mTransition = new Fade();
                    name_scene = Scene.getSceneForLayout(sceneRoot, R.layout.ask_name, context);
                    TransitionManager.go(name_scene, mTransition);
                    askName();
                }
            }
        });
    }

    void askName(){
        TextView greeting = (TextView) findViewById(R.id.ask_name_box);
        final EditText first_name = (EditText) findViewById(R.id.first_name);
        final EditText last_name = (EditText) findViewById(R.id.last_name);
        final EditText preferred_name = (EditText) findViewById(R.id.preferred_name);
        Button next = (Button) findViewById(R.id.ask_name_next);
        Button previous = (Button) findViewById(R.id.ask_name_previous);

        first_name.setText(firstNameS);
        last_name.setText(lastNameS);
        preferred_name.setText(preferredNameS);

        greeting.setTypeface(fibon_sans);
        first_name.setTypeface(fibon_sans);
        last_name.setTypeface(fibon_sans);
        preferred_name.setTypeface(fibon_sans);
        next.setTypeface(fibon_sans);
        previous.setTypeface(fibon_sans);

        final ViewGroup sceneRoot = (ViewGroup) findViewById(R.id.an_scene_root);
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                firstNameS = first_name.getText().toString();
                lastNameS = last_name.getText().toString();
                preferredNameS = preferred_name.getText().toString();

                if(Build.VERSION.SDK_INT >= 19){
                    Transition mTransition = new Fade();
                    address_scene = Scene.getSceneForLayout(sceneRoot, R.layout.ask_address, context);
                    TransitionManager.go(address_scene, mTransition);
                    askAddress();
                }
            }
        });

        previous.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                firstNameS = first_name.getText().toString();
                lastNameS = last_name.getText().toString();
                preferredNameS = preferred_name.getText().toString();

                if(Build.VERSION.SDK_INT >= 19){
                    Transition mTransition = new Fade();
                    login_scene = Scene.getSceneForLayout(sceneRoot, R.layout.create_login, context);
                    TransitionManager.go(login_scene, mTransition);
                    createLogin();
                }
            }
        });

    }

    void askAddress(){
        TextView prompt = (TextView) findViewById(R.id.address_prompt);
        final EditText street_1 = (EditText) findViewById(R.id.street_address_1);
        final EditText street_2 = (EditText) findViewById(R.id.street_address_2);
        final EditText city = (EditText) findViewById(R.id.city);
        final EditText state = (EditText) findViewById(R.id.state);
        final EditText zipcode = (EditText) findViewById(R.id.zipcode);
        Button finish = (Button) findViewById(R.id.ask_address_finish);
        Button previous = (Button) findViewById(R.id.ask_address_previous);

        street_1.setText(street_addr_1S);
        street_2.setText(street_addr_2S);
        city.setText(cityS);
        state.setText(stateS);
        zipcode.setText(zipcodeS);

        prompt.setTypeface(fibon_sans);
        street_1.setTypeface(fibon_sans);
        street_2.setTypeface(fibon_sans);
        city.setTypeface(fibon_sans);
        state.setTypeface(fibon_sans);
        zipcode.setTypeface(fibon_sans);
        finish.setTypeface(fibon_sans);
        previous.setTypeface(fibon_sans);

        final ViewGroup sceneRoot = (ViewGroup) findViewById(R.id.aa_scene_root);
        finish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                street_addr_1S = street_1.getText().toString();
                street_addr_2S = street_2.getText().toString();
                cityS = city.getText().toString();
                stateS = state.getText().toString();
                zipcodeS = zipcode.getText().toString();

                if(Build.VERSION.SDK_INT >= 19){
                    Transition mTransition = new Fade();
                    homepage_scene = Scene.getSceneForLayout(sceneRoot, R.layout.activity_main, context);
                    TransitionManager.go(homepage_scene, mTransition);
                    homepage();
                }
            }
        });

        previous.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                street_addr_1S = street_1.getText().toString();
                street_addr_2S = street_2.getText().toString();
                cityS = city.getText().toString();
                stateS = state.getText().toString();
                zipcodeS = zipcode.getText().toString();

                if(Build.VERSION.SDK_INT >= 19){
                    Transition mTransition = new Fade();
                    name_scene = Scene.getSceneForLayout(sceneRoot, R.layout.ask_name, context);
                    TransitionManager.go(name_scene, mTransition);
                    askName();
                }
            }
        });
    }

    void homepage(){

    }
}
