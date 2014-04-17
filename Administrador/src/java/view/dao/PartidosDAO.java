package view.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import view.beans.DetailsPartidoBean;
import view.beans.EquiposBean;
import view.beans.GolesBean;
import view.beans.PartidosBean;
import view.beans.TPosicionesBean;

public class PartidosDAO {
    
    /*public List<PartidosBean> getPartidos(String equipo){
        List<PartidosBean> listP=new ArrayList<PartidosBean>();
            List<Map> result = ConnectionManager.executeQuery("select * from partidos where idequipoa='"+equipo+"' or idequipob='"+equipo+"' order by(fecha)");
            if(result!=null){
                for(Map map:result){
                    PartidosBean pBean=new PartidosBean();
                    //pBean.setIdpartido((Integer)map.get("idpartido"));
                    pBean.setEquipoa((String)map.get("EQUIPOA"));
                    pBean.setEquipob((String)map.get("EQUIPOB"));
                    pBean.setFecha((String)map.get("FECHA"));
                    pBean.setHora((String)map.get("HORA"));
                    pBean.setGolesequipoa((String)map.get("GOLESEQUIPOA"));
                    pBean.setMingequipoa((String)map.get("MINGEQUIPOA"));
                    pBean.setGolesequipob((String)map.get("GOLESEQUIPOB"));
                    pBean.setMingequipob((String)map.get("MINGEQUIPOB"));
                    pBean.setStatuspartido((String)map.get("STATUS"));
                    pBean.setRonda((String)map.get("RONDA"));
                    listP.add(pBean);
                }
            }
        return listP;
    }*/
    
    public ArrayList<PartidosBean> getTodosPartidos(){
        ArrayList<PartidosBean> listP=new ArrayList<PartidosBean>();
            List<Map> result = ConnectionManager.executeQuery("select p.id_partido,p.date_time,p.place,p.idlocalteam,el.display_name as display_name_local,p.idvisitteam,ev.display_name as display_name_visit,p.ronda,d.status,d.goleslocalteam,d.golesvisitteam,d.num_notif from partidos p,equipos el, equipos ev ,details_partidos d where(p.idlocalteam=el.id_equipo) and (p.idvisitteam=ev.id_equipo) and (p.id_partido=d.id_partido) order by(to_timestamp(date_time,'DD/MM/YYYY HH24:MI'))");
            if(result!=null){
                for(Map map:result){
                    PartidosBean pBean=new PartidosBean();
                    pBean.setIdpartido((String)map.get("id_partido"));
                    pBean.setDatetime((String)map.get("date_time"));
                    pBean.setPlace((String)map.get("place"));
                    EquiposBean localteam=new EquiposBean();
                    localteam.setIdequipo((String)map.get("idlocalteam"));
                    localteam.setDisplayname((String)map.get("display_name_local"));
                    pBean.setIdlocalteam(localteam);
                    
                    EquiposBean visitteam=new EquiposBean();
                    visitteam.setIdequipo((String)map.get("idvisitteam"));
                    visitteam.setDisplayname((String)map.get("display_name_visit"));
                    pBean.setIdvisitteam(visitteam);
                    
                    pBean.setRonda((String)map.get("ronda"));
                    DetailsPartidoBean details=new DetailsPartidoBean();
                    details.setStatus((String) map.get("status"));
                    details.setGoleslocalteam((String)map.get("goleslocalteam"));
                    details.setGolesvisitteam((String)map.get("golesvisitteam"));
                    details.setNum_notf((String)map.get("num_notif"));
                    pBean.setDetails(details);
                    
                    listP.add(pBean);
                }
            }
        return listP;
    }
    
    
    
    
    public PartidosBean searchPartidos(PartidosBean partido){
            List<Map> result = ConnectionManager.executeQuery("select p.id_partido,p.date_time,p.place,p.idlocalteam,el.display_name as display_name_local,p.idvisitteam,ev.display_name as display_name_visit,p.ronda,d.status,d.goleslocalteam,d.golesvisitteam,d.num_notif from partidos p,equipos el, equipos ev ,details_partidos d where(p.idlocalteam=el.id_equipo) and (p.idvisitteam=ev.id_equipo) and (p.id_partido=d.id_partido) and p.id_partido="+partido.getIdpartido()+" order by(p.ronda)");
            PartidosBean pBean=new PartidosBean();
            if(result!=null){
                for(Map map:result){
                    pBean.setIdpartido((String)map.get("id_partido"));
                    pBean.setDatetime((String)map.get("date_time"));
                    pBean.setPlace((String)map.get("place"));
                    EquiposBean localteam=new EquiposBean();
                    localteam.setIdequipo((String)map.get("idlocalteam"));
                    localteam.setDisplayname((String)map.get("display_name_local"));
                    pBean.setIdlocalteam(localteam);
                    
                    EquiposBean visitteam=new EquiposBean();
                    visitteam.setIdequipo((String)map.get("idvisitteam"));
                    visitteam.setDisplayname((String)map.get("display_name_visit"));
                    pBean.setIdvisitteam(visitteam);
                    
                    pBean.setRonda((String)map.get("ronda"));
                    DetailsPartidoBean details=new DetailsPartidoBean();
                    details.setStatus((String) map.get("status"));
                    details.setGoleslocalteam((String)map.get("goleslocalteam"));
                    details.setGolesvisitteam((String)map.get("golesvisitteam"));
                    details.setNum_notf((String)map.get("num_notif"));
                    //GolesBean goles=new GolesBean();
                    //goles.setGolminute((String)map.get("GOL_MINUTE"));
                    pBean.setDetails(details);
                }
            }
        return pBean;
    }
    
    
    public String getIdPartido(PartidosBean partido){
            List<Map> result = ConnectionManager.executeQuery("select id_partido from partidos where date_time='"+partido.getDatetime()+"' and idlocalteam="+partido.getIdlocalteam().getIdequipo()+" and idvisitteam="+partido.getIdvisitteam().getIdequipo());
            if(result!=null)
                for(Map map:result)
                    return(String)map.get("id_partido");
              
        return null;
    }
    
