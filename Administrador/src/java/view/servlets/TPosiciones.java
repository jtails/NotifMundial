package view.servlets;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import view.beans.TPosicionesBean;
import view.dao.TPosicionesDAO;


public class TPosiciones extends HttpServlet {
    
     public void init(ServletConfig config) throws ServletException {
        super.init(config);
    }

    public void doPost(HttpServletRequest request,
                       HttpServletResponse response) throws ServletException,
                                                            IOException {
        TPosicionesDAO tposicionesDAO=new TPosicionesDAO();
        List<TPosicionesBean> estadisticas=tposicionesDAO.getTPosiciones();
        request.setAttribute("Estadisticas",estadisticas);
        request.getRequestDispatcher("/WEB-INF/TPosiciones.jsp").forward(request, response);
    }
    
}
