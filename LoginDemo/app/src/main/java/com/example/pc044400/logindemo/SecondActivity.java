package com.example.pc044400.logindemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.w3c.dom.Text;

public class SecondActivity extends AppCompatActivity {

    //logged in home page, profile page

    private EditText UserName;
    private EditText Password;
    private Button Login;







    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);


        TextView nameView = (TextView) findViewById(R.id.tvStatus);

        nameView.setText("testing of the variable to commence");
        String UserName = getIntent().getExtras().getString("userName");
        nameView.setText("My Profile Page");
    }
}