    /*public List<PartidosBean> getPartidosSinIniciar(){
        List<PartidosBean> listP=new ArrayList<PartidosBean>();
            List<Map> result = ConnectionManager.executeQuery("select * from partidos where status='0' order by(ronda)");
            for(Map map:result){
                PartidosBean pBean=new PartidosBean();
                //pBean.setIdpartido((Integer)map.get("idpartido"));
                pBean.setEquipoa((String)map.get("EQUIPOA"));
                pBean.setEquipob((String)map.get("EQUIPOB"));
                pBean.setFecha((String)map.get("FECHA"));
                pBean.setHora((String)map.get("HORA"));
                pBean.setGolesequipoa((String)map.get("GOLESEQUIPOA"));
                pBean.setGolesequipob((String)map.get("GOLESEQUIPOB"));
                pBean.setStatuspartido((String)map.get("STATUS"));
                pBean.setRonda((String)map.get("RONDA"));
                listP.add(pBean);
            }
        return listP;
    }*/
    
    /*public boolean addPartido(PartidosBean partido){
        boolean statusP=ConnectionManager.execute("insert into partidos(date_time,place,idlocalteam,idvisitteam,ronda) values('"+partido.getDatetime()+"','"+partido.getPlace()+"',"+partido.getIdlocalteam().getIdequipo()+","+partido.getIdvisitteam().getIdequipo()+","+partido.getRonda()+")");
        boolean statusD=ConnectionManager.execute("insert into details_partidos(goleslocalteam,golesvisitteam,status) values(0,0,"+partido.getDetails().getStatus()+")");
        if(statusP && statusD)
            return true;
        else
            return false;
    }*/
    
