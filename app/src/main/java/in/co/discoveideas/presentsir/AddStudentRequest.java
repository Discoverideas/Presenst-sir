package in.co.discoveideas.presentsir;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Ankit on 06-06-2017.
 */

public class AddStudentRequest extends StringRequest {
    private static final String REGISTER_REQUEST_URL = "http://54.172.92.46/presentsir/sregister.php";
    private Map<String, String> params;

    public  AddStudentRequest(String tutname, String name, String sclass, String father, String phone, String address, String join, String fees,Response.Listener<String> listener)
    {
        super(Request.Method.POST, REGISTER_REQUEST_URL, listener, null);
        params = new HashMap<>();
        params.put("tname", tutname);
        params.put("sname", name);
        params.put("sclass", sclass);
        params.put("sfname", father);
        params.put("sphone", phone);
        params.put("saddress", address);
        params.put("sjoin", join);
        params.put("sfees", fees);
    }

    @Override
    public Map<String, String> getParams() {
        return params;
    }
}
