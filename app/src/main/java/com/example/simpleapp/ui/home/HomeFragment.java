package com.example.simpleapp.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.VerifiedInputEvent;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.simpleapp.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import android.util.Log;
import android.widget.Toast;

public class HomeFragment extends Fragment {

    private Connection connection = new Connection();

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        HomeViewModel homeViewModel = ViewModelProviders.of(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);
//        final TextView textView = root.findViewById(R.id.text_home);
//        homeViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
//            @Override
//            public void onChanged(@Nullable String s) {
//                textView.setText(s);
//            }
//        });
        return root;
    }

    @Override
    public void onViewCreated(@NonNull final View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        JSONArray paymentMethods = connection.fetchData();
        ArrayList<String> listDatos = new ArrayList<String>();
        RecyclerView recycler = view.findViewById(R.id.recycler);
        recycler.setLayoutManager(new LinearLayoutManager(this.getContext()));
        //recycler.setLayoutManager(new LinearLayoutManager(this.getContext(), LinearLayoutManager.VERTICAL, false));
        try {
            for (int i = 0; i < paymentMethods.length(); i++) {
                JSONObject paymentMethod = paymentMethods.getJSONObject(i);
                Log.e("PICHULA", paymentMethod.getString("nom_metodo"));
                listDatos.add(paymentMethod.getString("nom_metodo"));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        AdapterView adapter = new AdapterView(listDatos);

        adapter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(view.getContext(), "CHULAPI", Toast.LENGTH_SHORT).show();
                Log.e("PICHULA", "AGARRAMELA");
                //NavController navController = Navigation.findNavController(view, R.id.nav_host_fragment);
                //NavHostFragment.findNavController(view);
                //Navigation.findNavController(view);
                Navigation.findNavController(view).navigate(R.id.action_nav_home_to_nav_edit);
                //Navigation.findNavController(view).navigate(R.id.viewTransactionsAction);
            }
        });

        adapter.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemEdiClick(int position) {
                Bundle bundle = new Bundle();
                bundle.putInt("position", position);
                Log.e("PICHULA", "POSITION EDIT: " + position);
            }

            //@Override
            public void onItemDeleteClick(int position) {
                Log.e("PICHULA", "POSITION DELETE: " + position);
                Bundle bundle = new Bundle();
                bundle.putInt("position", position);
                Navigation.findNavController(view).navigate(R.id.action_nav_home_to_nav_edit, bundle);
                //changeItem(position, "Clicked");
            }
        });

        recycler.setAdapter(adapter);
    }
}