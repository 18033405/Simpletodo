package sg.edu.rp.c346.simpletodo;

import android.renderscript.ScriptGroup;

import android.support.v7.app.AppCompatActivity;

import android.os.Bundle;

import android.text.InputType;

import android.view.View;

import android.widget.AdapterView;

import android.widget.ArrayAdapter;

import android.widget.Button;

import android.widget.EditText;

import android.widget.ListView;

import android.widget.Spinner;

import android.widget.Toast;



import java.util.ArrayList;



public class MainActivity extends AppCompatActivity {


    EditText etTask;

    Button btnAdd, btnDelete, btnClear;

    ListView lv;

    Spinner spin;

    ArrayList<String> alTask;
    ArrayAdapter<String> aaTask;


    @Override

    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);


        etTask = findViewById(R.id.etTask);

        btnAdd = findViewById(R.id.add);

        btnDelete = findViewById(R.id.delete);

        btnClear = findViewById(R.id.clear);

        lv = findViewById(R.id.listView);

        spin = findViewById(R.id.spinner);


        alTask = new ArrayList<>();

        aaTask = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, alTask);


        lv.setAdapter( aaTask );


        spin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override

            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long id) {

                switch (i) {


                    case 0:

                        etTask.setHint("Type in a new task here");

                        btnAdd.setEnabled(true);

                        btnDelete.setEnabled(false);

                        etTask.setInputType(InputType.TYPE_CLASS_TEXT);

                        break;


                    case 1:

                        etTask.setHint("Type in the index of the task to be removed");

                        btnAdd.setEnabled(false);

                        btnDelete.setEnabled(true);

                        etTask.setInputType(InputType.TYPE_CLASS_NUMBER);

                        break;


                }

            }


            @Override

            public void onNothingSelected(AdapterView<?> adapterView) {


            }

        });


        btnAdd.setOnClickListener(new View.OnClickListener() {

            @Override

            public void onClick(View v) {


                String task = etTask.getText().toString();


                alTask.add(task);


                etTask.setText(null);


                aaTask.notifyDataSetChanged();


            }

        });


        btnDelete.setOnClickListener(new View.OnClickListener() {

            @Override

            public void onClick(View v) {



                if (etTask.getText().toString().equals("")) {


                    Toast.makeText(MainActivity.this, "You don't have any task to remove", Toast.LENGTH_LONG).show();


                    return;
                }

                else if (alTask.isEmpty()) {


                    Toast.makeText(MainActivity.this, "wrong index number", Toast.LENGTH_LONG).show();


                }else{
                    int index = Integer.parseInt(etTask.getText().toString());
                    alTask.remove(index);

                    aaTask.notifyDataSetChanged();


                }


            }


        });


        btnClear.setOnClickListener(new View.OnClickListener() {

            @Override

            public void onClick(View v) {


                alTask.clear();


                aaTask.notifyDataSetChanged();





            }

        });


    }
}



