package view.servlets;

import java.io.IOException;
import java.util.List;
import javax.servlet.*;
import javax.servlet.http.*;
import view.beans.DetailsPartidoBean;
import view.beans.EquiposBean;
import view.beans.GolesBean;
import view.beans.PartidosBean;
import view.dao.ConnectionManager;
import view.dao.PartidosDAO;
import view.notification.android.GCMRequest;
import view.notification.android.NotificationServiceImpl;

public class IniciarPartido extends HttpServlet {
    private static final String CONTENT_TYPE = "text/html; charset=windows-1252";

    public void init(ServletConfig config) throws ServletException {
        super.init(config);
    }

    public void doPost(HttpServletRequest request,
                       HttpServletResponse response) throws ServletException,
                                                            IOException {
        response.setContentType(CONTENT_TYPE);
        
        String fechaHora=request.getParameter("FechaHora");
        String idlocalteam=request.getParameter("Idlocalteam");
        String idvisitteam=request.getParameter("Idvisitteam");
        String ronda=request.getParameter("Ronda");
        String minuto=request.getParameter("Minuto");
        String id=request.getParameter("Id");
        
        PartidosBean partidosBean=new PartidosBean();
        partidosBean.setIdpartido(id);
        partidosBean.setDatetime(fechaHora);
        
        EquiposBean localteam=new EquiposBean();
        localteam.setIdequipo(idlocalteam);
        
        EquiposBean visitteam=new EquiposBean();
        visitteam.setIdequipo(idvisitteam);
        
        partidosBean.setIdlocalteam(localteam);
        partidosBean.setIdvisitteam(visitteam);
        partidosBean.setRonda(ronda);
        
        GolesBean goles=new GolesBean();
        goles.setGolminute(minuto);
        goles.setStatus("0");
        
        DetailsPartidoBean details=new DetailsPartidoBean();
        details.setGoles(goles);
        
        partidosBean.setDetails(details);
         
      
        String opcion=request.getParameter("opcion");
        if(opcion.equalsIgnoreCase("Iniciar")){
            boolean status=IniciarPartido(partidosBean);
            if(status)
                request.setAttribute("Status",true);
            else
                request.setAttribute("Status",false);
        }
        
        if(opcion.equalsIgnoreCase("AgregarGEquipoA")){
            boolean status=AgregarGEquipoA(partidosBean);
            if(status)
                request.setAttribute("Status",true);
            else
                request.setAttribute("Status",false);
        }
        
        if(opcion.equalsIgnoreCase("AgregarGEquipoB")){
            boolean status=AgregarGEquipoB(partidosBean);
            if(status)
                request.setAttribute("Status",true);
            else
                request.setAttribute("Status",false);
        }
        
        if(opcion.equalsIgnoreCase("EliminarGEquipoA")){
            boolean status=EliminarGEquipoA(partidosBean);
            if(status)
                request.setAttribute("Status",true);
            else
                request.setAttribute("Status",false);
        }
        
        if(opcion.equalsIgnoreCase("EliminarGEquipoB")){
            boolean status=EliminarGEquipoB(partidosBean);
            if(status)
                request.setAttribute("Status",true);
            else
                request.setAttribute("Status",false);
        }
        
        if(opcion.equalsIgnoreCase("TerminarP")){
            boolean status=TerminarPartido(partidosBean);
            if(status)
                request.setAttribute("Status",true);
            else
                request.setAttribute("Status",false);
        }
        
        //Consultamos Todos los Partidos Existentes
        PartidosDAO partidosDAO=new PartidosDAO();
        List<PartidosBean> partidos=partidosDAO.getTodosPartidos();
        request.setAttribute("Partidos",partidos);
        
        request.getRequestDispatcher("/WEB-INF/IniciarPartido.jsp").forward(request,response);
        
    }
    
