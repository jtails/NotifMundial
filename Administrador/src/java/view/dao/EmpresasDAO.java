package view.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import view.beans.DetailsPartidoBean;
import view.beans.EmpresasBean;
import view.beans.EquiposBean;
import view.beans.NotificacionesBean;
import view.beans.PartidosBean;
import view.beans.WrapperBean;


public class EmpresasDAO {
    
    public List<EmpresasBean> getEmpresas(){
        List<EmpresasBean> listE=new ArrayList<EmpresasBean>();
            List<Map> result = ConnectionManager.executeQuery("select e.id_empresa,e.nombre,e.codigo,e.imagen1,e.imagen2,e.imagen3,e.imagen4,e.imagen5,e.imagen6,e.licencias,(select count(*) from notificaciones where id_empresa=e.id_empresa) as nolicencias from empresas e");
            if(result!=null){
                for(Map map:result){
                    EmpresasBean eBean=new EmpresasBean();
                    eBean.setId_empresa((String)map.get("id_empresa"));
                    eBean.setNombre((String)map.get("nombre"));
                    eBean.setCodigo((String)map.get("codigo"));
                    eBean.setLicencias((String)map.get("licencias"));
                    eBean.setNolicencias((String)map.get("nolicencias"));
                    ArrayList<WrapperBean> imagenes=new ArrayList<WrapperBean>();
                    for(int i=1;i<7;i++){
                        WrapperBean wrapper=new WrapperBean();
                        wrapper.setData((String)map.get("imagen"+i));
                        imagenes.add(wrapper);
                    }
                    eBean.setImagenes(imagenes);
                    listE.add(eBean);
                }
            }
        return listE;
    }
    
    
    public EmpresasBean searchEmpresa(EmpresasBean empresa){
            List<Map> result = ConnectionManager.executeQuery("select e.id_empresa,e.nombre,e.codigo,e.imagen1,e.imagen2,e.imagen3,e.imagen4,e.imagen5,e.imagen6,e.licencias,(select count(*) from notificaciones where id_empresa=e.id_empresa) as nolicencias from empresas e where e.codigo='"+empresa.getCodigo()+"'");
            EmpresasBean eBean=new EmpresasBean();
            if(result!=null){
                for(Map map:result){
                    eBean.setId_empresa((String)map.get("id_empresa"));
                    eBean.setNombre((String)map.get("nombre"));
                    eBean.setCodigo((String)map.get("codigo"));
                    eBean.setLicencias((String)map.get("licencias"));
                    eBean.setNolicencias((String)map.get("nolicencias"));
                    ArrayList<WrapperBean> imagenes=new ArrayList<WrapperBean>();
                    for(int i=1;i<7;i++){
                        WrapperBean wrapper=new WrapperBean();
                        wrapper.setData((String)map.get("imagen"+i));
                        imagenes.add(wrapper);
                    }
                    eBean.setImagenes(imagenes);
                }
            }
        return eBean;
    }
    
    public EmpresasBean searchEmpresaById(EmpresasBean empresa){
            List<Map> result = ConnectionManager.executeQuery("select e.id_empresa,e.nombre,e.codigo,e.imagen1,e.imagen2,e.imagen3,e.imagen4,e.imagen5,e.imagen6,e.licencias,(select count(*) from notificaciones where id_empresa=e.id_empresa) as nolicencias from empresas e where e.id_empresa='"+empresa.getId_empresa()+"'");
            EmpresasBean eBean=new EmpresasBean();
            if(result!=null){
                for(Map map:result){
                    eBean.setId_empresa((String)map.get("id_empresa"));
                    eBean.setNombre((String)map.get("nombre"));
                    eBean.setCodigo((String)map.get("codigo"));
                    eBean.setLicencias((String)map.get("licencias"));
                    eBean.setNolicencias((String)map.get("nolicencias"));
                    ArrayList<WrapperBean> imagenes=new ArrayList<WrapperBean>();
                    for(int i=1;i<7;i++){
                        WrapperBean wrapper=new WrapperBean();
                        wrapper.setData((String)map.get("imagen"+i));
                        imagenes.add(wrapper);
                    }
                    eBean.setImagenes(imagenes);
                }
            }
        return eBean;
    }
    
    
    public boolean addEmpresa(EmpresasBean empresa){
        ArrayList<WrapperBean> imagenes=empresa.getImagenes();
        boolean status=ConnectionManager.execute("insert into empresas(nombre,codigo,imagen1,imagen2,imagen3,imagen4,imagen5,imagen6,licencias) values('"+empresa.getNombre()+"','"+empresa.getCodigo()+"','"+imagenes.get(0).getData()+"','"+imagenes.get(1).getData()+"','"+imagenes.get(2).getData()+"','"+imagenes.get(3).getData()+"','"+imagenes.get(4).getData()+"','"+imagenes.get(5).getData()+"',"+empresa.getLicencias()+")");
        return status;
    }
    
    public boolean deleteEmpresa(EmpresasBean empresa){
        boolean status=ConnectionManager.execute("delete from empresas where id_empresa="+empresa.getId_empresa()+"");
        return status;
    }
    
    //Validamos que existan licencias disponibles
    public boolean validaLicencias(EmpresasBean empresa){
        //Refrescamos el Objeto Empresa
        empresa=searchEmpresaById(empresa);
        //Verificamos que existan licencias Disponibles
        if(empresa.getId_empresa()!=null)
            if(Integer.valueOf(empresa.getLicencias())>=Integer.valueOf(empresa.getNolicencias()))
                return true;
        return false;
    }
}
