package com.example.thinkit;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.thinkit.connection.login;
import com.example.thinkit.connection.connectionREST;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.concurrent.ExecutionException;

public class LoginActivity extends AppCompatActivity {

    private Button mButton;

    private boolean isButtonEnabled = false;
    private boolean isEmailValid = false;
    private boolean isPasswordValid = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // Form Content
        EditText emailInput = findViewById(R.id.activity_login_text_email);
        EditText passwordInput = findViewById(R.id.activity_login_text_password);

        // Validate Form Button
        mButton = findViewById(R.id.activity_login_button_login);
        mButton.setEnabled(isButtonEnabled);

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

        mButton.setOnClickListener(
                new View.OnClickListener() {

                    @Override
                    public void onClick(View v) {
                        //login connection = new login("clement.stauner@gmail.com", "Computer0987");
                        //connection.execute((Object) null);
                        //String body = connection.getResponse();
                        //Log.v("HTTP POST /users Body", body);

                        String url = "http://node-thinkit.herokuapp.com/users/login";
                        String method = "POST";
                        String response;

                        // Create the Request Body
                        JSONObject body = new JSONObject();
                        try {
                            body.put("email", "clement.stauner@gmail.com");
                            body.put("password", "Computer0987");
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }


                        try {

                            connectionREST login = new connectionREST(url, method, body);
                            login.execute((Object) null);

                            // Synchronus Method
                            // Wait for the response
                            response = (String) login.get();
                            JSONObject json = new JSONObject(response);
                            String token = json.getString("token");

                            // Go to new activity
                            Intent MainActivity = new Intent(LoginActivity.this, MainActivity.class);
                            MainActivity.putExtra("token", token);
                            startActivity(MainActivity);

                        } catch (ExecutionException e) {
                            e.printStackTrace();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }
        );

    }

    private void setButton() {
        // Enable button if conditions are required
        boolean isFormValid = isEmailValid && isPasswordValid;

        if (isFormValid != isButtonEnabled) {
            isButtonEnabled = isFormValid;
            mButton.setEnabled(isFormValid);
        }

    }
}