    //Iniciamos el Partido
    public boolean IniciarPartido(PartidosBean partido){
        //Inicamos una Transaccion
        ConnectionManager.startTransaction();
        PartidosDAO partidosDAO=new PartidosDAO();
        boolean status=partidosDAO.iniciarPartido(partido);
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
    
    //Aumentamos un Gol al EquipoA y Actualizamos Tabla de Posiciones
    public boolean AgregarGEquipoA(PartidosBean partido){
        //Inicamos una Transaccion
        ConnectionManager.startTransaction();
        
        String minute=partido.getDetails().getGoles().getGolminute();
        PartidosDAO partidosDAO=new PartidosDAO();
        boolean status=partidosDAO.agregarGEquipoA(partido);
        //Refrescamos el Objeto Partido
        partido=partidosDAO.searchPartidos(partido);
        //Confirmamos la Transaccion
        if(status){
            ConnectionManager.commit();
            //Enviamos la Notificacion
            GCMRequest requestGCM=new GCMRequest();
            requestGCM.setIdPartido(partido.getIdpartido());
            requestGCM.setAction("G");
            requestGCM.setEquipoLocal(partido.getIdlocalteam().getIdequipo());
            requestGCM.setEquipoVisitante(partido.getIdvisitteam().getIdequipo());
            requestGCM.setEstatusPartido(partido.getDetails().getStatus());
            requestGCM.setFechaHora(partido.getDatetime());
            requestGCM.setLugar(partido.getPlace());
            requestGCM.setRonda(partido.getRonda());
            requestGCM.setGolDe(partido.getIdlocalteam().getIdequipo());
            requestGCM.setGolesLocal(partido.getDetails().getGoleslocalteam());
            requestGCM.setGolesVisitante(partido.getDetails().getGolesvisitteam());
            requestGCM.setMinuto(minute);
            requestGCM.setNum_notif(partido.getDetails().getNum_notf());
            Notificaciones.sendNotificaciones(requestGCM);
            return true;
        }
        else{
            ConnectionManager.rollback();
            return false;
        }   
    }
    
    //Aumentamos un Gol al EquipoB
    public boolean AgregarGEquipoB(PartidosBean partido){
        //Inicamos una Transaccion
        ConnectionManager.startTransaction();
        
        String minute=partido.getDetails().getGoles().getGolminute();
        PartidosDAO partidosDAO=new PartidosDAO();
        boolean status=partidosDAO.agregarGEquipoB(partido);
        //Refrescamos el Objeto Partido
        partido=partidosDAO.searchPartidos(partido);
        //Confirmamos la Transaccion
        if(status){
            ConnectionManager.commit();
            //Enviamos la Notificacion
            GCMRequest requestGCM=new GCMRequest();
            requestGCM.setIdPartido(partido.getIdpartido());
            requestGCM.setAction("G");
            requestGCM.setEquipoLocal(partido.getIdlocalteam().getIdequipo());
            requestGCM.setEquipoVisitante(partido.getIdvisitteam().getIdequipo());
            requestGCM.setEstatusPartido(partido.getDetails().getStatus());
            requestGCM.setFechaHora(partido.getDatetime());
            requestGCM.setLugar(partido.getPlace());
            requestGCM.setRonda(partido.getRonda());
            requestGCM.setGolDe(partido.getIdvisitteam().getIdequipo());
            requestGCM.setGolesLocal(partido.getDetails().getGoleslocalteam());
            requestGCM.setGolesVisitante(partido.getDetails().getGolesvisitteam());
            requestGCM.setMinuto(minute);
            requestGCM.setNum_notif(partido.getDetails().getNum_notf());
            Notificaciones.sendNotificaciones(requestGCM);
            return true;
        }
        else{
            ConnectionManager.rollback();
            return false;
        }   
    }
    
    //Disminuimos un Gol al EquipoA
    public boolean EliminarGEquipoA(PartidosBean partido){
        //Inicamos una Transaccion
        ConnectionManager.startTransaction();
        
        String minute=partido.getDetails().getGoles().getGolminute();
        PartidosDAO partidosDAO=new PartidosDAO();
        boolean status=partidosDAO.eliminarGEquipoA(partido);
        //Refrescamos el Objeto Partido
        partido=partidosDAO.searchPartidos(partido);
        //Confirmamos la Transaccion
        if(status){
            ConnectionManager.commit();
            //Enviamos la Notificacion
            GCMRequest requestGCM=new GCMRequest();
            requestGCM.setIdPartido(partido.getIdpartido());
            requestGCM.setAction("A");
            requestGCM.setEquipoLocal(partido.getIdlocalteam().getIdequipo());
            requestGCM.setEquipoVisitante(partido.getIdvisitteam().getIdequipo());
            requestGCM.setEstatusPartido(partido.getDetails().getStatus());
            requestGCM.setFechaHora(partido.getDatetime());
            requestGCM.setLugar(partido.getPlace());
            requestGCM.setRonda(partido.getRonda());
            requestGCM.setGolDe(partido.getIdlocalteam().getIdequipo());
            requestGCM.setGolesLocal(partido.getDetails().getGoleslocalteam());
            requestGCM.setGolesVisitante(partido.getDetails().getGolesvisitteam());
            requestGCM.setMinuto(minute);
            requestGCM.setNum_notif(partido.getDetails().getNum_notf());
            Notificaciones.sendNotificaciones(requestGCM);
            return true;
        }
        else{
            ConnectionManager.rollback();
            return false;
        }   
    }
    
    //Disminuimos un Gol al EquipoB
    public boolean EliminarGEquipoB(PartidosBean partido){
        //Inicamos una Transaccion
        ConnectionManager.startTransaction();
        
        String minute=partido.getDetails().getGoles().getGolminute();
        PartidosDAO partidosDAO=new PartidosDAO();
        boolean status=partidosDAO.eliminarGEquipoB(partido);
        //Refrescamos el Objeto Partido
        partido=partidosDAO.searchPartidos(partido);
        //Confirmamos la Transaccion
        if(status){
            ConnectionManager.commit();
            //Enviamos la Notificacion
            GCMRequest requestGCM=new GCMRequest();
            requestGCM.setIdPartido(partido.getIdpartido());
            requestGCM.setAction("A");
            requestGCM.setEquipoLocal(partido.getIdlocalteam().getIdequipo());
            requestGCM.setEquipoVisitante(partido.getIdvisitteam().getIdequipo());
            requestGCM.setEstatusPartido(partido.getDetails().getStatus());
            requestGCM.setFechaHora(partido.getDatetime());
            requestGCM.setLugar(partido.getPlace());
            requestGCM.setRonda(partido.getRonda());
            requestGCM.setGolDe(partido.getIdvisitteam().getIdequipo());
            requestGCM.setGolesLocal(partido.getDetails().getGoleslocalteam());
            requestGCM.setGolesVisitante(partido.getDetails().getGolesvisitteam());
            requestGCM.setMinuto(minute);
            requestGCM.setNum_notif(partido.getDetails().getNum_notf());
            Notificaciones.sendNotificaciones(requestGCM);
            return true;
        }
        else{
            ConnectionManager.rollback();
            return false;
        }   
    }
    
    //Terminamos el Partido
    public boolean TerminarPartido(PartidosBean partido){
        //Inicamos una Transaccion
        ConnectionManager.startTransaction();
        PartidosDAO partidosDAO=new PartidosDAO();
        boolean status=partidosDAO.terminarPartido(partido);
        //Refrescamos el Objeto Partido
        partido=partidosDAO.searchPartidos(partido);
        //Confirmamos la Transaccion
        if(status){
            ConnectionManager.commit();
            //Enviamos la Notificacion
            GCMRequest requestGCM=new GCMRequest();
            requestGCM.setIdPartido(partido.getIdpartido());
            requestGCM.setAction("F");
            requestGCM.setEstatusPartido(partido.getDetails().getStatus());
            requestGCM.setNum_notif(partido.getDetails().getNum_notf());
            Notificaciones.sendNotificaciones(requestGCM);
            return true;
        }
        else{
            ConnectionManager.rollback();
            return false;
        }   
    }
    
}
