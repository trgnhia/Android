package com.example.explicit_intent;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity3 extends AppCompatActivity {

    private EditText edtSur;
    private Button btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main3);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        edtSur = findViewById(R.id.edtSurname);
        btn = findViewById(R.id.btnSub);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(edtSur.getText().toString().isEmpty())
                {
                    Toast.makeText(MainActivity3.this,"Please enter field", Toast.LENGTH_SHORT).show();

                }else{
                    String surName = edtSur.getText().toString().trim();
                    Intent intent = new Intent();
                    intent.putExtra("surname", surName);  // 📨 Đưa dữ liệu vào intent
                    setResult(RESULT_OK, intent);  // 🔄 Gửi kết quả về MainActivity
                    MainActivity3.this.finish();  // 🏁 Đóng activity hiện tại
                }
            }
        });
    }
}