package com.example.pc044400.logindemo;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

public class GoTeamStats extends AppCompatActivity {
    private ArrayAdapter adapter;

    private SQLiteDatabase db;
    SQLiteOpenHelper openHelper;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_go_team_stats);

        openHelper = new DBHandler(this);

        db = openHelper.getWritableDatabase();


        final String UserName = getIntent().getExtras().getString("userName");

        ListView list = (ListView) findViewById(R.id.listV1);

        ListView list2 = (ListView) findViewById(R.id.listV2);

        final DBHandler dab = new DBHandler(getApplicationContext());

        final List<String> kudos = dab.getAllKudos(" ");

        final List<String> kudolikes = dab.getTopLikes();

        adapter = new ArrayAdapter(this,R.layout.list_item_layout, kudos);
        list.setAdapter(adapter);

        adapter = new ArrayAdapter(this,R.layout.list_item_layout, kudolikes);
        list2.setAdapter(adapter);



        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                dab.setFoundUserName(kudos.get(i));
                db.execSQL("Update kudos set likecnt = likecnt +1 where SearchString = '" + dab.getFoundUserName()+ "'");


                Toast.makeText(getApplicationContext(), "Kudo liked!! ", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(GoTeamStats.this, GoTeamStats.class);
                intent.putExtra("userName", UserName);
                startActivity(intent);

            }
        });

















    }




}
