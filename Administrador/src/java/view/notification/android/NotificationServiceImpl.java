package view.notification.android;

import com.google.gson.Gson;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.URL;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLSession;

public class NotificationServiceImpl{

    private static final String GCM_URL =
        "https://android.googleapis.com/gcm/send";
    private static final String REQUEST_METHOD = "POST";
    private static final String CONTENT_TYPE = "application/json";
    private static final String GOOGLE_API_KEY =
        "key=AIzaSyAmaHZc1FgwGGZkQe1t9zHj3rE3eoqHse0";

    public GCMResponse sendNotification(GCMRequest request) {
        GCMResponse response = new GCMResponse();
        javax.net.ssl.HttpsURLConnection connection=null;
        try {
            URL url = new URL(GCM_URL);
            connection=(javax.net.ssl.HttpsURLConnection) url.openConnection();
            connection.setHostnameVerifier(new HostnameVerifier(){
                    public boolean verify(String string,
                                          SSLSession sslSession) {
                        return true;
                    }
                });
            
            connection.setDoOutput(true);
            connection.setRequestMethod(REQUEST_METHOD);
            connection.setRequestProperty("Content-Type", CONTENT_TYPE);
            connection.setRequestProperty("Authorization", GOOGLE_API_KEY);

            String input = request.toString();
            OutputStream os = connection.getOutputStream();
            os.write(input.getBytes());
            os.flush();
            
            if (connection.getResponseCode() != HttpsURLConnection.HTTP_OK) {
                response.setSuccess("0");
                response.setErrorMessage("Bad request "+connection.getResponseCode());
                return response;
            }
            
            BufferedReader br =
                new BufferedReader(new InputStreamReader((connection.getInputStream())));
            String output;
            String msg = "";
            while ((output = br.readLine()) != null) {
                msg += output;
            }
            Gson gson = new Gson();
            response = gson.fromJson(msg, GCMResponse.class);
            if("1".equals(response.getSuccess())){
                response.setErrorMessage("mensaje enviado");
                System.out.println("mensaje enviado");
            }else{
                response.setErrorMessage((String)response.getErrorMessage());
                System.out.println(response.getErrorMessage());
            }
        } catch (Exception e) {
            response.setSuccess("0");
            response.setErrorMessage(e.getMessage());
        }finally{
            connection.disconnect();
        } 
        return response;
    }

}
