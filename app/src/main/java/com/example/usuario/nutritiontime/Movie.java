package com.example.usuario.nutritiontime;

import io.realm.RealmObject;

/**
 * Created by Usuário on 15/10/2016.
 */

public class Movie extends RealmObject {

    private int year;
    private float rating;
    private String title;
    private String genre;
    private String thumb;

    public Movie(){}

    public Movie( String title, String genre , String thumb, int year, float rating) {
        this.year = year;
        this.rating = rating;
        this.title = title;
        this.genre = genre;
        this.thumb = thumb;
    }


    public String getThumb() {
        return thumb;
    }

    public void setThumb(String thumb) {
        this.thumb = thumb;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }
}
