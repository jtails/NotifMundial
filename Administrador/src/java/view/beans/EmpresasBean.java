package view.beans;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;


@XmlRootElement(name = "employee")
public class EmpresasBean {

    @XmlElement
    public String getId_empresa() {
        return id_empresa;
    }

    public void setId_empresa(String id_empresa) {
        this.id_empresa = id_empresa;
    }

    @XmlElement
    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }
    
    @XmlElement
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    @XmlElement
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @XmlElement
    public ArrayList<WrapperBean> getImagenes() {
        return imagenes;
    }

    public void setImagenes(ArrayList<WrapperBean> imagenes) {
        this.imagenes = imagenes;
    }
    
    @XmlElement
    public ArrayList<PartidosBean> getPartidos() {
        return partidos;
    }

    public void setPartidos(ArrayList<PartidosBean> partidos) {
        this.partidos = partidos;
    }
  
    public String getLicencias() {
        return licencias;
    }

    public void setLicencias(String licencias) {
        this.licencias = licencias;
    }
    
    public String getNolicencias() {
        return nolicencias;
    }

    public void setNolicencias(String nolicencias) {
        this.nolicencias = nolicencias;
    }
    
    private String status;
    private String id_empresa;
    private String nombre;
    private String codigo;
    private String licencias;
    private String nolicencias;
    
    private ArrayList<WrapperBean> imagenes;
    private ArrayList<PartidosBean> partidos;
    
}
