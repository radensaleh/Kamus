package com.example.rdsaleh.kamus.Database;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;

import com.example.rdsaleh.kamus.Model.KamusModel;

import java.util.ArrayList;

import static android.provider.BaseColumns._ID;
import static com.example.rdsaleh.kamus.Database.DatabaseContract.KamusColumns.TRANSLATE;
import static com.example.rdsaleh.kamus.Database.DatabaseContract.KamusColumns.WORD;
import static com.example.rdsaleh.kamus.Database.DatabaseContract.TABLE_ENIN;
import static com.example.rdsaleh.kamus.Database.DatabaseContract.TABLE_INEN;

public class KamusHelper {

    private Context mContext;
    private DatabaseHelper databaseHelper;

    private SQLiteDatabase sqLiteDatabase;

    public KamusHelper(Context mContext){ this.mContext = mContext; }

    public KamusHelper open() throws SQLException{
        databaseHelper = new DatabaseHelper(mContext);
        sqLiteDatabase = databaseHelper.getWritableDatabase();
        return this;
    }

    public void close(){ databaseHelper.close(); }

    public ArrayList<KamusModel> getDataENByWord(String word){
        Cursor cursor = sqLiteDatabase.query(TABLE_ENIN, null, WORD + " LIKE ?", new String[]{word}, null, null, _ID + " ASC", null);
        cursor.moveToFirst();
        ArrayList<KamusModel> arrayList = new ArrayList<>();
        KamusModel kamusModel;
        if(cursor.getCount() > 0){
            do{
                kamusModel = new KamusModel();
                kamusModel.setId(cursor.getInt(cursor.getColumnIndexOrThrow(_ID)));
                kamusModel.setWord(cursor.getString(cursor.getColumnIndexOrThrow(WORD)));
                kamusModel.setTranslate(cursor.getString(cursor.getColumnIndexOrThrow(TRANSLATE)));

                arrayList.add(kamusModel);
                cursor.moveToNext();

            } while(!cursor.isAfterLast());
        }
        cursor.close();
        return arrayList;
    }

    public ArrayList<KamusModel> getDataINByWord(String word){
        Cursor cursor = sqLiteDatabase.query(TABLE_INEN, null, WORD + " LIKE ?", new String[]{word}, null, null, _ID + " ASC", null);
        cursor.moveToFirst();
        ArrayList<KamusModel> arrayList = new ArrayList<>();
        KamusModel kamusModel;
        if(cursor.getCount() > 0){
            do{
                kamusModel = new KamusModel();
                kamusModel.setId(cursor.getInt(cursor.getColumnIndexOrThrow(_ID)));
                kamusModel.setWord(cursor.getString(cursor.getColumnIndexOrThrow(WORD)));
                kamusModel.setTranslate(cursor.getString(cursor.getColumnIndexOrThrow(TRANSLATE)));

                arrayList.add(kamusModel);
                cursor.moveToNext();

            } while(!cursor.isAfterLast());
        }
        cursor.close();
        return arrayList;
    }

    public ArrayList<KamusModel> getAllDataEN(){
        Cursor cursor = sqLiteDatabase.query(TABLE_ENIN,null,null,null,null,null,_ID+ " ASC",null);
        cursor.moveToFirst();
        ArrayList<KamusModel> arrayList = new ArrayList<>();
        KamusModel kamusModel;
        if(cursor.getCount() > 0){
            do{
                kamusModel = new KamusModel();
                kamusModel.setId(cursor.getInt(cursor.getColumnIndexOrThrow(_ID)));
                kamusModel.setWord(cursor.getString(cursor.getColumnIndexOrThrow(WORD)));
                kamusModel.setTranslate(cursor.getString(cursor.getColumnIndexOrThrow(TRANSLATE)));

                arrayList.add(kamusModel);
                cursor.moveToNext();

            } while(!cursor.isAfterLast());
        }
        cursor.close();
        return arrayList;
    }

    public ArrayList<KamusModel> getAllDataIN(){
        Cursor cursor = sqLiteDatabase.query(TABLE_INEN,null,null,null,null,null,_ID+ " ASC",null);
        cursor.moveToFirst();
        ArrayList<KamusModel> arrayList = new ArrayList<>();
        KamusModel kamusModel;
        if(cursor.getCount() > 0){
            do{
                kamusModel = new KamusModel();
                kamusModel.setId(cursor.getInt(cursor.getColumnIndexOrThrow(_ID)));
                kamusModel.setWord(cursor.getString(cursor.getColumnIndexOrThrow(WORD)));
                kamusModel.setTranslate(cursor.getString(cursor.getColumnIndexOrThrow(TRANSLATE)));

                arrayList.add(kamusModel);
                cursor.moveToNext();

            } while(!cursor.isAfterLast());
        }
        cursor.close();
        return arrayList;
    }

    public void setTransactionSuccess() {
        sqLiteDatabase.setTransactionSuccessful();
    }

    public void insertTransactionEN(KamusModel kamusModel){
        String sql = "INSERT INTO "+TABLE_ENIN+" ("+WORD+", "+TRANSLATE
                +") VALUES (?, ?)";
        SQLiteStatement stmt = sqLiteDatabase.compileStatement(sql);
        stmt.bindString(1, kamusModel.getWord());
        stmt.bindString(2, kamusModel.getTranslate());
        stmt.execute();
        stmt.clearBindings();
    }

    public void insertTransactionIN(KamusModel kamusModel){
        String sql = "INSERT INTO "+TABLE_INEN+" ("+WORD+", "+TRANSLATE
                +") VALUES (?, ?)";
        SQLiteStatement stmt = sqLiteDatabase.compileStatement(sql);
        stmt.bindString(1, kamusModel.getWord());
        stmt.bindString(2, kamusModel.getTranslate());
        stmt.execute();
        stmt.clearBindings();
    }
}
