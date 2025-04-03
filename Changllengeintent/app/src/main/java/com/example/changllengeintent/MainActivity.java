package com.example.changllengeintent;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.*;

import androidx.activity.EdgeToEdge;
import androidx.activity.result.ActivityResult;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    private static final int REQUEST_CODE = 1; // Mã request để nhận dữ liệu
    private Button btnCreate;
    private String web = "", locate="", phone="";
    private ImageView im_sad, im_norm, im_hap, im_phone, im_web, im_locate;

    @SuppressLint("MissingInflatedId")
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
        btnCreate = findViewById(R.id.btnCreate);
        im_hap = findViewById(R.id.imgHap);
        im_locate = findViewById(R.id.imgL);
        im_sad = findViewById(R.id.imgSad);
        im_phone = findViewById(R.id.imgP);
        im_web = findViewById(R.id.imgN);
        im_norm = findViewById(R.id.imgNorm);
        //
        btnCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, InputContacts.class);
                startActivityForResult(intent, REQUEST_CODE);
            }
        });
        implicit_intent();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == REQUEST_CODE && resultCode == MainActivity.RESULT_OK && data != null){
            web = data.getStringExtra("web");
            phone = data.getStringExtra("phone");
            locate = data.getStringExtra("location");
            String iconType = data.getStringExtra("iconType");
            im_web.setVisibility(View.VISIBLE);
            im_phone.setVisibility(View.VISIBLE);
            im_locate.setVisibility(View.VISIBLE);
            if(iconType.equals("happy")) im_hap.setVisibility(View.VISIBLE);
            if(iconType.equals("normal")) im_norm.setVisibility(View.VISIBLE);
            if(iconType.equals("sad")) im_sad.setVisibility(View.VISIBLE);
        }
    }
    public void implicit_intent()
    {
        im_phone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:"+phone));
                Intent chooser = Intent.createChooser(intent, "Choose the app to make a call!");
                startActivity(chooser);
            }
        });
        im_web.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW,Uri.parse(web));
                startActivity(intent);

            }
        });
        im_locate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW,Uri.parse("geo:"+locate));
                startActivity(intent);
            }
        });
    }

}