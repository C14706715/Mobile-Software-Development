package com.example.jake.coffee;


import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

//This class is used to retrieve data from the database and display it on screen
public class GetData extends AppCompatActivity implements View.OnClickListener{

    //Delcared variables
    private Button buttonGet;
    private TextView textViewResult;
    private ProgressDialog loading;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_getdata);

        //Initialised variables
        buttonGet = (Button) findViewById(R.id.buttonGet);
        textViewResult = (TextView) findViewById(R.id.textView);

        //Set a listener to listen for when the user clicks the option to load user data
        buttonGet.setOnClickListener(this);
    }

    private void getData() {
        //Create a dialog to inform user
        loading = ProgressDialog.show(this,"Please wait...","Fetching...",false,false);

        //send web address to url
        String url = Config.URL;

        //put the response from the connection response into this  variable
        StringRequest stringRequest = new StringRequest(url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                loading.dismiss();
                showJSON(response);
            }
        },      //if there is an error show error message
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(GetData.this,error.getMessage().toString(),Toast.LENGTH_LONG).show();
                    }
                });

        //Using the volley library to access data from external database
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

    //The data is in a JSON file so convert it to accessible strings
    private void showJSON(String response){

        //Initialising the variables to a null value
        String name="";
        String username="";
        String password = "";

        //Try Catch() used to parse the data and catch the exception
        try {
            JSONObject jsonObject = new JSONObject(response);
            JSONArray result = jsonObject.getJSONArray(Config.JSON_ARRAY);
            JSONObject UserData = result.getJSONObject(0);
            name = UserData.getString(Config.Name);
            username = UserData.getString(Config.Username);
            password = UserData.getString(Config.Password);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        //This is what will be shown in the TextView for the user
        textViewResult.setText("Name:\t"+name+"\nUsername:\t" +username+ "\nPassword:\t"+ password);
    }

    @Override
    public void onClick(View v) {
        getData();
    }
}