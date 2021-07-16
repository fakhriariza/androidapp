package com.lpikitb.lpikitbmobile.Activity.Chat;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.tabs.TabLayout;
import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.lpikitb.lpikitbmobile.Activity.LoginActivity;
import com.lpikitb.lpikitbmobile.Activity.MainActivity2;
import com.lpikitb.lpikitbmobile.Activity.ProfileActivity;
import com.lpikitb.lpikitbmobile.R;
import com.squareup.picasso.Picasso;

import org.jetbrains.annotations.NotNull;

public class ChatActivity extends AppCompatActivity {

    FirebaseAuth firebaseAuth;
    ImageView ivProfile, iv_home, ivsosial;
    FirebaseFirestore firebaseFirestore;
    private TabLayout tabLayout;
    FirebaseUser firebaseUser;
    ViewPager2 pa;
    TextView tvLoginNotice, tvsosialjudul;
    Button btnLogin;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        tabLayout = findViewById(R.id.tabMain);
        ivProfile = (ImageView) findViewById(R.id.ivProfile);
        iv_home = (ImageView) findViewById(R.id.iv_home);
        ivsosial = (ImageView) findViewById(R.id.ivsosial);
        tvLoginNotice = (TextView) findViewById(R.id.tvLoginNotice);
        tvsosialjudul = (TextView) findViewById(R.id.tvsosialjudul);
        btnLogin = (Button) findViewById(R.id.btnLogin);
        FragmentManager fm = getSupportFragmentManager();
        ViewStateAdapter sa = new ViewStateAdapter(fm, getLifecycle());
        pa = findViewById(R.id.vpMain);
        pa.setAdapter(sa);

        firebaseFirestore = FirebaseFirestore.getInstance();
        firebaseAuth = FirebaseAuth.getInstance();
        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        TabLayout tabLayout = findViewById(R.id.tabMain);

        if (firebaseUser != null) {
            setViewPager();
            tvLoginNotice.setVisibility(View.GONE);
            btnLogin.setVisibility(View.GONE);
        }
        else {
            tvsosialjudul.setVisibility(View.GONE);
            btnLogin.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(new Intent(ChatActivity.this, LoginActivity.class));
                }
            });
        }

        iv_home.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick (View v){
                startActivity(new Intent(ChatActivity.this, MainActivity2.class));
            }
        });

        ivProfile.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick (View v){
                startActivity(new Intent(ChatActivity.this, ProfileActivity.class));
            }
        });

        ivsosial.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick (View v){
                startActivity(new Intent(ChatActivity.this, ChatActivity.class));
            }
        });

    }
    private class ViewStateAdapter extends FragmentStateAdapter {

        public ViewStateAdapter(@NonNull FragmentManager fragmentManager, @NonNull Lifecycle lifecycle) {
            super(fragmentManager, lifecycle);
        }

            @NonNull
            @Override
            public Fragment createFragment ( int position){
            // Hardcoded in this order, you'll want to use lists and make sure the titles match
            if (position == 0) {
                return new UserFragmenttiga();
            } else
                return new UserFragment();
        }
            @Override
            public int getItemCount () {
            // Hardcoded, use lists
            return 1;
        }

        }


    private void setViewPager(){
        tabLayout.addTab(tabLayout.newTab().setText("Chat"));

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                pa.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        pa.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                tabLayout.selectTab(tabLayout.getTabAt(position));
            }
        });

    }

}