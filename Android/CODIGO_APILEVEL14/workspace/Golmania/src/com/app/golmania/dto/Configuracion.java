package com.app.golmania.dto;

/**
 * Clase que transporta la configuracion de la empresa en la APP
 * @author gsmirandal
 *
 */
public class Configuracion {
	private String codigo;
	private String idEmpresa;
	private String nombre;
	private String imagenes[];
	private String partidos[];
	private String status;
	
	
	/**
	 * Constructor minimo
	 * @param codigo
	 * @param idEmpresa
	 * @param nombre
	 * @param estatus
	 */
	public Configuracion(String codigo, String idEmpresa, String nombre) {
		super();
		this.codigo = codigo;
		this.idEmpresa = idEmpresa;
		this.nombre = nombre;
		this.setImagenes(new String[6]);
	}
	public String getCodigo() {
		return codigo;
	}
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	public String getIdEmpresa() {
		return idEmpresa;
	}
	public void setIdEmpresa(String idEmpresa) {
		this.idEmpresa = idEmpresa;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String[] getImagenes() {
		return imagenes;
	}
	public void setImagenes(String imagenes[]) {
		this.imagenes = imagenes;
	}
	public String[] getPartidos() {
		return partidos;
	}
	public void setPartidos(String[] partidos) {
		this.partidos = partidos;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}

	
	
}
