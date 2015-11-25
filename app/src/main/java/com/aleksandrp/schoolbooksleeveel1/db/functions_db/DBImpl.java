package com.aleksandrp.schoolbooksleeveel1.db.functions_db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.support.annotation.NonNull;
import android.util.Log;

import com.aleksandrp.schoolbooksleeveel1.db.DBHelper.MyDBHelper;
import com.aleksandrp.schoolbooksleeveel1.db.constants.ValuesDB;
import com.aleksandrp.schoolbooksleeveel1.db.entity.SchoolItem;
import com.aleksandrp.schoolbooksleeveel1.frament.BooksFragment;
import com.aleksandrp.schoolbooksleeveel1.frament.GDZFragment;

import java.util.ArrayList;

/**
 * Created by Aleksandr on 25.09.2015.
 */
public class DBImpl implements ValuesDB {

    private MyDBHelper dbHelper;
    private Context context;
    private ContentValues contentValues;
    private SQLiteDatabase database;
    private Cursor cursor;

    public DBImpl(Context context) {
        this.context = context;
    }

    public void openDb() {
        if (dbHelper == null) {
            dbHelper = new MyDBHelper(context);
            database = dbHelper.getWritableDatabase();
            Log.i(ValuesDB.TAG_DB, "Open method openDb - \n dbHelper::: "
                    + dbHelper.toString() + "\ndatabase::: "
                    + database.toString());
        }
    }

    public void close() {
        if (dbHelper != null) dbHelper.close();
        Log.i(ValuesDB.TAG_DB, "dbHelper isxlosed");
    }

    private void refresh() {
        BooksFragment.getInstance().updateList();
        GDZFragment.getInstance().update();
    }


//    public void putNewTime(TimeFix timeFix) {
//        openDb();
//        Log.i(ValuesDB.TAG_DB, "putNewTime where timeFix = " + timeFix.toString());
//        contentValues = new ContentValues();
//        contentValues.put(ValuesDB.COLUMN_TITLE, timeFix.getTitle());
//        contentValues.put(ValuesDB.COLUMN_DATE, timeFix.getDate());
//        contentValues.put(ValuesDB.COLUMN_TIME_DATA, timeFix.getTimeLong());
//        try {
//            database.insert(ValuesDB.NAME_TABLE_TABLE_TIME, null, contentValues);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        refresh();
//    }


    @NonNull
    public ArrayList<SchoolItem> getSchoolItems(int level) {
        ArrayList<SchoolItem> mSchoolItems = new ArrayList<>();
        openDb();
        try {
            cursor = database.query(TABLE_ITEM, null, COLUMN_LEVEL_ITEM + " = ?",
                    new String[]{level + ""}, null, null, COLUMN_NAME_ITEM);
            if (cursor.moveToFirst()) {
                do {
                    long id = cursor.getLong(cursor.getColumnIndex(COLUMN_ID));
                    String name = cursor.getString(cursor.getColumnIndex(COLUMN_NAME_ITEM));
                    int lev = cursor.getInt(cursor.getColumnIndex(COLUMN_LEVEL_ITEM));
                    mSchoolItems.add(new SchoolItem(id, lev, name));
                } while (cursor.moveToNext());
            }
            cursor.close();
        } catch (SQLiteException e) {
            Log.e(ValuesDB.TAG_DB, e.getStackTrace().toString());
        }
        return mSchoolItems;
    }

//    public void removeAllTime() {
//        openDb();
//        Log.i(ValuesDB.TAG_DB, "removeAllTime all ");
//        database.delete(ValuesDB.NAME_TABLE_TABLE_TIME, null, null);
//        refresh();
//    }
//
//
//    public void removeById(long l) {
//        openDb();
//        Log.i(ValuesDB.TAG_DB, "removeById where time = " + l);
//        database.delete(ValuesDB.NAME_TABLE_TABLE_TIME,
//                ValuesDB.COLUMN_ID + " = " + l, null);
//        refresh();
//    }

}
