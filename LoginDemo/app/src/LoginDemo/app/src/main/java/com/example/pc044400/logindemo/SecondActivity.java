package com.example.pc044400.logindemo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

public class SecondActivity extends AppCompatActivity {

    //logged in home page, profile page

    private Button _btnGoTeam;
    private Button _btnMyBio;
    private Button _btnSubmitKudo;
    private Button _btnMyTeam;









    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        _btnGoTeam = (Button)findViewById(R.id.btnGoTeam);
        _btnMyBio = (Button)findViewById(R.id.btnMyBio);
        _btnSubmitKudo = (Button)findViewById(R.id.btnSubmitKudo);
        _btnMyTeam = (Button)findViewById(R.id.btnMyTeam);



        TextView nameView = (TextView) findViewById(R.id.tvWelcome);

        final String UserName = getIntent().getExtras().getString("userName");
        DBHandler dab = new DBHandler(getApplicationContext());

        dab.setCurUserName(UserName);
        nameView.setText(UserName + "'s Profile Page " );


        _btnMyBio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SecondActivity.this, MyBio.class);//Intend to go to a new registration activity
                intent.putExtra("userName", UserName);
                startActivity(intent);

            }
        });

        _btnGoTeam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SecondActivity.this, GoTeamStats.class);//Intend to go to a new registration activity

                startActivity(intent);

            }
        });


        _btnSubmitKudo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SecondActivity.this, SubmitKudo.class);//Intend to go to a new registration activity

                startActivity(intent);

            }
        });

        _btnMyTeam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SecondActivity.this, MyTeam.class);//Intend to go to a new registration activity

                startActivity(intent);

            }
        });











    }
}
