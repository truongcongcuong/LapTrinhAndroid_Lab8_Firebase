package com.example.laptrinhandroid_lab8_firebase;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.laptrinhandroid_lab8_firebase.entity.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class Main_Theme_Activity extends AppCompatActivity {
    TextView txt_the_title;
    ImageButton ibt_the_nothing,ibt_the_happy,ibt_the_unhappy;
    Button btn_the_finish,btn_the_signout;
    FirebaseAuth auth;
    String choice = "";
    DatabaseReference database;
    String userId;
    FirebaseUser user;
    private User userInfo = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main__theme);
        auth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance().getReference("users");
        userId = database.push().getKey();
        user = auth.getCurrentUser();

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

        ///// connet database
        Query query = FirebaseDatabase.getInstance().getReference().child("users").orderByChild("email").equalTo("truongcongcuong322@gmail.com");
        query.addChildEventListener(new ChildEventListener() {


            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                User user = snapshot.getValue(User.class);
                setUSerInfo(user);
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot snapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        btn_the_finish.setOnClickListener(v->{
            if(!TextUtils.isEmpty(choice))
            {
                setChoice(choice);

            }
            else
                Toast.makeText(getApplicationContext(),"Vui lòng chọn trạng thái ngày hôm nay của bạn", Toast.LENGTH_SHORT).show();
            if(userInfo!=null){
                Toast.makeText(Main_Theme_Activity.this,userInfo.toString(),Toast.LENGTH_SHORT).show();
            }else
                Toast.makeText(Main_Theme_Activity.this,"null obj",Toast.LENGTH_SHORT).show();
        });
        ibt_the_happy.setOnClickListener(v->{
            choice = "happy";
            Toast.makeText(getApplicationContext(),"Trạng thái của bạn : " + choice, Toast.LENGTH_SHORT).show();
        });
        ibt_the_nothing.setOnClickListener(v->{
            choice = "nothing";
            Toast.makeText(getApplicationContext(),"Trạng thái của bạn : " + choice, Toast.LENGTH_SHORT).show();
        });
        ibt_the_unhappy.setOnClickListener(v->{
            choice = "unhappy";
            Toast.makeText(getApplicationContext(),"Trạng thái của bạn : " + choice, Toast.LENGTH_SHORT).show();
        });
        btn_the_signout.setOnClickListener(v->{
            auth.signOut();
            Toast.makeText(getApplicationContext(),"Đăng xuất thành công", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(Main_Theme_Activity.this, MainActivity.class));
            finish();
        });
    }

    private void setChoice(String choice) {
        switch (choice){
            case "happy":
                userInfo.setHappy(userInfo.getHappy()+1);
                database.child(userInfo.getId()).child("happy").setValue(userInfo.getHappy());
                break;
            case "unhappy":
                userInfo.setUnhappy(userInfo.getUnhappy()+1);
                database.child(userInfo.getId()).child("unhappy").setValue(userInfo.getUnhappy());
                break;
            case "nothing":
                userInfo.setNothing(userInfo.getNothing()+1);
                database.child(userInfo.getId()).child("nothing").setValue(userInfo.getNothing());
                break;
        }
    }

    public void setUSerInfo(User userInfo){
        this.userInfo = userInfo;

    }


}