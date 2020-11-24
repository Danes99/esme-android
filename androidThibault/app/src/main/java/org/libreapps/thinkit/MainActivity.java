package org.libreapps.thinkit;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ActionBar;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

   private ImageButton buttonHome;
   private ImageButton buttonAdd;
   private ImageButton buttonLike;
   private ImageButton buttonUser;
   private LinearLayout articles;
    @Override
    //https://mathias-seguy.developpez.com/tutoriels/android/construction-interfaces-graphiques-dynamiques/
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.buttonAdd=findViewById(R.id.buttonAdd);
        this.buttonHome=findViewById(R.id.buttonHome);
        this.buttonLike=findViewById(R.id.buttonLike);
        this.buttonUser=findViewById(R.id.buttonUser);
        this.articles = (LinearLayout) findViewById(R.id.ContentArticle);

        LinearLayout.LayoutParams ArticleParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams. MATCH_PARENT , LinearLayout.LayoutParams. WRAP_CONTENT );
        ArticleParams.setMargins(30,30,30,30);


       // LinearLayout articles = new LinearLayout(this);
       // articles.setBackgroundColor(getResources().getColor(R.color.title));
        articles.setOrientation(LinearLayout.VERTICAL);
        articles.setLayoutParams(ArticleParams);
        articles.setGravity(Gravity.CENTER);


        ViewGroup.LayoutParams params = new ActionBar.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);

        for(int i=0;i<7;i++){
            LinearLayout artest = new LinearLayout(this);
            artest.setBackgroundColor(getResources().getColor(R.color.articles));
            artest.setOrientation(LinearLayout.VERTICAL);
            artest.setPadding(10,10,10,50);
            artest.setLayoutParams(ArticleParams);
            artest.setGravity(Gravity.CENTER);
            articles.addView(artest);

            TextView title = new TextView(this);
            title.setLayoutParams(params);
            title.setText(getResources().getString(R.string.title));
            title.setTextSize(18);
            title.setTypeface(Typeface.DEFAULT_BOLD);
            title.setPadding(10,5,10,20);
            artest.addView(title);

            TextView textArticle = new TextView(this);
            textArticle.setLayoutParams(params);
            textArticle.setText(getResources().getString(R.string.article));
            textArticle.setPadding(30,0,10,0);
            artest.addView(textArticle);
        }





        buttonAdd.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent otherActivity = new Intent(getApplicationContext(),AddPost.class);
                startActivity(otherActivity);

            }
        });
        buttonLike.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent otherActivity = new Intent(getApplicationContext(),LikePost.class);
                startActivity(otherActivity);

            }
        });
        buttonUser.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent otherActivity = new Intent(getApplicationContext(),UserPost.class);
                startActivity(otherActivity);

            }
        });
    }
}