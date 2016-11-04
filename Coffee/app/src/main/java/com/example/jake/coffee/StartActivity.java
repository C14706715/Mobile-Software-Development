package com.example.jake.coffee;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

//This activity deals with the list page. It lists the pages you can navigate to
public class StartActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        //Create and array with Page names
        String[] myStringArray={
                "Home", "Timer", "Nearest Coffee Shop"
        };
    
        //An adapter is created in order to use listView
        final ArrayAdapter<String> myAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_activated_1, myStringArray);
        //Linking ListView to the id on the ListPage
        ListView myList = (ListView) findViewById(R.id.ButtonListView);
        //Setting the adapter
        myList.setAdapter(myAdapter);

        //Implementing a listener to change the pages depending on which list item is clicked
        myList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = null;
                switch(position){
                    case 0:
                        intent = new Intent( StartActivity.this, MainActivity.class);
                        break;
                    case 1:
                        intent = new Intent (StartActivity.this, TimerActivity.class);
                        break;
                    case 2:
                        intent= new Intent (StartActivity.this, MapsActivity.class);
                        break;
                }
                startActivity(intent);
            }
        });
    }
}











