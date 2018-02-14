package com.example.guille.examen1;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.example.guille.milib.LoginFragment;
import com.example.guille.milib.LoginFragmentListener;
import com.example.guille.milib.RegisterFragment;
import com.example.guille.milib.RegisterFragmentsListener;
import com.google.firebase.database.DataSnapshot;

public class MainActivity extends AppCompatActivity {

    LoginFragment loginFragment;
    RegisterFragment registerFragment;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        loginFragment = (LoginFragment) getSupportFragmentManager().findFragmentById(R.id.frgLogin);
        registerFragment = (RegisterFragment) getSupportFragmentManager().findFragmentById(R.id.frgRegister);
        MainActivityEvents mainActivityEvents = new MainActivityEvents(this);

        loginFragment.setListener(mainActivityEvents);
        registerFragment.setListener(mainActivityEvents);
        com.example.guille.examen1.DataHolder.instances.firebaseAdmin.setListener(mainActivityEvents);

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.hide(registerFragment);

        transaction.show(loginFragment);

        transaction.commit();



    }
}
class MainActivityEvents implements LoginFragmentListener, RegisterFragmentsListener, FirebaseAdminListener{

    MainActivity mainActivity;
    public MainActivityEvents(MainActivity mainActivity){
        this.mainActivity = mainActivity;
    }

    @Override
    public void OnclickedLoginButton(String email, String pass) {
        com.example.guille.examen1.DataHolder.instances.firebaseAdmin.LoginEmailPass(email,pass,mainActivity);
    }

    @Override
    public void OnclickedRegisterButton() {
        FragmentTransaction transaction = this.mainActivity.getSupportFragmentManager().beginTransaction();
        transaction.show(this.mainActivity.registerFragment);

        transaction.hide(this.mainActivity.loginFragment);
        transaction.commit();
    }

    @Override
    public void OnClickAceptarRegistro(String email, String pass) {
        com.example.guille.examen1.DataHolder.instances.firebaseAdmin.RegisterEmailPass(email,pass,mainActivity);
    }

    @Override
    public void OnclickCancelarRegistro() {
        FragmentTransaction transaction = this.mainActivity.getSupportFragmentManager().beginTransaction();
        transaction.hide(this.mainActivity.registerFragment);

        transaction.show(this.mainActivity.loginFragment);
        transaction.commit();
    }

    @Override
    public void registerOK(boolean itsOk) {
        if (itsOk){
            //Hacer metodos cuando el registro esta bien
            Intent intent=new Intent(mainActivity,SecondActivity.class);
            mainActivity.startActivity(intent);
            mainActivity.finish();
            Log.v("MAINAKAHFG","BIEN EL Regis");
        }
        else{

        }
    }

    @Override
    public void loginOK(boolean itsOk) {
        if (itsOk){
            //Hacer metodos cuando el login esta bien
            Intent intent=new Intent(mainActivity,SecondActivity.class);
            mainActivity.startActivity(intent);
            mainActivity.finish();
            Log.v("MAINAKAHFG","BIEN EL LOGIN");
        }
    }

    @Override
    public void ramaDescargada(String rama, DataSnapshot dataSnapshot) {

    }
}
