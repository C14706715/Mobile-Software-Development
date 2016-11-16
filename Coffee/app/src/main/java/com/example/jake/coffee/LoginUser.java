package com.example.jake.coffee;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

//This Activity deals with the simple login page. It simply has one button on the page
public class LoginUser extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_user);

        //Declaring variables
        final EditText etUsername = (EditText) findViewById(R.id.UsernameID);
        final EditText etPassword = (EditText) findViewById(R.id.PasswordID);
        final Button Register = (Button) findViewById(R.id.RegisterBtnID);
        final Button Login = (Button) findViewById(R.id.LoginBtnID);

        //To go to Register page
        Register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent registerIntent = new Intent(LoginUser.this, SignUpUser.class);
                LoginUser.this.startActivity(registerIntent);
            }
        });

        //To go to the login page
        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String username = etUsername.getText().toString();
                final String password = etPassword.getText().toString();

                Response.Listener<String> responseListener = new Response.Listener<String>(){
                    @Override
                    public void onResponse(String response){
                        try {
                            //the response is in a json object
                            JSONObject jsonResponse = new JSONObject(response);
                            boolean success = jsonResponse.getBoolean("success");
                            //here using a boolean variable i was able to check if the user entered
                            //the correct login details or not and do actions accordingly
                            if(success){
                                String name= jsonResponse.getString("name");
                                int age = jsonResponse.getInt("age");

                                Intent intent = new Intent(LoginUser.this, ListActivity.class);
                                //This is putting the variables into the login screen
                                intent.putExtra("name", name);
                                intent.putExtra("username", username);
                                intent.putExtra("age", age);

                                LoginUser.this.startActivity(intent);
                            }
                            else {
                                //If login was unsuccessful the user will see a message box with info
                                AlertDialog.Builder builder = new AlertDialog.Builder(LoginUser.this);
                                builder.setMessage("Login Failed!")
                                        .setNegativeButton("Retry", null)
                                        .create()
                                        .show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                };

                //Child of the LoginRequest class to send data to that class
                LoginRequest loginRequest = new LoginRequest(username, password, responseListener);
                RequestQueue queue = Volley.newRequestQueue(LoginUser.this);
                queue.add(loginRequest);
            }
        });
    }
}
