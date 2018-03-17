package com.example.pc044400.logindemo;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.List;
import java.util.jar.Attributes;

public class MyBio extends AppCompatActivity {
    SQLiteOpenHelper openHelper;
    SQLiteDatabase db;

    private Spinner _spinner;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_bio);
        final String UserName = getIntent().getExtras().getString("userName");
        final Button _btnreg = (Button)findViewById(R.id.btnUpdate);
        final Button _btnHome = (Button)findViewById(R.id.button);

        final EditText _txtBio = (EditText) findViewById(R.id.etBio);
        //final EditText _txtSkill = (EditText) findViewById(R.id.etSkill);
        Spinner s = (Spinner) findViewById(R.id.spinner);

        TextView bioView = (TextView) findViewById(R.id.etBio);
        TextView NameView = (TextView) findViewById(R.id.etName);
        //TextView Phone = (TextView) findViewById(R.id.)

        openHelper = new DBHandler(this);
        DBHandler dab = new DBHandler(getApplicationContext());
        String Fname = dab.getUDataFname(UserName);
        String Lname = dab.getUDataLname(UserName);

        final String Name = Fname + " " + Lname;
        final String bio = dab.getBioData(UserName);

        bioView.setText(bio);
        NameView.setText(Name);

        final List<String> labels = dab.getAllTeams();


        _spinner = (Spinner)findViewById(R.id.spinner);


        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, labels);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        s.setAdapter(adapter);

        _spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                               @Override
                                               public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                                                   // your code here

                                                   if (position == labels.size() - 1) {
                                                       Toast.makeText(getApplicationContext(), "Re-directing to skill creation page", Toast.LENGTH_LONG).show();
                                                       Intent intent = new Intent(MyBio.this, NewSkill.class);

                                                       startActivity(intent);

                                                   }
                                                   ;
                                               }

                                               @Override
                                               public void onNothingSelected(AdapterView<?> adapterView) {

                                               }
                                           });
        _btnreg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                db = openHelper.getWritableDatabase();
                DBHandler dab = new DBHandler(getApplicationContext());

                String bio= _txtBio.getText().toString();
                String skill = _spinner.getSelectedItem().toString();
                dab.updateBio(UserName,bio,skill);


                Toast.makeText(getApplicationContext(), "updated successfully", Toast.LENGTH_LONG).show();

                Intent intent = new Intent(MyBio.this, MyBio.class);
                intent.putExtra("userName", UserName);
                startActivity(intent);

            }
        });

        _btnHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MyBio.this, SecondActivity.class);//Intend to go to a new registration activity
                intent.putExtra("userName", UserName);
                startActivity(intent);

            }
        });



    }




}
