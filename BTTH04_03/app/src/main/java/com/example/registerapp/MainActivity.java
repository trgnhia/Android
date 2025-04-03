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

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity {

    EditText etEmployeeName, etUsername, etPassword;
    Button btnRegister;
    TextView tvGoToLogin;
    FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etEmployeeName = findViewById(R.id.etEmployeeName);
        etUsername = findViewById(R.id.etUsername);
        etPassword = findViewById(R.id.etPassword);
        btnRegister = findViewById(R.id.btnRegister);
        tvGoToLogin = findViewById(R.id.tvGoToLogin);

        db = FirebaseFirestore.getInstance();

        btnRegister.setOnClickListener(v -> {
            String name = etEmployeeName.getText().toString();
            String username = etUsername.getText().toString();
            String password = etPassword.getText().toString();
            String createdAt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());

            if (TextUtils.isEmpty(name) || TextUtils.isEmpty(username) || TextUtils.isEmpty(password)) {
                Toast.makeText(this, "Vui lòng nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show();
                return;
            }

            HashMap<String, Object> account = new HashMap<>();
            account.put("employeeName", name);
            account.put("username", username);
            account.put("password", password);
            account.put("createdAt", createdAt);

            db.collection("accounts")
                    .whereEqualTo("username", username)
                    .get()
                    .addOnSuccessListener(query -> {
                        if (!query.isEmpty()) {
                            Toast.makeText(this, "Tên tài khoản đã tồn tại", Toast.LENGTH_SHORT).show();
                        } else {
                            db.collection("accounts")
                                    .add(account)
                                    .addOnSuccessListener(doc -> {
                                        Toast.makeText(this, "Đăng ký thành công", Toast.LENGTH_SHORT).show();
                                        startActivity(new Intent(this, LoginActivity.class));
                                        finish();
                                    })
                                    .addOnFailureListener(e -> {
                                        Toast.makeText(this, "Lỗi: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                                    });
                        }
                    });
        });

        tvGoToLogin.setOnClickListener(v -> {
            startActivity(new Intent(this, LoginActivity.class));
            finish();
        });
    }
}
