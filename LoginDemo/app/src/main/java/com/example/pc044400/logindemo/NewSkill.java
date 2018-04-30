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
import android.widget.Toast;

public class NewSkill extends AppCompatActivity {

    SQLiteOpenHelper openHelper;
    SQLiteDatabase db;
    private Button _btnreg;
    EditText _txtTeamName, _txtManName;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_new_skill);
        final String UserNameZ = getIntent().getExtras().getString("userName");

        openHelper = new DBHandler(this);
        _txtTeamName = (EditText) findViewById(R.id.etSkillName);
        _txtManName = (EditText)findViewById(R.id.etManagerName);
        _btnreg = (Button)findViewById(R.id.btnSubmit);

        _btnreg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                db=openHelper.getWritableDatabase();
                String Sname = _txtTeamName.getText().toString();
               // String Mname= _txtManName.getText().toString();
                insertdata(Sname,UserNameZ);
                Toast.makeText(getApplicationContext(), "Skill created successfully", Toast.LENGTH_LONG).show();

                Intent intent = new Intent(NewSkill.this, MyBio.class);
                intent.putExtra("userName", UserNameZ);
                startActivity(intent);

            }
        });


    }


    public void insertdata(String tname, String username){

        ContentValues contentValues = new ContentValues();
        contentValues.put(DBHandler.COL_9, tname);
        contentValues.put(DBHandler.COL_10, username);
        long id = db.insert(DBHandler.SKILLS_TABLE_NAME, null, contentValues);

    }


}
