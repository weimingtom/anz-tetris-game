package com.huynt.libs.io;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;

import android.util.Log;

public class HTTPStream {

	public static String getHTTPPostString(String url, HashMap<String, String> params) {
		InputStream inputStream = null;
		try {
			DefaultHttpClient client = new DefaultHttpClient();
			HttpPost post = new HttpPost(url);
			
			
			HttpResponse responsePOST = client.execute(post);
			HttpEntity resEntity = responsePOST.getEntity();

			inputStream = resEntity.getContent();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

		StringBuilder builder = new StringBuilder();
		try {
			if (inputStream != null) {
				int c;
				while ((c = inputStream.read()) != -1) {
					builder.append((char)c);
				}
			}
		} catch (IOException e) {
			return null;
		}

		Log.d("HTTPStream.getHTTPPostString", builder.toString());
		return builder.toString();
	}

}
