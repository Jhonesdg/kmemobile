package com.kmelx.kmemobile;

/**
 * Created by jonathan on 27/05/16.
 */
import android.util.Log;

import java.util.ArrayList;

public class Course {
    private String title, thumbnailUrl,url;
    private int year;
    private String description;

    public Course() {
    }

    public Course(String name, String description,String thumbnailUrl,
                 ArrayList<String> genre) {

        this.title = name;
        this.thumbnailUrl = thumbnailUrl;
        this.description = description;

    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String name) {
        this.title = name;
    }

    public String getThumbnailUrl() {
        return thumbnailUrl;
    }

    public void setThumbnailUrl(String thumbnailUrl) {
        this.thumbnailUrl = thumbnailUrl;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String rating) {
        this.description = rating;
    }

}
