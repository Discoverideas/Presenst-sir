package in.co.discoveideas.presentsir;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.DatePicker;
import java.util.Calendar;
import java.util.HashMap;

import android.app.DatePickerDialog;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;
import in.co.discoveideas.presentsir.SQLiteHandler;

public class AddStudent extends AppCompatActivity {
    private static final String TAG = AddStudent.class.getSimpleName();
    ProgressDialog nDialog;
    Button btAddStu;
    ImageView ivsphoto;
    public SQLiteHandler db;
    DatePickerDialog datePickerDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_student);

        ivsphoto = (ImageView) findViewById(R.id.ivsphoto);
        final EditText etsname = (EditText) findViewById(R.id.etsname) ;
        final EditText etsclass = (EditText) findViewById(R.id.etsclass) ;
        final EditText etsfathername = (EditText) findViewById(R.id.etsfathername);
        final EditText etsphoneno = (EditText) findViewById(R.id.etsphoneno);
        final EditText etsaddress = (EditText) findViewById(R.id.etsaddress);
        final EditText etjoin = (EditText) findViewById(R.id.etpickdate);
        final EditText etfees = (EditText) findViewById(R.id.etfees);
        btAddStu = (Button) findViewById(R.id.btAddStu);

        // perform click event on submit button
        etjoin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // calender class's instance and get current date , month and year from calender
                final Calendar c = Calendar.getInstance();
                int mYear = c.get(Calendar.YEAR); // current year
                int mMonth = c.get(Calendar.MONTH); // current month
                int mDay = c.get(Calendar.DAY_OF_MONTH); // current day
                // date picker dialog
                datePickerDialog = new DatePickerDialog(AddStudent.this,
                        new DatePickerDialog.OnDateSetListener() {

                            @Override
                            public void onDateSet(DatePicker view, int year,
                                                  int monthOfYear, int dayOfMonth) {
                                // set day of month , month and year value in the edit text
                                etjoin.setText(dayOfMonth + "/"
                                        + (monthOfYear + 1) + "/" + year);

                            }
                        }, mYear, mMonth, mDay);
                datePickerDialog.show();
            }
        });

        ivsphoto.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent getimageIntent = new Intent(AddStudent.this, UploadImage.class);
                AddStudent.this.startActivity(getimageIntent);
            }
        });

        btAddStu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nDialog = new ProgressDialog(AddStudent.this);
                nDialog.setMessage("Loading..");
                nDialog.setTitle("Please Wait!");
                nDialog.setIndeterminate(false);
                nDialog.setCancelable(true);
                nDialog.show();
                SQLiteHandler db = new SQLiteHandler(getApplicationContext());
                HashMap<String, String> tuser = db.getUserDetails();
                String tutname = tuser.get("name");
               // String email = tuser.get("email");
                Log.e(TAG, "Response: " + tutname);

                final String name = etsname.getText().toString();
                final String sclass = etsclass.getText().toString();
                final String father = etsfathername.getText().toString();
                final String phone = etsphoneno.getText().toString();
                final String address = etsaddress.getText().toString();
                final String join = etjoin.getText().toString();
                final String fees = etfees.getText().toString();
                if (!name.isEmpty() && !father.isEmpty() && !phone.isEmpty()&& !address.isEmpty()&& !join.isEmpty()&& !fees.isEmpty()) {


                Response.Listener<String> responseListener = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        nDialog.dismiss();
                        try {
                            JSONObject jsonResponse = new JSONObject(response);
                            boolean success = jsonResponse.getBoolean("success");

                            if (success){
                                Intent intent = new Intent(AddStudent.this, StudentsList.class);
                                AddStudent.this.startActivity(intent);
                                AlertDialog.Builder builder = new AlertDialog.Builder(AddStudent.this);
                                builder.setMessage("Thanks For Adding Student.")
                                        .setPositiveButton("Ok", null)
                                        .create()
                                        .show();
                            }
                            else{
                                AlertDialog.Builder builder = new AlertDialog.Builder(AddStudent.this);
                                builder.setMessage("Student Name Already Registered.")
                                        .setNegativeButton("Retry", null)
                                        .create()
                                        .show();
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                };

                AddStudentRequest registerRequest = new AddStudentRequest(tutname, name, sclass, father, phone, address, join, fees ,responseListener);
                RequestQueue queue = Volley.newRequestQueue(AddStudent.this);
                queue.add(registerRequest);
            }
            else {
                // Prompt user to enter credentials
                nDialog.dismiss();
                Toast.makeText(getApplicationContext(),
                        "Please enter the Details. All fields are Compulsory.", Toast.LENGTH_LONG)
                        .show();
            }
            }
        });
    }
}
