package com.example.guille.actividad3;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FacebookAuthProvider;
import com.google.firebase.auth.FirebaseUser;

import org.json.JSONObject;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Arrays;

public class FBLogin extends AppCompatActivity {
    CallbackManager callbackManager;
    LoginButton loginButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fblogin);
        callbackManager = CallbackManager.Factory.create();
        loginButton = (LoginButton) findViewById(R.id.login_button);
        loginButton.setReadPermissions(Arrays.asList(
                "public_profile", "email"));

        // If using in a fragment
        //loginButton.setFragment(this);

        // Callback registration
        loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                // App code
                Log.v("LogFB", "bien");
                handleFacebookAccessToken(loginResult.getAccessToken());

                private Bundle getFacebookData(JSONObject object){
                    Bundle bundle = new Bundle();

                    try {
                        String id = object.getString("id");
                        URL profile_pic;
                        try {
                            profile_pic = new URL("https://graph.facebook.com/" + id + "/picture?type=large");
                            Log.i("profile_pic", profile_pic + "");
                            bundle.putString("profile_pic", profile_pic.toString());
                        } catch (MalformedURLException e) {
                            e.printStackTrace();
                            return null;
                        }

                        bundle.putString("idFacebook", id);
                        if (object.has("first_name"))
                            bundle.putString("first_name", object.getString("first_name"));
                        if (object.has("last_name"))
                            bundle.putString("last_name", object.getString("last_name"));
                        if (object.has("email"))
                            bundle.putString("email", object.getString("email"));
                        if (object.has("gender"))
                            bundle.putString("gender", object.getString("gender"));


                        prefUtil.saveFacebookUserInfo(object.getString("first_name"),
                                object.getString("last_name"),object.getString("email"),
                                object.getString("gender"), profile_pic.toString());

                    } catch (Exception e) {
                        Log.d(TAG, "BUNDLE Exception : "+e.toString());
                    }

                    return bundle;
                }




            }

            @Override
            public void onCancel() {
                // App code
                Log.v("LogFB", "cancel");

            }

            @Override
            public void onError(FacebookException exception) {
                // App code
                Log.v("LogFB", "mal");

            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        callbackManager.onActivityResult(requestCode, resultCode, data);
        super.onActivityResult(requestCode, resultCode, data);
    }
    private void handleFacebookAccessToken(AccessToken token) {

        AuthCredential credential = FacebookAuthProvider.getCredential(token.getToken());
        DataHolder.instances.firebaseAdmin.mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            FirebaseUser user = DataHolder.instances.firebaseAdmin.mAuth.getCurrentUser();
                            Log.v("arr", user.getPhotoUrl().toString());
                            //updateUI(user);

                        } else {
                            // If sign in fails, display a message to the user.
                            Toast.makeText(FBLogin.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                            //updateUI(null);
                        }

                        // ...


                    }
                });
    }
}
