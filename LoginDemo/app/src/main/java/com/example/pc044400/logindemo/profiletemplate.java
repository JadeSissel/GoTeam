package com.example.pc044400.logindemo;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class profiletemplate extends AppCompatActivity {
    SQLiteOpenHelper openHelper;
    SQLiteDatabase db;

    private Spinner _spinner;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profiletemplate);
        final String UserName = getIntent().getExtras().getString("UserName");
        final String UserFoundName = getIntent().getExtras().getString("UserFoundName");
        final Button _btnreg = (Button)findViewById(R.id.btnUpdate);
        Toolbar toolbar = (Toolbar) findViewById(R.id.app_bar);
        setSupportActionBar(toolbar);

        final EditText _txtBio = (EditText) findViewById(R.id.etBio);
        //final EditText _txtSkill = (EditText) findViewById(R.id.etSkill);
        Spinner s = (Spinner) findViewById(R.id.spinnerSkills);

        TextView bioView = (TextView) findViewById(R.id.etBio);
        TextView NameView = (TextView) findViewById(R.id.tvName2);
        //TextView Phone = (TextView) findViewById(R.id.)

        openHelper = new DBHandler(this);
        DBHandler dab = new DBHandler(getApplicationContext());
        String Fname = dab.getUDataFname(UserFoundName);
        String Lname = dab.getUDataLname(UserFoundName);

        final String Name = "Name: "+ Fname + " " + Lname;
        final String bio = dab.getBioFoundData(UserFoundName);

        bioView.setText(bio);
        NameView.setText(Name);

        final List<String> skills = dab.getAllSkills(UserFoundName);


        _spinner = (Spinner)findViewById(R.id.spinnerSkills);


        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, skills);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        s.setAdapter(adapter);

        _spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                // your code here

                if (position == skills.size() - 1) {
                    Toast.makeText(getApplicationContext(), "Re-directing to skill creation page", Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(profiletemplate.this, NewSkill.class);
                    intent.putExtra("userName", UserName);
                   // startActivity(intent);

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

                dab.updateBio(UserName,bio);


                Toast.makeText(getApplicationContext(), "updated successfully", Toast.LENGTH_LONG).show();

                Intent intent = new Intent(profiletemplate.this, MyBio.class);
                intent.putExtra("userName", UserName);
                startActivity(intent);

            }
        });



    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId())
        {
            case R.id.action_home:
                Intent intent = new Intent(profiletemplate.this, SecondActivity.class);
                final String UserName = getIntent().getExtras().getString("userName");
                intent.putExtra("userName", UserName);
                startActivity(intent);

                break;
            case R.id.action_logout:
                final String UserNameZ = getIntent().getExtras().getString("userName");
                Toast.makeText(getApplicationContext(), "Logout", Toast.LENGTH_SHORT).show();
                intent = new Intent(profiletemplate.this, MainActivity.class);
                intent.putExtra("userName", UserNameZ);

                startActivity(intent);

                break;
            default:

        }



        return super.onOptionsItemSelected(item);
    }



}
