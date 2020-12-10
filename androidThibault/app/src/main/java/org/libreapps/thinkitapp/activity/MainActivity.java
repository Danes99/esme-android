package org.libreapps.thinkitapp.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import org.json.JSONArray;
import org.libreapps.thinkitapp.AddFragment;
import org.libreapps.thinkitapp.HomeFragment;
import org.libreapps.thinkitapp.LikeFragment;
import org.libreapps.thinkitapp.R;
import org.libreapps.thinkitapp.UserFragment;
import org.libreapps.thinkitapp.connection.APIThinkit;

public class MainActivity extends AppCompatActivity {

    // JSON Web Token (JWT)
    private String token;
    private String user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        token = getIntent().getStringExtra("token");
        user = getIntent().getStringExtra("user");

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_nav);
        bottomNavigationView.setOnNavigationItemSelectedListener(navigationItemSelectedListner);
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new HomeFragment(token)).commit();
    }


    private BottomNavigationView.OnNavigationItemSelectedListener navigationItemSelectedListner = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {

            Fragment selectedFragment = null;
            switch (item.getItemId())
            {
                case R.id.menu_home:
                    selectedFragment = new HomeFragment(token);
                    break;
                case R.id.menu_add:
                    selectedFragment = new AddFragment(token);
                    break;
                case R.id.menu_user:
                    selectedFragment = new UserFragment(token, user);
                    break;
            }
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,selectedFragment).commit();
            return true;
        }
    };
}