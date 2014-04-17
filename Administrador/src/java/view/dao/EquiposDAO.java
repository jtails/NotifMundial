package view.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import view.beans.EquiposBean;

public class EquiposDAO {
    
    public List<EquiposBean> getTodosEquipos(){
        List<EquiposBean> listE=new ArrayList<EquiposBean>();
            List<Map> result = ConnectionManager.executeQuery("select * from equipos");
            if(result!=null){
                for(Map map:result){
                    EquiposBean eBean=new EquiposBean();
                    eBean.setIdequipo((String)map.get("id_equipo"));
                    eBean.setIdgrupo((String)map.get("id_grupo"));
                    eBean.setDisplayname((String)map.get("display_name"));
                    eBean.setShortname((String)map.get("short_name"));
                    eBean.setPathflag((String)map.get("pathflag"));
                    listE.add(eBean);
                }
            }
        return listE;
    }
    
    /*public boolean addEquipo(EquiposBean equipo){
        boolean status=ConnectionManager.execute("insert into equipos values('"+equipo.getNombre()+"','"+equipo.getNombrecorto()+"','"+equipo.getGrupo()+"','"+equipo.getPosicion()+"','0')");
        return status;
    }
    
    public boolean editEquipo(EquiposBean equipo){
        boolean status=ConnectionManager.execute("update equipos set grupo='"+equipo.getGrupo()+"',posicion='"+equipo.getPosicion()+"' where nombre='"+equipo.getNombre()+"'");
        return status;
    }
    
    public boolean deleteEquipo(EquiposBean equipo){
        boolean status=ConnectionManager.execute("delete from equipos where nombre='"+equipo.getNombre()+"'");
        return status;
    }
    
    public EquiposBean searchEquipo(String nombre){
        List<Map> result = ConnectionManager.executeQuery("select * from equipos where nombre='"+nombre+"'");
        EquiposBean eBean=new EquiposBean();
        if(result!=null){
            for(Map map:result){
                eBean.setNombre((String)map.get("NOMBRE"));
                eBean.setNombre((String)map.get("NOMBRECORTO"));
                eBean.setGrupo((String)map.get("GRUPO"));
                eBean.setPosicion((String)map.get("POSICION"));
                eBean.setPuntos((String)map.get("PUNTOS"));
            }
        }
        return eBean;
    }*/
}
