package view.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import view.beans.DetailsPartidoBean;
import view.beans.EmpresasBean;
import view.beans.EquiposBean;
import view.beans.NotificacionesBean;
import view.beans.PartidosBean;
import view.notification.Dispositivos;


public class NotificacionesDAO {
    
    public List<NotificacionesBean> getDispNotificaciones(){
        List<NotificacionesBean> listN=new ArrayList<NotificacionesBean>();
            List<Map> result = ConnectionManager.executeQuery("select * from notificaciones");
            if(result!=null){
                for(Map map:result){
                    NotificacionesBean nBean=new NotificacionesBean();
                    nBean.setIdnotificacion((String)map.get("id_notificacion"));
                    nBean.setIdnotif((String)map.get("idnotif"));
                    Enum dispositivo=Dispositivos.getDispositivo((String)map.get("tipodisp"));
                    nBean.setDispositivo(dispositivo);
                    listN.add(nBean);
                }
            }
        return listN;
    }
    
    public NotificacionesBean getDispNotificacionesById(NotificacionesBean notificacion){
        NotificacionesBean nBean=new NotificacionesBean();
            List<Map> result = ConnectionManager.executeQuery("select * from notificaciones where idnotif='"+notificacion.getIdnotif()+"'");
            if(result!=null){
                for(Map map:result){
                    nBean.setIdnotificacion((String)map.get("id_notificacion"));
                    nBean.setIdnotif((String)map.get("idnotif"));
                    Enum dispositivo=Dispositivos.getDispositivo((String)map.get("tipodisp"));
                    nBean.setDispositivo(dispositivo);
                    nBean.setId_empresa((String)map.get("id_empresa"));
                }
            }
        return nBean;
    }
    
    
    public boolean addDispNotificacion(NotificacionesBean notificacion){
        //Validamos que el dispositivo no este Registrado para Recibir Notificaciones
        NotificacionesBean nBean=getDispNotificacionesById(notificacion);
        if(nBean.getIdnotificacion()==null){
            boolean status=ConnectionManager.execute("insert into notificaciones(id_empresa,idnotif,tipodisp) values("+notificacion.getId_empresa()+",'"+notificacion.getIdnotif()+"','"+notificacion.getDispositivo()+"')");
            return status;
        }
        return true;
    }
        
}
