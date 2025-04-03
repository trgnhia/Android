package com.example.explicit_intent;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.*;
import androidx.activity.EdgeToEdge;
import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;


public class MainActivity extends AppCompatActivity {

    private Button btnAc2, btnAc3;
    private EditText edtName;
    private TextView tvRes;

    // dùng lambda

//    private ActivityResultLauncher<Intent> activity3Launcher = registerForActivityResult(
//            new ActivityResultContracts.StartActivityForResult(),
//            result -> {
//                if (result.getResultCode() == RESULT_OK && result.getData() != null) {
//                    String surname = result.getData().getStringExtra("surname");
//                    tvRes.setText(surname);
//                } else {
//                    tvRes.setText("no data");
//                }
//            }
//    );

    private ActivityResultLauncher<Intent> activity3Launcher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    // 1️⃣ Kiểm tra xem có dữ liệu trả về hay không
                    if (result.getResultCode() == RESULT_OK && result.getData() != null) {
                        // 2️⃣ Lấy dữ liệu từ intent trả về
                        String surname = result.getData().getStringExtra("surname");
                        // 3️⃣ Hiển thị dữ liệu lên TextView
                        tvRes.setText(surname);
                    } else {
                        tvRes.setText("no data");
                    }
                }
            }
    );


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        btnAc2 = findViewById(R.id.btnAct2);
        btnAc3 = findViewById(R.id.btnAc3);
        edtName = findViewById(R.id.edtName);
        tvRes = findViewById(R.id.tvRes);
        btnAc2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(edtName.getText().toString().isEmpty()){
                    Toast.makeText(MainActivity.this, "Hãy điền vào đây!", Toast.LENGTH_SHORT).show();
                }
                else{
                    String name = edtName.getText().toString().trim();
                    Intent intent = new Intent(MainActivity.this,
                            com.example.explicit_intent.MainActivity2.class);
                    intent.putExtra("data", name);
                    startActivity(intent);
                }
            }
        });

        btnAc3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, MainActivity3.class);
                activity3Launcher.launch(intent); // 🚀 Dùng ActivityResultLauncher
            }
        });
    }
}