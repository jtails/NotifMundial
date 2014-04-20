package view.servlets;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.*;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.*;
import org.apache.commons.io.IOUtils;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;
import view.beans.ConfiguracionesBean;
import view.beans.EmpresasBean;
import view.beans.WrapperBean;
import view.dao.ConfiguracionesDAO;
import view.dao.ConnectionManager;
import view.dao.EmpresasDAO;

@MultipartConfig
public class Configuraciones extends HttpServlet {
    
    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
    }

    @Override
    public void doPost(HttpServletRequest request,
                       HttpServletResponse response) throws ServletException,
                                                            IOException {   
         
        String opcion=request.getParameter("opcion");
        //Generamos el Bean de la Configuracion
        ConfiguracionesBean configuraciones=new ConfiguracionesBean();
        
        if(opcion.equalsIgnoreCase("Agregar")){
            configuraciones.setCertificadoios(uploadCertificado(request));
            boolean status=upsertConfiguraciones(configuraciones);
            if(status)
                request.setAttribute("Status",true);
            else
                request.setAttribute("Status",false);
        }
        
        //Consultamos las Configuraciones
        ConfiguracionesDAO configuracionesDAO=new ConfiguracionesDAO();
        configuraciones=configuracionesDAO.getConfiguraciones();
        request.setAttribute("Configuraciones",configuraciones);
        
        request.getRequestDispatcher("/WEB-INF/Configuraciones.jsp").forward(request,response);
    }
    
    //Agregamos o Modificamos la Configuracion
    public boolean upsertConfiguraciones(ConfiguracionesBean configuraciones){
        //Inicamos una Transaccion
        ConnectionManager.startTransaction();
        ConfiguracionesDAO configuracionesDAO=new ConfiguracionesDAO();
        ConfiguracionesBean cBean=configuracionesDAO.getConfiguraciones();
        boolean status=false;
        if(cBean.getId_configuracion()==null)
            status=configuracionesDAO.addConfiguraciones(configuraciones);
        else{
            configuraciones.setId_configuracion(cBean.getId_configuracion());
            status=configuracionesDAO.updateConfiguraciones(configuraciones);
        }
        //Confirmamos la Transaccion
        if(status){
            ConnectionManager.commit();
            return true;
        }
        else{
            ConnectionManager.rollback();
            return false;
        }
    }
   
    
    //Obtenemos el Certificado y lo Codificamos en Base64
    public String uploadCertificado(HttpServletRequest request){
        try{
            for (int i=1;i<7;i++){
                Part part=request.getPart("certificado");
                String name = part.getName();
                InputStream is = part.getInputStream();
                BASE64Encoder encoder = new BASE64Encoder();
                byte[] ios=IOUtils.toByteArray(is);
                //Verificamos que la longuitud del Objeto ios sea mayor a 100 para estar seguros que es un archivo
                if(ios.length>100){
                    String imageBase64=encoder.encode(ios);
                    is.close();
                    //Guardamos el Archivo en Disco
                    /*BASE64Decoder decoder = new BASE64Decoder();
                    FileOutputStream fo=new FileOutputStream("d:\\"+patterName+i+".png");
                    fo.write(decoder.decodeBuffer(imageBase64));
                    fo.close();*/
                    return imageBase64;
                }
            }   
        }catch(Exception e){
            e.printStackTrace();
        }
        return "";
    }
    
}
