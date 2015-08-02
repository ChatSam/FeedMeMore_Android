package samaraca.feedmemore.dao;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.protocol.HttpRequestHandler;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.ProtocolException;
import java.net.URL;

/**
 * Created by ChatSam on 8/1/15.
 * Creates the HTTP connections and gets raw data from the webservice
 */
public class NetworkDAO {

    /**
     * Invokes a URI and obtains a string result from the webservice
     * @param requestUri - the URI
     * @return - string of raw data form the web service
     * @throws IOException
     */
    public String Request(String requestUri) throws ClientProtocolException, IOException {

        String response = null;
        // the request which is going to be made
        HttpGet httpGet = new HttpGet(requestUri);

        // the object with gets the raw content from the request
        ResponseHandler<String> responseHandler = new BasicResponseHandler();

        // connects the request and the response
        HttpClient httpClient = new DefaultHttpClient();

        // executes the request and returns the response
        try {
             response= httpClient.execute(httpGet, responseHandler);

        }catch (Exception ex){
            ex.getMessage();
        }

        return response;
    }

}
