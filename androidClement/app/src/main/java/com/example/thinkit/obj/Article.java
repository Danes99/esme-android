package com.example.thinkit.obj;

import org.json.JSONObject;

public class Article {

    private final String mId;
    private String mTitle;
    private String mContent;
    private final String mCreatedAt;
    private String mUpdatedAt;

    // Constructor
    public Article(JSONObject json) {
        this.mId = json.optString("id");
        this.mTitle = json.optString("title");
        this.mContent = json.optString("content");
        this.mCreatedAt = json.optString("createdAt");
        this.mUpdatedAt = json.optString("UpdatedAt");
    }

    // Getter
    public String getId() { return mId; }
    public String getTitle() { return mTitle; }
    public String getContent() { return mContent; }
    public String getCreatedAt() { return mCreatedAt; }
    public String getUpdatedAt() { return mUpdatedAt; }

}
