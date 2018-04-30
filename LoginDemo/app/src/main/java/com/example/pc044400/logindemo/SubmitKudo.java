package com.example.pc044400.logindemo;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.Date;
import java.util.List;

public class SubmitKudo extends AppCompatActivity {
    SQLiteOpenHelper openHelper;
    SQLiteDatabase db;
    private Button _btnSubKudo;
    EditText _txtKudoDesc, _txtDateIn;

    private Spinner _spinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_submit_kudo);

        openHelper = new DBHandler(this);
        DBHandler dab = new DBHandler(getApplicationContext());

        final String UserName = getIntent().getExtras().getString("userName");
        final Spinner s = (Spinner) findViewById(R.id.spnTeamMates);

        //gather all current team mates
        final List<String> names = dab.getAllTeamMates(dab.getCurTeam(UserName));

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, names);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //bind the names to the list
        s.setAdapter(adapter);

        _txtKudoDesc = (EditText) findViewById(R.id.etKudoDescription);
        _txtDateIn = (EditText)findViewById(R.id.etDate);
        //hard coded date, user can change at leisure to format as desired
        _txtDateIn.setText("04162018");
        _btnSubKudo = (Button)findViewById(R.id.btnSubKudo);

        _btnSubKudo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                db=openHelper.getWritableDatabase();
                String Desc = _txtKudoDesc.getText().toString();
                String DateIn = _txtDateIn.getText().toString();
                String ToUserName = s.getSelectedItem().toString();
                // String Mname= _txtManName.getText().toString();
                insertdata(Desc,UserName,ToUserName,DateIn);
                Toast.makeText(getApplicationContext(), "Kudo created successfully", Toast.LENGTH_LONG).show();

                Intent intent = new Intent(SubmitKudo.this, SecondActivity.class);
                intent.putExtra("userName", UserName);
                startActivity(intent);

            }
        });
    }



    public void insertdata(String desc, String Fusername , String Tusername , String curdatetime ){

//        insert row for kudo
        String search = desc + " from " + Fusername+ " to "+Tusername+" on "+curdatetime;
        ContentValues contentValues = new ContentValues();
        contentValues.put(DBHandler.COL_11, desc);
        contentValues.put(DBHandler.COL_12, Fusername);
        contentValues.put(DBHandler.COL_13, Tusername);
        contentValues.put(DBHandler.COL_14, curdatetime);
        contentValues.put(DBHandler.COL_15, search);
        contentValues.put(DBHandler.COL_16, 0);
        long id = db.insert(DBHandler.KUDOS_TABLE_NAME, null, contentValues);

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
                Intent intent = new Intent(SubmitKudo.this, SecondActivity.class);
                final String UserName = getIntent().getExtras().getString("userName");
                intent.putExtra("userName", UserName);
                startActivity(intent);

                break;
            case R.id.action_logout:
                final String UserNameZ = getIntent().getExtras().getString("userName");
                Toast.makeText(getApplicationContext(), "Logout", Toast.LENGTH_SHORT).show();
                intent = new Intent(SubmitKudo.this, MainActivity.class);
                intent.putExtra("userName", UserNameZ);

                startActivity(intent);

                break;
            default:

        }



        return super.onOptionsItemSelected(item);
    }

}
