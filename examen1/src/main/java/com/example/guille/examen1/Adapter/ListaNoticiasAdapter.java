package com.example.guille.examen1.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.guille.examen1.FBObjects.FBNoticia;
import com.example.guille.examen1.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by guille on 19/12/17.
 */

public class ListaNoticiasAdapter extends RecyclerView.Adapter<NoticiasViewHolder> {
    private ArrayList<FBNoticia> noticias;
    private Context mContext;
    private ListaNoticiasAdapterListener listener;

    public ListaNoticiasAdapter(ArrayList<FBNoticia> noticias, Context mContext) {
        this.noticias=noticias;
        this.mContext=mContext;
    }

    public void setListener(ListaNoticiasAdapterListener listener){
        this.listener=listener;
    }

    @Override
    public NoticiasViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.celda_noticia_layout,null);
        NoticiasViewHolder noticiasViewHolder=new NoticiasViewHolder(v);
        return noticiasViewHolder;    }

    @Override
    public void onBindViewHolder(NoticiasViewHolder holder, int position) {
        holder.tvtitulo.setText(noticias.get(position).Titulo+"");
        holder.tvnoticia.setText(noticias.get(position).Noticia+"");
        Picasso.with(mContext).load(noticias.get(position).imgurl).into(holder.imgnot);
        holder.setListener(this.listener);

    }

    @Override
    public int getItemCount() {
        return noticias.size();    }
}



