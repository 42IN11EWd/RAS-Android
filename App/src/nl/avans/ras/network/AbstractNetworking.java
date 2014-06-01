package nl.avans.ras.network;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.StatusLine;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import android.os.AsyncTask;
import android.util.Log;

public abstract class AbstractNetworking extends AsyncTask<String, String, String> {
	
	// Fields
	private HttpClient httpclient = new DefaultHttpClient();
	private HttpResponse response = null;
	private String responseString = "";
	
	@Override
	protected String doInBackground(String... list) {
		String url = list[0];
		String httpType = list[1];
		
		// Start the HTTP request
		try {
			if (httpType.equals("POST")) {
				response = httpclient.execute(new HttpGet(url));
			} else if (httpType.equals("GET")) {
				response = httpclient.execute(new HttpGet(url));
			}
			
			if(response != null) {
				// Get the response status
				StatusLine statusLine = response.getStatusLine();
				
				// Check if the status code is 200
				if(statusLine.getStatusCode() == HttpStatus.SC_OK) {
					ByteArrayOutputStream out = new ByteArrayOutputStream();
	                response.getEntity().writeTo(out);
	                out.close();
	                responseString = out.toString();
	                Log.i("responseString", responseString);
				}
			}
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return responseString;
	}
}
