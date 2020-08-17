package com.example.recyclerviewgroupitem.models;

import androidx.annotation.NonNull;

public class EventModel {

    private String title;
    private String date;

    public EventModel(@NonNull String title, @NonNull String date) {
        this.title = title;
        this.date = date;
    }

    public String getTitle() {
        return title;
    }

    public String getDate() {
        return date;
    }
}
