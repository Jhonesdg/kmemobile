package com.kmelx.kmemobile.Courses;

/**
 * Created by jonathan on 27/05/16.
 */
import android.util.Log;

import java.util.ArrayList;

public class Course {
    private String title, thumbnailUrl,uuid;

    private String description;

    public Course() {
    }

    public Course(String name, String description,String thumbnailUrl,
                 ArrayList<String> genre) {

        this.title = name;
        this.thumbnailUrl = thumbnailUrl;
        this.description = description;
        this.uuid=uuid;

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

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String rating) {
        this.description = rating;
    }

}
