package in.co.discoveideas.presentsir;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import in.co.discoveideas.presentsir.SQLiteHandler;
import in.co.discoveideas.presentsir.SessionManager;

public class MainActivity extends AppCompatActivity {

    private SessionManager session;
    private SQLiteHandler db;
    ProgressDialog nDialog;
    private static int SPLASH_TIME_OUT =3000;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        session = new SessionManager(getApplicationContext());

        // Check if user is already logged in or not
        if (session.isLoggedIn()) {
            nDialog = new ProgressDialog(MainActivity.this);
            nDialog.setMessage("Loading..");
            nDialog.setTitle("Please Wait!");
            nDialog.setIndeterminate(false);
            nDialog.setCancelable(true);
            nDialog.show();
            // User is already logged in. Take him to main activity
            Intent intent = new Intent(MainActivity.this, StudentsList.class);
            startActivity(intent);
            finish();
        }

        else{ new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent homeIntent = new Intent(MainActivity.this, WelcomeMenu.class);
                startActivity(homeIntent);
                finish();
            }
        },SPLASH_TIME_OUT);}
    }
}
