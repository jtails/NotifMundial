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


public class NotificacionesDAO {
    
    public List<NotificacionesBean> getDispNotificaciones(){
        List<NotificacionesBean> listN=new ArrayList<NotificacionesBean>();
            List<Map> result = ConnectionManager.executeQuery("select * from notificaciones");
            if(result!=null){
                for(Map map:result){
                    NotificacionesBean nBean=new NotificacionesBean();
                    nBean.setIdnotificacion((String)map.get("id_notificacion"));
                    nBean.setIdnotif((String)map.get("idnotif"));
                    nBean.setTipodisp((String)map.get("tipodisp"));
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
                    nBean.setTipodisp((String)map.get("tipodisp"));
                    nBean.setId_empresa((String)map.get("id_empresa"));
                }
            }
        return nBean;
    }
    
    
    /*public EmpresasBean searchEmpresa(String codigo){
            List<Map> result = ConnectionManager.executeQuery("select * from empresas where codigo='"+codigo+"'");
            EmpresasBean eBean=new EmpresasBean();
            if(result!=null){
                for(Map map:result){
                    eBean.setId_empresa((String)map.get("id_empresa"));
                    eBean.setNombre((String)map.get("nombre"));
                    eBean.setCodigo((String)map.get("codigo"));
                    Map<String,String> imagenes=new HashMap<String,String>();
                    for(int i=1;i<7;i++)
                        imagenes.put("imagen"+i,(String)map.get("imagen"+i));
                    eBean.setImagenes(imagenes);
                }
            }
        return eBean;
    }*/
    
    
    public boolean addDispNotificacion(NotificacionesBean notificacion){
        //Validamos que el dispositivo no este Registrado para Recibir Notificaciones
        NotificacionesBean nBean=getDispNotificacionesById(notificacion);
        if(nBean.getIdnotificacion()==null){
            boolean status=ConnectionManager.execute("insert into notificaciones(id_empresa,idnotif,tipodisp) values("+notificacion.getId_empresa()+",'"+notificacion.getIdnotif()+"','"+notificacion.getTipodisp()+"')");
            return status;
        }
        return true;
    }
        
    /*public boolean deleteEmpresa(EmpresasBean empresa){
        boolean status=ConnectionManager.execute("delete from empresas where id_empresa="+empresa.getId_empresa()+"");
        return status;
    }*/
}
