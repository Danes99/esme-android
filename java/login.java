
import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import org.json.JSONObject;

public class login {

    private String response;

    public login(String email, String password) {

        // Create a neat value object to hold the URL
        URL url = null;
        try {
            url = new URL("https://node-thinkit.herokuapp.com/users");

            // Open a connection(?) on the URL(??) and cast the response(???)
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            // Now it's "open", we can set the request method, headers etc.
            connection.setRequestProperty("Content-Type", "application/json; utf-8");
            connection.setRequestProperty("accept", "application/json");
            connection.setRequestMethod("POST");
            connection.setDoOutput(true);

            // Create the Request Body
            JSONObject body = new JSONObject();
            body.put("email", email);
            body.put("password", password);
            String jsonInputString = body.toString();
            System.out.println(jsonInputString);

            // try(OutputStream os = connection.getOutputStream()) {
            //     byte[] input = jsonInputString.getBytes("utf-8");
            //     os.write(input, 0, input.length);			
            // }

            // This line makes the request
            InputStream responseStream = connection.getInputStream();
            InputStream in = new BufferedInputStream(responseStream);

            System.out.println(String.valueOf(responseStream));
            System.out.println(String.valueOf(in));

            response = String.valueOf(responseStream);

            connection.disconnect();

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getResponse() {
        return response;
    }

}
