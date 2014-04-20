package view.dao;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;
import view.beans.ConfiguracionesBean;
import view.beans.DetailsPartidoBean;
import view.beans.EmpresasBean;
import view.beans.EquiposBean;
import view.beans.NotificacionesBean;
import view.beans.PartidosBean;
import view.beans.WrapperBean;


public class ConfiguracionesDAO {
    
    public ConfiguracionesBean getConfiguraciones(){
            List<Map> result = ConnectionManager.executeQuery("select * from configuraciones");
            ConfiguracionesBean cBean=new ConfiguracionesBean();
            if(result!=null){
                for(Map map:result){
                    cBean.setId_configuracion((String)map.get("id_configuracion"));
                    String certificadoBase64=(String)map.get("certificadoios");
                    cBean.setCertificadoios(certificadoBase64);
                    //Decodificamos el Certificado
                    BASE64Decoder decoder = new BASE64Decoder();
                    byte[] certificado;                    
                    try {
                        certificado = decoder.decodeBuffer(certificadoBase64);
                        cBean.setCertificadoiosDecode(certificado);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        return cBean;
    }    
    
    public boolean addConfiguraciones(ConfiguracionesBean configuraciones){
        boolean status=ConnectionManager.execute("insert into configuraciones(certificadoios) values('"+configuraciones.getCertificadoios()+"')");
        return status;
    }
    
    public boolean updateConfiguraciones(ConfiguracionesBean configuraciones){
        boolean status=ConnectionManager.execute("update configuraciones set certificadoios='"+configuraciones.getCertificadoios()+"' where id_configuracion="+configuraciones.getId_configuracion()+"");
        return status;
    }

}
