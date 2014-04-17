package view.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import view.beans.EquiposBean;
import view.beans.PartidosBean;
import view.beans.TPosicionesBean;

public class TPosicionesDAO {
   
   public List<TPosicionesBean> getTPosiciones(){
        List<TPosicionesBean> listTP=new ArrayList<TPosicionesBean>();
            List<Map> result = ConnectionManager.executeQuery("select es.idgrupo,es.idequipo,e.display_name,es.jugados,es.ganados,es.empatados,es.perdidos,es.gfavor,es.gcontra,es.puntos,es.difgoles from estadisticas es,equipos e where es.idequipo=e.id_equipo and es.idgrupo!=9");
            if(result!=null){
                for(Map map:result){
                    TPosicionesBean TPBean=new TPosicionesBean();
                    TPBean.setId_grupo((String)map.get("idgrupo"));
                    TPBean.setId_equipo((String)map.get("idequipo"));
                    TPBean.setName((String)map.get("display_name"));
                    TPBean.setJugados((String)map.get("jugados"));
                    TPBean.setGanados((String)map.get("ganados"));
                    TPBean.setEmpatados((String)map.get("empatados"));
                    TPBean.setPerdidos((String)map.get("perdidos"));
                    TPBean.setGfavor((String)map.get("gfavor"));
                    TPBean.setGcontra((String)map.get("gcontra"));
                    TPBean.setPuntos((String)map.get("puntos"));
                    TPBean.setDifgoles((String)map.get("difgoles"));
                    listTP.add(TPBean);
                }
            }
            return listTP;
    }
    
    
    /*public static TPosicionesBean searchTPosiciones(String equipo){
            List<Map> result = ConnectionManager.executeQuery("select * from tposiciones where equipo='"+equipo+"'");
            TPosicionesBean TPBean=new TPosicionesBean();
            if(result!=null){
                for(Map map:result){
                    //TPBean.setIdposicion((Integer)map.get("idposicion"));
                    TPBean.setEquipo((String)map.get("EQUIPO"));
                    TPBean.setEquipocorto((String)map.get("EQUIPOCORTO"));
                    TPBean.setGrupo((String)map.get("GRUPO"));
                    TPBean.setPosicion((String)map.get("POSICION"));
                    TPBean.setPts((String)map.get("PTS"));
                    TPBean.setPj((String)map.get("PJ"));
                    TPBean.setPg((String)map.get("PG"));
                    TPBean.setPe((String)map.get("PE"));
                    TPBean.setPp((String)map.get("PP"));
                    TPBean.setGf((String)map.get("GF"));
                    TPBean.setGc((String)map.get("GC"));
                    TPBean.setDif((String)map.get("DIF"));
                }
            }
            return TPBean;
    }
    
    public boolean addTPosiciones(TPosicionesBean tposiciones){
        boolean status=ConnectionManager.execute("insert into tposiciones values('"+tposiciones.getEquipo()+"','"+tposiciones.getEquipocorto()+"','"+tposiciones.getGrupo()+"','"+tposiciones.getPosicion()+"','0','0','0','0','0','0','0','0')");
        return status;
    }
    
    /*public boolean editTPosiciones(TPosicionesBean tposiciones){
        boolean status=ConnectionManager.execute("update tposiciones set grupo='"+tposiciones.getGrupo()+"',posicion='"+tposiciones.getPosicion()+"' where equipo='"+tposiciones.getEquipo()+"'");
        return status;
    }*/
    
    /*public boolean deleteTPosiciones(TPosicionesBean tposiciones){
        boolean status=ConnectionManager.execute("delete from tposiciones where equipo='"+tposiciones.getEquipo()+"'");
        return status;
    }*/
    
