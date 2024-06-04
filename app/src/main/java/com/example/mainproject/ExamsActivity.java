package com.example.mainproject;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class ExamsActivity extends AppCompatActivity {
    ImageView lv;
    private BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exams);


        bottomNavigationView = findViewById(R.id.bottomNavigationView);


        // iv.setOnClickListener(new View.OnClickListener() {
        //   @Override
        //  public void onClick(View view) {
        // startActivity(new Intent(pen.this, Scan.class));
        // }

        //}

        bottomNavigationView.setOnItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();

                if (id == R.id.Home) {
                    startActivity(new Intent(ExamsActivity.this, HomeActivity.class));
                }
                else if (id == R.id.classes){
                    startActivity(new Intent(ExamsActivity.this, pen.class));
                }
                else if (id == R.id.scans){
                    startActivity(new Intent(ExamsActivity.this, Scan.class));
                }
                return false;
            }

        });
        lv = findViewById(R.id.iconScan);


        lv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(ExamsActivity.this, ImageCapture.class));

            }
        });



        }


        }



