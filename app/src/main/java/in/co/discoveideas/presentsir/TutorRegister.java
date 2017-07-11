package in.co.discoveideas.presentsir;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class TutorRegister extends AppCompatActivity {
    private static final String TAG = TutorRegister.class.getSimpleName();
    ProgressDialog nDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tutor_register);

        final EditText ettname = (EditText) findViewById(R.id.ettname) ;
        final EditText ettcname = (EditText) findViewById(R.id.ettcname);
        final EditText ettemail = (EditText) findViewById(R.id.ettemail);
        final EditText ettpassword = (EditText) findViewById(R.id.ettpassword);
        final EditText ettphone = (EditText) findViewById(R.id.ettphone);
        final EditText ettaddress = (EditText) findViewById(R.id.ettaddress);

        final Button bttRegister = (Button) findViewById(R.id.bttRegister);

        bttRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nDialog = new ProgressDialog(TutorRegister.this);
                nDialog.setMessage("Loading..");
                nDialog.setTitle("Please Wait!");
                nDialog.setIndeterminate(false);
                nDialog.setCancelable(true);
                nDialog.show();
                final String name = ettname.getText().toString();
                final String coaching_name = ettcname.getText().toString();
                final String email = ettemail.getText().toString();
                final String password = ettpassword.getText().toString();
                final String phone = ettphone.getText().toString();
                final String address = ettaddress.getText().toString();
                if (!name.isEmpty() && !coaching_name.isEmpty() && !email.isEmpty()) {
                    // login user

                Response.Listener<String> responseListener = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        nDialog.dismiss();
                        try {
                            JSONObject jsonResponse = new JSONObject(response);
                            //Log.e(TAG, "Response: " + response);
                            boolean success = jsonResponse.getBoolean("success");

                            if (success){
                                // Intent intent = new Intent(Addnewbook.this, LoginActivity.class);
                                //Addnewbook.this.startActivity(intent);
                                AlertDialog.Builder builder = new AlertDialog.Builder(TutorRegister.this);
                                builder.setMessage("Thanks! For Registration")

                                        .setNegativeButton("OK", new AlertDialog.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialog, int which) {
                                                finish();
                                            }
                                        })
                                        .create()
                                        .show();
                            }
                            else{
                                AlertDialog.Builder builder = new AlertDialog.Builder(TutorRegister.this);
                                builder.setMessage("Email or Phone No Already Registered")
                                        .setNegativeButton("Retry", null)
                                        .create()
                                        .show();
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                };

                TregisterRequest donateBookRequest = new TregisterRequest(name, coaching_name, email, password, phone, address, responseListener);
                RequestQueue queue = Volley.newRequestQueue(TutorRegister.this);
                queue.add(donateBookRequest);
                } else {
                    // Prompt user to enter credentials
                    nDialog.dismiss();
                    Toast.makeText(getApplicationContext(),
                            "Please enter the Details", Toast.LENGTH_LONG)
                            .show();
                }
            }
        });
    }
}
