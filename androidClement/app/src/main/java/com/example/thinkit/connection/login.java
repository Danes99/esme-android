package com.example.thinkit.connection;

import android.os.AsyncTask;
import android.os.Build;
import android.util.Log;

import androidx.annotation.RequiresApi;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import org.json.JSONException;
import org.json.JSONObject;

public class login extends AsyncTask {

    private String email;
    private String password;
    private String response = "";

    public login (String email, String password) {
        this.email = email;
        this.password = password;
    }

    @Override
    protected Object doInBackground(Object[] Objects) {

        try {
            // Create a neat value object to hold the URL
            URL url = new URL("http://node-thinkit.herokuapp.com/users/login");

            // Open a connection(?) on the URL(??) and cast the response(???)
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            // Now it's "open", we can set the request method, headers etc.
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setRequestProperty("accept", "application/json");
            // connection.setRequestMethod("POST");
            connection.setDoInput(true);
            connection.setDoOutput(true);

            // Create the Request Body
            JSONObject body = new JSONObject();
            body.put("email", this.email);
            body.put("password", this.password);

            // Creating a custom JSON String
            String jsonInputString = body.toString();

            //  We'd need to write it
            //OutputStreamWriter out = new OutputStreamWriter(connection.getOutputStream());
            //out.write(jsonInputString);// here i sent the parameter
            //out.close();

            // Write request body
            OutputStream os = connection.getOutputStream();
            writeStream(os, jsonInputString);

            // This line makes the request
            InputStream responseStream = connection.getInputStream();
            //InputStream in = new BufferedInputStream(responseStream);

            this.response = readStream(responseStream);
            Log.v("response", response);

            connection.disconnect();

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return null;
    }

    private void writeStream(OutputStream os, String jsonInputString) throws IOException {
        // Write request body
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(os, "UTF-8"));
        writer.write(jsonInputString);
        writer.close();
        os.close();
    }

    private String readStream(InputStream is) throws IOException {
        StringBuilder sb = new StringBuilder();
        BufferedReader r = new BufferedReader(new InputStreamReader(is),1000);
        for (String line = r.readLine(); line != null; line =r.readLine()){
            sb.append(line);
        }
        is.close();
        return sb.toString();
    }

    public String getResponse() {
        return response;
    }
}