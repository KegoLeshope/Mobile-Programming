package com.example.myexpensemanager;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.Locale;

public class DatabaseHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "Expense Manager.db";
    public static final String TABLE_NAME = "Expenses";
    public static final String COL_1 = "ITEM_NO";
    public static final String COL_2 = "ITEM";
    public static final String COL_3 = "CATEGORY";
    public static final String COL_4 = "AMOUNT";
    public static final String COL_5 = "DATE";

    public static final String TABLE_NAME2 = "Users";
    public static final String COLUMN_1 = "FIRST_NAME";
    public static final String COLUMN_2 = "LAST_NAME";
    public static final String COLUMN_3 = "USERNAME";
    public static final String COLUMN_4 = "PASSWORD";
    public static final String COLUMN_5 = "REPASSWORD";

    public DatabaseHelper(Context context) {
        super(context, "Expenses Manager.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(" create table Expenses (ITEM_NO primary key AUTOINCREMENT,ITEM TEXT,CATEGORY TEXT,AMOUNT INTEGER, DATE INTEGER)");
        db.execSQL(" create table Users (FIRST_NAME TEXT primary key,LAST_NAME TEXT,USERNAME TEXT,PASSWORD TEXT,REPASSWORD TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS Expenses");
        db.execSQL("DROP TABLE IF EXISTS Users");
        onCreate(db);
    }

    public boolean insertData(String item, String category, String amount, String date) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_2, item);
        contentValues.put(COL_3, category);
        contentValues.put(COL_4, amount);
        contentValues.put(COL_5, date);
        long result = db.insert("Expenses", null, contentValues);
        if (result == -1) {
            return false;
        } else {
            return true;
        }
    }

    public Cursor viewData() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from Expenses", null);
        return res;
    }

    public Integer deleteData(String s) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TABLE_NAME, "ItemNo?", new String[]{});
    }

    public boolean updateData(String ItemNo, String Item, String Category, String Amount) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_2, Item);
        contentValues.put(COL_3, Category);
        contentValues.put(COL_4, Amount);
        db.update(TABLE_NAME, contentValues, "ItemNo?", new String[]{ItemNo});
        return true;
    }

    public boolean insertUserData(String firstname,String lastname,String username, String password) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_1, firstname);
        contentValues.put(COLUMN_2, lastname);
        contentValues.put(COLUMN_3, username);
        contentValues.put(COLUMN_4, password);
        long result = db.insert("Users", null, contentValues);
        if (result == -1) {
            return false;
        } else {
            return true;
        }
    }

    public boolean checkusername(String username) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from users where username=?", new String[]{username});
        if (cursor.getCount() > 0) {
            return true;
        } else {
            return false;
        }
    }

    public boolean checkusernamepassword(String username,String password) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from users where username=? and password = ?", new String[]{password});
        if (cursor.getCount() > 0) {
            return true;
        } else{
            return false;
        }
    }


    /*public Cursor getListContents (){
        SQLiteDatabase dbh = this.getWritableDatabase();
        Cursor data = dbh.rawQuery()
    }*/
}



