package com.example.thinkit.obj;

public class User {

    private final String id;
    private final String name;
    private final String email;
    private final String token;
    private final String createdAt;
    private final String updatedAt;

    private final int age;

    public User(String id, String name, String email, String token, String createdAt, String updatedAt, int age) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.token = token;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.age = age;
    }
}
