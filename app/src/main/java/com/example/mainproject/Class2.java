package com.example.mainproject;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class Class2 extends AppCompatActivity {

    Button button;
    LinearLayout Layout;

    AlertDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_class2);
        buildDialog();
        Layout = findViewById(R.id.container);


        button = findViewById(R.id.AddClass);


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                dialog.show();

            }
        });

    }



    private void buildDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        View view = getLayoutInflater().inflate(R.layout.layout_student, null);
        EditText name = view.findViewById(R.id.studentName);

        builder.setView(view)
                .setTitle("Student Name")
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();

                    }
                })

                .setPositiveButton("Add", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        addcard(name.getText().toString());


                    }

                });


        dialog = builder.create();
    }
    private void addcard(String btnClass) {

        View view = getLayoutInflater().inflate(R.layout.list_student, null);
        TextView nameView = view.findViewById(R.id.className);

        nameView.setText(btnClass);


        Layout.addView(view);


    }
}