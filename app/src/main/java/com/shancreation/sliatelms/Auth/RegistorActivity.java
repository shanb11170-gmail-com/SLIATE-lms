package com.shancreation.sliatelms.Auth;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.shancreation.sliatelms.R;

public class RegistorActivity extends AppCompatActivity {
    EditText mName,mEmail,mPassword,mCpassword,mIndex;
    Button mRegistor;
    FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registor);

        mName = findViewById(R.id.txtRegDisName);
        mIndex = findViewById(R.id.txtRegIndex);
        mEmail =findViewById(R.id.txtLoginEmail);
        mCpassword =findViewById(R.id.txtRegConfermPassword);
        mPassword =findViewById(R.id.txtLoginPassword);

        mRegistor =findViewById(R.id.btnLogin);

        mAuth = FirebaseAuth.getInstance();

        mRegistor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name, email,index,password,confrmPassword;
                name = mName.getText().toString();
                email = mEmail.getText().toString();
                index = mIndex.getText().toString();
                password = mPassword.getText().toString();
                confrmPassword = mCpassword.getText().toString();

                registorUser(email,password);
            }


        });

    }
    private void registorUser(String email, String password) {

        mAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(!task.isSuccessful()){
                    Toast.makeText(RegistorActivity.this, "Cannot Register Please Check Your Details.!",
                            Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}