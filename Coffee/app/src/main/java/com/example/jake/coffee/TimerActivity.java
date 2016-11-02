package com.example.jake.coffee;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.concurrent.TimeUnit;

public class TimerActivity extends AppCompatActivity {
    Button btnStart, btnStop, Go;
    TextView textViewTime;
    EditText TimeCalc;
    private long BrewTime;
    int UserGram;
    private CounterClass timer;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timer);

        btnStart = (Button)findViewById(R.id.btnStart);
        btnStop = (Button)findViewById(R.id.btnStop);
        textViewTime  = (TextView)findViewById(R.id.textViewTime);
        textViewTime.setText("00:00:00");
        final EditText TimeCalc = (EditText) findViewById(R.id.TimeCalcID);
        Button Go = (Button) findViewById(R.id.GoID);

        Go.setOnClickListener(new OnClickListener() {
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
            textViewTime.setText("Completed.");
        }

        @Override
        public void onTick(long millisUntilFinished) {

            long millis = millisUntilFinished;
            String hms = String.format("%02d:%02d:%02d", TimeUnit.MILLISECONDS.toHours(millis),
                    TimeUnit.MILLISECONDS.toMinutes(millis) - TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(millis)),
                    TimeUnit.MILLISECONDS.toSeconds(millis) - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(millis)));
            System.out.println(hms);

            textViewTime.setText(hms);
        }
    }
}



