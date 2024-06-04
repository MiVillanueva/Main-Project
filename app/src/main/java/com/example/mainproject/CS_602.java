package com.example.mainproject;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;



public class CS_602 extends AppCompatActivity {
    ArrayAdapter arrayAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cs602);

        ListView listView = findViewById(R.id.listView2);
        List<String> list = new ArrayList<>();

        list.add("Jairo Sibal");
        list.add("Miguel Villanueva");
        list.add("Dayzerlyn Daine Vargas");
        list.add("Ramesh Kumar");
        list.add("Patrick Javier");
        list.add("Patricia Lei Roldan");
        list.add("Koleen Ciacico");
        list.add("...");



        ArrayAdapter arrayAdapter = new ArrayAdapter(getApplicationContext(), android.R.layout.simple_list_item_1, list);



        listView.setAdapter(arrayAdapter);


    }
}