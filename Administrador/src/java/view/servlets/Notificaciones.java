package view.servlets;

import java.util.ArrayList;
import java.util.List;
import javapns.Push;
import javapns.communication.exceptions.CommunicationException;
import javapns.communication.exceptions.KeystoreException;
import javapns.devices.Device;
import javapns.notification.PushNotificationManager;
import org.apache.log4j.*;
import view.beans.ConfiguracionesBean;
import view.beans.NotificacionesBean;
import view.dao.ConfiguracionesDAO;
import view.dao.NotificacionesDAO;
import view.notification.android.Dispositivos;
import view.notification.android.GCMRequest;
import view.notification.android.NotificationServiceImpl;


public class Notificaciones {

    private static Logger log = Logger.getLogger(Notificaciones.class); 
    public static void sendNotificaciones(GCMRequest gcmrequest){
        NotificacionesDAO notificacionesDAO=new NotificacionesDAO();
        List<NotificacionesBean> DispNotificaciones=notificacionesDAO.getDispNotificaciones();
        List<String> IOSids=new ArrayList<String>();
        for(NotificacionesBean dispNotif:DispNotificaciones){
            if(dispNotif.getDispositivo()==Dispositivos.ANDROID){
                gcmrequest.setGcmId(dispNotif.getIdnotif());
                System.out.println(gcmrequest.toString());
                System.out.println("NOTIFICATION :" + new NotificationServiceImpl().sendNotification(gcmrequest).getErrorMessage());
            }
            if(dispNotif.getDispositivo()==Dispositivos.IOS){
                IOSids.add(dispNotif.getIdnotif());
            }
        }
        
        //Enviamos el Bloque de Notificaciones iOS
        ConfiguracionesDAO configuracionesDAO=new ConfiguracionesDAO();
        ConfiguracionesBean cBean=configuracionesDAO.getConfiguraciones();
        try {
            //Push.test(cBean.getCertificadoiosDecode(),"mundialapp",false,"7cfc9bf541173f6c4f6c1e77cc541774f2de812cef90705be93f74d0e96b2249");
            //Para Versión FULL, el Numero de Threads sera Parametrizable
            Push.payload(gcmrequest,cBean.getCertificadoiosDecode(),"mundialapp",false,15,IOSids);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
