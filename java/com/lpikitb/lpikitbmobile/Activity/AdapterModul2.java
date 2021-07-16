package com.lpikitb.lpikitbmobile.Activity;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.lpikitb.lpikitbmobile.R;
import com.lpikitb.lpikitbmobile.response.ModulItem;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by Rizal Hilman on 12/02/18.
 * www.khilman.com
 */

class AdapterModul2 extends RecyclerView.Adapter<AdapterModul2.MyViewHolder> {
    // Buat Global variable untuk manampung context
    Context context;
    List<ModulItem> modul;
    public AdapterModul2(Context context, List<ModulItem> data_modul) {
        // Inisialisasi
        this.context = context;
        this.modul = data_modul;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        // Deklarasi widget
        ImageView ivPosterModul;
        TextView tvJudulModul, tvPenulis;
        public MyViewHolder(View itemView) {
            super(itemView);
            // inisialisasi widget
            ivPosterModul = (ImageView) itemView.findViewById(R.id.ivPosterModul);
            tvJudulModul = (TextView) itemView.findViewById(R.id.tvJudulModul);
            tvPenulis = (TextView) itemView.findViewById(R.id.tvPenulis);
        }
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // Layout inflater
        View view = LayoutInflater.from(context).inflate(R.layout.modul_item, parent, false);

        // Hubungkan dengan MyViewHolder
        MyViewHolder holder = new MyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        // Set widget
        holder.tvJudulModul .setText(modul.get(position).getJudul());
        holder.tvPenulis.setText(modul.get(position).getPenulis());

        // Dapatkan url gambar
        final String urlGambarThumbnail = "http://lpik.masuk.web.id/admin/gambar/" + modul.get(position).getThumbnail();
        // Set image ke widget dengna menggunakan Library Piccasso
        // krena imagenya dari internet
        Picasso.with(context).load(urlGambarThumbnail).into(holder.ivPosterModul);

        // Event klik ketika item list nya di klik
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Mulai activity Detail
                Intent varIntent = new Intent(context, DetailModulActivity.class);
                // sisipkan data ke intent
                varIntent.putExtra("JDL_MODUL", modul.get(position).getJudul());
                varIntent.putExtra("PNS_MODUL", modul.get(position).getPenulis());
                varIntent.putExtra("YTB_MODUL", modul.get(position).getUrlYoutube());
                varIntent.putExtra("TMB_MODUL", urlGambarThumbnail);
                varIntent.putExtra("TGL_MODUL", modul.get(position).getTanggal());
                varIntent.putExtra("ISI_MODUL", modul.get(position).getIsi());

                // method startActivity cma bisa di pake di activity/fragment
                // jadi harus masuk ke context dulu
                context.startActivity(varIntent);
            }
        });
    }
    // Menentukan Jumlah item yang tampil
    @Override
    public int getItemCount() {
        return Math.min(modul.size(), 5);
    }

}