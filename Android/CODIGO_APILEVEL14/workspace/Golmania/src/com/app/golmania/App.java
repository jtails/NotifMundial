package com.app.golmania;

import com.app.golmania.R;

import android.app.Activity;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.view.View;
import android.widget.TextView;

/**
 * Clase de utilidades para manegar la APP
 * 
 * @author gsmirandal
 * 
 */
public class App {

	public static final String PREF_GENERALES = "GENERAL_PREFS";
	public static final String APP_ACTIVADA = "APP_ACTIVADA";
	 /**
     * Substitute you own sender ID here. This is the project number you got
     * from the API Console, as described in "Getting Started."
     */
	public static final String SENDER_ID = "956450257964";
	public final static int PLAY_SERVICES_RESOLUTION_REQUEST = 9000;
	public static final String TAG = "com.app.golmania";
	public static final String PROPERTY_REG_ID = "registration_id";
	public static final String PROPERTY_APP_VERSION = "appVersion";
	public static final String USER_PREFERENCES = "USER_PREFERENCES";
	
	public static final String ACTION = "action";
	public static final String ACTUALIZAR_PARTIDO = "P";
	public static final String GOL = "G";
	public static final String ANULACION_GOL = "A";
	public static final String FIN_PARTIDO = "F";
	public static final String PARTIDO = "id";
	public static final String GOLDE="golDe";
	public static final String EQUIPOA="eL";
	public static final String EQUIPOB="eV";	
	public static final String GOLESA="gL";
	public static final String GOLESB="gV";
	public static final String MINUTO="min";
	public static final String FECHAHORA="fHr";
	public static final String LUGAR = "lugar";
	public static final String RONDA = "ronda";
	public static final String ESTATUS_P="eP";
	public static final String NUM_NOTIF="nN";
	
	public static final int NUM_PAGES = 10;
	public static final int SUCCESS = 0;
	public static final int SIN_CONEXION = -101;
	public static final int SIN_GOOGLE_SERVICES = -102;
	public static final int CODIGO_INVALIDO = -103;
	public static final String IMG_SIGUIENTE = "IMG_SIG";
	

	/**
	 * Pone el texto en un textview
	 * 
	 * @param textView
	 * @param text
	 */
	public static void setText(View textView, String text) {
		((TextView) textView).setText(text);
	}

	public static void setTitle(Activity context, String title) {
		App.setText(
				context.getActionBar().getCustomView()
						.findViewById(R.id.menu_titulo), title);
	}

	/**
	 * 
	 * @param findViewById
	 * @return
	 */
	public static String getText(View editText) {
		// TODO Auto-generated method stub
		CharSequence text = ((TextView) editText).getText();
		return text.toString();
	}
	/**
	 * Verifica que la red este disponible
	 * @param context
	 * @return
	 */
	public static boolean redDisponible(Context context){
		ConnectivityManager cm = 
		         (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
		    NetworkInfo netInfo = cm.getActiveNetworkInfo();
		    if (netInfo != null && netInfo.isConnected()) {
		        return true;
		    }
		    return false;
	}
	
}
