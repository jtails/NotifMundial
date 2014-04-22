package view.servlets;

import java.util.ArrayList;
import java.util.List;
import javapns.Push;
import javapns.communication.exceptions.CommunicationException;
import javapns.communication.exceptions.KeystoreException;
import javapns.devices.Device;
import javapns.notification.PushNotificationManager;
import javapns.notification.PushNotificationPayload;
import javapns.notification.PushedNotifications;
import org.apache.log4j.*;
import org.json.JSONException;
import view.beans.ConfiguracionesBean;
import view.beans.NotificacionesBean;
import view.dao.ConfiguracionesDAO;
import view.dao.NotificacionesDAO;
import view.notification.AndroidNotification;
import view.notification.AndroidPayloadRequest;
import view.notification.Dispositivos;
import view.notification.GenericPayloadRequest;
import view.notification.PayloadResponse;


public class Notificaciones {

    private static Logger log = Logger.getLogger(Notificaciones.class); 
    
    public static void sendNotificaciones(GenericPayloadRequest payloadRequest){
        PushNotificationPayload iOSpayloadRequest=payloadRequest.getIOSPayloadRequest();
        AndroidPayloadRequest androidPayloadRequest=payloadRequest.getAndroidPayloadRequest();
        
        NotificacionesDAO notificacionesDAO=new NotificacionesDAO();
        List<NotificacionesBean> DispNotificaciones=notificacionesDAO.getDispNotificaciones();
        List<String> IOSids=new ArrayList<String>();
        
        //Generamos el Bloque de Notificaciones Android
        for(NotificacionesBean notificacion:DispNotificaciones){
            if(notificacion.getDispositivo()==Dispositivos.ANDROID){
                androidPayloadRequest.addGcmId(notificacion.getIdnotif());
            }
            //Generamos el Bloque de Notificaciones iOS
            if(notificacion.getDispositivo()==Dispositivos.IOS){
                IOSids.add(notificacion.getIdnotif());
            }
        }
        
        //Enviamos el Bloque de Notificaciones Android 
        PayloadResponse payloadResponse=new AndroidNotification().sendNotification(androidPayloadRequest);
        System.out.println("Notificaciones Android OK :"+payloadResponse.getSuccess());
        System.out.println("Notificaciones Android KO :"+payloadResponse.getFailure());
        System.out.println(androidPayloadRequest.toString());
        
        //Enviamos el Bloque de Notificaciones iOS
        ConfiguracionesDAO configuracionesDAO=new ConfiguracionesDAO();
        ConfiguracionesBean cBean=configuracionesDAO.getConfiguraciones();
        try {
            //Push.test(cBean.getCertificadoiosDecode(),"mundialapp",false,"7cfc9bf541173f6c4f6c1e77cc541774f2de812cef90705be93f74d0e96b2249");
            //Para Versión FULL, el Numero de Threads sera Parametrizable
            PushedNotifications pnotifications=Push.payload(iOSpayloadRequest,cBean.getCertificadoiosDecode(),"mundialapp",false,15,IOSids);
            System.out.println("Notificaciones iOS OK :"+pnotifications.getSuccessfulNotifications().size());
            System.out.println("Notificaciones iOS KO :"+pnotifications.getFailedNotifications().size());
            System.out.println(iOSpayloadRequest.getPayload().toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
}
