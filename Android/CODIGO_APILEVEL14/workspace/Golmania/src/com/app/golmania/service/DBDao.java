package com.app.golmania.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.app.golmania.dto.Equipo;
import com.app.golmania.dto.Grupo;
import com.app.golmania.dto.Partido;

public class DBDao extends SQLiteOpenHelper {

	private static final String DATABASE_NAME = "appmundial";
	private static int VERSION = 1;
	private static final String SCRIPT = "appmundial.sql";
	private static final String FIND_GRUPO_BY_DISPL = "select  G.id_grupo,G.display_name,short_name from grupos G where UPPER(G.display_name) = ?";
	private static final String FIND_GRUPO_BY_ID = "select E.idgrupo,G.display_name,G.short_name,E.idequipo,E.displayname,E.short_name,E.pathFlag,E.jugados,E.ganados,E.empatados,E.perdidos,E.gf,E.gc,E.pts,E.dif from estadisticas E join grupos G on E.idgrupo = G.id_grupo where E.idgrupo = ?";
	private static final String UPDATE_PARTIDO = "update partidos set place=?, idlocalteam=?,idvisitteam =?,ronda=?,date_time=? where id_partido = ?";
	private static final String UPDATE_DETAILS_PARTIDO = "update details_partidos set goleslocalteam=?,golesvisitteam=?,notificacion=?,status=? where id_partido=?";
	private static final String ACTUALIZA_ESTATUS = "update details_partidos set status=? where id_partido=?";
	private static final String FIND_EQUIPO_BY_ID = "select id_equipo,id_grupo,display_name,short_name,pathFlag from equipos where id_equipo = ?";
	private static final String FIND_PARTIDOS_ORDENADOS  ="select P.id_partido,P.date_time from partidos P order by P.orden asc,P.id_partido asc";
	private static final String FIND_PARTIDOS_PAGINA ="select P.id_partido,P.date_time,P.place,P.ronda,DP.goleslocalteam,DP.golesvisitteam,EL.id_equipo local,EL.pathFlag,EV.id_equipo local,EV.pathFlag,DP.status,EL.display_name,EV.display_name from partidos P inner join equipos EL on P.idlocalteam = EL.id_equipo inner join equipos EV on P.idvisitteam = EV.id_equipo inner join details_partidos DP on P.id_partido = DP.id_partido where P.id_partido in (?,?,?,?,?,?,?)";
	private static final String FIND_NM_POR_PARTIDO = "select notificacion from details_partidos where id_partido = ?";
	private static final String UPDATE_MARCADOR = "update details_partidos set goleslocalteam=?,golesvisitteam=?,notificacion=? where id_partido=?";
	private static final String TAG = "DB";
			
	private Context context;