    /*public boolean deletePartido(PartidosBean partido){
        boolean statusP=ConnectionManager.execute("delete from partidos where id_partido="+partido.getIdpartido()+"");
        boolean statusD=ConnectionManager.execute("delete from details_partidos where id_partido="+partido.getIdpartido()+"");
        if(statusP && statusD)
            return true;
        else
            return false;
    }*/
    
    public boolean iniciarPartido(PartidosBean partido){
        boolean status=ConnectionManager.execute("update details_partidos set status='2' where id_partido="+partido.getIdpartido());
        return status;
    }
    
    public boolean terminarPartido(PartidosBean partido){
        //Refrescamos el Objeto Partido
        partido=searchPartidos(partido);
        if(partido.getIdpartido()!=null){
            int num_notif=0;
            num_notif=Integer.valueOf(partido.getDetails().getNum_notf())+1;
            boolean status=ConnectionManager.execute("update details_partidos set status='3',num_notif="+num_notif+" where id_partido="+partido.getIdpartido());
            return status;
        }
        return false;
    }
    
    public boolean editarPartido(PartidosBean partido){
        boolean status=ConnectionManager.execute("update partidos set idlocalteam="+partido.getIdlocalteam().getIdequipo()+",idvisitteam="+partido.getIdvisitteam().getIdequipo()+",date_time='"+partido.getDatetime()+"',place='"+partido.getPlace()+"' where id_partido="+partido.getIdpartido()+"");
        return status;
    }

    public boolean agregarGEquipoA(PartidosBean partido){
        //Refrescamos el Objeto Partido
        partido=searchPartidos(partido);
        if(partido.getIdpartido()!=null){
            int goles=0;
            goles=Integer.valueOf(partido.getDetails().getGoleslocalteam())+1;
            int num_notif=0;
            num_notif=Integer.valueOf(partido.getDetails().getNum_notf())+1;
            boolean status=ConnectionManager.execute("update details_partidos set goleslocalteam="+goles+", num_notif="+num_notif+" where id_partido="+partido.getIdpartido());
            return status;
        }
        return false;
    }
    
    public boolean agregarGEquipoB(PartidosBean partido){
        //Refrescamos el Objeto Partido
        partido=searchPartidos(partido);
        if(partido.getIdpartido()!=null){
            int goles=0;
            goles=Integer.valueOf(partido.getDetails().getGolesvisitteam())+1;
            int num_notif=0;
            num_notif=Integer.valueOf(partido.getDetails().getNum_notf())+1;
            boolean status=ConnectionManager.execute("update details_partidos set golesvisitteam='"+goles+"', num_notif="+num_notif+" where id_partido="+partido.getIdpartido());
            return status;
        }
        return false;
    }
    
    public boolean eliminarGEquipoA(PartidosBean partido){
        //Refrescamos el Objeto Partido
        partido=searchPartidos(partido);
        if(partido.getIdpartido()!=null){
            int goles=0;
            goles=Integer.valueOf(partido.getDetails().getGoleslocalteam());
            if(goles>0)
                goles--;
            int num_notif=0;
            num_notif=Integer.valueOf(partido.getDetails().getNum_notf())+1;
            boolean status=ConnectionManager.execute("update details_partidos set goleslocalteam='"+goles+"', num_notif="+num_notif+" where id_partido="+partido.getIdpartido());
            return status;
         }
         return false;
    }
    
    
    public boolean eliminarGEquipoB(PartidosBean partido){
        //Refrescamos el Objeto Partido
        partido=searchPartidos(partido);
        if(partido.getIdpartido()!=null){
            int goles=0;
            goles=Integer.valueOf(partido.getDetails().getGolesvisitteam());
            if(goles>0)
                goles--;
            int num_notif=0;
            num_notif=Integer.valueOf(partido.getDetails().getNum_notf())+1;
            boolean status=ConnectionManager.execute("update details_partidos set golesvisitteam='"+goles+"' ,num_notif="+num_notif+" where id_partido="+partido.getIdpartido());
            return status;
        }
        return false;
    }
    
    
}
