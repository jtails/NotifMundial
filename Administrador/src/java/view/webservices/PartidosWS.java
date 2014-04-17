package view.webservices;


import java.util.List;
import javax.jws.WebMethod;
import javax.jws.WebService;
import view.beans.PartidosBean;

@WebService
public interface PartidosWS {
    @WebMethod
    public List<PartidosBean> getPartidos(String equipo);
}
