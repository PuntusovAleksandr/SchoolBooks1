package com.aleksandrp.schoolbooksleeveel1.db.constants;

/**
 * Created by Aleksandr on 25.09.2015.
 */
public interface ValuesDB {


    String TAG_DB = "My_Db";

    String NAME_DB = "level_1";

    int VERSION_DB = 1;
    String TABLE_BOOKS_LEVEL_1 = "school_books_level_1";
    String TABLE_GDZ_LEVEL_1 = "gdz_level_1";

    String COLUMN_NAME_BOOK = "name_book";
    String COLUMN_SMALL_ICON = "small_icon_book";
    String COLUMN_ICON_STATUS = "icon_status_loader";
    String COLUMN_LINK_LOADER = "link_download";
    String COLUMN_ITEM = "item";

    String CREATE_TABLE_BOOK = "CREATE TABLE IF NOT EXISTS " +
            TABLE_BOOKS_LEVEL_1 + " (\n" +
            COLUMN_NAME_BOOK + "          TEXT    NOT NULL, \n" +
            COLUMN_SMALL_ICON + "    INTEGER DEFAULT (0), \n" +
            COLUMN_ICON_STATUS + " INTEGER NOT NULL\n" +
            "                               DEFAULT (0), \n" +
            COLUMN_LINK_LOADER + "      TEXT    NOT NULL, \n" +
            COLUMN_ITEM + " INTEGER NOT NULL);";

    String CREATE_TABLE_GDZ = "CREATE TABLE IF NOT EXISTS " +
            TABLE_GDZ_LEVEL_1 + " (\n" +
            COLUMN_NAME_BOOK + "          TEXT    NOT NULL, \n" +
            COLUMN_SMALL_ICON + "    INTEGER DEFAULT (0), \n" +
            COLUMN_ICON_STATUS + " INTEGER NOT NULL\n" +
            "                               DEFAULT (0), \n" +
            COLUMN_LINK_LOADER + "      TEXT    NOT NULL, \n" +
            COLUMN_ITEM + " INTEGER NOT NULL);";

    String Insert = "\n" +
            "INSERT INTO school_books_level_1 (name_book, small_icon_book, icon_status_loader, link_download, item) VALUES \n" +
            "('fi15', 0, 0, \"ff1\", 1),\n" +
            "('fi52', 0, 0, \"ff1\", 2),\n" +
            "('fi53', 0, 0, \"ff1\", 3),\n" +
            "('fi54', 0, 0, \"ff1\", 4),\n" +
            "('fi55', 0, 0, \"ff1\", 5),\n" +
            "('fi56', 0, 0, \"ff1\", 6),\n" +
            "('fi57', 0, 0, \"ff1\", 1),\n" +
            "('fi85', 0, 0, \"ff1\", 2),\n" +
            "('fi95', 0, 0, \"ff1\", 1),\n" +
            "('fi10', 0, 0, \"ff1\", 1);";



}
