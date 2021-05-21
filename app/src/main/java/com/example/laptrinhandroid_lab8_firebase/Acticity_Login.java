package com.example.laptrinhandroid_lab8_firebase;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Acticity_Login extends AppCompatActivity {
    EditText edt_sign_in_email,edt_sign_in_password;
    Button btn_sign_in_action,btn_sign_in_registry;
    FirebaseAuth auth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.acticity_login);
        FirebaseApp.initializeApp(this);
        auth = FirebaseAuth.getInstance();

        edt_sign_in_email = findViewById(R.id.edt_sign_in_email);
        edt_sign_in_password = findViewById(R.id.edt_sign_in_password);
        btn_sign_in_action = findViewById(R.id.btn_sign_in_action);
        btn_sign_in_registry = findViewById(R.id.btn_sign_in_registry);


        edt_sign_in_email.setHint("Email");
        edt_sign_in_password.setHint("Password");
        btn_sign_in_action.setText("SIGN IN");
        btn_sign_in_registry.setText("REGISTRY");

        btn_sign_in_registry.setOnClickListener(v->startActivity(new Intent(Acticity_Login.this,Registry_Activity.class)));


        btn_sign_in_action.setOnClickListener(v->{
            String email = edt_sign_in_email.getText().toString();
            if(TextUtils.isEmpty(email)){
                Toast.makeText(getApplicationContext(),"Pls Enter Email.....",Toast.LENGTH_SHORT).show();
                return;
            }
            String pass = edt_sign_in_password.getText().toString();
            if(TextUtils.isEmpty(pass)){
                Toast.makeText(getApplicationContext(),"Pls Enter Password.....",Toast.LENGTH_SHORT).show();
                return;
            }

            auth.signInWithEmailAndPassword(email,pass)
                    .addOnCompleteListener(Acticity_Login.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {

                            if(!task.isSuccessful()){
                                Toast.makeText(Acticity_Login.this, "Đăng  nhập thất bại", Toast.LENGTH_SHORT).show();
                            }
                            else {
                                Toast.makeText(Acticity_Login.this, "Đăng  nhập thành công", Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(Acticity_Login.this,Main_Theme_Activity.class));
                            }
                        }
                    });
        });
    }
}