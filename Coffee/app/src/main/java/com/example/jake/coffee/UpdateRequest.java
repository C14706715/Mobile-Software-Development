package com.example.jake.coffee;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;
import java.util.HashMap;
import java.util.Map;

/*
    Reference:
    Parts of this code is from TutorialPoint
 */
public class UpdateRequest extends StringRequest{
    //My domain is C14706715.96.lt
    //here i have my register and login files
    private static final String URL ="http://c14706715.96.lt/Update.php";
    private Map<String, String> params;

    public UpdateRequest(String name, String username, int age, String password, Response.Listener<String> listener) {
        super(Method.POST, URL, listener, null);
        params = new HashMap<>();
        //Putting the details into the register request
        params.put("name", name);
        params.put("username", username);
        params.put("age", age + "");
        params.put("password", password);
    }

    @Override
    public Map<String, String> getParams() {
        return params;
    }
}
