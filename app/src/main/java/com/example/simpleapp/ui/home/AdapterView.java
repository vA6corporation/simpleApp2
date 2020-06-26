package com.example.simpleapp.ui.home;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
//import android.widget.AdapterView.OnItemClickListener;

import com.example.simpleapp.R;

import java.util.ArrayList;

public class AdapterView
        extends RecyclerView.Adapter<AdapterView.HolderDatos>
        implements View.OnClickListener {

    ArrayList<String> listDatos;
    private View.OnClickListener clickListener;
    private OnItemClickListener onItemClickListener;

    public AdapterView(ArrayList<String> listDatos) {

        this.listDatos = listDatos;
    }

    public interface OnItemClickListener {
        void onItemEdiClick(int position);
        void onItemDeleteClick(int position);
    }

    @NonNull
    @Override
    public HolderDatos onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_payment, null, false);
        view.setOnClickListener(this);
        return new HolderDatos(view, onItemClickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull HolderDatos holder, int position) {

        holder.asignarDatos(listDatos.get(position));
    }

    @Override
    public int getItemCount() {

        return listDatos.size();
    }

    public void setOnItemClickListener(OnItemClickListener listener) {

        onItemClickListener = listener;
    }

    public void setOnClickListener(View.OnClickListener listener) {

        this.clickListener = listener;
    }

    @Override
    public void onClick(View view) {

        if (clickListener != null) {
            clickListener.onClick(view);
        }
    }

    public static class HolderDatos extends RecyclerView.ViewHolder {

        TextView dato;
        ImageButton imageButtonEdit;
        ImageButton imageButtonDelete;

        public HolderDatos(@NonNull View itemView, final OnItemClickListener listener) {

            super(itemView);
            dato = itemView.findViewById(R.id.item_title);
            imageButtonEdit = itemView.findViewById(R.id.buttonEdit);
            imageButtonDelete = itemView.findViewById(R.id.buttonDelete);

            imageButtonEdit.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View view) {
                    if (listener != null) {
                        Log.e("CHULAPI", "ME LLEGAS");
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION) {
                            listener.onItemEdiClick(position);
                            //listener.onItemDeleteClick(position);
                        }
                    }
                }
            });

            imageButtonDelete.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View view) {
                    if (listener != null) {
                        Log.e("CHULAPI", "ME LLEGAS");
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION) {
                            listener.onItemDeleteClick(position);
                            //listener.onItemDeleteClick(position);
                        }
                    }
                }
            });
        }

        public void asignarDatos(String s) {

            dato.setText(s);
        }
    }
}
