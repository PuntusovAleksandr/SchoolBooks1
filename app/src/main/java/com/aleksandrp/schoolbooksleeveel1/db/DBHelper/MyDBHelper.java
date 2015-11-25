package com.aleksandrp.schoolbooksleeveel1.db.DBHelper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.aleksandrp.schoolbooksleeveel1.db.constants.ValuesDB;


/**
 * Created by Aleksandr on 25.09.2015.
 */
public class MyDBHelper extends SQLiteOpenHelper implements ValuesDB {

    public MyDBHelper(Context context) {
        super(context, NAME_DB, null, VERSION_DB);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.i(TABLE_BOOK, "Start ::: " + CREATE_TABLE_BOOK);
        db.execSQL(CREATE_TABLE_BOOK);

        Log.i(TABLE_ITEM, "Start ::: " + CREATE_TABLE_ITEM);
        db.execSQL(CREATE_TABLE_ITEM);

        Log.i(TABLE_BOOK, "Start ::: " + INSERT_ITEMS);
        db.execSQL(INSERT_ITEMS);


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }


}
