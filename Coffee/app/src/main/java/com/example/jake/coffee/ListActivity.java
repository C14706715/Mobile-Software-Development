package com.example.jake.coffee;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

//This activity deals with the list page. It lists the pages you can navigate to
public class ListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        //Create and array with Page names
        String[] myStringArray={
                "Home", "Timer", "Nearest Coffee Shop"
        };

        //Create Array for CRUD operations
        String[] CRUD={
                "Create User", "Read User", "Update User", "Delete User"
        };
    
        //An adapter is created in order to use listView
        final ArrayAdapter<String> myAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_activated_1, myStringArray);
        //Linking ListView to the id on the ListPage
        ListView myList = (ListView) findViewById(R.id.ButtonListView);
        //Setting the adapter
        myList.setAdapter(myAdapter);

        //An adapter is created in order to use listView
        final ArrayAdapter<String> myAdapter2 = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_activated_1, CRUD);
        //Linking ListView to the id on the ListPage
        ListView CRUDList = (ListView) findViewById(R.id.CRUDListView);
        //Setting the adapter
        CRUDList.setAdapter(myAdapter2);

        //Implementing a listener to change the pages depending on which list item is clicked
        myList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = null;
                switch(position){
                    case 0:
                        intent = new Intent( ListActivity.this, MainActivity.class);
                        break;
                    case 1:
                        intent = new Intent (ListActivity.this, TimerActivity.class);
                        break;
                    case 2:
                        intent= new Intent (ListActivity.this, MapsActivity.class);
                        break;
                }
                startActivity(intent);
            }
        });


        //Implementing a listener to change the pages depending on which CRUD operation is clicked
        CRUDList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = null;
                switch(position){
                    case 0:
                        //Brings User to create a new account on Register page (CREATE)
                        intent = new Intent(ListActivity.this, SignUpUser.class);
                        break;
                    case 1:
                        intent = new Intent(ListActivity.this, GetData.class);
                        break;
                    case 2:

                        break;
                }
                startActivity(intent);
            }
        });
    }
}











