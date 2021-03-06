package com.example.guille.actividad3.FBObjects;

import com.google.android.gms.maps.model.Marker;
import com.google.firebase.database.IgnoreExtraProperties;

/**
 * Created by guille on 19/12/17.
 */
@IgnoreExtraProperties
public class FBCoche {

    public String Fabricado;
    public String Marca;
    public String Nombre;
    public double lat;
    public double lon;

    private Marker marker=null;

    public Marker getMarker() {
        return marker;
    }

    public void setMarker(Marker marker) {
        this.marker = marker;
    }


    public FBCoche(){

    }

    public FBCoche(String Fabricado, String Marca, String Nombre, double lat, double lon){

        this.Fabricado=Fabricado;
        this.Marca=Marca;
        this.Nombre=Nombre;
        this.lat=lat;
        this.lon=lon;
    }
}
