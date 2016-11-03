package com.example.jake.coffee;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

public class StartActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        Button Timer = (Button) findViewById(R.id.TimerBtnID);

        Timer.setOnClickListener(new android.view.View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(StartActivity.this, TimerActivity.class));
            }
        });

        String[] myStringArray={
                "Timer", "Mapping"
        };


        final ArrayAdapter<String> myAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_activated_1, myStringArray);
        ListView myList = (ListView) findViewById(R.id.ButtonListView);
        myList.setAdapter(myAdapter);

        myList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = null;
                switch(position){
                    case 0:
                        intent = new Intent (StartActivity.this, TimerActivity.class);
                        break;
                    case 1:
                        intent= new Intent (StartActivity.this, LoginUser.class);
                        break;
                }
                startActivity(intent);

            }
        });
    }
}











