package com.app.golmania;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.app.golmania.dto.Equipo;
import com.app.golmania.dto.Grupo;
import com.app.golmania.service.AppServices;

/**
 * Fragment that appears in the "content_frame", shows a planet
 */
public class EstadisticasFragment extends AbstractFragment  {

	public static final String INDICE_GRUPO = "ID_GRUPO";
	private static final String TAG = "ESTADISTICAS_FRAGMENT";
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		onCreateViewRoot(inflater, container, savedInstanceState,
				R.layout.fragment_estadisticas);
		
		AppServices services = new AppServices(this.activityRoot);
		services.setImgFromExternalDir(findViewById(R.id.estabackgroundpublicidad),"imagen2.png");
		services.setImgFromResources(findViewById(R.id.estbackalltabla),R.drawable.est_back_all);
		
		
		return viewRoot;
	}

	/**
	 * Clase para capturar el clic de un equipo para presentar el calendario
	 * 
	 * @author gsmirandal
	 * 
	 */
	public class Grupos extends AsyncTask<Void, Void, Void> {
		private int indice;
		private Context context;
		private LayoutInflater inflater;
		public Grupos(Context context,int indice, LayoutInflater inflater){
			this.context = context;
			this.indice = indice;
			this.inflater = inflater;
		}
		@Override
		protected Void doInBackground(Void... params) {
			// TODO Auto-generated method stub
			AppServices appServices = new AppServices(context);		
			
			Grupo grupo = appServices.consultaGrupoPorIndice(indice);
			// poner titulo y nombre de grupo
			// TextView text = (TextView) findViewById(R.id.nombreGrupo);
			// text.setText(grupo.getDisplayNombre());
			getActivity().setTitle(grupo.getDisplayNombre());

			// agregar el template
			LinearLayout linearLayoutPuntos = (LinearLayout) findViewById(R.id.puntosEquipoLayout);

			// agregar layout a su padre
			LinearLayout linearLayoutTabla = ((LinearLayout) findViewById(R.id.est_layout_tabla));

			// crear lista puntos y estadisticos
			int conteo = 0;
			for (Equipo equipo : grupo.getEquipos()) {
				conteo++;
				// por cada equipo crear un layout
				View templatePuntos = inflater.inflate(
						R.layout.template_estadisticos_puntos, linearLayoutPuntos,
						false);
				// LayoutInflater li = (LayoutInflater)
				// getApplicationContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);

				// poner bandera
				ImageView flag = (ImageView) templatePuntos
						.findViewById(R.id.est_puntos_flag);

				Log.d(TAG, templatePuntos.toString());
				// TODO: cambiar por bandera dinamica
				int flagimg = getResources().getIdentifier(equipo.getFlag(), "drawable", this.context.getPackageName());
				flag.setImageResource(flagimg);
				// iv.setImageResource(getResources().getIdentifier("apple",
				// "drawable", getPackageName()));

				// poner nombre
				TextView nombreEquipo = (TextView) templatePuntos
						.findViewById(R.id.est_puntos_nombreEquipo);
				nombreEquipo.setText(equipo.getDisplayNombre());
				// nombreEquipo.setOnClickListener(new
				// EquiposListener(equipo.getId(), this.getActivity()));
				Log.d(TAG, (String) nombreEquipo.getText());

				// poner puntos
				TextView puntosEquipo = (TextView) templatePuntos
						.findViewById(R.id.est_puntos_puntosEquipo);
				puntosEquipo.setText(equipo.getPTS());
				Log.d(TAG, (String) puntosEquipo.getText());

				linearLayoutPuntos.addView(templatePuntos);

				// ESTADISTICOS
				// por cada equipo crear un layout por fila
				LinearLayout row = (LinearLayout) inflater.inflate(
						R.layout.template_estadisticos_row, linearLayoutTabla,
						false);
				TextView view = (TextView) row.findViewById(R.id.est_row_equipo);
				
				App.setText(view, equipo.getNombreCorto());

				App.setText(row.findViewById(R.id.est_row_pj), equipo.getPJ());
				App.setText(row.findViewById(R.id.est_row_pg), equipo.getG());
				App.setText(row.findViewById(R.id.est_row_pe), equipo.getP());
				App.setText(row.findViewById(R.id.est_row_pp), equipo.getE());
				App.setText(row.findViewById(R.id.est_row_gf), equipo.getGF());
				App.setText(row.findViewById(R.id.est_row_gc), equipo.getGC());
				App.setText(row.findViewById(R.id.est_row_pts), equipo.getPTS());

				linearLayoutTabla.addView(row);

			}
			return null;
		}

		

	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onActivityCreated(savedInstanceState);
	}

	@Override
	public void onViewCreated(View view, Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onViewCreated(view, savedInstanceState);
		int indice = getArguments().getInt(INDICE_GRUPO)+1;
		AppServices appServices = new AppServices(this.activityRoot);		
		
		Grupo grupo = appServices.consultaGrupoPorIndice(indice);
		// poner titulo y nombre de grupo
		// TextView text = (TextView) findViewById(R.id.nombreGrupo);
		// text.setText(grupo.getDisplayNombre());
		getActivity().setTitle(grupo.getDisplayNombre());

		// agregar el template
		LinearLayout linearLayoutPuntos = (LinearLayout) findViewById(R.id.puntosEquipoLayout);

		// agregar layout a su padre
		LinearLayout linearLayoutTabla = ((LinearLayout) findViewById(R.id.est_layout_tabla));

		// crear lista puntos y estadisticos
		int conteo = 0;
		LayoutInflater inflater = this.activityRoot.getLayoutInflater();
		for (Equipo equipo : grupo.getEquipos()) {
			conteo++;
			// por cada equipo crear un layout
			View templatePuntos = inflater.inflate(
					R.layout.template_estadisticos_puntos, linearLayoutPuntos,
					false);
			// LayoutInflater li = (LayoutInflater)
			// getApplicationContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			appServices.setImgFromResources(templatePuntos.findViewById(R.id.estbackequipo), R.drawable.est_back_equipo);
			// poner bandera
			ImageView flag = (ImageView) templatePuntos
					.findViewById(R.id.est_puntos_flag);

			Log.d(TAG, templatePuntos.toString());
			// TODO: cambiar por bandera dinamica
			int flagimg = getResources().getIdentifier(equipo.getFlag(), "drawable", this.activityRoot.getPackageName());
			//flag.setImageResource(flagimg);
			appServices.setImgFromResources(flag, flagimg);
			// iv.setImageResource(getResources().getIdentifier("apple",
			// "drawable", getPackageName()));

			// poner nombre
			TextView nombreEquipo = (TextView) templatePuntos
					.findViewById(R.id.est_puntos_nombreEquipo);
			nombreEquipo.setText(equipo.getDisplayNombre());
			// nombreEquipo.setOnClickListener(new
			// EquiposListener(equipo.getId(), this.getActivity()));
			Log.d(TAG, (String) nombreEquipo.getText());

			// poner puntos
			TextView puntosEquipo = (TextView) templatePuntos
					.findViewById(R.id.est_puntos_puntosEquipo);
			puntosEquipo.setText(equipo.getPTS());
			Log.d(TAG, (String) puntosEquipo.getText());

			linearLayoutPuntos.addView(templatePuntos);

			// ESTADISTICOS
			// por cada equipo crear un layout por fila
			LinearLayout row = (LinearLayout) inflater.inflate(
					R.layout.template_estadisticos_row, linearLayoutTabla,
					false);
			TextView viewt = (TextView) row.findViewById(R.id.est_row_equipo);
			
			App.setText(viewt, equipo.getNombreCorto());

			App.setText(row.findViewById(R.id.est_row_pj), equipo.getPJ());
			App.setText(row.findViewById(R.id.est_row_pg), equipo.getG());
			App.setText(row.findViewById(R.id.est_row_pe), equipo.getE());
			App.setText(row.findViewById(R.id.est_row_pp), equipo.getP());
			App.setText(row.findViewById(R.id.est_row_gf), equipo.getGF());
			App.setText(row.findViewById(R.id.est_row_gc), equipo.getGC());
			App.setText(row.findViewById(R.id.est_row_pts), equipo.getPTS());

			linearLayoutTabla.addView(row);
		}
	}

}

