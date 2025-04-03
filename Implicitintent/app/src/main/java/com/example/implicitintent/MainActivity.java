package com.example.implicitintent;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    private Button btn_call, btn_callF, btn_W, btn_M;
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
        btn_call = findViewById(R.id.btnCall);
        btn_callF = findViewById(R.id.btnCallF);
        btn_W = findViewById(R.id.btnWeb);
        btn_M = findViewById(R.id.btnMap);
        btn_call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_DIAL);
//                startActivity(intent);
                Intent chooser = Intent.createChooser(intent, "Chọn ứng dụng để quay số:");
                startActivity(chooser);
            }
        });

        btn_callF.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:0941767862"));
                Intent chooser = Intent.createChooser(intent, "Chọn ứng dụng để quay số:");
                startActivity(chooser);
            }
        });

        btn_W.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://translate.google.com/?hl=vi&sl=en&tl=vi&op=translate"));
                startActivity(intent);
            }
        });

        btn_M.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW,Uri.parse("geo:0,0?q=Ha noi, Viet Nam"));
                startActivity(intent);
            }
        });

    }
}