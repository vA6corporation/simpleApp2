package com.example.simpleapp.ui.home;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.simpleapp.R;

import java.util.ArrayList;

public class Adapter extends RecyclerView.Adapter<Adapter.Datos> {

    ArrayList<String> listDatos;

    public Adapter(ArrayList<String> listDatos) {
        this.listDatos = listDatos;
    }

    @NonNull
    @Override
    public Datos onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_payment, null, false);
        return new Datos(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Adapter.Datos holder, int position) {

    }

    @Override
    public int getItemCount() {
        return listDatos.size();
    }

    public class Datos extends RecyclerView.ViewHolder {

        TextView item;

        public Datos(@NonNull View itemView) {
            super(itemView);
            item = itemView.findViewById(R.id.item_payment);
        }
    }
}
