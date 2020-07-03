package com.shancreation.sliatelms.Auth;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.InstanceIdResult;
import com.shancreation.sliatelms.NavActivity;
import com.shancreation.sliatelms.R;

public class LoginActivity extends AppCompatActivity {
    private EditText mEmail,mPassword;
    private Button mLogin;
    private ProgressDialog mprogress;
    private FirebaseAuth mAuth;
    private FirebaseUser mCurrentUser;
    private DocumentReference muserRef;
    private FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        mEmail = findViewById(R.id.txtLoginEmail);
        mPassword = findViewById(R.id.txtLoginPassword);
        mLogin = findViewById(R.id.btnLogin);


        mAuth = FirebaseAuth.getInstance();
        db= FirebaseFirestore.getInstance();

        mLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = mEmail.getText().toString();
                String password = mPassword.getText().toString();

                if(!TextUtils.isEmpty(email)||TextUtils.isEmpty(password))
                {
                    Log.d("EMAILPASSWORD",email+"/"+password);
                    loginUser(email,password);
                }
            }


        });
    }
    private void loginUser(String email, String password) {
        mAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    String userID = mAuth.getCurrentUser().getUid();

                    FirebaseInstanceId.getInstance().getInstanceId().addOnCompleteListener(new OnCompleteListener<InstanceIdResult>() {
                        @Override
                        public void onComplete(@NonNull Task<InstanceIdResult> task) {
                            if(!task.isSuccessful()){

                                Log.d("LOGIN",task.getException().toString());
                                return;
                            }

                            String deviceToken = task.getResult().getToken();

                            Log.d("LOGIN",deviceToken);



                        }
                    });

                    Intent intent = new Intent(LoginActivity.this, NavActivity.class);
                    startActivity(intent);
                    finish();
                }else {

                    Log.d("LOGIN",task.getException().toString());
                    Toast.makeText(LoginActivity.this,task.getException().toString(),Toast.LENGTH_LONG).show();
                }
            }
        });


    }
}