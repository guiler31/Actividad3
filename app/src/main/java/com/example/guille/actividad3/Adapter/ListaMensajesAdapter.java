package com.example.guille.actividad3.Adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.guille.actividad3.FBObjects.Mensajes;
import com.example.guille.actividad3.R;

import java.util.ArrayList;

/**
 * Created by guille on 18/12/17.
 */

public class ListaMensajesAdapter extends RecyclerView.Adapter<MensajeViewHolder> {
    private ArrayList<Mensajes> mensajes;

    public ListaMensajesAdapter(ArrayList<Mensajes> mensajes) {
        this.mensajes = mensajes;
    }

    @Override
    public MensajeViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.celda_mensaje_layout,null);
        MensajeViewHolder mensajeViewHolder=new MensajeViewHolder(v);
        return mensajeViewHolder;
    }

    //vincula parte visual
    @Override
    public void onBindViewHolder(MensajeViewHolder holder, int position) {
        holder.textoMensaje.setText(mensajes.get(position).original);
    }

    @Override
    public int getItemCount() {
        return mensajes.size();
    }
}
//guarda en cada momento que se crea la celda de forma momentanea los datos
class MensajeViewHolder extends RecyclerView.ViewHolder{

    public TextView textoMensaje;

    public MensajeViewHolder(View itemView){
        super(itemView);
       textoMensaje=itemView.findViewById(R.id.textomensaje);
    }

}