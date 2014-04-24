package com.app.golmania.dto;

import java.util.ArrayList;
import java.util.List;

/**
 *  Representa un Grupo 
 * @author gsmirandal
 *
 */
public class Grupo {
	/**
	 * identificador del grupo 
	 */
	private String id;
	
	/**
	 * nombre que se muestra en la pantalla
	 */
	private String displayNombre;
	/**
	 * Lista de equipos ordenada por puntos
	 */
	private String shortName;
	
	private List<Equipo> equipos = new ArrayList<Equipo>();
	
	
	
	public Grupo(String id, String displayNombre,String shortName) {
		super();
		this.id = id;
		this.displayNombre = displayNombre;
		this.shortName = shortName;
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
	public List<Equipo> getEquipos() {
		return equipos;
	}
	public void setEquipos(List<Equipo> equipos) {
		this.equipos = equipos;
	}
	/**
	 *  Agrega un equipo al grupo
	 * @param e
	 */
	public void addEquipo(Equipo e){
		this.equipos.add(e);
	}

	@Override
	public String toString() {
		return "Grupo [displayNombre=" + displayNombre + ", equipos=" + equipos
				+ "]";
	}

	public String getShortName() {
		return shortName;
	}

	public void setShortName(String shortName) {
		this.shortName = shortName;
	}
	
	
}
