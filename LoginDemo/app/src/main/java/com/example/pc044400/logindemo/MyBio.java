package com.example.pc044400.logindemo;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MyBio extends AppCompatActivity {
    SQLiteOpenHelper openHelper;
    SQLiteDatabase db;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_bio);
        final String UserName = getIntent().getExtras().getString("userName");
        final Button _btnreg = (Button)findViewById(R.id.btnUpdate);
        final EditText _txtBio = (EditText) findViewById(R.id.etBio);


        TextView nameView = (TextView) findViewById(R.id.etBio);

        openHelper = new DBHandler(this);
        DBHandler dab = new DBHandler(getApplicationContext());


        final String bio = dab.getBioData(UserName);

        nameView.setText(bio);



        _btnreg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                db = openHelper.getWritableDatabase();
                DBHandler dab = new DBHandler(getApplicationContext());

                String bio= _txtBio.getText().toString();
                dab.updateBio(UserName,bio);
                Toast.makeText(getApplicationContext(), "updated successfully", Toast.LENGTH_LONG).show();

                Intent intent = new Intent(MyBio.this, MyBio.class);
                intent.putExtra("userName", UserName);
                startActivity(intent);

            }
        });




    }




}
