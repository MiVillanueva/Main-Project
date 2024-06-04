package com.example.mainproject;

import android.content.Context;
import android.widget.ArrayAdapter;

import java.util.ArrayList;

public class ListAdapter extends ArrayAdapter<List_Section> {

    public ListAdapter(Context context,  ArrayList<List_Section> List_SectionArrayList){

        super(context, R.layout.list_item, List_SectionArrayList);

        
    }


}
