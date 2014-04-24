package com.app.golmania.dto;

import java.util.List;

public class PaginaPartido {
	private int pagina;
	private List<Partido> partidos;

	public int getPagina() {
		return pagina;
	}

	public void setPagina(int pagina) {
		this.pagina = pagina;
	}

	public List<Partido> getPartidos() {
		return partidos;
	}

	public void setPartidos(List<Partido> partidos) {
		this.partidos = partidos;
	}

}
