package view.beans;

import java.util.List;


public class DetailsPartidoBean {

    public String getIdpartido() {
        return idpartido;
    }

    public void setIdpartido(String idpartido) {
        this.idpartido = idpartido;
    }

    public String getGoleslocalteam() {
        return goleslocalteam;
    }

    public void setGoleslocalteam(String goleslocalteam) {
        this.goleslocalteam = goleslocalteam;
    }

    public String getGolesvisitteam() {
        return golesvisitteam;
    }

    public void setGolesvisitteam(String golesvisitteam) {
        this.golesvisitteam = golesvisitteam;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    
    public GolesBean getGoles() {
        return goles;
    }

    public void setGoles(GolesBean goles) {
        this.goles = goles;
    }
    
    public String getNum_notf() {
        return num_notf;
    }

    public void setNum_notf(String num_notf) {
        this.num_notf = num_notf;
    }
    
    private String idpartido;
    private String goleslocalteam;
    private String golesvisitteam;
    private String status;
    private GolesBean goles;
    private String num_notf;
}
