package com.app.golmania.dto;

/**
 * Clase Equipo para tener todos los datos de los estadisticos
 * 
 * @author gsmirandal
 * 
 */
public class Equipo implements Comparable<Equipo> {

	private String id;
	private String displayNombre;
	private String nombreCorto;
	private String PJ;
	private String G;
	private String E;
	private String P;
	private String GF;
	private String GC;
	private String PTS;
	private String flag;

	public Equipo(String id, String displayNombre, String nombreCorto) {
		super();
		this.id = id;
		this.displayNombre = displayNombre;
		this.nombreCorto = nombreCorto;
	}

	/**
	 * Usando todos los fields de la clase
	 * 
	 * @param id
	 * @param displayNombre
	 * @param pJ
	 * @param g
	 * @param e
	 * @param p
	 * @param gF
	 * @param gC
	 * @param pTS
	 */
	public Equipo(String id, String displayNombre, String nombreCorto,
			String pJ, String g, String e, String p, String gF, String gC,
			String pTS, String flag) {
		super();
		this.id = id;
		this.displayNombre = displayNombre;
		this.nombreCorto = nombreCorto;
		PJ = pJ;
		G = g;
		E = e;
		P = p;
		GF = gF;
		GC = gC;
		PTS = pTS;
		this.flag = flag;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getDisplayNombre() {
		return displayNombre;
	}

	public void setDisplayNombre(String displayNombre) {
		this.displayNombre = displayNombre;
	}

	public String getPJ() {
		return PJ == null ? "0" : PJ;
	}

	public void setPJ(String pJ) {
		PJ = pJ;
	}

	public String getG() {
		return G == null ? "0" : G;
	}

	public void setG(String g) {
		G = g;
	}

	public String getE() {
		return E == null ? "0" : E;
	}

	public void setE(String e) {
		E = e;
	}

	public String getP() {
		return P == null ? "0" : P;
	}

	public void setP(String p) {
		P = p;
	}

	public String getGF() {
		return GF == null ? "0" : GF;
	}

	public void setGF(String gF) {
		GF = gF;
	}

	public String getGC() {
		return GC == null ? "0" : GC;
	}

	public void setGC(String gC) {
		GC = gC;
	}

	public String getPTS() {
		return PTS == null ? "0" : PTS;
	}

	public void setPTS(String pTS) {
		PTS = pTS;
	}

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	@Override
	public int compareTo(Equipo another) {
		// TODO Auto-generated method stub
		if (this.PTS == null || another == null || another.getPTS() == null) {
			return 1;
		}
		return this.PTS.compareTo(another.getPTS());
	}

	@Override
	public boolean equals(Object o) {
		// si o es instancia de Equipo y el id propio es diferente de null
		return (o instanceof Equipo & this.id != null) ?
		// son iguales si tienen el mismo id
		this.id.equals(((Equipo) o).getId())
				: false;
	}

	@Override
	public int hashCode() {
		// TODO Auto-generated method stub
		return super.hashCode();
	}

	@Override
	public String toString() {
		return "Equipo [id=" + id + "displayNombre=" + displayNombre + ", PJ="
				+ PJ + ", G=" + G + ", E=" + E + ", P=" + P + ", GF=" + GF
				+ ", GC=" + GC + ", PTS=" + PTS + "]";
	}

	public String getNombreCorto() {
		return nombreCorto;
	}

	public void setNombreCorto(String nombreCorto) {
		this.nombreCorto = nombreCorto;
	}

}
