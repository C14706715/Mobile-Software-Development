package com.example.jake.coffee;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.VideoView;

import java.util.concurrent.TimeUnit;

public class TimerActivity extends AppCompatActivity {
    //Declare Variables
    Button btnStart, btnStop, Calculate;
    TextView Time;
    EditText TimeCalc;
    VideoView Video;
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

        Time.setText("00:00");
        Video.setVideoPath("https://r15---sn-5hnednes.googlevideo.com/videoplayback?id=faf0fb5f986b01af&itag=15&source=picasa&begin=0&requiressl=yes&pl=24&sc=yes&mime=video/mp4&lmt=1478187732497550&ip=178.167.255.29&ipbits=8&expire=1478216568&sparams=expire,id,ip,ipbits,itag,lmt,mime,mm,mn,ms,mv,nh,pl,requiressl,sc,source&signature=4B17312A5A9D8AEA37242E95F77653D407551614.71A1D3F302AA3E04D06F0D826505EC8306A38444&key=cms1&cpn=Jm2I73HxUu3_9MMe&c=WEB&cver=1.20161101&redirect_counter=1&cm2rm=sn-q0cz7z&req_id=2d5030853d0fa3ee&cms_redirect=yes&mm=34&mn=sn-5hnednes&ms=ltu&mt=1478187745&mv=m&nh=IgpwcjAxLmR1YjA2KgkxMjcuMC4wLjE");


        Calculate.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                UserGram = Integer.parseInt(TimeCalc.getText().toString());

                double FinalTime = UserGram * 4.8;

                Integer Seconds = (int) FinalTime % 60;
                Integer Minutes = (int) FinalTime / 60;
                String Time = Minutes + ":" + Seconds;
                System.out.println(Time);
            }
        });


        btnStart.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                BrewTime = (int) ((UserGram * 4.8) * 1000);
                System.out.println("Brew Time: "+BrewTime);
                timer = new CounterClass(BrewTime,1000);
                timer.start();
                Video.start();

            }
        });

        btnStop.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                timer.cancel();
            }
        });
    }

    public class CounterClass extends CountDownTimer {

        public CounterClass(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);
        }

        @Override
        public void onFinish() {

            Time.setText("Brew Complete");
            Video.pause();
        }

        @Override
        public void onTick(long millisUntilFinished) {

            long millis = millisUntilFinished;
            String hms = String.format("%02d:%02d",
                    TimeUnit.MILLISECONDS.toMinutes(millis) - TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(millis)),
                    TimeUnit.MILLISECONDS.toSeconds(millis) - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(millis)));
            System.out.println(hms);

            Time.setText(hms);
        }
    }
}



