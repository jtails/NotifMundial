package view.servlets;

import java.io.IOException;

import java.util.ArrayList;
import java.util.List;
import javax.servlet.*;
import javax.servlet.http.*;
import view.beans.EquiposBean;
import view.beans.TPosicionesBean;
import view.dao.ConnectionManager;
import view.dao.EquiposDAO;
import view.dao.TPosicionesDAO;

public class Equipos extends HttpServlet {
    private static final String CONTENT_TYPE = "text/html; charset=windows-1252";
    
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
    }

    /*public void doPost(HttpServletRequest request,
                       HttpServletResponse response) throws ServletException,
                                                            IOException {   
            
        //Generamos el Bean del Equipo
        EquiposBean equipo=new EquiposBean();
        equipo.setNombre(request.getParameter("nombre"));
        equipo.setNombrecorto(request.getParameter("nombrecorto"));
        equipo.setGrupo(request.getParameter("grupo"));
        equipo.setPosicion(request.getParameter("posicion"));
            
        //Generamos el Bean de la Tabla de Posiciones
        TPosicionesBean tposicion=new TPosicionesBean();
        tposicion.setEquipo(request.getParameter("nombre"));
        tposicion.setEquipocorto(request.getParameter("nombrecorto"));
        tposicion.setGrupo(request.getParameter("grupo"));
        tposicion.setPosicion(request.getParameter("posicion"));
            
        String opcion=request.getParameter("opcion");     
        
        //Agregamos el Nuevo Equipo
        if(opcion.equalsIgnoreCase("Agregar"))
        {
            boolean status=AddEquipo(equipo,tposicion);
            if(status)
                request.setAttribute("Status",true);
            else
                request.setAttribute("Status",false);
        }
        
        //Eliminamos el Equipo Existente de Equipos y Tabla de Posiciones
        if(opcion.equalsIgnoreCase("Eliminar"))
        {
            boolean status=DeleteEquipo(equipo,tposicion);
            if(status)
                request.setAttribute("Status",true);
            else
                request.setAttribute("Status",false);  
        }
            
        
        //Consultamos los Equipos de los 4 Grupos Existentes
        List<String> grupos=new ArrayList<String>();
        grupos.add("A");
        grupos.add("B");
        grupos.add("C");
        grupos.add("D");
        request.setAttribute("Grupos",grupos);
        
        //Mostramos los Equipos de los 4 Grupos
        List<List> equipospGrupo=new ArrayList<List>();
        for(String grupo:grupos){
            EquiposDAO equiposDAO=new EquiposDAO();
            List<EquiposBean> equipos = equiposDAO.getEquipos(grupo);
            equipospGrupo.add(equipos);
        }
        request.setAttribute("EquipospGrupo",equipospGrupo);
        
        request.getRequestDispatcher("Equipos.jsp").forward(request,response);
    }
    
    //Se Agrega Equipo y se Agrega a la Tabla de Posiciones 
    public boolean AddEquipo(EquiposBean equipo,TPosicionesBean tposicion){
        
        //Inicamos una Transaccion
        //ConnectionManager.startTransaction();
        EquiposDAO equiposDAO=new EquiposDAO();
        boolean statusE=equiposDAO.addEquipo(equipo);
        TPosicionesDAO tposicionesDAO=new TPosicionesDAO();
        boolean statusT=tposicionesDAO.addTPosiciones(tposicion);
        //Confirmamos la Transaccion
        if(statusE && statusT){
            ConnectionManager.commit();
            return true;
        }
        else{
            //ConnectionManager.rollback();
            return false;
        }
    }
    
    
    //Se Elimina Equipo y se Elimina en la Tabla de Posiciones 
    public boolean DeleteEquipo(EquiposBean equipo,TPosicionesBean tposicion){
        
        //Inicamos una Transaccion
        //ConnectionManager.startTransaction();
        EquiposDAO equiposDAO=new EquiposDAO();
        boolean statusE=equiposDAO.deleteEquipo(equipo);
        TPosicionesDAO tposicionesDAO=new TPosicionesDAO();
        boolean statusT=tposicionesDAO.deleteTPosiciones(tposicion);
        //Confirmamos la Transaccion
        if(statusE && statusT){
            ConnectionManager.commit();
            return true;
        }
        else{
            //ConnectionManager.rollback();
            return false;
        }
    }*/
    
}
