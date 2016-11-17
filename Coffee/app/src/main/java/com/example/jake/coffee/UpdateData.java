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


    EditText etName, etPassword;
    Button UpdateBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_getdata);

        etName = (EditText) findViewById(R.id.editTextName);
        etPassword = (EditText) findViewById(R.id.editTextPassword);

        UpdateBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                final String NewName = etName.getText().toString();
                final String NewPassword = etPassword.getText().toString();

                //created a listener to listen if the user signed up successfully or not
                Response.Listener<String> responseListener = new Response.Listener<String>() {

                    @Override
                    public void onResponse(String response) {
                        //in a try catch to catch exception
                        try {
                            JSONObject jsonResponse = new JSONObject(response);
                            boolean success = jsonResponse.getBoolean("success");

                            if(success){

                                String name= jsonResponse.getString("name");
                                int age = jsonResponse.getInt("age");
                                //open page
                                Intent intent = new Intent(UpdateData.this, ListActivity.class);
                                intent.putExtra("name", NewName);
                                intent.putExtra("password", NewPassword);

                                UpdateData.this.startActivity(intent);
                            }
                            else{
                                //pop up box to inform user of failed registry
                                AlertDialog.Builder builder = new AlertDialog.Builder(UpdateData.this);
                                builder.setMessage("Updating Failed")
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
                UpdateRequest updateRequest = new UpdateRequest(NewName, NewPassword, responseListener);
                //I used volley as its a library which makes it easier to transport data through the app
                RequestQueue queue = Volley.newRequestQueue(UpdateData.this);
                queue.add(updateRequest);
            }
        });




    }
}
