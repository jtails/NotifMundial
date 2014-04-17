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
import view.beans.EmpresasBean;
import view.beans.WrapperBean;
import view.dao.ConnectionManager;
import view.dao.EmpresasDAO;

@MultipartConfig
public class Empresas extends HttpServlet {
    
    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
    }

    @Override
    public void doPost(HttpServletRequest request,
                       HttpServletResponse response) throws ServletException,
                                                            IOException {   
         
        String opcion=request.getParameter("opcion");
        //Generamos el Bean de la Empresa
        EmpresasBean empresa=new EmpresasBean();
        String id=request.getParameter("id");
        empresa.setId_empresa(id);
        
        if(opcion.equalsIgnoreCase("Agregar")){
            String nombre=request.getParameter("nombre");
            String licencias=request.getParameter("licencias");
            empresa.setNombre(nombre);
            empresa.setCodigo(String.valueOf((int)(Math.random()*50000)));
            empresa.setLicencias(licencias);
            empresa.setImagenes(uploadImages(request));
            boolean status=addEmpresa(empresa);
            if(status)
                request.setAttribute("Status",true);
            else
                request.setAttribute("Status",false);
        }
        
        if(opcion.equalsIgnoreCase("Eliminar")){
            empresa.setImagenes(uploadImages(request));
            boolean status=deleteEmpresa(empresa);
            if(status)
                request.setAttribute("Status",true);
            else
                request.setAttribute("Status",false);
        }
        
        //Consultamos Todos las Empresas
        EmpresasDAO empresasDAO=new EmpresasDAO();
        List<EmpresasBean> empresas=empresasDAO.getEmpresas();
        request.setAttribute("Empresas",empresas);
        
        request.getRequestDispatcher("/WEB-INF/Empresas.jsp").forward(request,response);
    }
    
    //Agregamos la nueva Empresa
    public boolean addEmpresa(EmpresasBean empresa){
        //Inicamos una Transaccion
        ConnectionManager.startTransaction();
        EmpresasDAO empresasDAO=new EmpresasDAO();
        boolean status=empresasDAO.addEmpresa(empresa);
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
    
    
    //Eliminamos la Empresa
    public boolean deleteEmpresa(EmpresasBean empresa){
        //Inicamos una Transaccion
        ConnectionManager.startTransaction();
        EmpresasDAO empresasDAO=new EmpresasDAO();
        boolean status=empresasDAO.deleteEmpresa(empresa);
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
    
    //Obtenemos las Imagenes y las Codificamos en Base64
    public ArrayList<WrapperBean> uploadImages(HttpServletRequest request){
        ArrayList<WrapperBean> images=new ArrayList<WrapperBean>();
        try{
            String patterName="imagen";
            for (int i=1;i<7;i++){
                Part part=request.getPart(patterName+i);
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
                    WrapperBean wrapper=new WrapperBean();
                    wrapper.setData(imageBase64);
                    images.add(wrapper);
                }
            }   
        }catch(Exception e){
            e.printStackTrace();
        }
        return images;
    }
    
}
