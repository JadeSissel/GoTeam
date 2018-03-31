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
import android.widget.Spinner;
import android.widget.Toast;

public class TeamReg extends AppCompatActivity {

    SQLiteOpenHelper openHelper;
    SQLiteDatabase db;
    private Button _btnreg;
    EditText _txtTeamName, _txtManName;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_team_reg);

        openHelper = new DBHandler(this);
        _txtTeamName = (EditText) findViewById(R.id.etTeamName);
        _txtManName = (EditText)findViewById(R.id.etManagerName);
        _btnreg = (Button)findViewById(R.id.btnSubmit);

        _btnreg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                db=openHelper.getWritableDatabase();
                String Tname= _txtTeamName.getText().toString();
                String Mname= _txtManName.getText().toString();
                insertdata(Tname, Mname);
                Toast.makeText(getApplicationContext(), "Team created successfully", Toast.LENGTH_LONG).show();

                Intent intent = new Intent(TeamReg.this, UserReg.class);
                startActivity(intent);

            }
        });


    }


    public void insertdata(String tname, String mname){

        ContentValues contentValues = new ContentValues();
        contentValues.put(DBHandler.TEAM_COL_1, tname);
        contentValues.put(DBHandler.TEAM_COL_2, mname);
        long id = db.insert(DBHandler.TEAM_TABLE_NAME, null, contentValues);

    }


}
