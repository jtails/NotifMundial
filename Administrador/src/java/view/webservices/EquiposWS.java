package view.webservices;

import java.util.List;
import javax.jws.WebMethod;
import javax.jws.WebService;
import view.beans.EquiposBean;


@WebService
public interface EquiposWS {
    @WebMethod
    public List<EquiposBean> getEquipos(String grupo);
}
