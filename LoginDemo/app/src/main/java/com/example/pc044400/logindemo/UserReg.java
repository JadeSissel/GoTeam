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
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;


public class UserReg extends AppCompatActivity {


    SQLiteOpenHelper openHelper;
    SQLiteDatabase db;
    private Button _btnreg, _btnlogin;
    EditText _txtfanme, _txtlname, _txtpass, _txtemail,_txtfname, _txtphone;
    private Spinner _spinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_reg);

        openHelper = new DBHandler(this);
        _txtfname = (EditText) findViewById(R.id.txtfname);
        _txtlname = (EditText)findViewById(R.id.txtlname);
        _txtpass = (EditText)findViewById(R.id.txtpass);
        _txtphone = (EditText)findViewById(R.id.txtphone);
        _txtemail = (EditText)findViewById(R.id.txtemail);
        _btnreg = (Button)findViewById(R.id.btnReg);
        _spinner = (Spinner)findViewById(R.id.TeamList);
        Spinner s = (Spinner) findViewById(R.id.TeamList);


        DBHandler dab = new DBHandler(getApplicationContext());


        final List<String> labels = dab.getAllTeams();




        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, labels);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        s.setAdapter(adapter);

        _spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                // your code here

                    if(position == labels.size() -1 )
                    {
                        Toast.makeText(getApplicationContext(), "Re-directing to team creation page", Toast.LENGTH_LONG).show();
                        Intent intent = new Intent(UserReg.this, TeamReg.class);

                        startActivity(intent);

                    };
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // your code here
            }

        });


        _btnreg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                db = openHelper.getWritableDatabase();
                String fname=_txtfname.getText().toString();
                String lname=_txtlname.getText().toString();
                String pass=_txtpass.getText().toString();
                String email=_txtemail.getText().toString();
                String phone=_txtphone.getText().toString();
                String team = _spinner.getSelectedItem().toString();
                insertdata(fname, lname,pass,email,phone,team);
                Toast.makeText(getApplicationContext(), "register successfully", Toast.LENGTH_LONG).show();

                Intent intent = new Intent(UserReg.this, MainActivity.class);
                intent.putExtra("userName", email);
                startActivity(intent);

            }
        });
    }






    public void insertdata(String fname, String lname, String pass, String email, String phone, String team){

        //subroutine handles inserting a new person
        ContentValues contentValues = new ContentValues();
        String search = fname+" "+lname;
        contentValues.put(DBHandler.COL_2, fname);
        contentValues.put(DBHandler.COL_3, lname);
        contentValues.put(DBHandler.COL_4, pass);
        contentValues.put(DBHandler.COL_5, email);
        contentValues.put(DBHandler.COL_6, phone);
        contentValues.put(DBHandler.COL_7, team);
        contentValues.put(DBHandler.COL_8, " Enter your Bio!");
        contentValues.put(DBHandler.COL_15, search);
        long id = db.insert(DBHandler.TABLE_NAME, null, contentValues);

    }



}




