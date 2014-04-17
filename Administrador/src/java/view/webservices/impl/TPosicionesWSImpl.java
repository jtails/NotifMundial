package view.webservices.impl;

import java.util.List;
import javax.jws.WebMethod;
import javax.jws.WebService;
import view.beans.TPosicionesBean;
import view.dao.TPosicionesDAO;
import view.webservices.TPosicionesWS;

@WebService
public class TPosicionesWSImpl implements TPosicionesWS{
    
    @WebMethod
    public List<TPosicionesBean> getTPosiciones(String grupo){
        //TPosicionesDAO tposicionesDAO=new TPosicionesDAO();
        //List<TPosicionesBean> tposiciones= tposicionesDAO.getTPosiciones(grupo);
        //return tposiciones;
        return null;
    }
}
