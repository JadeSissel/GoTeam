package com.example.pc044400.logindemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class MyTeam extends AppCompatActivity {

    private ArrayAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_team);


        ListView list = (ListView) findViewById(R.id.listView1);
        EditText theFilter = (EditText) findViewById(R.id.searchFilter);
        DBHandler dab = new DBHandler(getApplicationContext());

        final List<String> names = dab.getAllTeamMates(dab.getCurTeam(dab.getCurUserName()));



        adapter = new ArrayAdapter(this,R.layout.list_item_layout, names);
        list.setAdapter(adapter);

        theFilter.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                (MyTeam.this).adapter.getFilter().filter(charSequence);
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });





    }
}
