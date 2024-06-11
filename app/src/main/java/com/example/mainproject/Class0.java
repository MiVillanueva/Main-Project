package com.example.mainproject;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.mainproject.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.Collections;

public class Class0 extends AppCompatActivity {
    private LinearLayout container;
    private Button addClassButton;
    private ArrayList<Student> students = new ArrayList<>();
    private AlertDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_class0);

        container = findViewById(R.id.container);
        addClassButton = findViewById(R.id.AddClass);

        addClassButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog();
            }
        });

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigationView);
        bottomNavigationView.setOnNavigationItemSelectedListener(navListener);

        loadStudents();
    }

    private void showDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        View view = getLayoutInflater().inflate(R.layout.layout_student, null);
        EditText studentName = view.findViewById(R.id.studentName);

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
                        String name = studentName.getText().toString().trim();
                        if (!name.isEmpty()) {
                            addStudent(name);
                        }
                    }
                });

        dialog = builder.create();
        dialog.show();
    }

    private void addStudent(String name) {
        Student student = new Student(name);
        students.add(student);
        displayStudentsInAlphabeticalOrder();
    }

    private void displayStudentsInAlphabeticalOrder() {
        Collections.sort(students, (s1, s2) -> s1.getName().compareToIgnoreCase(s2.getName()));
        container.removeAllViews();
        for (Student student : students) {
            displayStudent(student);
        }
    }

    private void displayStudent(final Student student) {
        TextView studentTextView = new TextView(this);
        studentTextView.setText(student.getName());

        Button updateButton = new Button(this);
        updateButton.setText("Update");

        Button deleteButton = new Button(this);
        deleteButton.setText("Delete");

        LinearLayout layout = new LinearLayout(this);
        layout.setOrientation(LinearLayout.HORIZONTAL);
        layout.addView(studentTextView);
        layout.addView(updateButton);
        layout.addView(deleteButton);

        container.addView(layout);

        updateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showUpdateDialog(student);
            }
        });

        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                students.remove(student);
                displayStudentsInAlphabeticalOrder();
            }
        });
    }

    private void showUpdateDialog(final Student student) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        View view = getLayoutInflater().inflate(R.layout.layout_student, null);
        EditText editName = view.findViewById(R.id.studentName);
        editName.setText(student.getName());

        builder.setView(view)
                .setTitle("Update Student Name")
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                })
                .setPositiveButton("Update", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String newName = editName.getText().toString().trim();
                        if (!newName.isEmpty()) {
                            student.setName(newName);
                            displayStudentsInAlphabeticalOrder();
                        }
                    }
                });

        AlertDialog updateDialog = builder.create();
        updateDialog.show();
    }

    private void loadStudents() {
        // Load students from storage or database if needed
        // Assuming students are already loaded and sorted alphabetically
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

    private BottomNavigationView.OnNavigationItemSelectedListener navListener =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    int id = item.getItemId();

                    if (id == R.id.Home) {
                        // Handle Home navigation
                        return true;
                    } else if (id == R.id.classes) {
                        // Handle Classes navigation
                        return true;
                    } else if (id == R.id.scans) {
                        // Handle Scans navigation
                        return true;
                    }
                    return false;
                }
            };
}
