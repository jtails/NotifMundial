package view.servlets;

import java.io.IOException;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import view.beans.LoginBean;
import view.dao.LoginDAO;

public class Login extends HttpServlet {
    private static final String CONTENT_TYPE = "text/html; charset=windows-1252";
    
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
    }

    public void doPost(HttpServletRequest request,
                       HttpServletResponse response) throws ServletException,
                                                            IOException {
        String username=request.getParameter("username");
        String password=request.getParameter("password");
        LoginBean lBean= new LoginBean();
        lBean.setUsername(username);
        lBean.setPassword(password);
        LoginDAO login=new LoginDAO();
        boolean status=login.login(lBean);
        if(status){
            request.getRequestDispatcher("/WEB-INF/menu.jsp").forward(request, response);
            request.getSession().setAttribute("login",lBean);
        }
        else{
            request.setAttribute("Status",false);
            request.getRequestDispatcher("login.jsp").forward(request, response);
        }
    }
    
}
