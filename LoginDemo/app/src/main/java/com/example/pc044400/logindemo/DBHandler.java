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

    public static final String TEAM_TABLE_NAME = "team";
    public static final String TEAM_COL_1 = "TeamName";
    public static final String TEAM_COL_2 = "ManagerName";


    public DBHandler(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME); //Drop older table if exists

        db.execSQL("CREATE TABLE person (ID INTEGER PRIMARY KEY AUTOINCREMENT , FirstName TEXT , LastName TEXT, Password TEXT , Email TEXT , Phone TEXT , Team TEXT , BIO TEXT NOT NULL ) ");

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

    public void updateBio(String p, String bio) {

        // Select All Query
        String updateQuery = "UPDATE PERSON SET BIO = '" + bio + "' WHERE Email = '" + p +"'  ";

        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL(updateQuery);//selectQuery,selectedArguments

        // closing connection
        db.close();

        // returning lables
    }












}



