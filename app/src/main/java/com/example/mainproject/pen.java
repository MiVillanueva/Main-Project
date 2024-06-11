package com.example.mainproject;


import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import com.example.mainproject.Class0;
public class pen extends AppCompatActivity {

    ImageView iv;
    Button button, button1;


    LinearLayout Layout;


    AlertDialog dialog;



    private BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pen);





        Layout = findViewById(R.id.container);


        buildDialog();


        // binding = ActivityMainBinding.inflate(getLayoutInflater());
        // setContentView(binding.getRoot());


        //  iv = findViewById(R.id.scanner);

        button = findViewById(R.id.AddClass);

        bottomNavigationView = findViewById(R.id.bottomNavigationView);


        // iv.setOnClickListener(new View.OnClickListener() {
        //   @Override
        //  public void onClick(View view) {
        // startActivity(new Intent(pen.this, Scan.class));
        // }

        //}


        //This is  a code to that adds functionality to the bottom navigation view.

        bottomNavigationView.setOnItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();

                if (id == R.id.Home) {
                    startActivity(new Intent(pen.this, HomeActivity.class));
                }
                else if (id == R.id.classes){
                    startActivity(new Intent(pen.this, pen.class));
                }
                else if (id == R.id.scans){
                    startActivity(new Intent(pen.this, Scan.class));
                }
                return false;
            }

        });


        //Logout button to log in module
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                dialog.show();



            }
        });




   /*     setupData();

        setupList();

        setUpOnClickListener();
    }

    private void setupData() {

    }


    private void setupList() {

        listView = (ListView) findViewById(R.id.list_item);

        //ListAdapter adapter = new ListAdapter(getApplicationContext(),0 list_sections);
//        listView.setAdapter(adapter);

    }


    private void setUpOnClickListener() {
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                List_Section select = (List_Section) (listView.getItemAtPosition(position));
                Intent showDetail = new Intent(getApplicationContext(), List_Section.class);
            }
        });
    }

*/

        //String[] className = {"BSCS - 601", "BSIT - 401", "BSTM -701", "BSCRIM - 201", "BSCRIM - 201", "BSCRIM - 201"};

        //String[] yeaLevel = {"3rd Year", "2nd Year", "4th year", "1st year", "1st year", "1st year"};

        // listView = findViewById(R.layout.list_item);

        // ArrayList<List_Section> list_SectionArrayList = new ArrayList<>();

        // for (int i = 0; i < className.length; i++) {

        //   list_section = new List_Section(className[i], yeaLevel[i]);

        // list_sections.add(list_section);

     /*    ListView listView = findViewById(R.id.list_item);
        List<String> list = new ArrayList<>();

        list.add("BSCS - 601");
       list.add("BSCS - 602");
        list.add("BSCRIM - 201");
        list.add("BSIT - 204");
        list.add("BSIT - 205");
        list.add("BSIT - 206");
        list.add("BSIT - 207");
        list.add("BSIT - 208");


        ArrayAdapter arrayAdapter = new ArrayAdapter(getApplicationContext(), android.R.layout.simple_list_item_1, list);


        listView.setAdapter(arrayAdapter);

        listView.setClickable(true);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (position == 0) {
                    startActivity(new Intent(pen.this, CS_601.class));
                }
                if (position == 1) {
                    startActivity(new Intent(pen.this, CS_602.class));
                }
            }


        });
        //    list_SectionArrayList.add(list_section);
        //  }

        //    ListAdapter listAdapter = new ListAdapter(pen.this, list_SectionArrayList);

        //  binding.listview.setAdapter(listAdapter);
    }
  /*  public void order(View v){
//Create excel to android app
        try {
            AssetManager am = getAssets();
            InputStream is = am.open("Book1.xls");
            Workbook wb = Workbook.getWorkbook(is);
            Sheet s = wb.getSheet(0);
            int row =s.getRows();
            int col = s.getColumns();
            String xx="";

            for (int i=0; i<row; i++){
                for(int c=0; c<col; c++){
                    Cell z  =s.getCell(c,i);
                     xx= xx+z.getContents();

                }
                xx=xx +"\n";
            }
            displayy(xx);


        }catch (Exception e){

        }

    }
        public void displayy (String value){

            TextView x = (TextView) findViewById(R.id.textView23);
            x.setText(value);
       }*/

    }


    public void openDialog() {
        ExampleDialog exampleDialog = new ExampleDialog();
        exampleDialog.show(getSupportFragmentManager(), "ExampleDialog");


    }


//    @Override
    //  public void applyTexts(String input) {


    //  ListView listView = findViewById(R.id.list_item);


    //  ArrayList<String> list = new ArrayList<>();


    //   ArrayAdapter arrayAdapter = new ArrayAdapter(getApplicationContext(), android.R.layout.simple_list_item_1, list);


    //    listView.setAdapter(arrayAdapter);


    //   list.add(input);


    // list.add("BSCS - 602");
    // list.add("BSCRIM - 201");
    // list.add("BSIT - 204");
    // list.add("BSIT - 205");
    // list.add("BSIT - 206");
    // list.add("BSIT - 207");
    // list.add("BSIT - 208");


       /* listView.setClickable(true);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (position == 0) {

                    startActivity(new Intent(pen.this, CS_601.class));
                }
                if (position == 1) {

                    startActivity(new Intent(pen.this, CS_602.class));
                }
            }


        });*/

    private void buildDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        View view = getLayoutInflater().inflate(R.layout.layout_dialog, null);
        EditText name = view.findViewById(R.id.btnClass);
        EditText yearlvl = view.findViewById(R.id.yrlvl);




        builder.setView(view)
                .setTitle("Add Class Name")
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();

                    }
                })

                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        addcard(name.getText().toString(), yearlvl.getText().toString());




                    }

                });

        dialog = builder.create();



    }



    private void addcard(String btnClass, String yrlvl) {

        View view = getLayoutInflater().inflate(R.layout.list_item, null);
        TextView nameView = view.findViewById(R.id.className);
        TextView yearLevel = view.findViewById(R.id.yearLevel);

        nameView.setText(btnClass);
        yearLevel.setText(yrlvl);


        Layout.addView(view);

        view.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {

                Layout.removeView(view);
                return true;
            }
        });




        for (int i = 0; i < Layout.getChildCount(); i++) {
            View childView = Layout.getChildAt(i);




        childView.setClickable(true);
            int finalI = i;
            childView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (finalI == 0) {
                    startActivity(new Intent(pen.this, Class0.class));

                } else if (finalI == 1) {
                    startActivity(new Intent(pen.this, Class1.class));
                } else if (finalI == 2) {
                    startActivity(new Intent(pen.this, Class2.class));
                } else if (finalI == 3) {
                    startActivity(new Intent(pen.this, Class3.class));
                }
            }

        });



          /*  for (int c = 0; c < Layout.getChildCount(); i++) {
                View childView2 = Layout.getChildAt(i);


                childView2.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {

                    int finalC = c;
                    if (finalC == 0) ;
                    {
                        Layout.removeView(view);
                    }
                    {
                        return true;
                    }
                }
            });
*/

}

        }


        }









        












