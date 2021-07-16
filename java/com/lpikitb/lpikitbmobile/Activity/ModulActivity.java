package com.lpikitb.lpikitbmobile.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.lpikitb.lpikitbmobile.Activity.Chat.ChatActivity;
import com.lpikitb.lpikitbmobile.Network.ApiServices;
import com.lpikitb.lpikitbmobile.Network.InitRetrofitModul;
import com.lpikitb.lpikitbmobile.R;
import com.lpikitb.lpikitbmobile.response.ModulItem;
import com.lpikitb.lpikitbmobile.response.ResponseModul;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ModulActivity extends AppCompatActivity {

    TextView DaftarModul, tvNoticeLogin;
    ImageView iv_home, ivProfile, ivsosial;
    Button btnLogin;
    private RecyclerView recyclerView;
    FirebaseUser firebaseUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modul);

        // Inisialisasi Widget
        recyclerView = (RecyclerView) findViewById(R.id.rvListModul);
        // RecyclerView harus pakai Layout manager
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        // Eksekusi method
        DaftarModul = (TextView) findViewById(R.id.DaftarModul);
        iv_home = (ImageView) findViewById(R.id.iv_home);
        ivsosial = (ImageView) findViewById(R.id.ivsosial);
        ivProfile = (ImageView) findViewById(R.id.ivProfile);
        tvNoticeLogin = (TextView) findViewById(R.id.tvLoginNotice);
        btnLogin = (Button) findViewById(R.id.btnLogin);
        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();


        iv_home.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick (View v){
                startActivity(new Intent(ModulActivity.this, MainActivity2.class));
            }
        });

        ivProfile.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick (View v){
                startActivity(new Intent(ModulActivity.this, ProfileActivity.class));
            }
        });

        ivsosial.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick (View v){
                startActivity(new Intent(ModulActivity.this, ChatActivity.class));
            }
        });

        if (firebaseUser !=null) {
            tampilModul();
            tvNoticeLogin.setVisibility(View.GONE);
            btnLogin.setVisibility(View.GONE);
        }
        else {
            recyclerView.setVisibility(View.GONE);
            DaftarModul.setVisibility(View.GONE);
            btnLogin.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick (View v){
                    startActivity(new Intent(ModulActivity.this, LoginActivity.class));
                }
            });

        }
    }

    private void tampilModul() {
        ApiServices api = InitRetrofitModul.getInstance();
        // Siapkan request
        Call<ResponseModul> modulCall = api.request_show_all_modul();
        // Kirim request
        modulCall.enqueue(new Callback<ResponseModul>() {
            @Override
            public void onResponse(Call<ResponseModul> call, Response<ResponseModul> response) {
                // Pasikan response Sukses
                if (response.isSuccessful()){
                    Log.d("response api", response.body().toString());
                    // tampung data response body ke variable
                    List<ModulItem> data_modul = response.body().getModul();
                    boolean status = response.body().isStatus();
                    // Kalau response status nya = true
                    if (status){
                        // Buat Adapter untuk recycler view
                        AdapterModul adapter = new AdapterModul(ModulActivity.this, data_modul);
                        recyclerView.setAdapter(adapter);
                    } else {
                        // kalau tidak true
                        Toast.makeText(ModulActivity.this, "Tidak ada berita untuk saat ini", Toast.LENGTH_SHORT).show();
                    }
                }
            }
            @Override
            public void onFailure(Call<ResponseModul> call, Throwable t) {
                // print ke log jika Error
                t.printStackTrace();
            }
        });
    }
}