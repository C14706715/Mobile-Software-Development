package com.example.jake.coffee;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

//This is the main Activity page it is the first screen in which the user will see.
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //An array of images used to flow through different images as seen
        final int[] imageArray = {
                R.drawable.tree,
                R.drawable.bagging,
                R.drawable.coffeebean,
                R.drawable.drying,
                R.drawable.fairtrade,
                R.drawable.harvest
        };

        //Declared variables
        Button Login, SignUp;

        //Declared as final as they are used within inner classes
        final ImageView Facebook, Instagram, GalleryImages;
        final Handler handler;
        final Runnable runnable;

        //Initialised variables
        Login = (Button) findViewById(R.id.LoginID);
        SignUp = (Button) findViewById(R.id.SignUpID);
        Facebook = (ImageView) findViewById(R.id.FacebookID);
        Instagram = (ImageView) findViewById(R.id.InstagramID);
        GalleryImages = (ImageView) findViewById(R.id.HarvestID);
        handler = new Handler();
        runnable = new Runnable() {
            int i=0;
            public void run() {
                GalleryImages.setImageResource(imageArray[i]);
                i++;
                if(i>imageArray.length-1)
                {
                    i=0;
                }
                handler.postDelayed(this, 1000);  //for interval...
            }
        };
        handler.postDelayed(runnable, 2000); //for initial delay..

        //Listeners for each button or image on screen
        Login.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, LoginUser.class));
            }
        });

        SignUp.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, SignUpUser.class));
            }
        });

        Instagram.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Uri used to connect to the website
                Uri uri = Uri.parse("http://www.instagram.com");
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
            }
        });

        Facebook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri uri = Uri.parse("http://www.facebook.com");
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
            }
        });
    }
}
