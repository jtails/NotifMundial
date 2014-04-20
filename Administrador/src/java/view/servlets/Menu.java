package view.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.*;
import javax.servlet.http.*;

import view.beans.ConfiguracionesBean;
import view.beans.EmpresasBean;
import view.beans.EquiposBean;
import view.beans.PartidosBean;
import view.beans.TPosicionesBean;
import view.dao.ConfiguracionesDAO;
import view.dao.ConnectionManager;
import view.dao.EmpresasDAO;
import view.dao.EquiposDAO;
import view.dao.PartidosDAO;
import view.dao.TPosicionesDAO;

public class Menu extends HttpServlet {
    private static final String CONTENT_TYPE = "text/html; charset=windows-1252";

    public void init(ServletConfig config) throws ServletException {
        super.init(config);
    }

    public void doPost(HttpServletRequest request,
                       HttpServletResponse response) throws ServletException,
                                                            IOException {
        doGet(request,response);
    }
    
    public void doGet(HttpServletRequest request,
                       HttpServletResponse response) throws ServletException,
                                                            IOException {
        
        //Mostramos los Equipos de los 8 Grupos
        EquiposDAO equiposDAO=new EquiposDAO();
        List<EquiposBean> equipos = equiposDAO.getTodosEquipos();
        request.setAttribute("Equipos",equipos);
        
        //Mostramos la Tabla de Posiciones
        TPosicionesDAO tposicionesDAO=new TPosicionesDAO();
        List<TPosicionesBean> estadisticas=tposicionesDAO.getTPosiciones();
        request.setAttribute("Estadisticas",estadisticas);
        
        
        //Consultamos Todos los Partidos Existentes
        PartidosDAO partidosDAO=new PartidosDAO();
        List<PartidosBean> partidos=partidosDAO.getTodosPartidos();
        request.setAttribute("Partidos",partidos);
        
        //Consultamos Todos las Empresas
        EmpresasDAO empresasDAO=new EmpresasDAO();
        List<EmpresasBean> empresas=empresasDAO.getEmpresas();
        request.setAttribute("Empresas",empresas);
        
        //Consultamos las Configuraciones
        ConfiguracionesDAO configuracionesDAO=new ConfiguracionesDAO();
        ConfiguracionesBean configuraciones=configuracionesDAO.getConfiguraciones();
        request.setAttribute("Configuraciones",configuraciones);
        
        
        String opcion=request.getParameter("opcion");
        if(opcion.equalsIgnoreCase("Partidos")){
            request.getRequestDispatcher("/WEB-INF/Partidos.jsp").forward(request,response);
        }
        if(opcion.equalsIgnoreCase("Equipos")){
            request.getRequestDispatcher("/WEB-INF/Equipos.jsp").forward(request,response);
        }
        if(opcion.equalsIgnoreCase("Posiciones")){
            request.getRequestDispatcher("/WEB-INF/TPosiciones.jsp").forward(request,response);
        }
        if(opcion.equalsIgnoreCase("IPartido")){
            request.getRequestDispatcher("/WEB-INF/IniciarPartido.jsp").forward(request,response);
        }
        if(opcion.equalsIgnoreCase("Empresas")){
            request.getRequestDispatcher("/WEB-INF/Empresas.jsp").forward(request,response);
        }
        if(opcion.equalsIgnoreCase("Menu")){
            request.getRequestDispatcher("/WEB-INF/menu.jsp").forward(request,response);
        }
        if(opcion.equalsIgnoreCase("Estadisticas")){
            request.getRequestDispatcher("/WEB-INF/TPosiciones.jsp").forward(request, response);
        }
        if(opcion.equalsIgnoreCase("Configuraciones")){
            request.getRequestDispatcher("/WEB-INF/Configuraciones.jsp").forward(request, response);
        }
    }
    
}
