package com.lpikitb.lpikitbmobile.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.lpikitb.lpikitbmobile.Activity.Chat.ChatActivity;
import com.lpikitb.lpikitbmobile.Model.User;
import com.lpikitb.lpikitbmobile.Network.NodeNames;
import com.lpikitb.lpikitbmobile.R;

public class ProfileActivity extends AppCompatActivity {

    SharedPreferences sp;
    ImageView ivProfile, iv_home, ivsosial;
    TextView tvResultNama, tvResultEmail, tvResultNoHP, tvResultInstitusi;
    Button btnLogout;
    private SharedPreferences sharedPreferences;
    private Context _context;
    FirebaseUser firebaseUser;
    DatabaseReference reference;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        sp = getSharedPreferences("login",MODE_PRIVATE);
        ivProfile = (ImageView) findViewById(R.id.ivProfile);
        iv_home = (ImageView) findViewById(R.id.iv_home);
        ivsosial = (ImageView) findViewById(R.id.ivsosial);
        btnLogout = (Button) findViewById(R.id.btnLogout);
        tvResultNama = (TextView) findViewById(R.id.tvResultNama);
        tvResultEmail = (TextView) findViewById(R.id.tvResultEmail);
        tvResultNoHP = (TextView) findViewById(R.id.tvResultNoHP);
        tvResultInstitusi = (TextView) findViewById(R.id.tvResultInstitusi);
        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();

            if (firebaseUser != null) {
                reference = FirebaseDatabase.getInstance().getReference().child(NodeNames.USERS).child(firebaseUser.getUid());
                reference.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        User user = dataSnapshot.getValue(User.class);
                        tvResultNama.setText(user.getNama_lengkap());
                        tvResultInstitusi.setText(user.getInstitusi());
                        tvResultEmail.setText(user.getEmail());
                        tvResultNoHP.setText(user.getNo_hp());
                        btnLogout.setOnClickListener(new View.OnClickListener()
                        {
                            @Override
                            public void onClick(View v) {
                                FirebaseAuth.getInstance().signOut();
                                Intent intent = new Intent(ProfileActivity.this, MainActivity2.class);
                                startActivity(intent);
                                finish();
                            }
                        });
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {
                    }
                });
            }

            else {
                tvResultNama.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        startActivity(new Intent(ProfileActivity.this, LoginActivity.class));
                    }
                });
                tvResultEmail.setVisibility(View.GONE);
                tvResultNoHP.setVisibility(View.GONE);
                tvResultInstitusi.setVisibility(View.GONE);
                btnLogout.setVisibility(View.GONE);

            }

        iv_home.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick (View v){
                startActivity(new Intent(ProfileActivity.this, MainActivity2.class));
            }
        });

        ivProfile.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick (View v){
                startActivity(new Intent(ProfileActivity.this, ProfileActivity.class));
            }
        });

        ivsosial.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick (View v){
                startActivity(new Intent(ProfileActivity.this, ChatActivity.class));
            }
        });
    }

}