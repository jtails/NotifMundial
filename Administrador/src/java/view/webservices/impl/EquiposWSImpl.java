package view.webservices.impl;

import java.util.List;
import javax.jws.WebMethod;
import javax.jws.WebService;
import view.beans.EquiposBean;
import view.dao.EquiposDAO;
import view.webservices.EquiposWS;

@WebService
public class EquiposWSImpl implements EquiposWS{
    
    @WebMethod
    public List<EquiposBean> getEquipos(String grupo){
        EquiposDAO equiposDAO=new EquiposDAO();
        List<EquiposBean> equipos= equiposDAO.getTodosEquipos();
        return equipos;
    }
}
