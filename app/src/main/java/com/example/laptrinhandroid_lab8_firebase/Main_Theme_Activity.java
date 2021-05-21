package com.example.laptrinhandroid_lab8_firebase;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

public class Main_Theme_Activity extends AppCompatActivity {
    TextView txt_the_title;
    ImageButton ibt_the_nothing,ibt_the_happy,ibt_the_unhappy;
    Button btn_the_finish,btn_the_signout;
    FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main__theme);
        auth = FirebaseAuth.getInstance();

        txt_the_title = findViewById(R.id.txt_the_title);
        ibt_the_nothing = findViewById(R.id.ibt_the_nothing);
        ibt_the_happy = findViewById(R.id.ibt_the_happy);
        ibt_the_unhappy = findViewById(R.id.ibt_the_unhappy);
        btn_the_finish = findViewById(R.id.btn_the_finish);
        btn_the_signout = findViewById(R.id.btn_the_signout);

        txt_the_title.setText("PLEASE TOUCH ONE!");
        ibt_the_happy.setBackgroundResource(R.drawable.happy);
        ibt_the_nothing.setBackgroundResource(R.drawable.nothing);
        ibt_the_unhappy.setBackgroundResource(R.drawable.unhappy);
        btn_the_finish.setText("FINISH");
        btn_the_signout.setText("SIGN OUT");

        btn_the_finish.setOnClickListener(v->{
            Toast.makeText(getApplicationContext(),"Thành công", Toast.LENGTH_SHORT).show();
        });
        btn_the_signout.setOnClickListener(v->{
            auth.signOut();
            Toast.makeText(getApplicationContext(),"Đăng xuất thành công", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(Main_Theme_Activity.this, MainActivity.class));
            finish();
        });
    }
}