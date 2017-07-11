package in.co.discoveideas.presentsir;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Ankit on 07-06-2017.
 */

public class TregisterRequest extends StringRequest {
    private static final String REGISTER_REQUEST_URL = "http://54.172.92.46/presentsir/tregister.php";
    private Map<String, String> params;

    public  TregisterRequest(String name, String cname, String email, String password, String phone, String address, Response.Listener<String> listener)
    {
        super(Request.Method.POST, REGISTER_REQUEST_URL, listener, null);
        params = new HashMap<>();
        params.put("name", name);
        params.put("coaching_name", cname);
        params.put("email", email);
        params.put("password", password);
        params.put("phone", phone);
        params.put("address", address);

    }

    @Override
    public Map<String, String> getParams() {
        return params;
    }
}
