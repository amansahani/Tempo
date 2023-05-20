package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Switch;
import android.widget.Toast;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    EditText name, age,index;
    Switch sb;
    ListView lv;
    Button rview, delete, Screen;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Screen = findViewById(R.id.Screen);
        index = findViewById(R.id.index);
        rview = findViewById(R.id.view);
        delete = findViewById(R.id.delete);
        lv = findViewById(R.id.lview);
        age = findViewById(R.id.age);
        name = findViewById(R.id.custName);
        sb = findViewById(R.id.sb);


        rview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Model model;
                try {
                    model = new Model(1,
                            name.getText().toString(),
                            Integer.parseInt(age.getText().toString()),
                            sb.isChecked()
                    );
                    DB db = new DB(MainActivity.this);
                    boolean flag = db.addOne(model);

                }catch (Exception e){
                    Toast.makeText(MainActivity.this, "Error! or Add All Fields.", Toast.LENGTH_SHORT).show();

                }

                Toast.makeText(MainActivity.this, "Added Customer", Toast.LENGTH_SHORT).show();
                DB db = new DB(MainActivity.this);
                List<Model> everyone = db.getEveryone();

                ArrayAdapter<Model> everyoneAdapter = new ArrayAdapter<Model>(MainActivity.this, android.R.layout.simple_list_item_1, everyone);
                lv.setAdapter(everyoneAdapter);

            }
        });

    sb.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            boolean flag = sb.isChecked();
            if(flag){
                sb.setText("Active Customer");
            }else{
                sb.setText("Inactive Customer");
            }
        }
    });

    delete.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            int deleteIndex = Integer.parseInt(index.getText().toString());
            DB db = new DB(MainActivity.this);
            boolean flag = db.delete(deleteIndex);
            if(flag){
                Toast.makeText(MainActivity.this, "Deleted", Toast.LENGTH_SHORT).show();
                db = new DB(MainActivity.this);
                List<Model> everyone = db.getEveryone();

                ArrayAdapter<Model> everyoneAdapter = new ArrayAdapter<Model>(MainActivity.this, android.R.layout.simple_list_item_1, everyone);
                lv.setAdapter(everyoneAdapter);
            }
            else
                Toast.makeText(MainActivity.this, "Failed", Toast.LENGTH_SHORT).show();
        }
    });


        Screen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, MainActivity2.class);
                startActivity(intent);
            }
        });

    }
}