	/**
	 * Constructor
	 * 
	 * @param context
	 * @param name
	 * @param factory
	 * @param version
	 */
	public DBDao(Context context) {
		super(context, DATABASE_NAME, null, VERSION);
		this.context = context;

	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		// crear la base desde el script
		try {
			InputStream is = this.context.getAssets().open(SCRIPT);
			InputStreamReader isr = new InputStreamReader(is);
			BufferedReader reader = new BufferedReader(isr);
			for (String line; (line = reader.readLine()) != null;) {
				db.execSQL(line);
				Log.i(TAG, line);
			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void onUpgrade(SQLiteDatabase arg0, int arg1, int arg2) {
		// TODO Auto-generated method stub

	}

	/**
	 * 
	 * @param nombreGrupo
	 *            A,B,C
	 * @return
	 */
	public Grupo consultaGrupoPorId(int idGrupo) {
		SQLiteDatabase db = this.getReadableDatabase();
		Cursor c = db.rawQuery(FIND_GRUPO_BY_ID, new String[] { idGrupo
				+ "" });
		Grupo grupo = null;
		List<Equipo> equipos = new ArrayList<Equipo>();

		if (c != null) {
			
			Equipo e = null;
			//llenamos los equipos
			for (boolean hasItem = c.moveToFirst(); 
				     hasItem; 
				     hasItem = c.moveToNext()) {
				
				e = new Equipo(c.getString(3),c.getString(4),c.getString(5),c.getString(7),c.getString(8),c.getString(9),c.getString(10),c.getString(11),c.getString(12),c.getString(13),c.getString(6));
				equipos.add(e);
				
			}
			c.moveToFirst();
			grupo = new Grupo(c.getString(0), c.getString(1),
					c.getString(2));
			grupo.setEquipos(equipos);
			c.close();
		}
		db.close();

		return grupo;
	}

	
	
	/**
	 * 
	 * @param id
	 * @return
	 */
	public Equipo consultaEquipoPorId(String id){
		SQLiteDatabase db = this.getReadableDatabase();
		Cursor cursor = db.rawQuery(FIND_EQUIPO_BY_ID, new String[] {id});
		Equipo equipo = null;

		if (cursor != null) {
			cursor.moveToFirst();
			equipo = new Equipo(id,cursor.getString(2),cursor.getString(3));
			equipo.setFlag(cursor.getString(4));
		}
		db.close();
		return equipo;
	}
	
	/**
	 * 
	 * @param idPartido
	 * @param idLocal
	 * @param idVisitante
	 * @param fechaHora
	 * @param lugar
	 * @param ronda
	 * @param estatusP
	 */
	public void modificarPartido(String idPartido, String idLocal,
			String idVisitante, String fechaHora, String lugar, String ronda) {
		SQLiteDatabase db = this.getWritableDatabase();
		//llega dia/mes/anio
		db.execSQL(UPDATE_PARTIDO, new String[]{lugar,idLocal,idVisitante,ronda,fechaHora,idPartido});
		
		db.close();
	}

	/**
	 * 
	 * @param idPartido
	 * @param golesL
	 * @param golesV
	 * @param nN 
	 */
	public void actualizaMarcador(String idPartido, String golesL, String golesV, int nN) {
		// TODO Auto-generated method stub
		SQLiteDatabase db = this.getWritableDatabase();
		//update details_partidos set goleslocalteam=?,golesvisitteam=?,notificacion=?,status=? where id_partido=?
		db.execSQL(UPDATE_MARCADOR, new String[]{golesL,golesV,nN+"",idPartido});
		db.close();
	}
	
	public void actualizaDetails(String idPartido, String golesL, String golesV, int nN,String status){
		SQLiteDatabase db = this.getWritableDatabase();
		//update details_partidos set goleslocalteam=?,golesvisitteam=?,notificacion=?,status=? where id_partido=?
		db.execSQL(UPDATE_DETAILS_PARTIDO, new String[]{golesL,golesV,nN+"",status,idPartido});
		db.close();
	}
	
	/**
	 * Actualiza el status
	 * @param estatus
	 */
	public void actualizarEstatus(String idPartido,String estatus){
		SQLiteDatabase db = this.getWritableDatabase();
		db.execSQL(ACTUALIZA_ESTATUS, new String[]{estatus,idPartido});
		db.close();
	}
	/**
	 * Consulta los partidos ordenados cronologicamente
	 * @return
	 */
	public List<Partido> consultaPartidos(){
		SQLiteDatabase db = this.getReadableDatabase();
		Cursor cursor = db.rawQuery(FIND_PARTIDOS_ORDENADOS,new String[]{});
		Partido partido = null;
		List<Partido> partidos = new ArrayList<Partido>();
		
		for (; cursor.moveToNext();) {
			partido = new Partido(cursor.getString(0),cursor.getString(1),null);
			partidos.add(partido);
		}
		db.close();
		return partidos;
	}
	
	public List<Partido> consultaPartidosPorId(String[] ids){
		SQLiteDatabase db = this.getReadableDatabase();
		Cursor c = db.rawQuery(FIND_PARTIDOS_PAGINA,ids);
		
		
		List<Partido> partidos = new ArrayList<Partido>();
		
		for (; c.moveToNext();) {
			Partido partido = null;
			partido = new Partido(c.getString(0),c.getString(1),c.getString(2),c.getString(4),c.getString(5),c.getInt(3));
			Equipo local = new Equipo(c.getString(6),c.getString(11),"");
			local.setFlag(c.getString(7));
			Equipo visita = new Equipo(c.getString(8),c.getString(12),"");
			visita.setFlag(c.getString(9));
			
			partido.setEquipoLocal(local);
			partido.setEquipoVisitante(visita);
			partido.setStatus(c.getInt(10));
			partidos.add(partido);
		}
		c.close();
		db.close();
		return partidos;
	}
	/**
	 * Consulta grupo por nombre
	 * @param displayName
	 * @return
	 */
	public Grupo consultaGrupoPorNombre(String displayName) {
		SQLiteDatabase db = this.getReadableDatabase();
		Cursor c = db.rawQuery(FIND_GRUPO_BY_DISPL, new String[] { displayName });
		Grupo grupo = null;

		if (c != null) {
			
			c.moveToFirst();
			grupo = new Grupo(c.getString(0), c.getString(1),
					c.getString(2));
			c.close();
		}
		db.close();
		return grupo;
	}
	/**
	 * 
	 * @param nN
	 * @return
	 */
	public int consultaNotificacionPorPartido(String idpartido) {
		SQLiteDatabase db = this.getReadableDatabase();
		Cursor c = db.rawQuery(FIND_NM_POR_PARTIDO, new String[] { idpartido });
		int nm = 0;
		if (c != null) {
			
			c.moveToFirst();
			nm = c.getInt(0);
		}
		db.close();
		return nm;
	}
	

}
