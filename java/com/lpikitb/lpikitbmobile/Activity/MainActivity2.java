package com.lpikitb.lpikitbmobile.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.lpikitb.lpikitbmobile.Activity.Chat.ChatActivity;
import com.lpikitb.lpikitbmobile.Model.User;
import com.lpikitb.lpikitbmobile.Network.ApiServices;
import com.lpikitb.lpikitbmobile.Network.InitRetrofit;
import com.lpikitb.lpikitbmobile.Network.InitRetrofitModul;
import com.lpikitb.lpikitbmobile.Network.NodeNames;
import com.lpikitb.lpikitbmobile.R;
import com.lpikitb.lpikitbmobile.response.BeritaItem;
import com.lpikitb.lpikitbmobile.response.ModulItem;
import com.lpikitb.lpikitbmobile.response.ResponseBerita;
import com.lpikitb.lpikitbmobile.response.ResponseModul;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity2 extends AppCompatActivity {

    TextView tvResultNama;
    TextView tvTglTerbit, tvPenulis, tvBelajar, tv_lainnya, tv_lainnya2;
    ImageView iv_DaftarTenant, iv_home, ivProfile, IvModul, ivsosial, ivsosialwarna, ivBerita;
    SharedPreferences sp;
    FirebaseUser firebaseUser;
    DatabaseReference reference;

    private RecyclerView recyclerView, recyclerView2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        sp = getSharedPreferences("login", MODE_PRIVATE);
        iv_DaftarTenant = (ImageView) findViewById(R.id.iv_DaftarTenant);
        ivProfile = (ImageView) findViewById(R.id.ivProfile);
        iv_home = (ImageView) findViewById(R.id.iv_home);
        IvModul = (ImageView) findViewById(R.id.IvModul);
        ivsosial = (ImageView) findViewById(R.id.ivsosial);
        ivsosialwarna = (ImageView) findViewById(R.id.ivsosialwarna);
        ivBerita = (ImageView) findViewById(R.id.ivBerita);
        // Inisialisasi Widget
        recyclerView = (RecyclerView) findViewById(R.id.rvListBerita);
        // RecyclerView harus pakai Layout manager
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView2 = (RecyclerView) findViewById(R.id.rvListModul);
        recyclerView2.setLayoutManager(new LinearLayoutManager(this));
        // Eksekusi method
        tampilBerita();
        tvTglTerbit = (TextView) findViewById(R.id.tvTglTerbit);
        tvPenulis = (TextView) findViewById(R.id.tvPenulis);
        tv_lainnya = (TextView) findViewById(R.id.tv_lainnya);
        tvResultNama = (TextView) findViewById(R.id.tvResultNama);
        tvBelajar = (TextView) findViewById(R.id.tvBelajar);
        tv_lainnya2 = (TextView) findViewById(R.id.tv_lainnya2);
        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();

        iv_DaftarTenant.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity2.this, TenantActivity.class));
            }
        });

        if (firebaseUser != null) {
            reference = FirebaseDatabase.getInstance().getReference().child(NodeNames.USERS).child(firebaseUser.getUid());
            reference.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    User user = dataSnapshot.getValue(User.class);
                    tvResultNama.setText(user.getNama_lengkap());
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });
            tampilModul();
        }

        else {
            tvResultNama.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(new Intent(MainActivity2.this, LoginActivity.class));
                }
            });
            tvResultNama.setText("Login");
            recyclerView2.setVisibility(View.GONE);
            tvBelajar.setVisibility(View.GONE);
            tv_lainnya2.setVisibility(View.GONE);

        }

        iv_home.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick (View v){
                startActivity(new Intent(MainActivity2.this, MainActivity2.class));
            }
        });

        ivProfile.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick (View v){
                startActivity(new Intent(MainActivity2.this, ProfileActivity.class));
            }
        });

        ivsosial.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick (View v){
                startActivity(new Intent(MainActivity2.this, ChatActivity.class));
            }
        });

        IvModul.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick (View v){
                startActivity(new Intent(MainActivity2.this, ModulActivity.class));
            }
        });
        ivsosialwarna.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick (View v){
                startActivity(new Intent(MainActivity2.this, ChatActivity.class));
            }
        });
        ivBerita.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick (View v){
                startActivity(new Intent(MainActivity2.this, BeritaActivity.class));
            }
        });
        tv_lainnya.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick (View v){
                startActivity(new Intent(MainActivity2.this, BeritaActivity.class));
            }
        });
        tv_lainnya2.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick (View v){
                startActivity(new Intent(MainActivity2.this, ModulActivity.class));
            }
        });

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
                        AdapterModul2 adapter = new AdapterModul2(MainActivity2.this, data_modul);
                        recyclerView2.setAdapter(adapter);
                    } else {
                        // kalau tidak true
                        Toast.makeText(MainActivity2.this, "Tidak ada berita untuk saat ini", Toast.LENGTH_SHORT).show();
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


    private void tampilBerita() {
        ApiServices api = InitRetrofit.getInstance();
        // Siapkan request
        Call<ResponseBerita> beritaCall = api.request_show_all_berita();
        // Kirim request
        beritaCall.enqueue(new Callback<ResponseBerita>() {
            @Override
            public void onResponse(Call<ResponseBerita> call, Response<ResponseBerita> response) {
                // Pasikan response Sukses
                if (response.isSuccessful()){
                    Log.d("response api", response.body().toString());
                    // tampung data response body ke variable
                    List<BeritaItem> data_berita = response.body().getBerita();
                    boolean status = response.body().isStatus();
                    // Kalau response status nya = true
                    if (status){
                        // Buat Adapter untuk recycler view
                        AdapterBerita2 adapter = new AdapterBerita2(MainActivity2.this, data_berita);
                        recyclerView.setAdapter(adapter);
                    } else {
                        // kalau tidak true
                        Toast.makeText(MainActivity2.this, "Tidak ada berita untuk saat ini", Toast.LENGTH_SHORT).show();
                    }
                }
            }

            private void showData(){
                String penulis_berita = getIntent().getStringExtra("PNS_BERITA");
                tvPenulis.setText("Oleh : " + penulis_berita);
            }

            @Override
            public void onFailure(Call<ResponseBerita> call, Throwable t) {
                // print ke log jika Error
                t.printStackTrace();
            }
        });
    }
}