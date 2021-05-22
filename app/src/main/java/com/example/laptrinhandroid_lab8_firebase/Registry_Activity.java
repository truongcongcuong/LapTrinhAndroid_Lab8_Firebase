package com.example.laptrinhandroid_lab8_firebase;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.laptrinhandroid_lab8_firebase.entity.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Registry_Activity extends AppCompatActivity {
    EditText edt_reg_name,edt_reg_email,edt_reg_password,edt_reg_re_password;
    TextView txt_reg_title;
    Button btn_reg_registry,btn_reg_sign_in;
    FirebaseAuth auth;
    DatabaseReference database;
    FirebaseUser user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registry);

        auth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance().getReference("users");
        user = auth.getCurrentUser();

        edt_reg_name = findViewById(R.id.edt_reg_name);
        edt_reg_email = findViewById(R.id.edt_reg_email);
        edt_reg_password = findViewById(R.id.edt_reg_password);
        edt_reg_re_password = findViewById(R.id.edt_reg_re_password);
        txt_reg_title = findViewById(R.id.txt_reg_title);
        btn_reg_registry = findViewById(R.id.btn_reg_registry);
        btn_reg_sign_in = findViewById(R.id.btn_reg_sign_in);

        edt_reg_name.setHint("Your Name");
        edt_reg_email.setHint("Email");
        edt_reg_password.setHint("Password");
        edt_reg_re_password.setHint("Re-enter Password");
        txt_reg_title.setText("CREATE NEW ACCOUNT");
        btn_reg_registry.setText("REGISTRY");

        btn_reg_sign_in.setOnClickListener(v->startActivity(new Intent(Registry_Activity.this, Acticity_Login.class)));

        btn_reg_registry.setOnClickListener(v->{
            String email = edt_reg_email.getText().toString();
            if(TextUtils.isEmpty(email)){
                Toast.makeText(getApplicationContext(),"PVui lòng nhập email.....",Toast.LENGTH_SHORT).show();
                return;
            }
            String pass = edt_reg_password.getText().toString();
            if(TextUtils.isEmpty(pass)){
                Toast.makeText(getApplicationContext(),"Vui lòng nhập mật khẩu.....",Toast.LENGTH_SHORT).show();
                return;
            }
            String name = edt_reg_name.getText().toString();
            if(TextUtils.isEmpty(name)){
                Toast.makeText(getApplicationContext(),"Nhập tên của bạn.....",Toast.LENGTH_SHORT).show();
                return;
            }
            if (!pass.equalsIgnoreCase(edt_reg_re_password.getText().toString())){
                Toast.makeText(getApplicationContext(),"Mật khẩu không khớp",Toast.LENGTH_SHORT).show();
                return;
            }

            auth.createUserWithEmailAndPassword(email,pass)
                    .addOnCompleteListener(Registry_Activity.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (!task.isSuccessful()){
                                Toast.makeText(getApplicationContext(),"Đăng ký thất bại....",Toast.LENGTH_SHORT).show();
                            }
                            else{
                                User user = new User(name,email);
                                String userID = database.push().getKey();
                                user.setId(userID);
                                database.child(userID).setValue(user);
                                startActivity(new Intent(Registry_Activity.this,Main_Theme_Activity.class));
                                finish();
                            }
                        }
                    });

        });

    }
}