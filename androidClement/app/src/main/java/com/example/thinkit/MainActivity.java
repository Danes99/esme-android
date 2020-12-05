package com.example.thinkit;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import com.example.thinkit.connection.connectionREST;

import java.util.concurrent.ExecutionException;

public class MainActivity extends AppCompatActivity {

    String token;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // JSON Web Token (JWT)
        token = getIntent().getStringExtra("token");
        Log.v("Token", token);

        // Request parameters
        String url = "http://node-thinkit.herokuapp.com/article";
        String method = "GET";

        // Connection
        connectionREST connection = new connectionREST(url, method, null);
        connection.setToken(token);
        connection.execute((Object) null);

        try {
            // Synchronus Method
            // Wait for the response
            String response = (String) connection.get();
            Log.v("Article List", response);
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}