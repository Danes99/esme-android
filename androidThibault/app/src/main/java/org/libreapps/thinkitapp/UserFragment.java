package org.libreapps.thinkitapp;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.libreapps.thinkitapp.connection.APIThinkit;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class UserFragment extends Fragment {

    private String token;
    private String user;
    private String date;
    private TextView name;
    private TextView mail;
    private Button logOut;
    private TextView createdAt;
    private JSONObject userJSON;

    public UserFragment(String token, String user){
        this.token = token;
        this.user = user;
    }
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_user,container,false);


        //user
        try {
            userJSON = new JSONObject(user);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        String str =userJSON.optString("createdAt");
        date = str.substring(0, 10);
        name = v.findViewById(R.id.name);
        mail = v.findViewById(R.id.mail);
        createdAt = v.findViewById(R.id.createdAt);
        name.setText("name : " +userJSON.optString("name"));
        mail.setText("mail : " +userJSON.optString("email"));
        createdAt.setText("createdAt : " +date);

        logOut = v.findViewById(R.id.logout);
        logOut.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                APIThinkit api = new APIThinkit();
                //Create new article
                Boolean response = api.logout();
                System.exit(0);
            }
        });

        return v;
    }
}
