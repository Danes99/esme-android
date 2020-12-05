package com.example.thinkit;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.Button;
import android.widget.EditText;

public class SignupActivity extends AppCompatActivity {

    private Button mButton;

    private boolean isButtonEnabled = false;
    private boolean isEmailValid = false;
    private boolean isNameValid = false;
    private boolean isPasswordValid = false;


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
                setButton();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

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