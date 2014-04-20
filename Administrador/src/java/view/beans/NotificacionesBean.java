package view.beans;


public class NotificacionesBean {

    public String getIdnotificacion() {
        return idnotificacion;
    }

    public void setIdnotificacion(String idnotificacion) {
        this.idnotificacion = idnotificacion;
    }

    public String getIdnotif() {
        return idnotif;
    }

    public void setIdnotif(String idnotif) {
        this.idnotif = idnotif;
    }

    public Enum getDispositivo() {
        return dispositivo;
    }

    public void setDispositivo(Enum dispositivo) {
        this.dispositivo = dispositivo;
    }

    public String getId_empresa() {
        return id_empresa;
    }

    public void setId_empresa(String id_empresa) {
        this.id_empresa = id_empresa;
    }
    
    private String idnotificacion;
    private String idnotif;
    private Enum dispositivo;
    private String id_empresa;
}
