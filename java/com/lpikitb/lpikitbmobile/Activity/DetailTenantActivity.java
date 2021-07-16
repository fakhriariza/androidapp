package com.lpikitb.lpikitbmobile.Activity;

import android.os.Bundle;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.lpikitb.lpikitbmobile.R;
import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

public class DetailTenantActivity extends AppCompatActivity {

    // Deklarasi
    ImageView ivGambarTenant;
    TextView tvAlamat, tvNamaTenant, tvOwner, tvTelepon, tvIsiTenant;
    WebView wvKontenTenant;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailtenant);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        // Inisialisasi
        ivGambarTenant = (ImageView) findViewById(R.id.ivGambarTenant);
        tvAlamat = (TextView) findViewById(R.id.tvAlamat);
        tvTelepon = (TextView) findViewById(R.id.tvTelepon);
        tvNamaTenant = (TextView) findViewById(R.id.tvNamaTenant);
        tvIsiTenant = (TextView) findViewById(R.id.tvIsiTenant);
        tvOwner = (TextView) findViewById(R.id.tvOwner);

        // Jalankan method tampil detail berita
        showDetailTenant();

    }

    private void showDetailTenant() {
        // Tangkap data dari intent
        String nama_tenant = getIntent().getStringExtra("NMA_TENANT");
        String alamat_tenant = getIntent().getStringExtra("ALM_TENANT");
        String owner_tenant = getIntent().getStringExtra("OWN_TENANT");
        String isi_tenant = getIntent().getStringExtra("ISI_TENANT");
        String foto_tenant = getIntent().getStringExtra("FTO_TENANT");
        String telepon_tenant = getIntent().getStringExtra("TLP_TENANT");

        // Set judul actionbar / toolbar

        // Set ke widget
        tvTelepon.setText(telepon_tenant);
        tvOwner.setText(owner_tenant);
        tvAlamat.setText(alamat_tenant);
        tvNamaTenant.setText(nama_tenant);
        // Untuk gambar berita
        Picasso.with(this).load(foto_tenant).into(ivGambarTenant);
        // Set isi berita sebagai html ke WebView
        tvIsiTenant.setText(isi_tenant);
    }
}