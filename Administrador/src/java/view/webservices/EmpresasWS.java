package view.webservices;

import java.util.List;
import javax.jws.WebMethod;
import javax.jws.WebService;
import view.beans.EmpresasBean;

@WebService
public interface EmpresasWS {
    @WebMethod
    public EmpresasBean getDeatilsEmpresa(String codigo,String notifid,String tipod);
    
}
