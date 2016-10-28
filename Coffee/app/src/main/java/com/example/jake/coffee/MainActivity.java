package com.example.jake.coffee;


import android.content.Intent;
import android.net.Uri;
import android.os.CountDownTimer;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Gallery;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final int[] imageArray = {
                R.drawable.tree,
                R.drawable.bagging,
                R.drawable.coffeebean,
                R.drawable.drying,
                R.drawable.fairtrade,
                R.drawable.harvest
        };

        Button Login = (Button) findViewById(R.id.LoginID);
        Button SignUp = (Button) findViewById(R.id.SignUpID);
        ImageView Facebook = (ImageView) findViewById(R.id.FacebookID);
        ImageView Instagram = (ImageView) findViewById(R.id.InstagramID);
        final ImageView GalleryImages = (ImageView) findViewById(R.id.HarvestID);

        final Handler handler = new Handler();
        Runnable runnable = new Runnable() {
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
