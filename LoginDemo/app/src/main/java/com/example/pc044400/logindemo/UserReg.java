package com.example.pc044400.logindemo;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class UserReg extends AppCompatActivity {


    SQLiteOpenHelper openHelper;
    SQLiteDatabase db;
    private Button _btnreg, _btnlogin;
    EditText _txtfanme, _txtlname, _txtpass, _txtemail,_txtfname, _txtphone;

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

        _btnreg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                db=openHelper.getWritableDatabase();
                String fname=_txtfname.getText().toString();
                String lname=_txtlname.getText().toString();
                String pass=_txtpass.getText().toString();
                String email=_txtemail.getText().toString();
                String phone=_txtphone.getText().toString();
                insertdata(fname, lname,pass,email,phone);
                Toast.makeText(getApplicationContext(), "register successfully", Toast.LENGTH_LONG).show();

            }
        });
    }






    public void insertdata(String fname, String lname, String pass, String email, String phone){

        ContentValues contentValues = new ContentValues();
        contentValues.put(DBHandler.COL_2, fname);
        contentValues.put(DBHandler.COL_3, lname);
        contentValues.put(DBHandler.COL_4, pass);
        contentValues.put(DBHandler.COL_5, email);
        contentValues.put(DBHandler.COL_6, phone);
        long id = db.insert(DBHandler.TABLE_NAME, null, contentValues);

    }
}




