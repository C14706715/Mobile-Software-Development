package com.example.jake.coffee;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;
import java.util.HashMap;
import java.util.Map;


/*
    Reference:
    Parts of this code is from TutorialPoint
 */
public class RegisterRequest extends StringRequest{
    //My domain is C14706715.96.lt
    //here i have my register and login files
    private static final String Register_Request_URL ="http://c14706715.96.lt/register.php";
    private Map<String, String> params;

    public RegisterRequest(String name, String username, int age, String password, Response.Listener<String> listener) {
        super(Method.POST, Register_Request_URL, listener, null);
        params = new HashMap<>();
        //Putting the details into the register request
        params.put("name", name);
        params.put("username", username);
        params.put("password", password);
        params.put("age", age + "");
    }

    @Override
    public Map<String, String> getParams() {
        return params;
    }
}