package in.co.discoveideas.presentsir;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import in.co.discoveideas.presentsir.SQLiteHandler;
import in.co.discoveideas.presentsir.SessionManager;

public class StudentsList extends AppCompatActivity {
    private static final String TAG = StudentsList.class.getSimpleName();
   // public String DATA_URL = "http://54.172.92.46/presentsir/stulist.php?tname=";
        Button btAddStudent;
        private ImageView imageView;
        public SQLiteHandler db;
        public String stname;
       private SessionManager session;
   // private SessionManager session;
        //Web api url


        //Tag values to read from json
        public static final String TAG_IMAGE_URL = "image";
        public static final String TAG_NAME = "name";

        //GridView Object
        private GridView gridView;

        //ArrayList for Storing image urls and titles
        private ArrayList<String> images;
        private ArrayList names;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_students_list);
            btAddStudent = (Button) findViewById(R.id.btAddStudent);
            imageView = (ImageView) findViewById(R.id.imageView);
            gridView = (GridView) findViewById(R.id.gridView1);
            db = new SQLiteHandler(getApplicationContext());

            // session manager
            session = new SessionManager(getApplicationContext());

            if (!session.isLoggedIn()) {
                logoutUser();
            }
            images = new ArrayList<>();
            names = new ArrayList<>();

            btAddStudent.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent addstudentIntent = new Intent(StudentsList.this, AddStudent.class);
                    StudentsList.this.startActivity(addstudentIntent);
                }
            });
            //Calling the getData method
            getData();
        }

    private void getData(){
        SQLiteHandler db = new SQLiteHandler(getApplicationContext());
        HashMap<String, String> tuser = db.getUserDetails();
        String tutname = tuser.get("name");
        String email = tuser.get("email");
       // Log.e(TAG, "Response: " + tutname);
        final String URL = "http://54.172.92.46/presentsir/stulist.php?tname=";
        final String DATA_URL = URL+tutname;
        //Showing a progress dialog while our app fetches the data from url
        final ProgressDialog loading = ProgressDialog.show(this, "Please wait...","Fetching students list...",false,false);

        //Creating a json array request to get the json from our api
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(DATA_URL,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        //Dismissing the progressdialog on response
                        loading.dismiss();

                        //Displaying our grid
                        showGrid(response);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                }
        );

        //Creating a request queue
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        //Adding our request to the queue
        requestQueue.add(jsonArrayRequest);
    }


    private void showGrid(JSONArray jsonArray){
        //Looping through all the elements of json array
        for(int i = 0; i<jsonArray.length(); i++){
            //Creating a json object of the current index
            JSONObject obj = null;
            try {
                //getting json object from current index
                obj = jsonArray.getJSONObject(i);

                //getting image url and title from json object
                images.add(obj.getString(TAG_IMAGE_URL));
                names.add(obj.getString(TAG_NAME));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        //Creating GridViewAdapter Object
        CustomAdapter gridViewAdapter = new CustomAdapter(this,images,names);

        //Adding adapter to gridview
        gridView.setAdapter(gridViewAdapter);
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Bundle bundle = new Bundle();
                Intent studentinfo = new Intent(StudentsList.this, StudentActivity.class);


                //names.add(obj.getString(TAG_NAME));
                stname =  names.get(position).toString();
                //Toast.makeText(getApplicationContext(), "selected Item Name is " + text1, Toast.LENGTH_LONG).show();
                bundle.putString("stuname", stname);
                studentinfo.putExtras(bundle);
                StudentsList.this.startActivity(studentinfo);
            }


        });

        gridView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {

            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                stname =  names.get(position).toString();
                AlertDialog.Builder builder = new AlertDialog.Builder(StudentsList.this);
                builder.setMessage("Do you really want to remove "+ stname)

                        .setNegativeButton("OK", new AlertDialog.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                SQLiteHandler db = new SQLiteHandler(getApplicationContext());
                                HashMap<String, String> tuser = db.getUserDetails();
                                String tutname = tuser.get("name");
                                //removeStu(tutname,stname);
                                dialog.cancel();
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
                //Toast.makeText(StudentsList.this, "Remove Student", Toast.LENGTH_SHORT).show();
                // TODO Auto-generated method stub
                return true;
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu, menu);
        return true;
    }

    /**
     * Event Handling for Individual menu item selected
     * Identify single menu item by it's id
     * */
    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {

        switch (item.getItemId())
        {
            case R.id.menu_attendance:
               // Intent intent = new Intent(this, Notifications.class);
               // this.startActivity(intent);
                Toast.makeText(StudentsList.this, "Bookmark is Selected", Toast.LENGTH_SHORT).show();
                return true;

            case R.id.menu_test:
                //Toast.makeText(DeviceGrid.this, "Save is Selected", Toast.LENGTH_SHORT).show();
                return true;

            case R.id.menu_dues:
                //Intent intentsettings = new Intent(this, AdvancedSetting.class);
                //this.startActivity(intentsettings);
                Toast.makeText(StudentsList.this, "Search is Selected", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.menu_logout:

                AlertDialog.Builder builder = new AlertDialog.Builder(StudentsList.this);
                builder.setMessage("Are you sure you want to logout?")

                        .setNegativeButton("OK", new AlertDialog.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                logoutUser();
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
                return true;


            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void logoutUser() {
        session.setLogin(false);

        db.deleteUsers();

        // Launching the login activity
        Intent intent = new Intent(StudentsList.this, WelcomeMenu.class);
        startActivity(intent);
        finish();
    }

    private void removeStu(final String tname,final String sname) {
        // Tag used to cancel the request
        String tag_string_req = "req_deletest";

        //nDialog.setMessage("Logging in ...");
        //showDialog();

        StringRequest strReq = new StringRequest(Request.Method.POST,
                AppConfig.URL_STUDETAIL, new Response.Listener<String>() {
            final ProgressDialog loading = ProgressDialog.show(StudentsList.this, "Please wait...","Removing Student...",false,false);
            @Override
            public void onResponse(String response) {
                Log.d(TAG, "Detail Response: " + response.toString());
                //hideDialog();

                try {
                    JSONObject jObj = new JSONObject(response);
                    boolean error = jObj.getBoolean("error");

                    // Check for error node in json
                    if (!error) {

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

}