    /*public boolean terminarPartido(PartidosBean partido){
    
        boolean status1=false,status2=false,status3=false,status4=false,status5=false,status6=false,statuspg=false,statuspp=false;
        Integer gfea=0,gfeb=0,gcea=0,gceb=0,diffea=0,diffeb=0;
        
        //Actualizamos los goles a favor del equipo a
        List<Map> result = ConnectionManager.executeQuery("select gf from tposiciones where equipo='"+partido.getEquipoa()+"'");
        if(result!=null){
            String gf=(String)result.get(0).get("GF");
            gfea=Integer.valueOf(gf) + Integer.valueOf(partido.getGolesequipoa());
            status1=ConnectionManager.execute("update tposiciones set gf='"+gfea+"' where equipo='"+partido.getEquipoa()+"'");
        }
        
        //Actualizamos los goles a favor del equipo b
        result = ConnectionManager.executeQuery("select gf from tposiciones where equipo='"+partido.getEquipob()+"'");
        if(result!=null){
            String gf=(String)result.get(0).get("GF");
            gfeb=Integer.valueOf(gf) + Integer.valueOf(partido.getGolesequipob());
            status2=ConnectionManager.execute("update tposiciones set gf='"+gfeb+"' where equipo='"+partido.getEquipob()+"'");
        }
        
        //Actualizamos los goles en contra del equipo a
        result = ConnectionManager.executeQuery("select gc from tposiciones where equipo='"+partido.getEquipoa()+"'");
        if(result!=null){
            String gc=(String)result.get(0).get("GC");
            gcea=Integer.valueOf(gc) + Integer.valueOf(partido.getGolesequipob());
            status3=ConnectionManager.execute("update tposiciones set gc='"+gcea+"' where equipo='"+partido.getEquipoa()+"'");
        }
        
        //Actualizamos los goles en contra del equipo b
        result = ConnectionManager.executeQuery("select gc from tposiciones where equipo='"+partido.getEquipob()+"'");
        if(result!=null){
            String gc=(String)result.get(0).get("GC");
            gceb=Integer.valueOf(gc) + Integer.valueOf(partido.getGolesequipoa());
            status4=ConnectionManager.execute("update tposiciones set gc='"+gceb+"' where equipo='"+partido.getEquipob()+"'");
        }
        
        //Actualizamos la diferencia de goles del equipo a
        diffea=Math.abs(gfea-gcea);
        status5 = ConnectionManager.execute("update tposiciones set dif='"+diffea+"'where equipo='"+partido.getEquipoa()+"'");
        
        //Actualizamos la diferencia de goles del equipo b
        diffeb=Math.abs(gfeb-gceb);
        status6 = ConnectionManager.execute("update tposiciones set dif='"+diffeb+"'where equipo='"+partido.getEquipob()+"'");
        
        if(Integer.valueOf(partido.getGolesequipoa())>Integer.valueOf(partido.getGolesequipob())){
            //Actualizamos los partidos ganados equipo a
            result = ConnectionManager.executeQuery("select pg from tposiciones where equipo='"+partido.getEquipoa()+"'");
            if(result!=null){
                String pg=(String)result.get(0).get("PG");
                Integer pgI=Integer.valueOf(pg)+1;
                statuspg=ConnectionManager.execute("update tposiciones set pg='"+pgI+"' where equipo='"+partido.getEquipoa()+"'");
            }
            //Actualizamos los partidos perdidos del equipo b
            result = ConnectionManager.executeQuery("select pp from tposiciones where equipo='"+partido.getEquipob()+"'");
            if(result!=null){
                String pp=(String)result.get(0).get("PP");
                Integer ppI=Integer.valueOf(pp)+1;
                statuspp=ConnectionManager.execute("update tposiciones set pp='"+ppI+"' where equipo='"+partido.getEquipob()+"'");
            }
        }else{
            if(Integer.valueOf(partido.getGolesequipob())>Integer.valueOf(partido.getGolesequipoa())){
                //Actualizamos los partidos ganados equipo b
                result = ConnectionManager.executeQuery("select pg from tposiciones where equipo='"+partido.getEquipob()+"'");
                if(result!=null){
                    String pg=(String)result.get(0).get("PG");
                    Integer pgI=Integer.valueOf(pg)+1;
                    statuspg=ConnectionManager.execute("update tposiciones set pg='"+pgI+"' where equipo='"+partido.getEquipob()+"'");
                }
                //Actualizamos los partidos perdidos del equipo a
                result = ConnectionManager.executeQuery("select pp from tposiciones where equipo='"+partido.getEquipoa()+"'");
                if(result!=null){
                    String pp=(String)result.get(0).get("PP");
                    Integer ppI=Integer.valueOf(pp)+1;
                    statuspp=ConnectionManager.execute("update tposiciones set pp='"+ppI+"' where equipo='"+partido.getEquipoa()+"'");
                }
            }else{
                //Actualizamos los partidos empatados del equipo a
                result = ConnectionManager.executeQuery("select pe from tposiciones where equipo='"+partido.getEquipoa()+"'");
                if(result!=null){
                    String pe=(String)result.get(0).get("PE");
                    Integer peI=Integer.valueOf(pe)+1;
                    statuspg=ConnectionManager.execute("update tposiciones set pe='"+peI+"' where equipo='"+partido.getEquipoa()+"'");
                }
                //Actualizamos los partidos empatados del equipo b
                result = ConnectionManager.executeQuery("select pe from tposiciones where equipo='"+partido.getEquipob()+"'");
                if(result!=null){
                    String pe=(String)result.get(0).get("PE");
                    Integer peI=Integer.valueOf(pe)+1;
                    statuspp=ConnectionManager.execute("update tposiciones set pe='"+peI+"' where equipo='"+partido.getEquipob()+"'");
                }
            }
        }
        
        if(status1 && status2 && status3 && status4 && status5 && status6 && statuspg && statuspp)
            return true;
        else
            return false;
    }*/
        
}
