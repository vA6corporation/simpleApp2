package com.example.simpleapp.ui.home;

import android.os.StrictMode;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

public class Connection {
    private HttpsURLConnection urlConnection;
    private static final String TAG = "pichula";

    public JSONArray fetchData() {
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        try {
            URL url = new URL("https://80002.sos-delivery.com/web_service/api/v1/get_metodos_payment?id_business=987&token=app963");
            urlConnection = (HttpsURLConnection) url.openConnection();
            urlConnection.setRequestMethod("POST");
            InputStream in = new BufferedInputStream(urlConnection.getInputStream());
//            InputStream in = new BufferedInputStream(urlConnection.getErrorStream());
            String response = readStream(in);
            //Log.e(TAG, response);
            JSONObject object = new JSONObject(response);
            return object.getJSONArray("met_pagos_business");
//            for (int i = 0; i < pokes.length(); i++) {
//                JSONObject poke = pokes.getJSONObject(i);
//                Log.e("PICHULA", poke.getString("name"));
//            }
        } catch (IOException | JSONException e) {
            e.printStackTrace();
        } finally {
            urlConnection.disconnect();
        }
        return null;
    }

    private String readStream(InputStream in) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(in));
        StringBuilder buffer = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            buffer.append(line);
        }
        return buffer.toString();
    }
}
