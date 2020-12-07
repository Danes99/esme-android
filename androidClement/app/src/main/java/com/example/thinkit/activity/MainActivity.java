package com.example.thinkit.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import com.example.thinkit.R;
import com.example.thinkit.connection.APIThinkit;
import com.example.thinkit.connection.connectionREST;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

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

        APIThinkit api = new APIThinkit();

        // List of articles
        JSONArray response = api.readArticles(token);
        Log.v("Articles", String.valueOf(response));


    }
}