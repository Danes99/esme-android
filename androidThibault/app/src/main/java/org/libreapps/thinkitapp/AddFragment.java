package org.libreapps.thinkitapp;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputLayout;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.libreapps.thinkitapp.activity.MainActivity;
import org.libreapps.thinkitapp.activity.ReadActivity;
import org.libreapps.thinkitapp.connection.APIThinkit;

public class AddFragment extends Fragment {

    private LinearLayout layoutAdd;

    private boolean isButtonEnabled = false;
    private boolean isTitleValid = false;
    private boolean isContentValid = false;

    private Button buttonCreate;
    private EditText titleCreate;
    private EditText ContentCreate;
    private String title;
    private String content;

    private String token;

    public AddFragment(String token){
        this.token = token;
    }
   @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_add,container,false);

       titleCreate = v.findViewById(R.id.id_title);
       ContentCreate = v.findViewById(R.id.id_content);

       titleCreate.addTextChangedListener(new TextWatcher() {
           @Override
           public void beforeTextChanged(CharSequence s, int start, int count, int after) {

           }

           @Override
           public void onTextChanged(CharSequence s, int start, int before, int count) {
               title = s.toString();
               isTitleValid = s.toString().length() > 0;
               setButton();
           }

           @Override
           public void afterTextChanged(Editable s) {

           }
       });

       ContentCreate.addTextChangedListener(new TextWatcher() {
           @Override
           public void beforeTextChanged(CharSequence s, int start, int count, int after) {

           }

           @Override
           public void onTextChanged(CharSequence s, int start, int before, int count) {
               content = s.toString();
               isContentValid = s.toString().length() > 0;
               setButton();
           }

           @Override
           public void afterTextChanged(Editable s) {

           }
       });

       buttonCreate = v.findViewById(R.id.id_button);
       buttonCreate.setEnabled(isButtonEnabled);


       buttonCreate.setOnClickListener(new View.OnClickListener() {

           @Override
           public void onClick(View v) {

               APIThinkit api = new APIThinkit();
               //Create new article
               JSONObject response = api.createArticle(title, content, false, token);
           }
       });




       return v;
    }
    private void setButton() {
        // Enable button if conditions are required
        boolean isFormValid = isTitleValid && isContentValid;

        if (isFormValid != isButtonEnabled) {
            this.isButtonEnabled = isFormValid;
            this.buttonCreate.setEnabled(isFormValid);
        }

    }
}
/*APIThinkit api = new APIThinkit();
        //List of articles
        JSONArray response = api.readArticles(token);
        Log.v("Articles", String.valueOf(response));
        */

       /* View v = inflater.inflate(R.layout.fragment_add,container,false);
        this.layoutAdd = v.findViewById(R.id.layout_add);

        TextViewTitle = new TextInputLayout(layoutAdd.getContext());
        TextViewTitleInput = new TextInputLayout(layoutAdd.getContext());
        TextViewContent = new TextInputLayout(layoutAdd.getContext());
        TextViewContentInput = new TextInputLayout(layoutAdd.getContext());

        TextViewTitleInput.getEditText().setText(token);
        TextViewContentInput.getEditText().setText("test");
        TextViewTitle.addView(TextViewTitleInput);
        TextViewContent.addView(TextViewContentInput);

        layoutAdd.removeAllViewsInLayout();
        layoutAdd.addView(TextViewTitle);
        layoutAdd.addView(TextViewContent);
        //this.TextViewTitleInput = v.findViewById(R.id.container_article);
        //this.TextViewContentInput = v.findViewById(R.id.container_article);
        //token = getIntent().getStringExtra(article.optString("title"));


       /*

        id = getIntent().getStringExtra("id");
        title = getIntent().getStringExtra("title");
        content = getIntent().getStringExtra("content");
        isFavorite = getIntent().getStringExtra("isFavorite");
        completed = getIntent().getStringExtra("completed");
        owner = getIntent().getStringExtra("owner");
        createdAt = getIntent().getStringExtra("createdAt");
        updatedAt = getIntent().getStringExtra("updatedAt");
        version = getIntent().getStringExtra("version");
        */
