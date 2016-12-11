package com.example.agrofarmers.helpers;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.agrofarmers.model.DBContract;


/**
 * Created by Larry on 6/16/2016.
 */
public class DBHelper extends SQLiteOpenHelper {
    public static final String SQL_CREATE_QUERY = "CREATE TABLE " +
            DBContract.Rule.TABLENAME+"("+
            DBContract.Rule.REQUEST_ID    +" INTEGER PRIMARY KEY AUTOINCREMENT, "+
            DBContract.Rule.PRODUCT    +" TEXT, "+
            DBContract.Rule.WEIGHT     +" INTEGER, "+
            DBContract.Rule.TRANSPORT_TYPE        +" TEXT, "+
            DBContract.Rule.STATUS     +" TEXT );";

    private static final String SQL_DELETE_QUERY =
            "DROP TABLE IF EXISTS " + DBContract.Rule.TABLENAME;


    public static final int DB_VERSION = 1;
    public static final String DB_NAME = DBContract.Rule.TABLENAME;

    public DBHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_QUERY);
        Log.e("Create: ", "Database Created");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(SQL_DELETE_QUERY);
        onCreate(db);
        Log.e("Upgrade: ", "Database upgrade complete");
    }

    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }

    public void addRequest(SQLiteDatabase db, String product, int weight, String t_type, String status){
        ContentValues record = new ContentValues();
        record.put(DBContract.Rule.PRODUCT,product);
        record.put(DBContract.Rule.WEIGHT, weight);
        record.put(DBContract.Rule.TRANSPORT_TYPE,t_type);
        record.put(DBContract.Rule.STATUS,status);

        db.insert(DBContract.Rule.TABLENAME, null, record);
    }

    public Cursor showRequest()
    {
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT * FROM " + DBContract.Rule.TABLENAME;
        Cursor cursor = db.rawQuery(query, null);

        return cursor;
    }
}
