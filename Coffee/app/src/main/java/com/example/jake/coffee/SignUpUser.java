package com.example.jake.coffee;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
//This activity deals with the sign up page of the app. It contains different buttons along with
//changing pages depending on which button is clicked(listeners implemented to wait for user's response)
public class SignUpUser extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_user);

        Button Login = (Button) findViewById(R.id.LoginBtnID);
        Button SignUp = (Button) findViewById(R.id.SignUpBtnID);

        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SignUpUser.this, LoginUser.class));
            }
        });

        SignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SignUpUser.this, StartActivity.class));
            }
        });
    }

}
