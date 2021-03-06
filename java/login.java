
import java.io.BufferedInputStream;
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

import org.json.JSONException;
import org.json.JSONObject;

public class login {

    private String response;
    private String email;
    private String password;

    public login(String email, String password) {
        this.makeRequest();
    }

    private void makeRequest() {
        try {
            // Create a neat value object to hold the URL
            URL url = new URL("https://node-thinkit.herokuapp.com/users");

            // Open a connection(?) on the URL(??) and cast the response(???)
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            // Now it's "open", we can set the request method, headers etc.
            connection.setRequestProperty("Content-Type", "application/json; utf-8");
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
            // Log.v("Body", jsonInputString);

            //  We'd need to write it
            //OutputStreamWriter out = new OutputStreamWriter(connection.getOutputStream());
            //out.write(jsonInputString);// here i sent the parameter
            //out.close();

            //Write
            OutputStream os = connection.getOutputStream();
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(os, "UTF-8"));
            writer.write(jsonInputString);
            writer.close();
            os.close();

            // This line makes the request
            InputStream responseStream = connection.getInputStream();
            InputStream in = new BufferedInputStream(responseStream);
            this.response = readStream(in);
            // Log.v("Response", this.response);

            connection.disconnect();

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public String getResponse() {
        return response;
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

}
