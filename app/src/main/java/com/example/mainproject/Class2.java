package com.example.mainproject;


import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class Class2 extends AppCompatActivity {

    private LinearLayout container;
    private Button addClassButton;
    private ArrayList<Student> students = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_class0);

        container = findViewById(R.id.container);
        addClassButton = findViewById(R.id.AddClass);

        addClassButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addStudent();
            }
        });

        loadStudents();
    }

    private void addStudent() {
        final EditText studentName = new EditText(this);
        studentName.setHint("Enter student name");

        Button saveButton = new Button(this);
        saveButton.setText("Save");

        final LinearLayout layout = new LinearLayout(this);
        layout.setOrientation(LinearLayout.HORIZONTAL);
        layout.addView(studentName);
        layout.addView(saveButton);

        container.addView(layout);

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = studentName.getText().toString();
                if (!name.isEmpty()) {
                    Student student = new Student(name);
                    students.add(student);
                    container.removeView(layout);
                    displayStudent(student);
                }
            }
        });
    }

    private void displayStudent(final Student student) {
        final TextView studentTextView = new TextView(this);
        studentTextView.setText(student.getName());

        Button updateButton = new Button(this);
        updateButton.setText("Update");

        Button deleteButton = new Button(this);
        deleteButton.setText("Delete");

        final LinearLayout layout = new LinearLayout(this);
        layout.setOrientation(LinearLayout.HORIZONTAL);
        layout.addView(studentTextView);
        layout.addView(updateButton);
        layout.addView(deleteButton);

        container.addView(layout);

        updateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final EditText editName = new EditText(Class2.this);
                editName.setText(student.getName());

                Button saveButton = new Button(Class2.this);
                saveButton.setText("Save");

                final LinearLayout editLayout = new LinearLayout(Class2.this);
                editLayout.setOrientation(LinearLayout.HORIZONTAL);
                editLayout.addView(editName);
                editLayout.addView(saveButton);

                container.removeView(layout);
                container.addView(editLayout);

                saveButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String newName = editName.getText().toString();
                        if (!newName.isEmpty()) {
                            student.setName(newName);
                            container.removeView(editLayout);
                            displayStudent(student);
                        }
                    }
                });
            }
        });

        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                students.remove(student);
                container.removeView(layout);
            }
        });
    }

    private void loadStudents() {
        // Load students from storage or database if needed
        for (Student student : students) {
            displayStudent(student);
        }
    }

    private static class Student {
        private String name;

        public Student(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
