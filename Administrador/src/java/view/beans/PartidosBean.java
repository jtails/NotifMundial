package view.beans;

public class PartidosBean {

    public String getIdpartido() {
        return idpartido;
    }

    public void setIdpartido(String idpartido) {
        this.idpartido = idpartido;
    }

    public String getDatetime() {
        return datetime;
    }

    public void setDatetime(String datetime) {
        this.datetime = datetime;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public EquiposBean getIdlocalteam() {
        return idlocalteam;
    }

    public void setIdlocalteam(EquiposBean idlocalteam) {
        this.idlocalteam = idlocalteam;
    }

    public EquiposBean getIdvisitteam() {
        return idvisitteam;
    }

    public void setIdvisitteam(EquiposBean idvisitteam) {
        this.idvisitteam = idvisitteam;
    }

    public String getRonda() {
        return ronda;
    }

    public void setRonda(String ronda) {
        this.ronda = ronda;
    }
    
    public DetailsPartidoBean getDetails() {
        return details;
    }

    public void setDetails(DetailsPartidoBean details) {
        this.details = details;
    }
    
   private String idpartido;
   private String datetime;
   private String place;
   private EquiposBean idlocalteam;
   private EquiposBean idvisitteam;
   private String ronda;
   private DetailsPartidoBean details;

}
