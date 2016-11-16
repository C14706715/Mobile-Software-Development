package com.example.jake.coffee;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class RegisterRequest extends StringRequest{
    private static final String Register_Request_URL ="http://c14706715.96.lt/register.php";
    private Map<String, String> params;

    public RegisterRequest(String name, String email, int age, String password, Response.Listener<String> listener) {
        super(Method.POST, Register_Request_URL, listener, null);
        params = new HashMap<>();
        params.put("name", name);
        params.put("email", email);
        params.put("password", password);
        params.put("age", age + "");
    }

    @Override
    public Map<String, String> getParams() {
        return params;
    }
}
