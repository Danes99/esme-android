package com.example.thinkit.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import com.example.thinkit.R;
import com.example.thinkit.connection.APIThinkit;

import org.json.JSONArray;

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

        Boolean logout = api.logout();
        Log.v("Logout", String.valueOf(logout));

        /*
        // Patch article
        JSONObject patchedArticle = api.updateArticle(
                "5fcf893523666e0017ca6307",
                null,
                null,
                true,
                token
        );

        Log.v("patchedArticle", String.valueOf(patchedArticle));
         */

    }
}