package com.example.pc044400.logindemo;

import android.content.Intent;
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
       final DBHandler dab = new DBHandler(getApplicationContext());

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


    list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

            dab.setFoundUserName(names.get(i));

            Toast.makeText(getApplicationContext(), dab.getFoundUserName(), Toast.LENGTH_SHORT).show();

         //   Intent intent = new Intent(MyTeam.this, UserBio.class);
          //  startActivity(intent);

        }
    });



    }
}
