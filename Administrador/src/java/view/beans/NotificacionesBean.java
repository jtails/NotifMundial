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

    public String getTipodisp() {
        return tipodisp;
    }

    public void setTipodisp(String tipodisp) {
        this.tipodisp = tipodisp;
    }

    public String getId_empresa() {
        return id_empresa;
    }

    public void setId_empresa(String id_empresa) {
        this.id_empresa = id_empresa;
    }
    
    private String idnotificacion;
    private String idnotif;
    private String tipodisp;
    private String id_empresa;
}
