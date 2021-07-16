package com.lpikitb.lpikitbmobile.Activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.lpikitb.lpikitbmobile.Network.NodeNames;
import com.lpikitb.lpikitbmobile.R;
import com.lpikitb.lpikitbmobile.Network.ApiClient;
import com.lpikitb.lpikitbmobile.Network.ApiServices;
import com.rengwuxian.materialedittext.MaterialEditText;

import org.jetbrains.annotations.NotNull;

import java.util.HashMap;

import okhttp3.ResponseBody;
import retrofit2.Callback;
import retrofit2.Response;


public class  RegisterActivity extends AppCompatActivity {

    MaterialEditText etInstitusi, etEmail, etPassword, etHp, etNama, etkode;
    Button btnSave;
    ApiServices apiService;
    ProgressDialog pd;
    FirebaseAuth auth;
    DatabaseReference reference;
    private FirebaseUser firebaseUser;
    private String email, password, nohp, nama, institusi, kode;


    private static final String TAG = RegisterActivity.class.getSimpleName();


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        etInstitusi = (MaterialEditText) findViewById(R.id.etinstitusi);
        etEmail = (MaterialEditText) findViewById(R.id.etemail);
        etPassword = (MaterialEditText) findViewById(R.id.etpassword);
        etHp = (MaterialEditText) findViewById(R.id.ethp);
        etNama = (MaterialEditText) findViewById(R.id.etnama);
        etkode = (MaterialEditText) findViewById (R.id.etkode);
        btnSave = (Button) findViewById(R.id.btnsave);
        auth = FirebaseAuth.getInstance();
        apiService = ApiClient.getClient().create(ApiServices.class);

    }

    private void updateOnlyName()
    {
        UserProfileChangeRequest request = new UserProfileChangeRequest.Builder()
                .setDisplayName(etNama.getText().toString().trim())
                .build();

        firebaseUser.updateProfile(request).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull @NotNull Task<Void> task) {
                if (task.isSuccessful()) {
                    assert firebaseUser != null;
                    String userID= firebaseUser.getUid();
                    reference = FirebaseDatabase.getInstance().getReference().child(NodeNames.USERS);

                    HashMap<String, String> hashMap = new HashMap<>();

                    hashMap.put(NodeNames.NAMALENGKAP, etNama.getText().toString().trim());
                    hashMap.put(NodeNames.EMAIL, etEmail.getText().toString().trim());
                    hashMap.put(NodeNames.NOHP, etHp.getText().toString().trim());
                    hashMap.put(NodeNames.INSTITUSI, etInstitusi.getText().toString().trim());
                    hashMap.put(NodeNames.KODE, etkode.getText().toString().trim());
                    hashMap.put(NodeNames.ONLINE, "online");

                    reference.child(userID).setValue(hashMap).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull @NotNull Task<Void> task) {
                            Toast.makeText(RegisterActivity.this, R.string.akun_berhasil_dibuat, Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                            startActivity(intent);
                            finish();
                        }
                    });

                }
                else {
                    Toast.makeText(RegisterActivity.this,
                            getString(R.string.gagal_membuat_profile, task.getException()), Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    public void btnSaveClick (View v){
        email = etEmail.getText().toString().trim();
        nama = etNama.getText().toString().trim();
        password = etPassword.getText().toString().trim();
        institusi = etInstitusi.getText().toString().trim();
        nohp = etHp.getText().toString().trim();
        kode = etkode.getText().toString().trim();

        if (TextUtils.isEmpty(email) || TextUtils.isEmpty(nama) || TextUtils.isEmpty(password) || TextUtils.isEmpty(institusi) || TextUtils.isEmpty(nohp) || TextUtils.isEmpty(kode)) {
            Toast.makeText(RegisterActivity.this, "Semua Kolom Harus Diisi", Toast.LENGTH_SHORT).show();
        }
        else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches())
        {
            etEmail.setError(getString(R.string.enter_correct_email));
        }
        else {
            FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
            firebaseAuth.createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull @NotNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                firebaseUser = firebaseAuth.getCurrentUser();
                                updateOnlyName();

                            } else {
                                Toast.makeText(RegisterActivity.this,
                                        getString(R.string.signup_failed, task.getException()), Toast.LENGTH_SHORT).show();
                            }

                        }

                    });


        }

    }
}