package org.libreapps.thinkitapp.activity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.libreapps.thinkitapp.AddFragment;
import org.libreapps.thinkitapp.HomeFragment;
import org.libreapps.thinkitapp.R;
import org.libreapps.thinkitapp.connection.APIThinkit;

import static android.view.View.*;
import static org.libreapps.thinkitapp.R.drawable.shape;

public class ReadActivity extends AppCompatActivity {

    private LinearLayout readArticle;
    private LinearLayout layout_article;
    private TextView title_article;
    private TextView containt_article;
    private LinearLayout button_layout;
    private Button modification;
    private Button delete;

    private String token;
    private JSONObject article;

    private String id;
    private String title;
    private String content;
    private String isFavorite;
    private String completed;
    private String owner;
    private String createdAt;
    private String updatedAt;
    private String version;

    private LinearLayout layoutUdpate;
    private EditText updatedTitle;
    private EditText updatedContent;
    private Button updatedButton;


    @SuppressLint("ResourceAsColor")
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_read);
        this.readArticle = (LinearLayout)findViewById(R.id.mainLayout);

        this.layout_article = (LinearLayout)findViewById(R.id.container_article);
        this.layout_article.removeAllViewsInLayout();

        button_layout = new LinearLayout(readArticle.getContext());

        this.readArticle.removeAllViewsInLayout();
        readArticle.addView(button_layout);
        readArticle.addView(layout_article);

        token = getIntent().getStringExtra("token");
        try {
            article = new JSONObject(getIntent().getStringExtra("article"));
            id = article.getString("_id");
            title = article.getString("title");
            content = article.getString("content");
            isFavorite = article.getString("isFavorite");
            completed = article.getString("completed");
            owner = article.getString("owner");
            createdAt = article.getString("createdAt");
            updatedAt = article.getString("updatedAt");
            version = article.getString("_v");
        } catch (JSONException e) {
            e.printStackTrace();
        }


        //section of title
        title_article = new TextView(layout_article.getContext());
        title_article.setText(title);
        title_article.setTextSize(30);

        //section of content
        containt_article = new TextView(layout_article.getContext());
        containt_article.setText(content);
        containt_article.setTextSize(18);

        layout_article.addView(title_article);
        layout_article.addView(containt_article);


        //Layout with button modif and delete
        ViewGroup.MarginLayoutParams paramsContaint = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT,1);
        paramsContaint.setMargins(20,25,20,0);

        modification = new Button(readArticle.getContext());
        modification.setTextColor(getResources().getColor(R.color.white));
        modification.setGravity(Gravity.CENTER);
        modification.setTextSize(20);
        modification.setBackground(getResources().getDrawable(R.drawable.buttongreen));
        modification.setText("Modification");

        modification.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                // Go to new activity
                setContentView(R.layout.fragment_add);

                layoutUdpate = findViewById(R.id.layout_add);

                updatedTitle = findViewById(R.id.id_title);
                updatedContent = findViewById(R.id.id_content);
                updatedButton = findViewById(R.id.id_button);

                updatedTitle.setText(title);
                updatedContent.setText(content);
                updatedButton.setText("Submit");

                updatedButton.setOnClickListener(new View.OnClickListener() {

                    @Override
                    public void onClick(View v) {
                         String titleToUpdate = updatedTitle.getText().toString();
                         String contentToUpdate = updatedContent.getText().toString();
                         @SuppressLint("ResourceType") String ContentToUpdate = (String) getResources().getText(R.id.id_content);
                        APIThinkit api = new APIThinkit();
                        //List of articles
                       JSONObject response = api.updateArticle(
                            id,
                            titleToUpdate,
                           contentToUpdate,
                           null,
                           token
                       );
                        // Go to new activity
                        Intent MainActivity = new Intent(ReadActivity.this, org.libreapps.thinkitapp.activity.MainActivity.class);
                        MainActivity.putExtra("token", token);
                        startActivity(MainActivity);
                    }
                });
            }
        });


        delete = new Button(readArticle.getContext());
        delete.setTextColor(getResources().getColor(R.color.white));
        delete.setGravity(Gravity.CENTER);
        delete.setTextSize(20);
        delete.setBackground(getResources().getDrawable(R.drawable.buttonred));
        delete.setText("Delete");

        delete.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                APIThinkit api = new APIThinkit();
                //List of articles
                JSONObject response = api.deleteArticle(
                        id,
                        token
                );
                // Go to new activity
                Intent MainActivity = new Intent(ReadActivity.this, org.libreapps.thinkitapp.activity.MainActivity.class);
                MainActivity.putExtra("token", token);
                startActivity(MainActivity);
            }
        });



        button_layout.addView(modification,paramsContaint);
        button_layout.addView(delete, paramsContaint);



        /*ViewGroup.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        ViewGroup.MarginLayoutParams paramsContaint = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        paramsContaint.setMargins(40,10,40,30);

        readArticle = new LinearLayout(layout_article.getContext());
        readArticle.setOrientation(LinearLayout.VERTICAL);
        readArticle.setLayoutParams(paramsContaint);
        readArticle.setPadding(5,5,5,5);
        readArticle.setBackground(getResources().getDrawable(shape));

        readArticle.removeAllViewsInLayout();

        // section of title
        title_article = new TextView(layout_article.getContext());
        title_article.setText(title);
        title_article.setTextSize(30);

        //section of content
        containt_article = new TextView(layout_article.getContext());
        containt_article.setText(content);
        containt_article.setTextSize(18);


        //section adding widget
        readArticle.addView(title_article,params);
        readArticle.addView(containt_article,params);
        //layout_article.removeAllViewsInLayout();
        layout_article.addView(readArticle);

       */
    }
}