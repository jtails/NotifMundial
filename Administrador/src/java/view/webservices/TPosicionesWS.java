package view.webservices;

import java.util.List;
import javax.jws.WebMethod;
import javax.jws.WebService;
import view.beans.TPosicionesBean;

@WebService
public interface TPosicionesWS {
    @WebMethod
    public List<TPosicionesBean> getTPosiciones(String grupo);
}
