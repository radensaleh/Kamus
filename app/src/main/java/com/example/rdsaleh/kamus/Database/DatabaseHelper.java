package com.example.rdsaleh.kamus.Database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import static android.provider.BaseColumns._ID;
import static com.example.rdsaleh.kamus.Database.DatabaseContract.KamusColumns.TRANSLATE;
import static com.example.rdsaleh.kamus.Database.DatabaseContract.KamusColumns.WORD;
import static com.example.rdsaleh.kamus.Database.DatabaseContract.TABLE_ENIN;
import static com.example.rdsaleh.kamus.Database.DatabaseContract.TABLE_INEN;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static String DATABASE_NAME = "db_kamus";
    private static final int DATABASE_VERSION = 1;

    public static String CREATE_TABLE_ENIN = "create table "+TABLE_ENIN+
            " ("+_ID+" integer primary key autoincrement, " +
            WORD+" text not null, " +
            TRANSLATE+" text not null);";

    public static String CREATE_TABLE_INEN = "create table "+TABLE_INEN+
            " ("+_ID+" integer primary key autoincrement, " +
            WORD+" text not null, " +
            TRANSLATE+" text not null);";

    public DatabaseHelper(Context mContext){
        super(mContext, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_INEN);
        db.execSQL(CREATE_TABLE_ENIN);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_INEN);
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_ENIN);
        onCreate(db);
    }
}
