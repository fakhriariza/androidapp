package com.lpikitb.lpikitbmobile.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;
import com.lpikitb.lpikitbmobile.R;
import com.squareup.picasso.Picasso;

public class DetailModulActivity extends YouTubeBaseActivity {

    private static final String API_KEY = "AIzaSyD56iHLC3zBFr_eWjyYbvh6OMHRAa5kCzY";
    TextView tvPenulis, tvTanggal, tvIsi, tvJudulModul;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_modul);

        tvPenulis = (TextView) findViewById(R.id.tvPenulis);
        tvTanggal = (TextView) findViewById(R.id.tvTanggal);
        tvIsi = (TextView) findViewById(R.id.tvIsi);
        tvJudulModul = (TextView) findViewById(R.id.tvJudulModul);

        showDetailModul();
    }

    private void showDetailModul() {
        String judul_modul = getIntent().getStringExtra("JDL_MODUL");
        String penulis_modul = getIntent().getStringExtra("PNS_MODUL");
        String youtube_modul = getIntent().getStringExtra("YTB_MODUL");
        String thumbnail_modul = getIntent().getStringExtra("TMB_MODUL");
        String tanggal_modul = getIntent().getStringExtra("TGL_MODUL");
        String isi_modul = getIntent().getStringExtra("ISI_MODUL");

        tvJudulModul.setText(judul_modul);
        tvPenulis.setText(penulis_modul);
        tvTanggal.setText(tanggal_modul);
        tvIsi.setText(isi_modul);
        // Untuk gambar berita
        YouTubePlayerView youTubePlayerView = findViewById(R.id.YoutubeVid);
        youTubePlayerView.initialize(API_KEY, new YouTubePlayer.OnInitializedListener() {
            @Override
            public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean b) {
                //Memutar video
                youTubePlayer.loadVideo(youtube_modul);
            }

            @Override
            public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {
                Toast.makeText(getApplicationContext(), "Failed to initialize. Please check something!",
                        Toast.LENGTH_SHORT).show();
            }
        });

    }
}