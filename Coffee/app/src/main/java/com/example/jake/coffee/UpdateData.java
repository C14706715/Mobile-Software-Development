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


public class UpdateData extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_getdata);

        final EditText Name = (EditText) findViewById(R.id.editTextName);
        final EditText Username = (EditText) findViewById(R.id.editTextUsername);
        final EditText Age = (EditText) findViewById(R.id.editTextAge);
        final EditText Password = (EditText) findViewById(R.id.editTextPassword);
        final Button Update = (Button) findViewById(R.id.updateBtn);

        Update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String NewName = Name.getText().toString();
                final int NewAge = Integer.parseInt(Age.getText().toString());
                final String NewUsername = Username.getText().toString();
                final String NewPassword = Password.getText().toString();

                //created a listener to listen if the user signed up successfully or not
                Response.Listener<String> responseListener1 = new Response.Listener<String>() {

                    @Override
                    public void onResponse(String response) {
                        //in a try catch to catch exception
                        try {
                            JSONObject jsonResponse = new JSONObject(response);
                            boolean success = jsonResponse.getBoolean("Update Successful");

                            if (success) {
                                //open login page
                                Intent intent = new Intent(UpdateData.this, ListActivity.class);
                                UpdateData.this.startActivity(intent);
                            } else {
                                //pop up box to inform user of failed registry
                                AlertDialog.Builder builder = new AlertDialog.Builder(UpdateData.this);
                                builder.setMessage("Update Failed")
                                        .setNegativeButton("Retry", null)
                                        .create()
                                        .show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        Intent intent = new Intent(UpdateData.this, ListActivity.class);
                        UpdateData.this.startActivity(intent);
                    }
                };
                //Create a registerRequest child to send variables to the class
                UpdateRequest registerRequest = new UpdateRequest(NewName, NewUsername, NewAge, NewPassword, responseListener1);
                //I used volley as its a library which makes it easier to transport data through the app
                RequestQueue queue = Volley.newRequestQueue(UpdateData.this);
                queue.add(registerRequest);
            }
        });
    }
}

