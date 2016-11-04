package com.example.jake.coffee;

import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.VideoView;

import java.util.Random;
import java.util.concurrent.TimeUnit;
//This TimerActivity Displays the Timer Page and it's variables
public class TimerActivity extends AppCompatActivity {
    //Declare Variables
    Button btnStart, btnStop, Calculate;
    TextView Time;
    EditText TimeCalc;
    VideoView Video;
    Random random;
    MediaController mediaController;
    Uri uri;
    private long BrewTime;
    int UserGram;
    private CounterClass timer;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timer);

        btnStart = (Button)findViewById(R.id.btnStart);
        btnStop = (Button)findViewById(R.id.btnStop);
        Time  = (TextView)findViewById(R.id.textViewTime);
        TimeCalc = (EditText) findViewById(R.id.TimeCalcID);
        Calculate = (Button) findViewById(R.id.CalculateID);
        Video = (VideoView) findViewById(R.id.videoViewID);
        //To get the video path
        uri =Uri.parse("android.resource://"+getPackageName()+"/"+R.raw.latte);

        mediaController = new MediaController(this);
        random = new Random();

        Video.setMediaController(mediaController);
        Video.setVideoURI(uri);

        //Stating Timer display
        Time.setText("00:00");


        Calculate.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                //This changes the string from users input into an integer number
                UserGram = Integer.parseInt(TimeCalc.getText().toString());

                //Calculation (1gram needs 4.8 seconds)
                double FinalTime = UserGram * 4.8;

                //Splits up seconds and minutes
                Integer Seconds = (int) FinalTime % 60;
                Integer Minutes = (int) FinalTime / 60;
                String Time = Minutes + ":" + Seconds;
                //Prints out the Timer every second onto Android run screen
                System.out.println(Time);
            }
        });


        btnStart.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                //This needs to be multiplied by 1000 as 1 second is equivalent to 1000
                BrewTime = (int) ((UserGram * 4.8) * 1000);
                //Prints out the Brewing Time every second onto Android run screen
                System.out.println("Brew Time: "+BrewTime);
                timer = new CounterClass(BrewTime,1000);
                //On Click Start button: start timer and stream video
                timer.start();
                Video.start();
            }
        });

        btnStop.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                //Stop timer if stop button is pressed
                timer.cancel();
                Video.stopPlayback();
            }
        });
    }

    public class CounterClass extends CountDownTimer {
        //This class takes in two long varibales from its master class(CountDownTimer)
        public CounterClass(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);
        }

        @Override
        public void onFinish() {
            //Display message and stop video
            Time.setText("Brew Complete");
            //Video.stopPlayback();
        }

        @Override
        //This function records each second for display purposes
        public void onTick(long millisUntilFinished) {
            long millis = millisUntilFinished;
            String hms = String.format("%02d:%02d",
                    TimeUnit.MILLISECONDS.toMinutes(millis) - TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(millis)),
                    TimeUnit.MILLISECONDS.toSeconds(millis) - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(millis)));

            System.out.println(hms);
            Time.setText(hms);

            //This small code changes the Timer to different colours every seconds to make it stand out
            int colour = Color.argb(255, random.nextInt(255), random.nextInt(255), random.nextInt(255));
            Time.setTextColor(colour);
        }
    }
}



