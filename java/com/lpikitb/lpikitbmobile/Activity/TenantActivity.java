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
import com.lpikitb.lpikitbmobile.Network.InitRetrofitTenant;
import com.lpikitb.lpikitbmobile.R;
import com.lpikitb.lpikitbmobile.response.ResponseTenant;
import com.lpikitb.lpikitbmobile.response.TenantItem;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TenantActivity extends AppCompatActivity {

    TextView tvAlamat;
    ImageView iv_home, ivProfile, ivsosial;
    private RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tenant);

        // Inisialisasi Widget
        recyclerView = (RecyclerView) findViewById(R.id.rvListTenant);
        // RecyclerView harus pakai Layout manager
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        // Eksekusi method
        tampilTenant();
        tvAlamat = (TextView) findViewById(R.id.tvAlamat);
        iv_home = (ImageView) findViewById(R.id.iv_home);
        ivProfile= (ImageView) findViewById(R.id.ivProfile);
        ivsosial= (ImageView) findViewById(R.id.ivsosial);

        iv_home.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick (View v){
                startActivity(new Intent(TenantActivity.this, MainActivity2.class));
            }
        });

        ivProfile.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick (View v){
                startActivity(new Intent(TenantActivity.this, ProfileActivity.class));
            }
        });

        ivsosial.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick (View v){
                startActivity(new Intent(TenantActivity.this, ChatActivity.class));
            }
        });
    }
    private void tampilTenant() {
        ApiServices api = InitRetrofitTenant.getInstance();
        // Siapkan request
        Call<ResponseTenant> tenantCall = api.request_show_all_tenant();
        // Kirim request
        tenantCall.enqueue(new Callback<ResponseTenant>() {
            @Override
            public void onResponse(Call<ResponseTenant> call, Response<ResponseTenant> response) {
                // Pasikan response Sukses
                if (response.isSuccessful()){
                    Log.d("response api", response.body().toString());
                    // tampung data response body ke variable
                    List<TenantItem> data_tenant = response.body().getTenant();
                    boolean status = response.body().isStatus();
                    // Kalau response status nya = true
                    if (status){
                        // Buat Adapter untuk recycler view
                        AdapterTenant adapter = new AdapterTenant(TenantActivity.this, data_tenant);
                        recyclerView.setAdapter(adapter);
                    } else {
                        // kalau tidak true
                        Toast.makeText(TenantActivity.this, "Tidak ada berita untuk saat ini", Toast.LENGTH_SHORT).show();
                    }
                }
            }
            @Override
            public void onFailure(Call<ResponseTenant> call, Throwable t) {
                // print ke log jika Error
                t.printStackTrace();
            }
        });
    }
}