package view.webservices.impl;

import java.util.List;
import javax.jws.WebMethod;
import javax.jws.WebService;
import view.beans.PartidosBean;
import view.dao.PartidosDAO;
import view.webservices.PartidosWS;

@WebService
public class PartidosWSImpl implements PartidosWS{
    
    @WebMethod
    public List<PartidosBean> getPartidos(String equipo){
    /*    PartidosDAO partidosDAO=new PartidosDAO();
        List<PartidosBean> partidos= partidosDAO.getPartidos(equipo);
        
        //Ordenamos los Partidos para mostrar como equipo principal el equipo consultado
        for(PartidosBean partidoBean:partidos){
            if(!partidoBean.getEquipoa().equalsIgnoreCase(equipo)){
                String equipoOpo=partidoBean.getEquipoa();
                partidoBean.setEquipoa(partidoBean.getEquipob());
                partidoBean.setEquipob(equipoOpo);
            }    
        }
        return partidos;*/return null;
    }
}
