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

    String Insert = "INSERT INTO school_books_level_1 (name_book, small_icon_book, icon_status_loader, link_download, item) VALUES \n" +
            "        ('Англійська мова (Калініна, Самойлюкевич) 1 клас',  0, 0, 'zzzzzz', 1),\n" +
            "        ('Англійська мова (Карп’юк) 1 клас',  0, 0, 'zzzzzz', 1),\n" +
            "        ('Англійська мова (Несвіт) 1 клас', 0, 0, 'zzzzzz', 1),\n" +
            "        ('Англійська мова (Ростоцька, Карп’юк) 1 клас', 0, 0, 'zzzzzz', 1),\n" +
            "        ('Англійська мова Зошит для письма (Вітушинська, Косован) 1 клас', 0, 0, 'zzzzzz', 1),\n" +
            "        ('Англійська мова Робочий зошит (Карп’юк) 1 клас', 0, 0, 'zzzzzz', 1),\n" +
            "        ('Англійська мова Робочий зошит (Несвіт) 1 клас', 0, 0, 'zzzzzz', 1),\n" +
            "        ('Буквар (Вашуленко) 1 клас', 0, 0, 'zzzzzz', 2),\n" +
            "        ('Математика (Богданович, Лишенко) 1 клас', 0, 0, 'zzzzzz', 3),\n" +
            "        ('Математика (Рівкінд, Оляницька) 1 клас', 0, 0, 'zzzzzz', 3),\n" +
            "        ('Музичне мистецтво (Лобова) 1 клас', 0, 0, 'zzzzzz', 4),\n" +
            "        ('Німецька мова (Паршикова, Мельничук/, Савченко, Сидоренко, Горбач) 1 клас', 0, 0, 'zzzzzz', 5),\n" +
            "        ('Німецька мова (Скоропад) 1 клас', 0, 0, 'zzzzzz', 5),\n" +
            "        ('Основи здоров`я (Гнатюк) 1 клас', 0, 0, 'zzzzzz', 6),\n" +
            "        ('Основи здоров’я (Бех, Воронцова, Пономаренко, Страшко) 1 клас', 0, 0, 'zzzzzz', 6),\n" +
            "        ('Природознавство (Гільберг, Сак) 1 клас', 0, 0, 'zzzzzz', 7),\n" +
            "        ('Природознавство (Грущинська) 1 клас', 0, 0, 'zzzzzz', 8),\n" +
            "        ('Російська мова (Лапшина, Зорька) 1 клас', 0, 0, 'zzzzzz', 9),\n" +
            "        ('Російська мова (Статівка, Самонова) 1 клас', 0, 0, 'zzzzzz', 9),\n" +
            "        ('Українська мова (Захарійчук) 1 клас', 0, 0, 'zzzzzz', 10),\n" +
            "        ('Французька мова (Чумак, Кривошеєва) 1 клас', 0, 0, 'zzzzzz', 11);";

String ISERT_LIST_BOOKS = "INSERT INTO school_books_level_1 (name_book, small_icon_book, icon_status_loader, link_download, item) VALUES \n" +
        "('Англійська мова (Калініна, Самойлюкевич) 1 клас',  0, 0, 'zzzzzz')," +
        "('Англійська мова (Карп’юк) 1 клас',  0, 0, 'zzzzzz')," +
        "('Англійська мова (Несвіт) 1 клас', 0, 0, 'zzzzzz')," +
        "('Англійська мова (Ростоцька, Карп’юк) 1 клас', 0, 0, 'zzzzzz')," +
        "('Англійська мова Зошит для письма (Вітушинська, Косован) 1 клас', 0, 0, 'zzzzzz')," +
        "('Англійська мова Робочий зошит (Карп’юк) 1 клас', 0, 0, 'zzzzzz')," +
        "('Англійська мова Робочий зошит (Несвіт) 1 клас', 0, 0, 'zzzzzz')," +
        "('Буквар (Вашуленко) 1 клас', 0, 0, 'zzzzzz')," +
        "('Математика (Богданович, Лишенко) 1 клас', 0, 0, 'zzzzzz')," +
        "('Математика (Рівкінд, Оляницька) 1 клас', 0, 0, 'zzzzzz')," +
        "('Музичне мистецтво (Лобова) 1 клас', 0, 0, 'zzzzzz')," +
        "('Німецька мова (Паршикова, Мельничук/, Савченко, Сидоренко, Горбач) 1 клас', 0, 0, 'zzzzzz')," +
        "('Німецька мова (Скоропад) 1 клас', 0, 0, 'zzzzzz')," +
        "('Основи здоров`я (Гнатюк) 1 клас', 0, 0, 'zzzzzz')," +
        "('Основи здоров’я (Бех, Воронцова, Пономаренко, Страшко) 1 клас', 0, 0, 'zzzzzz')," +
        "('Природознавство (Гільберг, Сак) 1 клас', 0, 0, 'zzzzzz')," +
        "('Природознавство (Грущинська) 1 клас', 0, 0, 'zzzzzz')," +
        "('Російська мова (Лапшина, Зорька) 1 клас', 0, 0, 'zzzzzz')," +
        "('Російська мова (Статівка, Самонова) 1 клас', 0, 0, 'zzzzzz')," +
        "('Українська мова (Захарійчук) 1 клас', 0, 0, 'zzzzzz')," +
        "('Французька мова (Чумак, Кривошеєва) 1 клас', 0, 0, 'zzzzzz');";
}
