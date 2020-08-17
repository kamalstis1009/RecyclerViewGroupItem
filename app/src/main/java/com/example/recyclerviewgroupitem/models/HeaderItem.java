package com.example.recyclerviewgroupitem.models;

public class HeaderItem extends ListItem {

    private String header;

    public HeaderItem(String header) {
        this.header = header;
    }

    public String getHeader() {
        return header;
    }

    public void setHeader(String header) {
        this.header = header;
    }

    @Override
    public int getType() {
        return TYPE_HEADER;
    }
}
