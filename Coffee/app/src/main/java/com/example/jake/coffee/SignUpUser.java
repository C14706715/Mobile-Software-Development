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

        final Button Login = (Button) findViewById(R.id.LoginBtnID);
        final Button SignUp = (Button) findViewById(R.id.SignUpBtnID);
        final EditText etName = (EditText) findViewById(R.id.NameID);
        final EditText etAge = (EditText) findViewById(R.id.AgeID);
        final EditText etEmail = (EditText) findViewById(R.id.EmailID);
        final EditText etPassword = (EditText) findViewById(R.id.PasswordID);

        SignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String Name = etName.getText().toString();
                final int Age = Integer.parseInt(etAge.getText().toString());
                final String Email = etEmail.getText().toString();
                final String Password = etPassword.getText().toString();
                Response.Listener<String> responseListener = new Response.Listener<String>() {

                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonResponse = new JSONObject(response);
                            boolean success = jsonResponse.getBoolean("success");

                            if(success){
                                Intent intent = new Intent(SignUpUser.this, LoginUser.class);
                                SignUpUser.this.startActivity(intent);
                            }
                            else{
                                AlertDialog.Builder builder = new AlertDialog.Builder(SignUpUser.this);
                                builder.setMessage("Sorry Register Failed")
                                        .setNegativeButton("Retry", null)
                                        .create()
                                        .show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                };
                RegisterRequest registerRequest = new RegisterRequest(Name, Email, Age, Password, responseListener);
                RequestQueue queue = Volley.newRequestQueue(SignUpUser.this);
                queue.add(registerRequest);
            }
        });
    }

}
