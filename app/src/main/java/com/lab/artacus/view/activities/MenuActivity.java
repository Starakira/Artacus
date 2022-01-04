package com.lab.artacus.view.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.lab.artacus.R;

public class MenuActivity extends AppCompatActivity {

    private Button buttonMulai, buttonKeluar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        buttonMulai = findViewById(R.id.menuActivityButtonMulai);
        buttonKeluar = findViewById(R.id.menuActivityButtonKeluar);

        buttonMulai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MenuActivity.this, GameActivity.class);
                startActivity(i);
            }
        });

        buttonKeluar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MenuActivity.this, MainActivity.class);
                startActivity(i);
                finish();
            }
        });
    }
}