package com.example.guille.examen1;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
/*
import com.example.guille.actividad3.Adapter.ListaCochesAdapter;
import com.example.guille.actividad3.Adapter.ListaMensajesAdapter;
import com.example.guille.actividad3.FBObjects.FBCoche;
import com.example.guille.actividad3.FBObjects.Mensajes;
*/
import com.example.guille.examen1.Adapter.ListaNoticiasAdapter;
import com.example.guille.examen1.Adapter.ListaNoticiasAdapterListener;
import com.example.guille.examen1.Adapter.NoticiasViewHolder;
import com.example.guille.examen1.FBObjects.FBNoticia;
import com.example.guille.milib.ListaFragment;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.GenericTypeIndicator;

import java.util.ArrayList;

public class SecondActivity extends AppCompatActivity {
    ListaFragment listaFragmentNoticias;
    ListaNoticiasAdapter listaNoticiasAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        //Log.v("SecondActivity", "-------- email: "+DataHolder.instances.firebaseAdmin.user.getEmail());
        SecondActivityEvents events= new SecondActivityEvents(this);
        DataHolder.instances.firebaseAdmin.setListener(events);
        /*listaFragmentMensajes =(ListaFragment)getSupportFragmentManager().findFragmentById(R.id.fragmentListMensajes);
        listaFragmentCoches =(ListaFragment)getSupportFragmentManager().findFragmentById(R.id.fragmentListCoche);
        */
        listaFragmentNoticias =(ListaFragment)getSupportFragmentManager().findFragmentById(R.id.fragmentListNoticias);

        //selecciona la rama a descargar
        //DataHolder.instances.firebaseAdmin.descargarYObservarRama("messages");
        //DataHolder.instances.firebaseAdmin.descargarYObservarRama("Coches");
        DataHolder.instances.firebaseAdmin.descargarYObservarRama("Noticias");



        /*ArrayList<String> mdatos=new ArrayList<>();
        mdatos.add("Mensaje 1");
        ListaMensajesAdapter listaMensajesAdapter=new ListaMensajesAdapter(mdatos);
        listaFragment.recyclerView.setAdapter(listaMensajesAdapter);*/
    }
}

class SecondActivityEvents implements  FirebaseAdminListener,ListaNoticiasAdapterListener{
    SecondActivity secondActivity;

    public SecondActivityEvents(SecondActivity secondActivity){
        this.secondActivity=secondActivity;

    }
    @Override
    public void registerOK(boolean itsOk) {

    }

    @Override
    public void loginOK(boolean itsOk) {

    }

    @Override
    public void ramaDescargada(String rama, DataSnapshot dataSnapshot) {
        Log.v("SecondActivity", rama+"------- "+dataSnapshot);
        //Mensajes2 mensajes2=dataSnapshot.getValue(Mensajes2.class);
        // Log.v("SecondActivity", "Mensaje:  "+mensajes2.msgs2);
        if(rama.equals("Noticias")){
            GenericTypeIndicator<ArrayList<FBNoticia>> indicator= new GenericTypeIndicator<ArrayList<FBNoticia>>(){};
            ArrayList<FBNoticia> noticias=dataSnapshot.getValue(indicator);
            secondActivity.listaNoticiasAdapter= new ListaNoticiasAdapter(noticias,secondActivity);
            secondActivity.listaFragmentNoticias.recyclerView.setAdapter(secondActivity.listaNoticiasAdapter);
            secondActivity.listaNoticiasAdapter.setListener(this);
        }

    }

    @Override
    public void listaNoticiasAdapterCeldaClicked(NoticiasViewHolder celdaHolder) {
        Log.v("SecondActivity", "Celda presionada:  "+celdaHolder.getAdapterPosition());

    }
}
