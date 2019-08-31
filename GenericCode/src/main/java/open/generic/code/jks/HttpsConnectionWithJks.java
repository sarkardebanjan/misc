package open.generic.code.jks;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class HttpsConnectionWithJks {

    private static final Logger LOGGER = LoggerFactory.getLogger(HttpsConnectionWithJks.class);

    public static void main(String[] args) {
        new HttpsConnectionWithJks().doWork();
    }

    private void doWork() {
        String getUrl = "https://github.com/sarkardebanjan/misc";
        String responseStr = doGet(getUrl);
        System.out.println(responseStr);
    }

    private String doGet(String url) {
        HttpURLConnection httpURLConnection = null;
        try {
            URL myurl = new URL(url);
            httpURLConnection = (HttpURLConnection) myurl.openConnection();
            httpURLConnection.setRequestMethod("GET");
            StringBuilder content;
            try (BufferedReader in = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream()))) {
                String line;
                content = new StringBuilder();
                while ((line = in.readLine()) != null) {
                    content.append(line);
                    content.append(System.lineSeparator());
                }
            }
            return content.toString();

        } catch (IOException exp) {
            exp.printStackTrace();
        } finally {
            if (null != httpURLConnection)
                httpURLConnection.disconnect();
        }
        return null;
    }

}
