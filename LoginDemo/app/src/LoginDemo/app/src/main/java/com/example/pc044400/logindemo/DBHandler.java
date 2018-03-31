package com.example.pc044400.logindemo;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.List;


public class DBHandler extends SQLiteOpenHelper {
    public static String FoundUserName = " ";
    public static String CurUserName = " ";
    public static String CurTeam = " ";
    public static final String DATABASE_NAME="GoTeam.db";
    public static final String TABLE_NAME="person";
    public static final String COL_1="ID";
    public static final String COL_2="FirstName";
    public static final String COL_3="LastName";
    public static final String COL_4="Password";
    public static final String COL_5="Email";
    public static final String COL_6="Phone";
    public static final String COL_7="Team";
    public static final String COL_8="BIO";
    public static final String COL_9="Skills";

    public static final String TEAM_TABLE_NAME = "team";
    public static final String TEAM_COL_1 = "TeamName";
    public static final String TEAM_COL_2 = "ManagerName";


    public DBHandler(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME); //Drop older table if exists

        db.execSQL("CREATE TABLE person (ID INTEGER PRIMARY KEY AUTOINCREMENT , FirstName TEXT , LastName TEXT, Password TEXT , Email TEXT , Phone TEXT , Team TEXT , BIO TEXT NOT NULL, Skills TEXT ) ");

        db.execSQL("CREATE TABLE team (ID INTEGER PRIMARY KEY AUTOINCREMENT , TeamName TEXT , ManagerName TEXT ) " );


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME); //Drop older table if exists
        db.execSQL("DROP TABLE IF EXISTS " + TEAM_TABLE_NAME); //Drop older table if exists
        onCreate(db);
    }


    /**
     * Getting all labels
     * returns list of labels
     * */
    public List<String> getAllTeams() {
        List<String> list = new ArrayList<String>();

        // Select All Query
        String selectQuery = "SELECT  * FROM " + TEAM_TABLE_NAME;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);//selectQuery,selectedArguments
        list.add("Select a team from below: ");
        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                list.add(cursor.getString(1));//adding 2nd column data
            } while (cursor.moveToNext());
        }
        list.add("Create a new team!");
        // closing connection
        cursor.close();
        db.close();

        // returning lables
        return list;
    }


       public String getBioData(String p) {
        String bio = new String();

        // Select All Query
        String selectQuery = "SELECT BIO FROM " + TABLE_NAME + " WHERE Email = '" + p +"' ";

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);//selectQuery,selectedArguments
           cursor.moveToFirst();
           if(!cursor.isNull(0)){
              bio = cursor.getString(cursor.getColumnIndex("BIO"));
               cursor.close();
           }else
           {
                bio = "n/a";
           }
        // looping through all rows and adding to list


        // closing connection
        db.close();

        // returning lables
        return bio;
    }

    public String getUDataFname(String p) {
        String Fname = new String();

        // Select All Query
        String selectQuery = "SELECT FirstName FROM " + TABLE_NAME + " WHERE Email = '" + p +"' ";

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);//selectQuery,selectedArguments
        cursor.moveToFirst();
        if(!cursor.isNull(0)){
            Fname = cursor.getString(cursor.getColumnIndex("FirstName"));
            cursor.close();
        }else
        {
            Fname = "n/a";
        }
        // looping through all rows and adding to list


        // closing connection
        db.close();

        // returning lables
        return Fname;
    }

    public String getUDataLname(String p) {
        String Lname = new String();

        // Select All Query
        String selectQuery = "SELECT LastName FROM " + TABLE_NAME + " WHERE Email = '" + p +"' ";

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);//selectQuery,selectedArguments
        cursor.moveToFirst();
        if(!cursor.isNull(0)){
            Lname = cursor.getString(cursor.getColumnIndex("LastName"));
            cursor.close();
        }else
        {
            Lname = "n/a";
        }
        // looping through all rows and adding to list


        // closing connection
        db.close();

        // returning lables
        return Lname;
    }

    public String getUDataPhone(String p) {
        String Phone = new String();

        // Select All Query
        String selectQuery = "SELECT LastName FROM " + TABLE_NAME + " WHERE Email = '" + p +"' ";

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);//selectQuery,selectedArguments
        cursor.moveToFirst();
        if(!cursor.isNull(0)){
            Phone = cursor.getString(cursor.getColumnIndex("BIO"));
            cursor.close();
        }else
        {
            Phone = "n/a";
        }
        // looping through all rows and adding to list


        // closing connection
        db.close();

        // returning lables
        return Phone;
    }

    public String getUDataTeam(String p) {
        String Team = new String();

        // Select All Query
        String selectQuery = "SELECT Team FROM " + TABLE_NAME + " WHERE Email = '" + p +"' ";

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);//selectQuery,selectedArguments
        cursor.moveToFirst();
        if(!cursor.isNull(0)){
            Team = cursor.getString(cursor.getColumnIndex("BIO"));
            cursor.close();
        }else
        {
            Team = "n/a";
        }
        // looping through all rows and adding to list


        // closing connection
        db.close();

        // returning lables
        return Team;
    }

    public void updateBio(String p, String bio, String skill) {

        // Select All Query
        String updateQuery = "UPDATE PERSON SET BIO = '" + bio
                + "' , Skills = " + "'" + skill + "'"

                + " WHERE Email = '" + p +"'  ";

        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL(updateQuery);//selectQuery,selectedArguments

        // closing connection
        db.close();

        // returning lables
    }



    public List<String> getAllTeamMates(String team){
        List<String> list = new ArrayList<String>();

        // Select All Query
        String selectQuery = "SELECT (FirstName||' '||LastName) as NAME FROM " + TABLE_NAME + " where Team = " + '"' + team + '"' ;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);//selectQuery,selectedArguments
        // looping through all rows and adding to list

        if (cursor.moveToFirst()) {
            do {
                list.add(cursor.getString(0));//adding 2nd column data
            } while (cursor.moveToNext());
        }
        // closing connection
        cursor.close();
        db.close();

        // returning lables
        return list;
    }



    public String getCurTeam(String p){

        String team = new String();

        // Select All Query
        String selectQuery = "SELECT Team FROM " + TABLE_NAME + " WHERE Email = '" + p +"' ";

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);//selectQuery,selectedArguments
        cursor.moveToFirst();
        if(!cursor.isNull(0)){
            team = cursor.getString(cursor.getColumnIndex("Team"));
            cursor.close();
        }else
        {
            team = "n/a";
        }
        // looping through all rows and adding to list


        // closing connection
        db.close();

        // returning lables
        return team;
    }
    public List<String>  getAllSkills(){


        List<String> slist = new ArrayList<String>();

        // Select All Query
        String selectQuery = "SELECT skills FROM person where Skills is not null group by Skills";

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);//selectQuery,selectedArguments
        slist.add("please select a skill from below: ");
        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                slist.add(cursor.getString(0));//adding 2nd column data
               } while (cursor.moveToNext());
        }

        slist.add("Create a new skill!");
        // closing connection
        cursor.close();
        db.close();

        // returning lables
        return slist;
    }

    public void setCurUserName(String s)
    {
        CurUserName = s;
    }


    public String getCurUserName()
    {
        return CurUserName;
    }

    public void setFoundUserName(String s)
    {
        FoundUserName = s;
    }


    public String getFoundUserName()
    {
        return FoundUserName;
    }





}



