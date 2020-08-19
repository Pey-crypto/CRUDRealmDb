package com.securewk.realmdb.entity;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;
import io.realm.annotations.Required;

public class Books extends RealmObject{

    @PrimaryKey
    @Required
    private String bookId;
    @Required
    private String bookName;
    @Required
    private String authorName;
    @Required
    private Double bookPrice;
    @Required
    private String bookDescription;

    public Books(){

    }
    public Books(String bookName,String authorName,Double bookPrice,String bookDescription ){
        this.bookName=bookName;
        this.authorName=authorName;
        this.bookPrice=bookPrice;
        this.bookDescription=bookDescription;
    }

    public String getBookId() {
        return bookId;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public Double getBookPrice() {
        return bookPrice;
    }

    public void setBookPrice(Double bookPrice) {
        this.bookPrice = bookPrice;
    }

    public String getBookDescription() {
        return bookDescription;
    }

    public void setBookDescription(String bookDescription) {
        this.bookDescription = bookDescription;
    }
}
