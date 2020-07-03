package com.shancreation.sliatelms;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.shancreation.sliatelms.Auth.LoginActivity;
import com.shancreation.sliatelms.Auth.RegistorActivity;

public class MainActivity extends AppCompatActivity {
    private DocumentReference muserRef;
    private FirebaseFirestore db;
    private FirebaseAuth mAuth;
    private FirebaseUser mCurrentUser;

    private String TAG ="MAIN ACTIVITY";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mAuth = FirebaseAuth.getInstance();
        db = FirebaseFirestore.getInstance();

        mCurrentUser = mAuth.getCurrentUser();
        Log.d(TAG,"OnCreate");
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                StartFunc();
            }
        },2000);
    }



    void StartFunc(){

        if(mCurrentUser==null){
            Log.d(TAG,"USer Null");
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("You are not Logged");
            builder.setMessage("you are not logged in to system if you already have account please login. if you don't have an account please Register");
            builder.setPositiveButton("Login", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {

                    Intent loginIntent = new Intent(MainActivity.this, LoginActivity.class);
                    startActivity(loginIntent);
                    finish();
                }
            }).setNegativeButton("Registor", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    Intent RegistorIntent = new Intent(MainActivity.this, RegistorActivity.class);
                    startActivity(RegistorIntent);
                    finish();
                }
            });
            AlertDialog dialog= builder.create();
            dialog.setCancelable(false);
            dialog.setCanceledOnTouchOutside(false);
            dialog.show();
        }else {
            Log.d(TAG,"User Not null");
            Intent HomeIntent = new Intent(MainActivity.this,NavActivity.class);
            startActivity(HomeIntent);
            finish();

        }


    }
}