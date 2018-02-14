package com.example.guille.actividad3.Adapter;


import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.guille.actividad3.FBObjects.FBCoche;
import com.example.guille.actividad3.R;

import java.util.ArrayList;

/**
 * Created by guille on 19/12/17.
 */

public class ListaCochesAdapter extends RecyclerView.Adapter<CocheViewHolder> {
    private ArrayList<FBCoche> coches;
    private ListaCochesAdapterListener listener;


    public ListaCochesAdapter(ArrayList<FBCoche> coches) {
        this.coches = coches;
    }

    public void setListener(ListaCochesAdapterListener listener){
        this.listener=listener;
    }

    @Override
    public CocheViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.celda_coche_layout,null);
        CocheViewHolder cocheViewHolder=new CocheViewHolder(v);
        return cocheViewHolder;
    }

    //vincula contenidos y contenedor
    @Override
    public void onBindViewHolder(CocheViewHolder holder, int position) {
        //holder.textoMensaje.setText(coches.get(position).original);
        holder.tvfabricado.setText(coches.get(position).Fabricado+"");
        holder.tvmarca.setText(coches.get(position).Marca+"");
        holder.tvnombre.setText(coches.get(position).Nombre+"");
        holder.tvlat.setText(coches.get(position).lat+"");
        holder.tvlon.setText(coches.get(position).lon+"");
        holder.setListener(this.listener);

    }

    @Override
    public int getItemCount() {
        return coches.size();
    }


}
