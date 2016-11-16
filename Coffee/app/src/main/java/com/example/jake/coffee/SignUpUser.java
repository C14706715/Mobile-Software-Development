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

//This activity deals with the sign up page of the app. It contains different buttons along with
//changing pages depending on which button is clicked(listeners implemented to wait for user's response)
public class SignUpUser extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_user);

        //Declared Variables
        final EditText etName = (EditText) findViewById(R.id.NameID);
        final EditText etAge = (EditText) findViewById(R.id.AgeID);
        final EditText etUsername = (EditText) findViewById(R.id.UsernameID);
        final EditText etPassword = (EditText) findViewById(R.id.PasswordID);
        final Button Login = (Button) findViewById(R.id.LoginBtnID);
        final Button SignUp = (Button) findViewById(R.id.SignUpBtnID);

        //clickable button to reference back to login page
        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SignUpUser.this, LoginUser.class);
                startActivity(intent);
            }
        });

        //clickable button to reference back to signup page
        SignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //declaring local variables which values can't be changed
                final String name = etName.getText().toString();
                final int age = Integer.parseInt(etAge.getText().toString());
                final String username = etUsername.getText().toString();
                final String password = etPassword.getText().toString();

                //created a listener to listen if the user signed up successfully or not
                Response.Listener<String> responseListener = new Response.Listener<String>() {

                    @Override
                    public void onResponse(String response) {
                        //in a try catch to catch exception
                        try {
                            JSONObject jsonResponse = new JSONObject(response);
                            boolean success = jsonResponse.getBoolean("Register Successful");

                            if(success){
                                //open login page
                                Intent intent = new Intent(SignUpUser.this, LoginUser.class);
                                SignUpUser.this.startActivity(intent);
                            }
                            else{
                                //pop up box to inform user of failed registry
                                AlertDialog.Builder builder = new AlertDialog.Builder(SignUpUser.this);
                                builder.setMessage("Register Failed")
                                        .setNegativeButton("Retry", null)
                                        .create()
                                        .show();
                            }
                        }
                        catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                };
                //Create a registerRequest child to send variables to the class
                RegisterRequest registerRequest = new RegisterRequest(name, username, age, password, responseListener);
                //I used volley as its a library which makes it easier to transport data through the app
                RequestQueue queue = Volley.newRequestQueue(SignUpUser.this);
                queue.add(registerRequest);
            }
        });
    }

}
