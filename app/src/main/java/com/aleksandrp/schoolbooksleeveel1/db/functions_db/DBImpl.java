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
import com.aleksandrp.schoolbooksleeveel1.db.entity.Book;
import com.aleksandrp.schoolbooksleeveel1.frament.BooksFragment;
import com.aleksandrp.schoolbooksleeveel1.frament.GDZFragment;
import com.aleksandrp.schoolbooksleeveel1.values.StaticValues;

import java.util.ArrayList;

/**
 * Created by Aleksandr on 25.09.2015.
 */
public class DBImpl implements ValuesDB, StaticValues {

    private MyDBHelper dbHelper;
    private Context context;
    private ContentValues contentValues;
    private SQLiteDatabase database;
    private Cursor cursor;
    private ArrayList<Book> allBooksList;
    private ArrayList<Book> allGDZ;

    public DBImpl(Context context) {
        this.context = context;
    }

    public synchronized void openDb() {
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
    public ArrayList<Book> getBooksListByItems(int level) {
        if (level == DEF_COUNT_ITEMS) return getAllBooksList();
        ArrayList<Book> booksList = new ArrayList<>();
        openDb();
        try {
            cursor = database.query(TABLE_BOOKS_LEVEL_1, null, COLUMN_ITEM + " = ?",
                    new String[]{level + ""}, null, null, COLUMN_NAME_BOOK);
            if (cursor.moveToFirst()) {
                do {
                    String nameBook = cursor.getString(cursor.getColumnIndex(COLUMN_NAME_BOOK));
                    int smallIcon = cursor.getInt(cursor.getColumnIndex(COLUMN_SMALL_ICON));
                    String iconStatus = cursor.getString(cursor.getColumnIndex(COLUMN_ICON_STATUS));
                    String link = cursor.getString(cursor.getColumnIndex(COLUMN_LINK_LOADER));
                    int item = cursor.getInt(cursor.getColumnIndex(COLUMN_ITEM));
                    booksList.add(new Book(nameBook, smallIcon, iconStatus, link, item));
                } while (cursor.moveToNext());
            }
        } catch (SQLiteException e) {
            Log.e(ValuesDB.TAG_DB, e.getStackTrace().toString());
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }
        return booksList;
    }

    public ArrayList<Book> getGDZListByItems(int level) {
        if (level == DEF_COUNT_ITEMS) return getAllGDZ();
        ArrayList<Book> booksList = new ArrayList<>();
        openDb();
        try {
            cursor = database.query(TABLE_GDZ_LEVEL_1, null, COLUMN_ITEM + " = ?",
                    new String[]{level + ""}, null, null, COLUMN_NAME_BOOK);
            if (cursor.moveToFirst()) {
                do {
                    String nameBook = cursor.getString(cursor.getColumnIndex(COLUMN_NAME_BOOK));
                    int smallIcon = cursor.getInt(cursor.getColumnIndex(COLUMN_SMALL_ICON));
                    String iconStatus = cursor.getString(cursor.getColumnIndex(COLUMN_ICON_STATUS));
                    String link = cursor.getString(cursor.getColumnIndex(COLUMN_LINK_LOADER));
                    int item = cursor.getInt(cursor.getColumnIndex(COLUMN_ITEM));
                    booksList.add(new Book(nameBook, smallIcon, iconStatus, link, item));
                } while (cursor.moveToNext());
            }
        } catch (SQLiteException e) {
            Log.e(ValuesDB.TAG_DB, e.getStackTrace().toString());
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }
        return booksList;
    }

    public void putFlagLoader(String key, String name) {
        openDb();
        try {
            contentValues = new ContentValues();
            if (key.equals("0")) {
                contentValues.put(COLUMN_ICON_STATUS, key);
            } else
                contentValues.put(COLUMN_ICON_STATUS, name);
            int row = database.update(TABLE_BOOKS_LEVEL_1, contentValues, COLUMN_NAME_BOOK + " = '" + name + "';", null);
        } catch (SQLiteException e) {
            e.printStackTrace();
        }
        refresh();
    }

    public ArrayList<Book> getAllBooksList() {
        ArrayList<Book> booksList = new ArrayList<>();
        openDb();
        try {
            cursor = database.query(TABLE_BOOKS_LEVEL_1, null, null,
                    null, null, null, COLUMN_NAME_BOOK);
            if (cursor.moveToFirst()) {
                do {
                    String nameBook = cursor.getString(cursor.getColumnIndex(COLUMN_NAME_BOOK));
                    int smallIcon = cursor.getInt(cursor.getColumnIndex(COLUMN_SMALL_ICON));
                    String iconStatus = cursor.getString(cursor.getColumnIndex(COLUMN_ICON_STATUS));
                    String link = cursor.getString(cursor.getColumnIndex(COLUMN_LINK_LOADER));
                    int item = cursor.getInt(cursor.getColumnIndex(COLUMN_ITEM));
                    booksList.add(new Book(nameBook, smallIcon, iconStatus, link, item));
                } while (cursor.moveToNext());
            }
        } catch (SQLiteException e) {
            Log.e(ValuesDB.TAG_DB, e.getStackTrace().toString());
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }
        return booksList;
    }

    public ArrayList<Book> getAllGDZ() {
        ArrayList<Book> booksList = new ArrayList<>();
        openDb();
        try {
            cursor = database.query(TABLE_GDZ_LEVEL_1, null, null,
                    null, null, null, COLUMN_NAME_BOOK);
            if (cursor.moveToFirst()) {
                do {
                    String nameBook = cursor.getString(cursor.getColumnIndex(COLUMN_NAME_BOOK));
                    int smallIcon = cursor.getInt(cursor.getColumnIndex(COLUMN_SMALL_ICON));
                    String iconStatus = cursor.getString(cursor.getColumnIndex(COLUMN_ICON_STATUS));
                    String link = cursor.getString(cursor.getColumnIndex(COLUMN_LINK_LOADER));
                    int item = cursor.getInt(cursor.getColumnIndex(COLUMN_ITEM));
                    booksList.add(new Book(nameBook, smallIcon, iconStatus, link, item));
                } while (cursor.moveToNext());
            }
        } catch (SQLiteException e) {
            Log.e(ValuesDB.TAG_DB, e.getStackTrace().toString());
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }
        return booksList;
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
