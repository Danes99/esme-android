package com.example.thinkit;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.thinkit.connection.APIThinkit;

import org.json.JSONException;
import org.json.JSONObject;

public class SignupActivity extends AppCompatActivity {

    private Button mButton;

    private boolean isButtonEnabled = false;
    private boolean isEmailValid = false;
    private boolean isNameValid = false;
    private boolean isPasswordValid = false;

    private String mName;
    private String mEmail;
    private String mPassword;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        // Form Content
        EditText nameInput = findViewById(R.id.activity_signup_text_name);
        EditText emailInput = findViewById(R.id.activity_signup_text_email);
        EditText passwordInput = findViewById(R.id.activity_signup_text_password);

        // Validate Form Button
        mButton = findViewById(R.id.activity_signup_button_signup);
        mButton.setEnabled(isButtonEnabled);

        // Form listener

        nameInput.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                isNameValid = s.toString().length() > 0;
                mName = s.toString();
                setButton();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        emailInput.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                isEmailValid = s.toString().length() > 0;
                mEmail = s.toString();
                setButton();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        passwordInput.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                isPasswordValid = s.toString().length() >= 7;
                mPassword = s.toString();
                setButton();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        mButton.setOnClickListener(
                new View.OnClickListener() {

                    @Override
                    public void onClick(View v) {
                        try {

                            APIThinkit api = new APIThinkit();
                            Log.v("Email", mEmail);
                            JSONObject response = api.signup(mName, mEmail, mPassword);

                            String token = response.getString("token");

                            // Go to new activity
                            Intent MainActivity = new Intent(SignupActivity.this, MainActivity.class);
                            MainActivity.putExtra("token", token);
                            startActivity(MainActivity);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }
        );

    }

    private void setButton() {
        // Enable button if conditions are required
        boolean isFormValid = isNameValid && isEmailValid && isPasswordValid;

        if (isFormValid != isButtonEnabled) {
            isButtonEnabled = isFormValid;
            mButton.setEnabled(isFormValid);
        }

    }


}