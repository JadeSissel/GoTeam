package com.example.pc044400.logindemo;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

    //LoginVariables

    private EditText UserName;
    private EditText Password;
    private Button Login;
    private Button Register;
    private TextView Info;
    SQLiteDatabase db;
    SQLiteOpenHelper openHelper;
    Cursor cursor;
    Button _btnLogin;
    EditText _txtEmail, _txtPass;
    int counter = 5;

//Triggers when this page is created - log in page
   @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.app_bar);
        setSupportActionBar(toolbar);

//Asking user input
       _txtEmail=(EditText)findViewById(R.id.EtUsername);
       _txtPass=(EditText)findViewById(R.id.EtPassword);
       _btnLogin=(Button)findViewById(R.id.btnLogin);

       openHelper = new DBHandler(this);

       db = openHelper.getReadableDatabase();



       Register = (Button)findViewById(R.id.btnRegister);

       Login = (Button)findViewById(R.id.btnLogin);



// When you hit the register button it will direct you to a new page
       Register.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               Intent intent = new Intent(MainActivity.this, UserReg.class);//Intend to go to a new registration activity
               //intent.putExtra("userName", userName);
               startActivity(intent);


           }
       });

// Upon clicking login it's going to query DB to see if it's valid
        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                    String email = _txtEmail.getText().toString();
                    String pass = _txtPass.getText().toString();

                cursor = db.rawQuery("SELECT * FROM " + DBHandler.TABLE_NAME + " WHERE " + DBHandler.COL_5 + " =? AND " + DBHandler.COL_4 + " =? ", new String[]{email, pass});
                    if (cursor != null) {
                        if (cursor.getCount() > 0) {
                            Toast.makeText(getApplicationContext(), "Login Success", Toast.LENGTH_SHORT).show();

                            Intent intent = new Intent(MainActivity.this, SecondActivity.class);
                            intent.putExtra("userName", email);
                            startActivity(intent);
                        } else {
                            Toast.makeText(getApplicationContext(), "Login error", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
            });




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
               Intent intent = new Intent(MainActivity.this, SecondActivity.class);
               startActivity(intent);

               break;
           case R.id.action_logout:
              Toast.makeText(getApplicationContext(), "Logout", Toast.LENGTH_SHORT).show();
               intent = new Intent(MainActivity.this, MainActivity.class);
               startActivity(intent);

               break;
           default:

       }



       return super.onOptionsItemSelected(item);
    }

    private void goToReg(String userName)
    {

       // if((userName.equals("Admin")) )
       // {

            Intent intent = new Intent(MainActivity.this, UserReg.class);
            intent.putExtra("userName", userName);
            startActivity(intent);


       //A }
        /*else{

            Info.setText("Incorrect password");


        }*/
    }

    private void validateLogin(String userName, String userPassword)
    {

        if((userName.equals("Admin")) && (userPassword.equals("1234")))
        {

            Intent intent = new Intent(MainActivity.this, SecondActivity.class);
            intent.putExtra("userName", userName);
            startActivity(intent);


       }
        else{

            Info.setText("Credentials provided are invalid.");


        }
    }


}
