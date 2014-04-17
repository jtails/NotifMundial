package view.webservices.impl;

import java.util.ArrayList;
import java.util.List;
import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import view.beans.EmpresasBean;
import view.beans.EquiposBean;
import view.beans.NotificacionesBean;
import view.beans.PartidosBean;
import view.beans.WrapperBean;
import view.dao.ConnectionManager;
import view.dao.EmpresasDAO;
import view.dao.EquiposDAO;
import view.dao.NotificacionesDAO;
import view.dao.PartidosDAO;
import view.webservices.EmpresasWS;

@Path("/EmpresasWS")
public class EmpresasWSImpl{
    
    @GET
    @Path("/json/getDetails/{codigo}/{notifid}/{tipod}")
    @Produces("application/json")
    public EmpresasBean getDeatilsEmpresa(@PathParam("codigo")String codigo,@PathParam("notifid")String notifid,@PathParam("tipod")String tipod){
        EmpresasDAO empresasDAO=new EmpresasDAO();
        EmpresasBean empresa=new EmpresasBean();
        empresa.setCodigo(codigo);
        empresa=empresasDAO.searchEmpresa(empresa);
        if(empresa.getId_empresa()!=null){
            empresa.setStatus("0");//Exitoso
            
            //Agregamos el Dispositivo para Notificaciones
            NotificacionesBean notificacion=new NotificacionesBean();
            notificacion.setId_empresa(empresa.getId_empresa());
            notificacion.setIdnotif(notifid);//Identificador para la Notificacion
            notificacion.setTipodisp(tipod);
            addNotificacion(notificacion);
            boolean status=validaLicencias(empresa);
            if(status){
                PartidosDAO partidosDAO=new PartidosDAO();
                ArrayList<PartidosBean> partidos=partidosDAO.getTodosPartidos();
                empresa.setPartidos(partidos);
            }else{
                //Si se excede el numero de licencias se regresa Status Fallido
                empresa=new EmpresasBean();
                empresa.setStatus("1");
                return empresa;
            }
        }
        else
            empresa.setStatus("1");//Fallido
        return empresa;
    }
    
    //Agregamos el Dispositivo para Notificaciones
    public boolean addNotificacion(NotificacionesBean notificacion){
        //Inicamos una Transaccion
        ConnectionManager.startTransaction();
        NotificacionesDAO notificacionesDAO=new NotificacionesDAO();
        boolean status=notificacionesDAO.addDispNotificacion(notificacion);
        //Confirmamos la Transaccion
        if(status){
            ConnectionManager.commit();
            return true;
        }
        else{
            ConnectionManager.rollback();
            return false;
        }
    }
    
    
    //Validamos que existan Licencias disponibles
    public boolean validaLicencias(EmpresasBean empresa){
        EmpresasDAO empresasDAO=new EmpresasDAO();
        boolean status=empresasDAO.validaLicencias(empresa);
        return status;
    }
}
