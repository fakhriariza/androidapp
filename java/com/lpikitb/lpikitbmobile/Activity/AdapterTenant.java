package com.lpikitb.lpikitbmobile.Activity;

import android.content.Context;
import android.content.Intent;
import android.icu.text.Transliterator;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.lpikitb.lpikitbmobile.R;
import com.lpikitb.lpikitbmobile.response.TenantItem;
import com.squareup.picasso.Picasso;

import java.util.List;

class AdapterTenant extends RecyclerView.Adapter<AdapterTenant.MyViewHolder> {
    // Buat Global variable untuk manampung context
    Context context;
    List<TenantItem> tenant;
    public AdapterTenant(Context context, List<TenantItem> data_tenant) {
        // Inisialisasi
        this.context = context;
        this.tenant = data_tenant;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // Layout inflater
        View view = LayoutInflater.from(context).inflate(R.layout.tenant_item, parent, false);

        // Hubungkan dengan MyViewHolder
        MyViewHolder holder = new MyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        // Set widget
        holder.tvNamaTenant.setText(tenant.get(position).getNama());
        holder.tvAlamat.setText(tenant.get(position).getAlamat());

        // Dapatkan url gambar
        final String urlGambarTenant = "http://lpik.masuk.web.id/admin/gambar/" + tenant.get(position).getFoto();
        // Set image ke widget dengna menggunakan Library Piccasso
        // krena imagenya dari internet
        Picasso.with(context).load(urlGambarTenant).into(holder.ivGambarTenant);

        // Event klik ketika item list nya di klik
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Mulai activity Detail
                Intent varIntent = new Intent(context, DetailTenantActivity.class);
                // sisipkan data ke intent
                varIntent.putExtra("NMA_TENANT", tenant.get(position).getNama());
                varIntent.putExtra("ALM_TENANT", tenant.get(position).getAlamat());
                varIntent.putExtra("OWN_TENANT", tenant.get(position).getOwner());
                varIntent.putExtra("FTO_TENANT", urlGambarTenant);
                varIntent.putExtra("ISI_TENANT", tenant.get(position).getIsi());
                varIntent.putExtra("TLP_TENANT", tenant.get(position).getTelepon());

                // method startActivity cma bisa di pake di activity/fragment
                // jadi harus masuk ke context dulu
                context.startActivity(varIntent);
            }
        });
    }
    // Menentukan Jumlah item yang tampil
    @Override
    public int getItemCount() {
        return tenant.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        // Deklarasi widget
        ImageView ivGambarTenant;
        TextView tvNamaTenant, tvAlamat, tvTelepon, tvOwner;
        public MyViewHolder(View itemView) {
            super(itemView);
            // inisialisasi widget
            ivGambarTenant = (ImageView) itemView.findViewById(R.id.ivGambarTenant);
            tvAlamat = (TextView) itemView.findViewById(R.id.tvAlamat);
            tvTelepon = (TextView) itemView.findViewById(R.id.tvTelepon);
            tvOwner = (TextView) itemView.findViewById(R.id.tvOwner);
            tvNamaTenant = (TextView) itemView.findViewById(R.id.tvNamaTenant);
        }
    }
}