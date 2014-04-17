package view.beans;

public class TPosicionesBean {

    public String getId_grupo() {
        return id_grupo;
    }

    public void setId_grupo(String id_grupo) {
        this.id_grupo = id_grupo;
    }

    public String getId_equipo() {
        return id_equipo;
    }

    public void setId_equipo(String id_equipo) {
        this.id_equipo = id_equipo;
    }

    public String getJugados() {
        return jugados;
    }

    public void setJugados(String jugados) {
        this.jugados = jugados;
    }

    public String getGanados() {
        return ganados;
    }

    public void setGanados(String ganados) {
        this.ganados = ganados;
    }

    public String getEmpatados() {
        return empatados;
    }

    public void setEmpatados(String empatados) {
        this.empatados = empatados;
    }

    public String getPerdidos() {
        return perdidos;
    }

    public void setPerdidos(String perdidos) {
        this.perdidos = perdidos;
    }

    public String getGfavor() {
        return gfavor;
    }

    public void setGfavor(String gfavor) {
        this.gfavor = gfavor;
    }

    public String getGcontra() {
        return gcontra;
    }

    public void setGcontra(String gcontra) {
        this.gcontra = gcontra;
    }

    public String getPuntos() {
        return puntos;
    }

    public void setPuntos(String puntos) {
        this.puntos = puntos;
    }

    public String getDifgoles() {
        return difgoles;
    }

    public void setDifgoles(String difgoles) {
        this.difgoles = difgoles;
    }
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
   private String id_grupo;
   private String id_equipo;
   private String name;
   private String jugados;
   private String ganados;
   private String empatados;
   private String perdidos;
   private String gfavor;
   private String gcontra;
   private String puntos;
   private String difgoles;
    
}
