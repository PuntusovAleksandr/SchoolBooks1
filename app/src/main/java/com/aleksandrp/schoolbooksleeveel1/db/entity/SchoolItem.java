package com.aleksandrp.schoolbooksleeveel1.db.entity;

/**
 * Created by Aleksandr on 21.11.2015.
 */
public class SchoolItem {

    private long id;
    private int level_item;
    private String name_item;

    public SchoolItem(int level_item, String name_item) {
        this.level_item = level_item;
        this.name_item = name_item;
    }

    public SchoolItem(long id, int level_item, String name_item) {
        this.id = id;
        this.level_item = level_item;
        this.name_item = name_item;
    }

    public long getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getLevel_item() {
        return level_item;
    }

    public void setLevel_item(int level_item) {
        this.level_item = level_item;
    }

    public String getName_item() {
        return name_item;
    }

    public void setName_item(String name_item) {
        this.name_item = name_item;
    }
}
