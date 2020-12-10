package org.libreapps.thinkitapp.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import org.libreapps.thinkitapp.R;

public class OpenApplicationActivity extends AppCompatActivity {

    private Button mButtonLogin;
    private Button mButtonSignup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_open_application);
        
        // Widget Button
        mButtonLogin = findViewById(R.id.activity_open_application_button_go_Login_activity);
        mButtonSignup = findViewById(R.id.activity_open_application_button_go_signup_activity);

        // Login (Sign In) Button
        mButtonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent loginActivity = new Intent(OpenApplicationActivity.this, LoginActivity.class);
                startActivity(loginActivity);
            }
        });

        // Sign Up Button
        mButtonSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent signupActivity = new Intent(OpenApplicationActivity.this, SignupActivity.class);
                startActivity(signupActivity);
            }
        });
    }
}