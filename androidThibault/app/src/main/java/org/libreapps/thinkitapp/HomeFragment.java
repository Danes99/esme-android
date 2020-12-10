package org.libreapps.thinkitapp;

import android.annotation.SuppressLint;
import android.app.ActionBar;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.libreapps.thinkitapp.activity.ReadActivity;
import org.libreapps.thinkitapp.connection.APIThinkit;
import org.w3c.dom.Text;

import static org.libreapps.thinkitapp.R.color.black;
import static org.libreapps.thinkitapp.R.color.bluelight2;
import static org.libreapps.thinkitapp.R.drawable.shape;

public class HomeFragment extends Fragment {
    private LinearLayout readArticle;
    private LinearLayout layout_article;
    private TextView title_article;
    private TextView containt_article;
    private final String token;
    private LayoutInflater inflater;

    public HomeFragment(String token){
        this.token = token;
    }

    @SuppressLint("ResourceAsColor")
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_home,container,false);

        APIThinkit api = new APIThinkit();
        //List of articles
        JSONArray response = api.readArticles(token);
        //Log.v("Articles", String.valueOf(response));
        /***********Activity read article************

        //readArticle = v.findViewById(R.id.container_article);
        //readArticle.setOrientation(LinearLayout.VERTICAL);

        readArticle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(),ReadActivity.class);
                startActivity(intent);
            }
        });*/



        ViewGroup.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        ViewGroup.MarginLayoutParams paramsContaint = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 450);
        paramsContaint.setMargins(40,10,40,30);

        layout_article = v.findViewById(R.id.mainLayout);
        layout_article.setOrientation(LinearLayout.VERTICAL);
        layout_article.removeAllViewsInLayout();
        //layout_article.addView(readArticle,paramsContaint);

        int boucle = response.length()-1;
        for(int i = 0; i < response.length(); i++)
        {
            //get title and content from web database

            JSONObject article = null;
            try {
                article = response.getJSONObject(boucle);
                boucle = boucle-1;
            } catch (JSONException e) {
                e.printStackTrace();
            }
            readArticle = new LinearLayout(layout_article.getContext());
            readArticle.setOrientation(LinearLayout.VERTICAL);
            readArticle.setLayoutParams(paramsContaint);
            readArticle.setPadding(5,5,5,5);
            readArticle.setBackground(getResources().getDrawable(shape));

            //readArticle.removeAllViewsInLayout();

            // section of title
            title_article = new TextView(layout_article.getContext());
            title_article.setText(article.optString("title"));
            title_article.setTextSize(30);

            //section of content
            containt_article = new TextView(layout_article.getContext());
            containt_article.setText(article.optString("content"));
            containt_article.setTextSize(18);


            //section adding widget
            readArticle.addView(title_article,params);
            readArticle.addView(containt_article,params);
            //layout_article.removeAllViewsInLayout();
            layout_article.addView(readArticle);

            JSONObject finalArticle = article;
            readArticle.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(getActivity(), ReadActivity.class);

                    //parameters for read arcticle activity
                    intent.putExtra("id",  finalArticle.optString("_id"));
                    intent.putExtra("title",  finalArticle.optString("title"));
                    intent.putExtra("content",  finalArticle.optString("content"));
                    intent.putExtra("isFavorite",  finalArticle.optString("isFavorite"));
                    intent.putExtra("completed",  finalArticle.optString("completed"));
                    intent.putExtra("owner",  finalArticle.optString("owner"));
                    intent.putExtra("createdAt",  finalArticle.optString("createdAt"));
                    intent.putExtra("updatedAt",  finalArticle.optString("updatedAt"));
                    intent.putExtra("version",  finalArticle.optString("__v"));

                    intent.putExtra("token",  token);
                    intent.putExtra("article", finalArticle.toString());

                    startActivity(intent);
                }
            });
        }

        //readArticle.setBackgroundColor(R.color.bluedark);
        //Color.parseColor("#E3EDFF")
        //layout_article = v.findViewById(R.id.content_article);

       /*
        for(int i = 0; i < response.length(); i++)
        {
            //get title and content from web database
            JSONObject article = null;
            try {
                article = response.getJSONObject(i);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            // Container layout
            layout_article = new LinearLayout(readArticle.getContext());
            //layout_article.setLayoutParams(params);
            layout_article.setOrientation(LinearLayout.VERTICAL);
            //layout_article.setDividerDrawable(getResources().getDrawable(R.drawable.shape));


            // title of article
            title_article = new TextView(readArticle.getContext());
            title_article.setLayoutParams(params);
            title_article.setTextSize(30);
            title_article.setPadding(5,5,5,5);
            title_article.setText(article.optString("title"));
            layout_article.addView(title_article);

            // Content of article
            containt_article = new TextView(readArticle.getContext());
            containt_article.setText(article.optString("content"));
            containt_article.setTextSize(18);
            containt_article.setPadding(5,5,5,5);
            containt_article.setLayoutParams(params);
            layout_article.addView(containt_article);

            layout_article.setLayoutParams(paramsContaint);
            layout_article.setPadding(10,5,10,5);


            readArticle.addView(layout_article);



        }*/

        return v;
    }
}
