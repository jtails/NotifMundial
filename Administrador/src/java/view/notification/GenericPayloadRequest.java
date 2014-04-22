package view.notification;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javapns.notification.PushNotificationPayload;
import org.json.JSONException;

public class GenericPayloadRequest {
    public Map<String,String> data;
    
    public GenericPayloadRequest(){
        data=new HashMap<String,String>();
    }
    
    public void put(String key,String value){
        data.put(key,value);
    }
    
    public PushNotificationPayload getIOSPayloadRequest(){
        PushNotificationPayload iOSpayloadRequest = PushNotificationPayload.complex();
        Iterator<String> iterator=data.keySet().iterator();
        try {
            iOSpayloadRequest.addAlert("Gol");
            iOSpayloadRequest.addSound("default");
            while(iterator.hasNext()){
                String key=iterator.next();
                iOSpayloadRequest.addCustomDictionary(key,data.get(key));
            }
        }catch (JSONException e) {
            e.printStackTrace();
        }
        return iOSpayloadRequest;
    }
    
    public AndroidPayloadRequest getAndroidPayloadRequest(){
        AndroidPayloadRequest androidPayloadRequest=new AndroidPayloadRequest();
        Iterator<String> iterator=data.keySet().iterator();
        while(iterator.hasNext()){
            String key=iterator.next();
            androidPayloadRequest.put(key,data.get(key));
        }
        return androidPayloadRequest;
    }
}
