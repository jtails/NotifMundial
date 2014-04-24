package com.app.golmania;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.app.golmania.dto.PaginaPartido;
import com.app.golmania.dto.Partido;
import com.app.golmania.service.AppServices;
import com.app.golmania.R;

public class CalendarioFragment extends Fragment {
	public static final String PAGINA = "PAGINA";

	/**
	 * Para enviar el nombre del equipo para consultar
	 */
	public static String ARG_EQUIPO = "ARG_EQUIPO";

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		ViewGroup rootView = (ViewGroup) inflater.inflate(
				R.layout.fragment_calendario, container, false);
		AppServices services = new AppServices(super.getActivity());
		int pagina = getArguments().getInt(PAGINA, 0);
		
		PaginaPartido paginaPartido = services
				.consultaPaginaPartidosPorPag(pagina);

		// por cada partido entonces crear layout e ir agregando LinearLayou
		String fechaTitulo = paginaPartido.getPartidos().get(0).getFecha();
		//App.setTitle(getActivity(), fechaTitulo);
		boolean faltantes = 7 - paginaPartido.getPartidos().size() > 0 ? true :false;
		
		for (Partido partido : paginaPartido.getPartidos()) {
			//si hay una nueva fecha entonces agregar un divisor
			if(!fechaTitulo.equals(partido.getFecha())){
				View view = inflater.inflate(
						R.layout.template_calendario_divisor, rootView, false);
				App.setText(view.findViewById(R.id.calendario_divisor_titulo), partido.getFecha());
				rootView.addView(view);
				fechaTitulo = partido.getFecha();
			}
			View viewRowPartido = null;
			if(partido.getEquipoLocal().getFlag().equals("")){
				
				viewRowPartido = inflater.inflate(
						R.layout.template_calendario_sinbanderas, rootView, false);
				// obtener el view y llenar dinamicamente View view =
				services.setImgFromResources(viewRowPartido.findViewById(R.id.calrowsbimg), R.drawable.cal_row);
				
				TextView flagEquipoA = (TextView) viewRowPartido
						.findViewById(R.id.calendario_img_equipo_local);
				// TODO: CAMBIAR POR BANDERAS DINAMICAS
				App.setText(flagEquipoA, partido.getEquipoLocal().getDisplayNombre());
				
				TextView flagEquipoB = (TextView) viewRowPartido
						.findViewById(R.id.calendario_img_equipo_visitante);
				// TODO: CAMBIAR POR BANDERAS DINAMICAS
				App.setText(flagEquipoB, partido.getEquipoVisitante().getDisplayNombre());
			}else{
				viewRowPartido = inflater.inflate(
						R.layout.template_calendario_partido, rootView, false);
				// obtener el view y llenar dinamicamente View view =
				//services.setImgFromResources(viewRowPartido.findViewById(R.id.calrowimg), R.drawable.cal_row);

				ImageView flagEquipoA = (ImageView) viewRowPartido
						.findViewById(R.id.calendario_img_equipo_local);
				// TODO: CAMBIAR POR BANDERAS DINAMICAS
				int idflagA = getResources().getIdentifier(partido.getEquipoLocal().getFlag(), "drawable", this.getActivity().getPackageName());
				flagEquipoA.setImageResource(idflagA);
				//services.setImgFromResources(flagEquipoA, idflagA);
				
				int idflagB = getResources().getIdentifier(partido.getEquipoVisitante().getFlag(), "drawable", this.getActivity().getPackageName());
				ImageView flagEquipoB = (ImageView) viewRowPartido
						.findViewById(R.id.calendario_img_equipo_visitante);
				// TODO: CAMBIAR POR BANDERAS DINAMICAS
				flagEquipoB.setImageResource(idflagB);
				//services.setImgFromResources(flagEquipoB, idflagB);
			}
		

			// horario
			TextView horario = (TextView) viewRowPartido
					.findViewById(R.id.calendario_horario);
			horario.setText(partido.getHora());

			// fecha
			TextView fecha = (TextView) viewRowPartido
					.findViewById(R.id.calendario_fecha);
			fecha.setText(partido.getFecha());

			// lugar
			TextView lugar = (TextView) viewRowPartido
					.findViewById(R.id.calendario_lugarPartido);
			lugar.setText(partido.getLugarPartido());

			// goles local
			TextView golesLocal = (TextView) viewRowPartido
					.findViewById(R.id.calendario_golesLocal);
			golesLocal.setText(partido.getStatus() == Partido.TERMINADO ? partido.getGolesEquipoA(): "-");

			// goles visita
			TextView golesVisita = (TextView) viewRowPartido
					.findViewById(R.id.calendario_golesVisitante);
			golesVisita.setText(partido.getStatus() == Partido.TERMINADO ?  partido.getGolesEquipoB(): "-");

			// agregar view
			rootView.addView(viewRowPartido);

		}
		if(faltantes){
			View view = inflater.inflate(
					R.layout.template_calendario_relleno, rootView, false);
			rootView.addView(view);
		}
		

		return rootView;
	}


}
