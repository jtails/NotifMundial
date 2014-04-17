package view.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.*;
import javax.servlet.http.*;

import view.beans.DetailsPartidoBean;
import view.beans.EquiposBean;
import view.beans.PartidosBean;
import view.dao.ConnectionManager;
import view.dao.EquiposDAO;
import view.dao.PartidosDAO;
import view.notification.android.GCMRequest;
import view.notification.android.NotificationServiceImpl;

public class Partidos extends HttpServlet {
    private static final String CONTENT_TYPE = "text/html; charset=windows-1252";

    public void init(ServletConfig config) throws ServletException {
        super.init(config);
    }

    public void doPost(HttpServletRequest request,
                       HttpServletResponse response) throws ServletException,
                                                            IOException {
        String fechaHora=request.getParameter("FechaHora");
        String place=request.getParameter("Place");
        String idlocalteam=request.getParameter("Idlocalteam");
        String idvisitteam=request.getParameter("Idvisitteam");
        String ronda=request.getParameter("Ronda");
        String id=request.getParameter("id");
        
        PartidosBean partidosBean=new PartidosBean();
        partidosBean.setIdpartido(id);
        partidosBean.setDatetime(fechaHora);
        partidosBean.setPlace(place);
        
        EquiposBean localteam=new EquiposBean();
        localteam.setIdequipo(idlocalteam);
        
        EquiposBean visitteam=new EquiposBean();
        visitteam.setIdequipo(idvisitteam);
        
        partidosBean.setIdlocalteam(localteam);
        partidosBean.setIdvisitteam(visitteam);
        partidosBean.setRonda(ronda);
        
        String opcion=request.getParameter("opcion");
        
        /*if(opcion.equalsIgnoreCase("Agregar")){
            boolean status=AddPartido(partidosBean);
            if(status)
                request.setAttribute("Status",true);
            else
                request.setAttribute("Status",false);
        }*/
        
        /*if(opcion.equalsIgnoreCase("Eliminar")){
            boolean status=DeletePartido(partidosBean);
            if(status)
                request.setAttribute("Status",true);
            else
                request.setAttribute("Status",false);
        }*/
        
        if(opcion.equalsIgnoreCase("Editar")){
            boolean status=EditPartido(partidosBean);
            if(status)
                request.setAttribute("Status",true);
            else
                request.setAttribute("Status",false);
        }
        
        
        //Mostramos los Equipos de los 8 Grupos
        EquiposDAO equiposDAO=new EquiposDAO();
        List<EquiposBean> equipos = equiposDAO.getTodosEquipos();
        request.setAttribute("Equipos",equipos);
        
        //Consultamos los Partidos Existentes
        PartidosDAO partidosDAO=new PartidosDAO();
        List<PartidosBean> partidos=partidosDAO.getTodosPartidos();
        request.setAttribute("Partidos",partidos);
        
        request.getRequestDispatcher("/WEB-INF/Partidos.jsp").forward(request,response);
    }
    
    //Agregamos un Nuevo Partido
    /*public boolean AddPartido(PartidosBean partido){
        //Inicamos una Transaccion
        ConnectionManager.startTransaction();
        PartidosDAO partidosDAO=new PartidosDAO();
        DetailsPartidoBean details= new DetailsPartidoBean();
        details.setStatus("1");
        partido.setDetails(details);
        boolean status=partidosDAO.addPartido(partido);
        
        //Confirmamos la Transaccion
        if(status){
            ConnectionManager.commit();
            //Enviamos la Notificacion
            GCMRequest requestGCM=new GCMRequest();
            String id_partido=partidosDAO.getIdPartido(partido);
            requestGCM.setIdPartido(id_partido);
            requestGCM.setAction("P");
            requestGCM.setEquipoLocal(partido.getIdlocalteam().getIdequipo());
            requestGCM.setEquipoVisitante(partido.getIdvisitteam().getIdequipo());
            requestGCM.setEstatusPartido(partido.getDetails().getStatus());
            requestGCM.setFechaHora(partido.getDatetime());
            requestGCM.setLugar(partido.getPlace());
            requestGCM.setRonda(partido.getRonda());
            Notificaciones.sendNotificaciones(requestGCM);
            return true;
        }
        else{
            ConnectionManager.rollback();
            return false;
        }
    }*/
    
    
    //Eliminamos el Partido Existente
    /*public boolean DeletePartido(PartidosBean partido){
        //Inicamos una Transaccion
        ConnectionManager.startTransaction();
        PartidosDAO partidosDAO=new PartidosDAO();
        boolean status=partidosDAO.deletePartido(partido);
        //Confirmamos la Transaccion
        if(status){
            ConnectionManager.commit();
            return true;
        }
        else{
            ConnectionManager.rollback();
            return false;
        }
    }*/
    
    
    //Editamos el Partido Existente Agregando los Equipos
    public boolean EditPartido(PartidosBean partido){
        //Inicamos una Transaccion
        ConnectionManager.startTransaction();
        PartidosDAO partidosDAO=new PartidosDAO();
        boolean status=partidosDAO.editarPartido(partido);
        //Confirmamos la Transaccion
        if(status){
            ConnectionManager.commit();
             //Enviamos la Notificacion
            GCMRequest requestGCM=new GCMRequest();
            requestGCM.setIdPartido(partido.getIdpartido());
            requestGCM.setAction("P");
            requestGCM.setEquipoLocal(partido.getIdlocalteam().getIdequipo());
            requestGCM.setEquipoVisitante(partido.getIdvisitteam().getIdequipo());
            requestGCM.setFechaHora(partido.getDatetime());
            requestGCM.setLugar(partido.getPlace());
            requestGCM.setRonda(partido.getRonda());
            Notificaciones.sendNotificaciones(requestGCM);
            return true;
        }
        else{
            ConnectionManager.rollback();
            return false;
        }
    }
}
