package com.app.golmania.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

import android.util.Log;

import com.app.golmania.dto.Configuracion;

/**
 * Realiza peticiones a webservices
 * 
 * @author gsmirandal
 * 
 */
public class WSDao {
	// Metodo que queremos ejecutar en el servicio web
	private static final String metodo = "getDeatilsEmpresa";
	// Namespace definido en el servicio web
	private static final String namespace = "http://impl.webservices.view/";
	// namespace + metodo
	private static final String accion = "";
	// Fichero de definicion del servcio web
	private static final String url = "http://cherry-sundae-4319.herokuapp.com:80/EmpresasWSImpl?WSDL";
	//http://notzuca.herokuapp.com/rest/EmpresasWS/json/getDetails/codigo/iddisp/IOS
	private static final String urlJSON = "http://notzuca.herokuapp.com/rest/EmpresasWS/json/getDetails/"; // SnVtYmE=/1/1";
	private static final String TAG = "WEBSERVICE";

	/**
	 * Making service call
	 * 
	 * @url - url to make request
	 * @method - http request method
	 * @params - http request params
	 * */
	public String consultaConfiguracionHTTPUtil(String codigo, String notifId) {

		// http client
		DefaultHttpClient httpClient = new DefaultHttpClient();
		HttpEntity httpEntity = null;
		HttpResponse httpResponse = null;
		String response = null;

		// Checking http request method type
		// appending params to url

		String url = urlJSON.concat(codigo).concat("/").concat(notifId)
				.concat("/").concat("ANDROID");
		HttpGet httpGet = new HttpGet(url);

		try {
			httpResponse = httpClient.execute(httpGet);

			httpEntity = httpResponse.getEntity();
			httpEntity.toString();
			response = EntityUtils.toString(httpEntity);

		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}

		return response;

	}

	public String consultaConfiguracion(String codigo, String notifId) {
		HttpURLConnection con = null;
		InputStream is = null;

		try {
			String url = urlJSON.concat(codigo).concat("/").concat(notifId)
					.concat("/").concat("ANDROID");

			Log.i(TAG,url);
			con = (HttpURLConnection) (new URL(url)).openConnection();
			
			// Let's read the response
			StringBuffer buffer = new StringBuffer();
			is = con.getInputStream();
			BufferedReader br = new BufferedReader(new InputStreamReader(is));
			String line = null;
			while ((line = br.readLine()) != null) {
				buffer.append(line);
			}

			is.close();
			con.disconnect();
			return buffer.toString();
		} catch (Throwable t) {
			t.printStackTrace();
		} finally {
			try {
				is.close();
			} catch (Throwable t) {
			}
			try {
				con.disconnect();
			} catch (Throwable t) {
			}
		}

		return null;
	}

	public Configuracion consultaDetalleActivacion(String codigoEmpresa,
			String gcmId) {
		try {

			// Modelo el request
			SoapObject request = new SoapObject(namespace, metodo);
			request.addProperty("arg0", codigoEmpresa);
			request.addProperty("arg1", gcmId);
			request.addProperty("arg2", "ANDROID");

			// Modelo el Sobre
			SoapSerializationEnvelope sobre = new SoapSerializationEnvelope(
					SoapEnvelope.VER11);
			sobre.dotNet = false;
			sobre.setOutputSoapObject(request);

			// Modelo el transporte
			HttpTransportSE transporte = new HttpTransportSE(url);

			// Llamada
			transporte.call("", sobre);

			// Resultado
			SoapObject resp = (SoapObject) sobre.getResponse();
			Log.i(TAG, "WS:" + resp.toString());

			String status = resp.getPropertyAsString("status");
			if (status != null && status.equals("0")) {
				String idEmpresa = resp.getPropertyAsString("id_empresa");
				String nombre = resp.getPropertyAsString("nombre");

				Configuracion conf = new Configuracion(codigoEmpresa,
						idEmpresa, nombre);

				// iterar imagenes
				SoapObject obj = (SoapObject) resp.getProperty("imagenes");
				Log.i("MS", obj.toString());
				for (int i = 0; i < obj.getPropertyCount(); i++) {
					SoapObject entry = (SoapObject) obj.getProperty(i);
					String imagen = entry.getPropertyAsString("value");
					Log.i("IMG", imagen);
					conf.getImagenes()[i] = imagen;
				}
				return conf;
			}

		} catch (Exception e) {
			Log.e("ERROR", e.getMessage());
		}

		return null;
	}
}
