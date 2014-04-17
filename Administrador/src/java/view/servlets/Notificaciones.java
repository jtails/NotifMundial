package view.servlets;

import java.util.List;
import view.beans.NotificacionesBean;
import view.dao.NotificacionesDAO;
import view.notification.android.GCMRequest;
import view.notification.android.NotificationServiceImpl;


public class Notificaciones {

    public static void sendNotificaciones(GCMRequest gcmrequest){
        NotificacionesDAO notificacionesDAO=new NotificacionesDAO();
        List<NotificacionesBean> DispNotificaciones=notificacionesDAO.getDispNotificaciones();
        for(NotificacionesBean dispNotif:DispNotificaciones){
            gcmrequest.setGcmId(dispNotif.getIdnotif());
            System.out.println(gcmrequest.toString());
            System.out.println("NOTIFICATION :" + new NotificationServiceImpl().sendNotification(gcmrequest).getErrorMessage());
        }
    }
}
