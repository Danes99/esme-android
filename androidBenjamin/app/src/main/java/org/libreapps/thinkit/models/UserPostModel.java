package org.libreapps.thinkit.models;

public class UserPostModel {

    private int id;
    private int completed;
    private String postId;
    private String title;
    private String content;
    private String owner;
    private String createdAt;
    private String updatedAt;
    private String v;
    private int isFavorite;

    public UserPostModel(int id, int completed, String postId, String title, String content, String owner, String createdAt, String updatedAt, String v, int isFavorite) {
        this.id = id;
        this.completed = completed;
        this.postId = postId;
        this.title = title;
        this.content = content;
        this.owner = owner;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.v = v;
        this.isFavorite = isFavorite;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCompleted() {
        return completed;
    }

    public void setCompleted(int completed) {
        this.completed = completed;
    }

    public String getPostId() {
        return postId;
    }

    public void setPostId(String postId) {
        this.postId = postId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getV() {
        return v;
    }

    public void setV(String v) {
        this.v = v;
    }

    public int getIsFavorite() {
        return isFavorite;
    }

    public void setIsFavorite(int isFavorite) {
        this.isFavorite = isFavorite;
    }
}
