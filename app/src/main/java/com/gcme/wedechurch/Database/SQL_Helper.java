package com.gcme.wedechurch.Database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class SQL_Helper extends SQLiteOpenHelper {
    private final static String DATABASE_NAME = "wedechurch";
    private final static int DATABASE_VERSION = 1;

    public SQL_Helper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        // TODO Auto-generated constructor stub
    }

    @Override
    public void onCreate(SQLiteDatabase arg0) {
        // TODO Auto-generated method stub

    }

    public void createTable(String Db_Table, String[] fields) {
        // Note: assumes fields[] starts with the "id" field (the one that is INTEGER PRIMARY KEY AUTOINCREMENT)

        String str = "(" + fields[0] + " INTEGER PRIMARY KEY AUTOINCREMENT, ";
        for (int x = 1; x < fields.length - 1; x++) {
            str += fields[x] + " TEXT, ";
        }
        str += fields[fields.length - 1] + " TEXT);";

        getWritableDatabase().execSQL("CREATE TABLE IF NOT EXISTS " + Db_Table + str);
    }

    @Override
    public void onUpgrade(SQLiteDatabase arg0, int arg1, int arg2) {
        // TODO Auto-generated method stub

    }

}
