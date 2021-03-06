package com.app.golmania;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.app.golmania.dto.Equipo;
import com.app.golmania.service.AppServices;

public class GolFragment extends AbstractFragment {

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		onCreateViewRoot(inflater, container, savedInstanceState,
				R.layout.fragment_gol);
		AppServices services = new AppServices(this.activityRoot);
		
		services.setImgFromExternalDir(
				findViewById(R.id.imagen3), "imagen3.png");
		services.setImgFromExternalDir(
		findViewById(R.id.imagenaleatoria),
				new StringBuilder("imagen").append(services.getImagenAleatoria())
						.append(".png").toString());
		
		//add
		services.setImgFromResources(this.findViewById(R.id.golbannerbackimg),R.drawable.gol_bannerback);
		services.setImgFromResources(this.findViewById(R.id.golBackgroundImg),R.drawable.gol_marcadorfull);
		services.setImgFromResources(this.findViewById(R.id.golbanderaimg), R.drawable.gol_bannerbandera);
		
		Bundle bundle = getArguments();
		AppServices appServices = new AppServices(this.activityRoot);
		Equipo golde = appServices.consultaEquipoPorId(bundle
				.getString(App.GOLDE));
		Equipo equipoA = appServices.consultaEquipoPorId(bundle
				.getString(App.EQUIPOA));
		Equipo equipoB = appServices.consultaEquipoPorId(bundle
				.getString(App.EQUIPOB));

		App.setText(findViewById(R.id.golDe),
				golde.getDisplayNombre().concat("!"));
		App.setText(findViewById(R.id.golesEquipoA),
				bundle.getString(App.GOLESA));
		App.setText(findViewById(R.id.golesEquipoB),
				bundle.getString(App.GOLESB));
		App.setText(findViewById(R.id.minuto), bundle.getString(App.MINUTO));
		App.setText(findViewById(R.id.equipoA), equipoA.getDisplayNombre());
		App.setText(findViewById(R.id.equipoB), equipoB.getDisplayNombre());
		// TODO PONER LA BANDER DINAMICAMENTE

		ImageView flagG = (ImageView) viewRoot.findViewById(R.id.flagGolDe);

		int flagimgG = getResources().getIdentifier(golde.getFlag(),
				"drawable", this.getActivity().getPackageName());
		//flagG.setImageResource(flagimgG);
		services.setImgFromResources(flagG, flagimgG);

		ImageView flagA = (ImageView) viewRoot.findViewById(R.id.flagEquipoA);

		int flagimgA = getResources().getIdentifier(equipoA.getFlag(),
				"drawable", this.getActivity().getPackageName());
		//flagA.setImageResource(flagimgA);
		services.setImgFromResources(flagA, flagimgA);

		ImageView flagB = (ImageView) viewRoot.findViewById(R.id.flagEquipoB);

		int flagimgB = getResources().getIdentifier(equipoB.getFlag(),
				"drawable", this.getActivity().getPackageName());
		//flagB.setImageResource(flagimgB);
		services.setImgFromResources(flagB, flagimgB);

		return this.viewRoot;
	}


}
