package in.co.discoveideas.presentsir;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class WelcomeMenu extends AppCompatActivity {
    ProgressDialog nDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome_menu);
        Button btLogin = (Button) findViewById(R.id.btLogin);
        Button btsingup = (Button) findViewById(R.id.btsignup);
        Button btLogin1 = (Button) findViewById(R.id.btLogin1);

        btLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*nDialog = new ProgressDialog(WelcomeMenu.this);
                nDialog.setMessage("Loading..");
                nDialog.setTitle("Please Wait!");
                nDialog.setIndeterminate(false);
                nDialog.setCancelable(true);
                nDialog.show();*/
                Intent tutorloginIntent = new Intent(WelcomeMenu.this, TutorLogin.class);
                WelcomeMenu.this.startActivity(tutorloginIntent);
               // nDialog.dismiss();

            }
        });
        btsingup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent tutorregisterIntent = new Intent(WelcomeMenu.this, TutorRegister.class);
                WelcomeMenu.this.startActivity(tutorregisterIntent);

            }
        });
        btLogin1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent parentsloginIntent = new Intent(WelcomeMenu.this, ParentsLogin.class);
                WelcomeMenu.this.startActivity(parentsloginIntent);

            }
        });
    }
}
