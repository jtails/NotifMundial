package com.app.golmania;

import android.content.res.Resources;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridLayout;
import android.widget.TextView;

import com.app.golmania.service.AppServices;
import com.app.golmania.R;

public class GruposFragment extends AbstractFragment {

    
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		onCreateViewRoot(inflater, container, savedInstanceState,R.layout.fragment_grupos);
		
		
		//obtener lista de equipos por grupo
		Resources res = getResources();
		
		this.agregarEquipo(res.getStringArray(R.array.grupo_a_array), R.id.gpoGridGrupoA);
		this.agregarEquipo(res.getStringArray(R.array.grupo_b_array), R.id.gpoGridGrupoB);
		this.agregarEquipo(res.getStringArray(R.array.grupo_c_array), R.id.grid_grupoC);
		this.agregarEquipo(res.getStringArray(R.array.grupo_d_array), R.id.grid_grupoD);
		this.agregarEquipo(res.getStringArray(R.array.grupo_e_array), R.id.grid_grupoE);
		this.agregarEquipo(res.getStringArray(R.array.grupo_f_array), R.id.grid_grupoF);
		this.agregarEquipo(res.getStringArray(R.array.grupo_g_array), R.id.grid_grupoG);
		this.agregarEquipo(res.getStringArray(R.array.grupo_h_array), R.id.grid_grupoH);
		
		//findViewById(R.id.imagen1).set
		new AppServices(this.activityRoot).setImgFromExternalDir(findViewById(R.id.gposbackpublicidad),"imagen1.png");
	
		
		return viewRoot;

		
	}
	/**
	 *  Agrega los equipos a cada grid Layout
	 * @param equipos
	 */
	private void agregarEquipo(String[] equipos, int idLayout){
		GridLayout gl = (GridLayout)findViewById(idLayout);
		LayoutInflater inflater = LayoutInflater.from(this.activityRoot);
		
		for(String equipo: equipos){
			TextView text = (TextView) inflater.inflate(R.layout.template_grupos_equipo, gl, false);
			text.setText(equipo);
			//text.setTextAppearance(this, R.style.LabelEquipos);
			gl.addView(text);
			/*View view = inflater.inflate(R.layout.template_grupos_equipo, null, false);
			Utilities.setText(view.findViewById(R.id.gpoFramLabelEq), equipo);
			gl.addView(view);*/
			
		}
		
	}



	
	
}
