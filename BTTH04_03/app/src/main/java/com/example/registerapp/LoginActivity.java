package com.example.registerapp;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.firestore.FirebaseFirestore;

public class LoginActivity extends AppCompatActivity {

    EditText etLoginUsername, etLoginPassword;
    Button btnLogin;
    TextView tvGoToRegister;
    FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        etLoginUsername = findViewById(R.id.etLoginUsername);
        etLoginPassword = findViewById(R.id.etLoginPassword);
        btnLogin = findViewById(R.id.btnLogin);
        tvGoToRegister = findViewById(R.id.tvGoToRegister);

        db = FirebaseFirestore.getInstance();

        btnLogin.setOnClickListener(v -> {
            String username = etLoginUsername.getText().toString();
            String password = etLoginPassword.getText().toString();

            if (TextUtils.isEmpty(username) || TextUtils.isEmpty(password)) {
                Toast.makeText(this, "Vui lòng nhập đầy đủ", Toast.LENGTH_SHORT).show();
                return;
            }

            // Tìm username và password trong Firestore
            db.collection("accounts")
                    .whereEqualTo("username", username)
                    .whereEqualTo("password", password)
                    .get()
                    .addOnSuccessListener(querySnapshot -> {
                        if (!querySnapshot.isEmpty()) {
                            Toast.makeText(this, "Đăng nhập thành công", Toast.LENGTH_SHORT).show();

                            // Gửi username sang HomeActivity
                            Intent intent = new Intent(this, HomeActivity.class);
                            intent.putExtra("username", username);
                            startActivity(intent);
                            finish();
                        } else {
                            Toast.makeText(this, "Sai tên tài khoản hoặc mật khẩu", Toast.LENGTH_SHORT).show();
                        }
                    })
                    .addOnFailureListener(e -> {
                        Toast.makeText(this, "Lỗi: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                    });
        });

        // Chuyển sang MainActivity (đăng ký)
        tvGoToRegister.setOnClickListener(v -> {
            startActivity(new Intent(this, MainActivity.class));
            finish();
        });
    }
}
