package com.lpikitb.lpikitbmobile.Activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.lpikitb.lpikitbmobile.R;
import com.lpikitb.lpikitbmobile.Network.ApiClient;
import com.lpikitb.lpikitbmobile.Network.ApiServices;
import com.rengwuxian.materialedittext.MaterialEditText;


public class LoginActivity extends AppCompatActivity {


    MaterialEditText etEmail, etPassword;
    TextView tvregister;
    Button btnLogin;
    ApiServices apiservice;
    SharedPreferences sp;
    FirebaseAuth auth;

    private static final String TAG ="LoginActivity";


    protected void onCreate(Bundle saveInstanceState){
        super.onCreate(saveInstanceState);

        setContentView(R.layout.activity_login);
        auth = FirebaseAuth.getInstance();
        etEmail= (MaterialEditText) findViewById(R.id.etemail);
        etPassword = (MaterialEditText) findViewById(R.id.etpassword);
        btnLogin = (Button) findViewById(R.id.btnLogin);
        tvregister = (TextView) findViewById(R.id.tvregister);

        sp = getSharedPreferences("login",MODE_PRIVATE);
        apiservice = ApiClient.getClient().create(ApiServices.class);



        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                loginUser();
            }
        });

        tvregister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                register_user();
            }
        });

    }

    private void register_user() {
        Intent i = new Intent(this,RegisterActivity.class);
        startActivity(i);
    }

    private void loginUser() {
            String strEmail = etEmail.getText().toString();
            String strPassword = etPassword.getText().toString();

            if (TextUtils.isEmpty(strEmail) || TextUtils.isEmpty(strPassword)){
                Toast.makeText(LoginActivity.this, "Semua Kolom Harus Diisi", Toast.LENGTH_SHORT).show();
            } else {

                auth.signInWithEmailAndPassword(strEmail, strPassword)
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()){
                                    Intent intent = new Intent(LoginActivity.this, MainActivity2.class);
                                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                                    startActivity(intent);
                                    finish();
                                } else {
                                    Toast.makeText(LoginActivity.this, "Login Gagal", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
            }
    }
}