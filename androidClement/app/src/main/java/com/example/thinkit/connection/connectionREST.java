package com.example.thinkit.connection;

import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

public class connectionREST extends AsyncTask {

    private URL mURL;
    private String mMethod;
    private String token;
    private String response;
    private JSONObject mBody;
    private Boolean hasAuthorizationBeenSet = false;

    public connectionREST(String url, String method, JSONObject body) {

        try {
            // Create a neat value object to hold the URL
            this.mURL = new URL(url);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        // HTTP method (GET, POST, PATCH, DELETE)
        this.mMethod = method;

        // HTTP Request body (JSON)
        this.mBody = body;

    }

    @Override
    protected Object doInBackground(Object[] objects) {

        // Open a connection(?) on the URL(??) and cast the response(???)
        HttpURLConnection connection;
        String response = null;

        try {
            switch (this.mMethod) {
                case "GET":
                    connection = get(this.mURL);
                    break;
                case "POST":
                    connection = post(this.mURL, this.mBody);
                    break;
                case "PATCH":
                    connection = patch(this.mURL, this.mBody);
                    break;
                case "DELETE":
                    connection = delete(this.mURL);
                    break;
                default:
                    throw new IllegalStateException("Unexpected value: " + this.mMethod);
            }

            // This line makes the request
            InputStream responseStream = connection.getInputStream();

            // Read the response form HTTP request
            response = readStream(responseStream);
            //Log.v("Response", response);

            // Close HTTP Connection
            connection.disconnect();
            connection = null;

        } catch (IOException e) {
            e.printStackTrace();
        }

        this.response = response;
        return response;
    }

    private HttpURLConnection get(URL url) {

        Log.v("GET @", url.toString());

        HttpURLConnection connection = null;

        // Open a connection(?) on the URL(??) and cast the response(???)
        try {
            connection = (HttpURLConnection) url.openConnection();

            // JSON Web Token (JWT)
            // For authentication
            if (token != null && !hasAuthorizationBeenSet) {
                connection.setRequestProperty("Authorization", "Bearer " + URLEncoder.encode(token, "utf-8"));
                hasAuthorizationBeenSet = true;
            }

            // Now it's "open", we can set the request method, headers etc.
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setRequestProperty("accept", "application/json");
            connection.setDoInput(true);

        } catch (IOException e) {
            e.printStackTrace();
        }

        return connection;
    }

    private HttpURLConnection post(URL url, JSONObject json) {

        Log.v("POST @", url.toString());

        // Creating a custom JSON String
        String jsonInputString = json.toString();

        HttpURLConnection connection = null;

        // Open a connection(?) on the URL(??) and cast the response(???)
        try {
            connection = (HttpURLConnection) url.openConnection();

            // JSON Web Token (JWT)
            // For authentication
            if (token != null && !hasAuthorizationBeenSet) {
                connection.setRequestProperty("Authorization", "Bearer " + URLEncoder.encode(token, "utf-8"));
                hasAuthorizationBeenSet = true;
            }

            // Now it's "open", we can set the request method, headers etc.
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setRequestProperty("accept", "application/json");
            connection.setDoInput(true);
            connection.setDoOutput(true);

            // Write request body
            OutputStream os = connection.getOutputStream();
            writeStream(os, jsonInputString);

        } catch (IOException e) {
            e.printStackTrace();
        }

        return connection;
    }

    private HttpURLConnection patch(URL url, JSONObject json) {

        Log.v("PATCH @", url.toString());

        // Creating a custom JSON String
        String jsonInputString = json.toString();

        HttpURLConnection connection = null;

        // Open a connection(?) on the URL(??) and cast the response(???)
        try {
            connection = (HttpURLConnection) url.openConnection();

            // JSON Web Token (JWT)
            // For authentication
            if (token != null && !hasAuthorizationBeenSet) {
                connection.setRequestProperty("Authorization", "Bearer " + URLEncoder.encode(token, "utf-8"));
                hasAuthorizationBeenSet = true;
            }

            // Now it's "open", we can set the request method, headers etc.
            connection.setRequestMethod("PATCH");
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setRequestProperty("accept", "application/json");
            connection.setDoInput(true);
            connection.setDoOutput(true);

            // Write request body
            OutputStream os = connection.getOutputStream();
            writeStream(os, jsonInputString);

        } catch (IOException e) {
            e.printStackTrace();
        }

        return connection;
    }

    private HttpURLConnection delete(URL url) {

        Log.v("DELETE @", url.toString());

        HttpURLConnection connection = null;

        // Open a connection(?) on the URL(??) and cast the response(???)
        try {
            connection = (HttpURLConnection) url.openConnection();

            // JSON Web Token (JWT)
            // For authentication
            if (token != null && !hasAuthorizationBeenSet) {
                connection.setRequestProperty("Authorization", "Bearer " + URLEncoder.encode(token, "utf-8"));
                hasAuthorizationBeenSet = true;
            }

            // Now it's "open", we can set the request method, headers etc.
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setRequestProperty("accept", "application/json");
            connection.setRequestMethod("DELETE");
            connection.setDoInput(true);

        } catch (IOException e) {
            e.printStackTrace();
        }

        return connection;
    }

    // Write the body of the HTTP request
    private void writeStream(OutputStream os, String jsonInputString) throws IOException {
        // Write request body
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(os, "UTF-8"));
        writer.write(jsonInputString);
        writer.close();
        os.close();
    }

    // Read the HTTP request response
    private String readStream(InputStream is) throws IOException {
        // https://stackoverflow.com/questions/8376072/whats-the-readstream-method-i-just-can-not-find-it-anywhere/17167640#17167640
        StringBuilder sb = new StringBuilder();
        BufferedReader r = new BufferedReader(new InputStreamReader(is),1000);
        for (String line = r.readLine(); line != null; line =r.readLine()){
            sb.append(line);
        }
        is.close();
        return sb.toString();
    }

    public String getResponse() { return this.response; }

    public void setToken(String token) { this.token = token; }
}
