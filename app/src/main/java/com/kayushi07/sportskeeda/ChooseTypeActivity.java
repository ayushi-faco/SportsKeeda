package com.kayushi07.sportskeeda;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.button.MaterialButton;

public class ChooseTypeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_type_acivity);

        MaterialButton btn_volley = findViewById(R.id.btn_volley);
        btn_volley.setOnClickListener(v->{
            startActivity(new Intent(this, MainActivityVolley.class));
        });
        MaterialButton btn_retrofit = findViewById(R.id.btn_retro);
        btn_retrofit.setOnClickListener(v->{
            startActivity(new Intent(this, RetrofitActivity.class));
        });
    }
}