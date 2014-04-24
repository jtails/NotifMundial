package com.app.golmania;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TextView.OnEditorActionListener;

import com.app.golmania.service.AppServices;
import com.app.golmania.service.GoogleServicesException;
import com.app.golmania.R;
import com.google.android.gms.common.GooglePlayServicesUtil;

public class LoginActivity extends Activity implements OnClickListener {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		AppServices services = new AppServices(this);
		if (services.appActivada()) {
			// navega a main activity
			Intent intent = new Intent(this, MainActivity.class);
			startActivity(intent);
			this.finish();
		}
		EditText text = (EditText) findViewById(R.id.login_ingrese_codigo);
		text.setOnEditorActionListener( new OnEditorActionListener()
	    {

		@Override
		public boolean onEditorAction(TextView arg0, int arg1, KeyEvent arg2) {
			// TODO Auto-generated method stub
			validaApp();
			return false;
		}
	    });

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.login, menu);
		return true;
	}

	class LoginService extends AsyncTask<Void, Void, Integer> {
		private static final String TAG = "LoginService";
		private String codigoEmpresa;

		public LoginService(String codigoEmpresa) {
			this.codigoEmpresa = codigoEmpresa;
		}

		private ProgressDialog progDailog;

		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			progDailog = new ProgressDialog(LoginActivity.this);
			progDailog.setMessage("Registrando...");
			progDailog.show();
		}

		@Override
		protected Integer doInBackground(Void... params) {
			int intentos = 0;
			AppServices services = new AppServices(LoginActivity.this);
			class ChangeMessage implements Runnable {
				private String message;

				ChangeMessage(String message) {
					this.message = message;
				}

				@Override
				public void run() {
					// Log.v(TAG, strCharacters);
					progDailog.setMessage(message);
				}
			}

			do {
				if (!App.redDisponible(LoginActivity.this)) {
					runOnUiThread(new ChangeMessage("Red no disponible..."));
				} else {

					String gcmd;
					try {
						gcmd = services.obtenerORegsitrarGCMID();
						if (gcmd != null) {
							if (services.activarApp(codigoEmpresa, gcmd)) {
								runOnUiThread(new ChangeMessage("Activada..."));
								return App.SUCCESS;
							}else{
								return App.CODIGO_INVALIDO;
							}
						}
					} catch (final GoogleServicesException e) {
						if (GooglePlayServicesUtil.isUserRecoverableError(e
								.getErrorCode())) {
							Log.i(TAG, "resulcode: " + e.toString());
							LoginActivity.this.runOnUiThread(new Runnable() {
								public void run() {
									GooglePlayServicesUtil
											.getErrorDialog(
													e.getErrorCode(),
													LoginActivity.this,
													App.PLAY_SERVICES_RESOLUTION_REQUEST)
											.show();
								}
							});
						} else {
							LoginActivity.this.runOnUiThread(new Runnable() {

								@Override
								public void run() {
									AlertDialog.Builder builder1 = new AlertDialog.Builder(
											LoginActivity.this);
									builder1.setMessage("Dispositivo no soporta Google Play Services");
									builder1.setCancelable(true);
									builder1.setPositiveButton(
											"OK",
											new DialogInterface.OnClickListener() {
												public void onClick(
														DialogInterface dialog,
														int id) {
													dialog.cancel();
												}
											});

									AlertDialog alert11 = builder1.create();
									alert11.show();

								}
							});
						}
						return  App.SIN_GOOGLE_SERVICES;
					}

				}

				intentos++;
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} while (intentos < 3);
			return App.SIN_CONEXION;
		}

		@Override
		protected void onPostExecute(Integer aprobado) {
			super.onPostExecute(aprobado);
			switch (aprobado) {
			case App.SUCCESS:
				// navega a main activity
				Intent intent = new Intent(LoginActivity.this,
						MainActivity.class);
				startActivity(intent);
				progDailog.dismiss();
				LoginActivity.this.finish();
				break;
			case App.SIN_CONEXION:
				showDialogOk("No pudimos establecer conexi�n,intenta nuevamente");
				progDailog.dismiss();
				break;
			case App.CODIGO_INVALIDO:
					showDialogOk("C�digo inv�lido");
					progDailog.dismiss();
					break;
			case App.SIN_GOOGLE_SERVICES:
				progDailog.dismiss();
				break;
			}
		}

	}


	private void showDialogOk(String mensaje) {

		AlertDialog.Builder builder1 = new AlertDialog.Builder(this);
		builder1.setMessage(mensaje);
		builder1.setCancelable(true);
		builder1.setPositiveButton("OK", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int id) {
				dialog.cancel();
			}
		});

		AlertDialog alert11 = builder1.create();
		alert11.show();
	}

	@Override
	public void onClick(View arg0) {
		validaApp();
		
	}
	
	private void validaApp(){
		String codigoEmpresa = App
				.getText(findViewById(R.id.login_ingrese_codigo));
		if (codigoEmpresa == null || codigoEmpresa.trim().isEmpty()) {
			showDialogOk("Ingrese un c�digo v�lido");
		} else {
			new LoginService(codigoEmpresa).execute();
		}
	}
	
	
}
