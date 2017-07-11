package in.co.discoveideas.presentsir;

import android.Manifest;
import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import in.co.discoveideas.presentsir.SQLiteHandler;

public class StudentActivity extends AppCompatActivity {
    public SQLiteHandler db;
    private static String sname;
    public TextView tsfname;
    public TextView tsphone;
    public TextView tsaddress;
    public TextView tsjoin;
    public TextView tsfees;
    public TextView tvsclass;
    public String sphone;
    public String value;
    public ImageView callstu;


    private static final String TAG = TutorRegister.class.getSimpleName();
    final Context context = this;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student);
        Bundle bundle = getIntent().getExtras();
        sname = bundle.getString("stuname");
        final TextView stdname = (TextView) findViewById(R.id.stdname);
        tsfname = (TextView) findViewById(R.id.tvsfname);
        tsphone = (TextView) findViewById(R.id.tvsphone);
        tsaddress = (TextView) findViewById(R.id.tvsaddress);
        tsjoin = (TextView) findViewById(R.id.tvsjoin);
        tsfees = (TextView) findViewById(R.id.tvsfees);
        tvsclass = (TextView) findViewById(R.id.tvsclass);
        callstu = (ImageView) findViewById(R.id.ivcallstu);
        

        stdname.setText(sname);
        ImageView info = (ImageView) findViewById(R.id.imageView2);
        ImageView stats = (ImageView) findViewById(R.id.imageView3);
        ImageView record = (ImageView) findViewById(R.id.imageView4);

        stdname.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {

                // get prompts.xml view
                LayoutInflater li = LayoutInflater.from(context);
                View promptsView = li.inflate(R.layout.edit_name_dialogbox, null);

                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                        context);

                // set prompts.xml to alertdialog builder
                alertDialogBuilder.setView(promptsView);
                final EditText userInput = (EditText) promptsView
                        .findViewById(R.id.editsname);

                // set dialog message
                alertDialogBuilder
                        .setCancelable(false)
                        .setPositiveButton("SAVE",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog,int id) {
                                        // get user input and set it to result
                                        // edit text
                                        stdname.setText(userInput.getText());
                                        value = stdname.getText().toString();
                                        SQLiteHandler db = new SQLiteHandler(getApplicationContext());
                                        HashMap<String, String> tuser = db.getUserDetails();
                                        String tname = tuser.get("name");
                                        editStuDetail(tname,sname,value,"sname");
                                    }
                                })
                        .setNegativeButton("Cancel",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog,int id) {
                                        dialog.cancel();
                                    }
                                });

                // create alert dialog
                AlertDialog alertDialog = alertDialogBuilder.create();

                // show it
                alertDialog.show();

            }
        });

        tvsclass.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {

                // get prompts.xml view
                LayoutInflater li = LayoutInflater.from(context);
                View promptsView = li.inflate(R.layout.edit_class_dialogbox, null);

                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                        context);

                // set prompts.xml to alertdialog builder
                alertDialogBuilder.setView(promptsView);
                final EditText userInput = (EditText) promptsView
                        .findViewById(R.id.editsclass);

                // set dialog message
                alertDialogBuilder
                        .setCancelable(false)
                        .setPositiveButton("SAVE",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog,int id) {
                                        // get user input and set it to result
                                        // edit text
                                        tvsclass.setText(userInput.getText());
                                        value = tvsclass.getText().toString();
                                        SQLiteHandler db = new SQLiteHandler(getApplicationContext());
                                        HashMap<String, String> tuser = db.getUserDetails();
                                        String tname = tuser.get("name");
                                        editStuDetail(tname,sname,value,"sclass");
                                    }
                                })
                        .setNegativeButton("Cancel",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog,int id) {
                                        dialog.cancel();
                                    }
                                });

                // create alert dialog
                AlertDialog alertDialog = alertDialogBuilder.create();

                // show it
                alertDialog.show();

            }
        });


        tsfname.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {

                // get prompts.xml view
                LayoutInflater li = LayoutInflater.from(context);
                View promptsView = li.inflate(R.layout.prompts, null);

                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                        context);

                // set prompts.xml to alertdialog builder
                alertDialogBuilder.setView(promptsView);
                final EditText userInput = (EditText) promptsView
                        .findViewById(R.id.editfname);

                // set dialog message
                alertDialogBuilder
                        .setCancelable(false)
                        .setPositiveButton("Save",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog,int id) {
                                        // get user input and set it to result
                                        // edit text
                                        tsfname.setText(userInput.getText());
                                        value = tsfname.getText().toString();
                                        SQLiteHandler db = new SQLiteHandler(getApplicationContext());
                                        HashMap<String, String> tuser = db.getUserDetails();
                                        String tname = tuser.get("name");
                                        editStuDetail(tname,sname,value,"sfname");
                                    }
                                })
                        .setNegativeButton("Cancel",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog,int id) {
                                        dialog.cancel();
                                    }
                                });

                // create alert dialog
                AlertDialog alertDialog = alertDialogBuilder.create();

                // show it
                alertDialog.show();

            }
        });

        tsphone.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {

                // get prompts.xml view
                LayoutInflater li = LayoutInflater.from(context);
                View promptsView = li.inflate(R.layout.edit_phone_dialogbox, null);

                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                        context);

                // set prompts.xml to alertdialog builder
                alertDialogBuilder.setView(promptsView);
                final EditText userInput = (EditText) promptsView
                        .findViewById(R.id.editsphone);

                // set dialog message
                alertDialogBuilder
                        .setCancelable(false)
                        .setPositiveButton("Save",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog,int id) {
                                        // get user input and set it to result
                                        // edit text
                                        tsphone.setText(userInput.getText());
                                        value = tsphone.getText().toString();
                                        SQLiteHandler db = new SQLiteHandler(getApplicationContext());
                                        HashMap<String, String> tuser = db.getUserDetails();
                                        String tname = tuser.get("name");
                                        editStuDetail(tname,sname,value,"sphone");
                                    }
                                })
                        .setNegativeButton("Cancel",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog,int id) {
                                        dialog.cancel();
                                    }
                                });

                // create alert dialog
                AlertDialog alertDialog = alertDialogBuilder.create();

                // show it
                alertDialog.show();

            }
        });
        tsaddress.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {

                // get prompts.xml view
                LayoutInflater li = LayoutInflater.from(context);
                View promptsView = li.inflate(R.layout.edit_address_dialogbox, null);

                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                        context);

                // set prompts.xml to alertdialog builder
                alertDialogBuilder.setView(promptsView);
                final EditText userInput = (EditText) promptsView
                        .findViewById(R.id.editsaddress);

                // set dialog message
                alertDialogBuilder
                        .setCancelable(false)
                        .setPositiveButton("Save",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog,int id) {
                                        // get user input and set it to result
                                        // edit text
                                        tsaddress.setText(userInput.getText());
                                        value = tsaddress.getText().toString();
                                        SQLiteHandler db = new SQLiteHandler(getApplicationContext());
                                        HashMap<String, String> tuser = db.getUserDetails();
                                        String tname = tuser.get("name");
                                        editStuDetail(tname,sname,value,"saddress");
                                    }
                                })
                        .setNegativeButton("Cancel",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog,int id) {
                                        dialog.cancel();
                                    }
                                });

                // create alert dialog
                AlertDialog alertDialog = alertDialogBuilder.create();

                // show it
                alertDialog.show();

            }
        });
        tsjoin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // calender class's instance and get current date , month and year from calender
                final Calendar c = Calendar.getInstance();
                int mYear = c.get(Calendar.YEAR); // current year
                int mMonth = c.get(Calendar.MONTH); // current month
                int mDay = c.get(Calendar.DAY_OF_MONTH); // current day
                DatePickerDialog datePickerDialog = new DatePickerDialog(StudentActivity.this,
                        new DatePickerDialog.OnDateSetListener() {

                            @Override
                            public void onDateSet(DatePicker view, int year,
                                                  int monthOfYear, int dayOfMonth) {
                                // set day of month , month and year value in the edit text
                                tsjoin.setText(dayOfMonth + "/"
                                        + (monthOfYear + 1) + "/" + year);

                            }
                        }, mYear, mMonth, mDay);
                datePickerDialog.show();
            }
        });
       /* tsjoin.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {

                // get prompts.xml view
                LayoutInflater li = LayoutInflater.from(context);
                View promptsView = li.inflate(R.layout.edit_joindate_dialogbox, null);

                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                        context);

                // set prompts.xml to alertdialog builder
                alertDialogBuilder.setView(promptsView);
                final EditText userInput = (EditText) promptsView
                        .findViewById(R.id.editsjoin);
                EditText.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        // calender class's instance and get current date , month and year from calender
                        final Calendar c = Calendar.getInstance();
                        int mYear = c.get(Calendar.YEAR); // current year
                        int mMonth = c.get(Calendar.MONTH); // current month
                        int mDay = c.get(Calendar.DAY_OF_MONTH); // current day
                        // date picker dialog
                        DatePickerDialog datePickerDialog = new DatePickerDialog(AddStudent.this,
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

                // set dialog message
                alertDialogBuilder
                        .setCancelable(false)
                        .setPositiveButton("Save",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog,int id) {
                                        // get user input and set it to result
                                        // edit text
                                        tsjoin.setText(userInput.getText());
                                        value = tsjoin.getText().toString();
                                        SQLiteHandler db = new SQLiteHandler(getApplicationContext());
                                        HashMap<String, String> tuser = db.getUserDetails();
                                        String tname = tuser.get("name");
                                        editStuDetail(tname,sname,value,"sjoin");
                                    }
                                })
                        .setNegativeButton("Cancel",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog,int id) {
                                        dialog.cancel();
                                    }
                                });

                // create alert dialog
                AlertDialog alertDialog = alertDialogBuilder.create();

                // show it
                alertDialog.show();

            }
        });*/

        tsfees.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {

                // get prompts.xml view
                LayoutInflater li = LayoutInflater.from(context);
                View promptsView = li.inflate(R.layout.edit_fees_dialogbox, null);

                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                        context);

                // set prompts.xml to alertdialog builder
                alertDialogBuilder.setView(promptsView);
                final EditText userInput = (EditText) promptsView
                        .findViewById(R.id.editsfees);

                // set dialog message
                alertDialogBuilder
                        .setCancelable(false)
                        .setPositiveButton("Save", new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog,int id) {
                                        // get user input and set it to result
                                        // edit text
                                        tsfees.setText(userInput.getText());
                                        value = tsfees.getText().toString();
                                        SQLiteHandler db = new SQLiteHandler(getApplicationContext());
                                        HashMap<String, String> tuser = db.getUserDetails();
                                        String tname = tuser.get("name");
                                        editStuDetail(tname,sname,value,"sfees");
                                    }
                                })
                        .setNegativeButton("Cancel",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog,int id) {
                                        dialog.cancel();
                                    }
                                });

                // create alert dialog
                AlertDialog alertDialog = alertDialogBuilder.create();

                // show it
                alertDialog.show();

            }
        });
     info.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {

                Intent infoIntent = new Intent(StudentActivity.this, StudentInfo.class);
                StudentActivity.this.startActivity(infoIntent);
                //setContentView(R.layout.activity_student_info);

            }
        });
        stats.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                //setContentView(R.layout.activity_student_stats);
                Intent infoIntent = new Intent(StudentActivity.this, StudentStats.class);
                StudentActivity.this.startActivity(infoIntent);

            }
        });
        record.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {

                Intent recordIntent = new Intent(StudentActivity.this, StudentRecord.class);
                StudentActivity.this.startActivity(recordIntent);

            }
        });


        callstu.setOnClickListener(new View.OnClickListener() {
            String phone = tsphone.getText().toString();
            public void onClick(View v) {

                AlertDialog.Builder builder = new AlertDialog.Builder(StudentActivity.this);
                builder.setMessage("Do you want to Call " + sname )

                        .setNegativeButton("OK", new AlertDialog.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Intent callIntent = new Intent(Intent.ACTION_CALL);
                                callIntent.setData(Uri.parse("tel:"+sphone));

                                if (ActivityCompat.checkSelfPermission(StudentActivity.this,
                                        Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                                    return;
                                }
                                startActivity(callIntent);
                            }
                        })
                        .setPositiveButton("Cancel", new AlertDialog.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.cancel();
                            }
                        })
                        .create()
                        .show();

            }
        });

        SQLiteHandler db = new SQLiteHandler(getApplicationContext());
        HashMap<String, String> tuser = db.getUserDetails();
        String tname = tuser.get("name");
        getStuDetail(tname, sname);
    }

    private void getStuDetail(final String tname,final String sname) {
        // Tag used to cancel the request
        String tag_string_req = "req_stdetail";

        //nDialog.setMessage("Logging in ...");
        //showDialog();

        StringRequest strReq = new StringRequest(Request.Method.POST,
                AppConfig.URL_STUDETAIL, new Response.Listener<String>() {
            final ProgressDialog loading = ProgressDialog.show(StudentActivity.this, "Please wait...","Fetching Student Information...",false,false);
            @Override
            public void onResponse(String response) {
                Log.d(TAG, "Detail Response: " + response.toString());
                //hideDialog();

                try {
                    JSONObject jObj = new JSONObject(response);
                    boolean error = jObj.getBoolean("error");

                    // Check for error node in json
                    if (!error) {

                        JSONObject user = jObj.getJSONObject("user");
                        String id = user.getString("id");
                        String sname = user.getString("sname");
                        String sclass = user.getString("sclass");
                        String sfname = user.getString("sfname");
                        sphone = user.getString("sphone");
                        String saddress = user.getString("saddress");
                        String sjoin = user.getString("sjoin");
                        String sfees = user.getString("sfees");

                        tsfname.setText(sfname);
                        tvsclass.setText(sclass);
                        tsphone.setText(sphone);
                        tsaddress.setText(saddress);
                        tsjoin.setText(sjoin);
                        tsfees.setText(sfees);
                        // Inserting row in users table
                        loading.dismiss();
                        // Launch main activity

                    } else {
                        // Error in login. Get the error message
                        String errorMsg = jObj.getString("error_msg");
                        Toast.makeText(getApplicationContext(),
                                errorMsg, Toast.LENGTH_LONG).show();
                        loading.dismiss();
                    }
                } catch (JSONException e) {
                    // JSON error
                    e.printStackTrace();
                    loading.dismiss();
                    Toast.makeText(getApplicationContext(), "Json error: " + e.getMessage(), Toast.LENGTH_LONG).show();

                }

            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e(TAG, "Get Details Error: " + error.getMessage());
                Toast.makeText(getApplicationContext(),
                        error.getMessage(), Toast.LENGTH_LONG).show();
                //hideDialog();
            }
        }) {

            @Override
            protected Map<String, String> getParams() {
                // Posting parameters to login url
                Map<String, String> params = new HashMap<String, String>();
                params.put("tname", tname);
                params.put("sname", sname);


                return params;
            }

        };

        // Adding request to request queue
        AppController.getInstance().addToRequestQueue(strReq, tag_string_req);
    }

    private void editStuDetail(final String tname,final String sname, final String value, final String fname) {
        // Tag used to cancel the request
        String tag_string_req = "req_steditdetail";

        //nDialog.setMessage("Logging in ...");
        //showDialog();

        StringRequest strReq = new StringRequest(Request.Method.POST,
                AppConfig.URL_EDITSTUDETAIL, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                Log.d(TAG, "Detail Response: " + response.toString());
                //hideDialog();

                try {
                    JSONObject jObj = new JSONObject(response);
                    boolean error = jObj.getBoolean("error");

                    // Check for error node in json
                    if (!error) {
                        // user successfully logged in
                        // Create login session

                        // Now store the user in SQLite
                        Toast.makeText(getApplicationContext(), "Values Edited" , Toast.LENGTH_LONG).show();

                    } else {
                        // Error in login. Get the error message
                        String errorMsg = jObj.getString("error_msg");
                        Toast.makeText(getApplicationContext(),
                                errorMsg, Toast.LENGTH_LONG).show();
                    }
                } catch (JSONException e) {
                    // JSON error
                    e.printStackTrace();
                    Toast.makeText(getApplicationContext(), "Json error: " + e.getMessage(), Toast.LENGTH_LONG).show();
                }

            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e(TAG, "Get Details Error: " + error.getMessage());
                Toast.makeText(getApplicationContext(),
                        error.getMessage(), Toast.LENGTH_LONG).show();
                //hideDialog();
            }
        }) {

            @Override
            protected Map<String, String> getParams() {
                // Posting parameters to login url
                Map<String, String> params = new HashMap<String, String>();
                params.put("tname", tname);
                params.put("sname", sname);
                params.put("value", value);
                params.put("fname", fname);

                return params;
            }

        };

        // Adding request to request queue
        AppController.getInstance().addToRequestQueue(strReq, tag_string_req);
    }
}
