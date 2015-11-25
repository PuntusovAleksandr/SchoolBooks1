package com.aleksandrp.schoolbooksleeveel1.db.entity;

/**
 * Created by Aleksandr on 25.09.2015.
 */
public class Book {

    private String nameBook;
    private int smallIcon;
    private int iconStatus;
    private String linkDownload;
    private int item;

    public Book(String nameBook, int smallIcon, int iconStatus, String linkDownload, int item) {
        this.nameBook = nameBook;
        this.smallIcon = smallIcon;
        this.iconStatus = iconStatus;
        this.linkDownload = linkDownload;
        this.item = item;
    }

    public String getNameBook() {
        return nameBook;
    }

    public void setNameBook(String nameBook) {
        this.nameBook = nameBook;
    }

    public int getSmallIcon() {
        return smallIcon;
    }

    public void setSmallIcon(int smallIcon) {
        this.smallIcon = smallIcon;
    }

    public int getIconStatus() {
        return iconStatus;
    }

    public void setIconStatus(int iconStatus) {
        this.iconStatus = iconStatus;
    }

    public String getLinkDownload() {
        return linkDownload;
    }

    public void setLinkDownload(String linkDownload) {
        this.linkDownload = linkDownload;
    }

    public int getItem() {
        return item;
    }

    public void setItem(int item) {
        this.item = item;
    }
}
