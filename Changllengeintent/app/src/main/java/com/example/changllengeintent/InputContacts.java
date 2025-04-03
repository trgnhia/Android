package com.example.changllengeintent;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class InputContacts extends AppCompatActivity {

    private EditText edtName, edtPhone, edtW, edtL;
    private ImageView im_Sad, im_norm, im_happy;

    private String getInputText(EditText editText) {
        String text = editText.getText().toString().trim();
        if (text.isEmpty()) {
            Toast.makeText(this, "Vui lòng nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show();
            return"";
        }
        return text;
    }
    public void sendData(String iconType){

        String web = getInputText(edtW);
        String phone = getInputText(edtPhone);
        String location = getInputText(edtL);

        Intent intent = new Intent();
        intent.putExtra("web", web);
        intent.putExtra("phone", phone);
        intent.putExtra("location", location);
        intent.putExtra("iconType",iconType);
        setResult(RESULT_OK, intent);
        finish();
    }

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_input_contacts);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        edtName = findViewById(R.id.edtName);
        edtPhone = findViewById(R.id.edtPhone);
        edtL = findViewById(R.id.edtLocate);
        edtW = findViewById(R.id.edtWeb);
        im_happy = findViewById(R.id.imHappy);
        im_Sad = findViewById(R.id.imSad);
        im_norm = findViewById(R.id.imNorm);

        String name = "",web = "",phone = "", location="";
        im_happy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendData("happy");
            }
        });
        im_norm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendData("norm");
            }
        });
        im_Sad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendData("sad");
            }
        });
    }
}