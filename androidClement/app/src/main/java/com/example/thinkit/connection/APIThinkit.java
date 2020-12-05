package com.example.thinkit.connection;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.concurrent.ExecutionException;

public class APIThinkit {


    public JSONObject login(String email, String password) {

        // Request parameters
        String url = "http://node-thinkit.herokuapp.com/users/login";
        String method = "POST";
        String response;

        // Create the Request Body
        JSONObject body = new JSONObject();
        try {
            body.put("email", email);
            body.put("password", password);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        connectionREST connection = new connectionREST(url, method, body);
        connection.execute((Object) null);

        JSONObject json = null;
        try {

            // Synchronus Method
            // Wait for the response
            response = (String) connection.get();
            json = new JSONObject(response);

        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return json;
    }

    public JSONObject signup(String name, String email, String password) {

        // Request parameters
        String url = "http://node-thinkit.herokuapp.com/users";
        String method = "POST";
        String response;

        // Create the Request Body
        JSONObject body = new JSONObject();
        try {
            body.put("name", name);
            body.put("email", email);
            body.put("password", password);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        connectionREST connection = new connectionREST(url, method, body);
        connection.execute((Object) null);

        JSONObject json = null;
        try {

            // Synchronus Method
            // Wait for the response
            response = (String) connection.get();
            json = new JSONObject(response);

        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return json;
    }

    public JSONArray readArticles(String token) {

        // Request parameters
        String url = "http://node-thinkit.herokuapp.com/article";
        String method = "GET";

        connectionREST connection = new connectionREST(url, method, null);
        connection.setToken(token);
        connection.execute((Object) null);

        JSONArray json = null;
        try {

            // Synchronus Method
            // Wait for the response
            String response = (String) connection.get();
            json = new JSONArray(response);

        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return json;
    }

    public JSONObject readArticle(String id, String token) {

        // Request parameters
        String url = "http://node-thinkit.herokuapp.com/article/" + id;
        String method = "GET";
        String response;

        connectionREST connection = new connectionREST(url, method, null);
        connection.setToken(token);
        connection.execute((Object) null);

        JSONObject json = null;
        try {

            // Synchronus Method
            // Wait for the response
            response = (String) connection.get();
            json = new JSONObject(response);

        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return json;
    }
}
