package com.example.mainproject;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;


public class HomeActivity extends AppCompatActivity {
     CardView iv;
     CardView bv;

     CardView bn;

     CardView cn, cg, au;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);


        iv = findViewById(R.id.figure1);
        bv = findViewById(R.id.figure6);
        bn = findViewById(R.id.figure4);
        cn = findViewById(R.id.figure3);
        cg = findViewById(R.id.figure2);
        au = findViewById(R.id.figure5);

        au.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HomeActivity.this, AnalysisActivity.class));
            }
        });

        cg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HomeActivity.this, AnswerKeyActivity.class));
            }
        });


        iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(HomeActivity.this, pen.class));
            }
        });

        bv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HomeActivity.this, AboutPage.class));
            }
        });

        //ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.Write_External_Storage, Manifest.permission.Read_External_Storage}, PackageManager.PERMISSION_GRANTED);

         // final String WRITE_EXTERNAL_STORAGE = ("android.permission.WRITE_EXTERNAL_STORAGE");

        bn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HomeActivity.this, SettingsActivity.class));
            }
        });


        cn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(HomeActivity.this, ExamsActivity.class));
            }

    });

}
    }





