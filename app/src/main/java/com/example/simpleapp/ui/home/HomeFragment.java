package com.example.simpleapp.ui.home;

import android.os.Bundle;
import android.os.StrictMode;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.simpleapp.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

import android.util.Log;

import javax.net.ssl.HttpsURLConnection;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;
    private HttpsURLConnection urlConnection;
    private static final String TAG = "pichula";
    private Connection connection = new Connection();

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                ViewModelProviders.of(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);
        final TextView textView = root.findViewById(R.id.text_home);
        homeViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        JSONArray paymentMethods = connection.fetchData();
        try {
            for (int i = 0; i < paymentMethods.length(); i++) {
                JSONObject paymentMethod = paymentMethods.getJSONObject(i);
                Log.e("PICHULA", paymentMethod.getString("nom_metodo"));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
//        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
//        StrictMode.setThreadPolicy(policy);
//        try {
//            URL url = new URL("https://pokeapi.co/api/v2/pokemon/?limit=10");
//            urlConnection = (HttpsURLConnection) url.openConnection();
//            InputStream in = new BufferedInputStream(urlConnection.getInputStream());
////            InputStream in = new BufferedInputStream(urlConnection.getErrorStream());
//            String response = readStream(in);
//            JSONObject object = new JSONObject(response);
//            JSONArray pokes = object.getJSONArray("results");
//            for (int i = 0; i < pokes.length(); i++) {
//                JSONObject poke = pokes.getJSONObject(i);
//                Log.e("PICHULA", poke.getString("name"));
//            }
//        } catch (IOException | JSONException e) {
//            e.printStackTrace();
//        } finally {
//            urlConnection.disconnect();
//        }
    }

//    private String readStream(InputStream in) throws IOException {
//        BufferedReader reader = new BufferedReader(new InputStreamReader(in));
//        StringBuilder buffer = new StringBuilder();
//        String line;
//        while ((line = reader.readLine()) != null) {
//            buffer.append(line);
//        }
//        return buffer.toString();
//    }
}