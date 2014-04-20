package view.notification.android;

import java.io.Serializable;
import javapns.notification.Payload;

public class GCMRequest extends Payload implements Serializable {
    private static final long serialVersionUID = 1826014008550426786L;
    /**
     * Accion a tomar
     * I  .- Insert  (Inserta un nuevo partido)
     * U  .- Update  (Actualiza el marcador del partido)
     */
    private String action;
    /**
     *  id del partido
     */
    private String idPartido;
    /**
     * id del equipo que anota el gol
     */
    private String golDe;
    /**
     * id de Google para enviar la notificacion
     */
    private String gcmId;
    /**
     * id del equipo Local
     */
    private String equipoLocal;
    /**
     * id del equipo Visitante
     */
    private String equipoVisitante;
    /**
     * goles del equipo Local
     */
    private String golesLocal;
    /**
     * goles del equipo visitante
     */
    private String golesVisitante;
    /**
     * minuto del gol
     */
    private String minuto;
    /**
     * fecha hora del partido
     */
    private String fechaHora;

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getFechaHora() {
        return fechaHora;
    }

    public void setFechaHora(String fechaHora) {
        this.fechaHora = fechaHora;
    }

    public String getLugar() {
        return lugar;
    }

    public void setLugar(String lugar) {
        this.lugar = lugar;
    }

    public String getRonda() {
        return ronda;
    }

    public void setRonda(String ronda) {
        this.ronda = ronda;
    }

    public String getEstatusPartido() {
        return estatusPartido;
    }

    public void setEstatusPartido(String estatusPartido) {
        this.estatusPartido = estatusPartido;
    }
    /**
     * Lugar del partido 
     */
    private String lugar;
    /**
     * Ronda del partido, 1,2,3,--6
     * 1 .- REGULAR
     * 2 .-8vos
     * 3 .-4tos
     * 4 .-Semifinal
     * 5 .-Final
     * 6 .-TercerLugar
     */
    private String ronda;
    /**
     * Estatus del partido 
     * 1.- No iniciado
     * 2.- Iniciado
     * 3.- Terminado
     * 4.- Suspendido
     */
    private String estatusPartido;
    
    private String num_notif;

    public String toString() {
        return "{ \"data\": {\n" +
            "    \"action\": \"" + action + "\",\n" +
            "    \"id\": \"" + idPartido + "\",\n" +
            "    \"golDe\": \"" + golDe + "\",\n" +
            "    \"eL\": \"" + equipoLocal + "\",\n" +
            "    \"eV\": \"" + equipoVisitante + "\",\n" +
            "    \"gL\": \"" + golesLocal + "\",\n" +
            "    \"gV\": \"" + golesVisitante + "\",\n" +
            "    \"min\": \"" + minuto + "\",\n" +
            "    \"fHr\": \"" + fechaHora + "\",\n" +
            "    \"lugar\": \"" + lugar + "\",\n" +
            "    \"ronda\": \"" + ronda + "\",\n" +
            "    \"eP\": \"" + estatusPartido + "\",\n" +
             "    \"nN\": \"" + num_notif + "\",\n" +  
            "  },\n" +
            "  \"registration_ids\": [\"" + gcmId + "\"]\n" +
            "}";
    }

    public void setGcmId(String gcmId) {
        this.gcmId = gcmId;
    }

    public String getGcmId() {
        return gcmId;
    }

    public void setEquipoVisitante(String estatusActivacion) {
        this.equipoVisitante = estatusActivacion;
    }

    public String getEquipoVisitante() {
        return equipoVisitante;
    }

    public void setGolesLocal(String tipoOperacion) {
        this.golesLocal = tipoOperacion;
    }

    public String getGolesLocal() {
        return golesLocal;
    }

    public void setGolDe(String idEquipo) {
        this.golDe = idEquipo;
    }

    public String getGolDe() {
        return golDe;
    }

    public void setMinuto(String minutoZ) {
        this.minuto = minutoZ;
    }

    public String getMinuto() {
        return minuto;
    }

    public void setGolesVisitante(String golesEquipoY) {
        this.golesVisitante = golesEquipoY;
    }

    public String getGolesVisitante() {
        return golesVisitante;
    }

    public void setEquipoLocal(String equipoA) {
        this.equipoLocal = equipoA;
    }

    public String getEquipoLocal() {
        return equipoLocal;
    }

    public void setIdPartido(String idPartido) {
        this.idPartido = idPartido;
    }

    public String getIdPartido() {
        return idPartido;
    }
    
    public String getNum_notif() {
        return num_notif;
    }

    public void setNum_notif(String num_notif) {
        this.num_notif = num_notif;
    }
}
