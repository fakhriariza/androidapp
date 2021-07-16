package com.lpikitb.lpikitbmobile.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.lpikitb.lpikitbmobile.Activity.Chat.ChatActivity;
import com.lpikitb.lpikitbmobile.Network.ApiServices;
import com.lpikitb.lpikitbmobile.Network.InitRetrofit;
import com.lpikitb.lpikitbmobile.Network.InitRetrofitTenant;
import com.lpikitb.lpikitbmobile.R;
import com.lpikitb.lpikitbmobile.response.BeritaItem;
import com.lpikitb.lpikitbmobile.response.ResponseBerita;
import com.lpikitb.lpikitbmobile.response.ResponseTenant;
import com.lpikitb.lpikitbmobile.response.TenantItem;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BeritaActivity extends AppCompatActivity {

    TextView tvAlamat, tvPenulis;
    ImageView iv_home, ivProfile, ivsosial;
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_berita);

        // Inisialisasi Widget
        recyclerView = (RecyclerView) findViewById(R.id.rvListBerita);
        // RecyclerView harus pakai Layout manager
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        // Eksekusi method
        tampilBerita();
        tvAlamat = (TextView) findViewById(R.id.tvAlamat);
        iv_home = (ImageView) findViewById(R.id.iv_home);
        ivProfile= (ImageView) findViewById(R.id.ivProfile);
        ivsosial= (ImageView) findViewById(R.id.ivsosial);
        tvPenulis= (TextView) findViewById(R.id.tvPenulis);

        iv_home.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick (View v){
                startActivity(new Intent(BeritaActivity.this, MainActivity2.class));
            }
        });

        ivProfile.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick (View v){
                startActivity(new Intent(BeritaActivity.this, ProfileActivity.class));
            }
        });

        ivsosial.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick (View v){
                startActivity(new Intent(BeritaActivity.this, ChatActivity.class));
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
                        AdapterBerita adapter = new AdapterBerita(BeritaActivity.this, data_berita);
                        recyclerView.setAdapter(adapter);
                    } else {
                        // kalau tidak true
                        Toast.makeText(BeritaActivity.this, "Tidak ada berita untuk saat ini", Toast.LENGTH_SHORT).show();
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