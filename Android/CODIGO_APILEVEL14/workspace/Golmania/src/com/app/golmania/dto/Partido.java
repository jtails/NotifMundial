package com.app.golmania.dto;

/**
 * Representa un partido
 * 
 * @author gsmirandal
 * 
 */
public class Partido {
	public static final int TERMINADO = 3;
	private String idPartido;
	private String fechaHora;
	private String lugarPartido;
	private String golesLocal;
	private String golesVisitante;
	private Equipo equipoLocal;
	private Equipo equipoVisitante;
	private int ronda;
	private int status;
	private int numnotif;

	/**
	 * Constructor con todos los argumentos
	 * 
	 * @param fechaHora
	 * @param lugarPartido
	 * @param vsEquipo
	 * @param golesEquipoA
	 * @param golesEquipoB
	 */
	public Partido(String fechaHora, String lugarPartido, String golesEquipoA,
			String golesEquipoB) {
		super();
		this.fechaHora = fechaHora;
		this.lugarPartido = lugarPartido;
		this.golesLocal = golesEquipoA;
		this.golesVisitante = golesEquipoB;
	}
	
	

	public Partido(String idPartido, String fechaHora, String lugarPartido,
			String golesLocal, String golesVisitante, int ronda) {
		super();
		this.idPartido = idPartido;
		this.fechaHora = fechaHora;
		this.lugarPartido = lugarPartido;
		this.golesLocal = golesLocal;
		this.golesVisitante = golesVisitante;
		this.ronda = ronda;
	}



	/**
	 * Constructor basico
	 * 
	 * @param fechaHora
	 * @param lugarPartido
	 * @param vsEquipo
	 */
	public Partido(String fechaHora, String lugarPartido) {
		super();
		this.fechaHora = fechaHora;
		this.lugarPartido = lugarPartido;
	}

	public Partido(String idPartido, String fechaHora, String lugarPartido) {
		super();
		this.idPartido = idPartido;
		this.fechaHora = fechaHora;
		this.lugarPartido = lugarPartido;
	}

	public String getFechaHora() {
		return fechaHora;
	}

	public String getFecha( ) {
		String f[] = fechaHora.split("/");
		return f[0].concat("/").concat(f[1]);
	}
	
	public String getHora(){
		return this.fechaHora != null ? fechaHora.split(" ")[1] : "";
	}

	public String getLugarPartido() {
		return lugarPartido;
	}

	public void setLugarPartido(String lugarPartido) {
		this.lugarPartido = lugarPartido;
	}

	public String getGolesEquipoA() {
		return golesLocal;
	}

	public void setGolesEquipoA(String golesEquipoA) {
		this.golesLocal = golesEquipoA;
	}

	public String getGolesEquipoB() {
		return golesVisitante;
	}

	public void setGolesEquipoB(String golesEquipoB) {
		this.golesVisitante = golesEquipoB;
	}

	public String getIdPartido() {
		return idPartido;
	}

	public void setIdPartido(String idPartido) {
		this.idPartido = idPartido;
	}

	public int getRonda() {
		return ronda;
	}

	public void setRonda(int ronda) {
		this.ronda = ronda;
	}

	public Equipo getEquipoVisitante() {
		return equipoVisitante;
	}

	public void setEquipoVisitante(Equipo equipoVisitante) {
		this.equipoVisitante = equipoVisitante;
	}

	public Equipo getEquipoLocal() {
		return equipoLocal;
	}

	public void setEquipoLocal(Equipo equipoLocal) {
		this.equipoLocal = equipoLocal;
	}



	public int getStatus() {
		return status;
	}



	public void setStatus(int status) {
		this.status = status;
	}



	public String getGolesLocal() {
		return golesLocal;
	}



	public void setGolesLocal(String golesLocal) {
		this.golesLocal = golesLocal;
	}



	public String getGolesVisitante() {
		return golesVisitante;
	}



	public void setGolesVisitante(String golesVisitante) {
		this.golesVisitante = golesVisitante;
	}



	public int getNumnotif() {
		return numnotif;
	}



	public void setNumnotif(int numnotif) {
		this.numnotif = numnotif;
	}



	public void setFechaHora(String fechaHora) {
		this.fechaHora = fechaHora;
	}



	@Override
	public String toString() {
		return "Partido [idPartido=" + idPartido + ", fechaHora=" + fechaHora
				+ ", lugarPartido=" + lugarPartido + ", golesLocal="
				+ golesLocal + ", golesVisitante=" + golesVisitante
				+ ", equipoLocal=" + equipoLocal + ", equipoVisitante="
				+ equipoVisitante + ", ronda=" + ronda + ", status=" + status
				+ ", numnotif=" + numnotif + "]";
	}

}
