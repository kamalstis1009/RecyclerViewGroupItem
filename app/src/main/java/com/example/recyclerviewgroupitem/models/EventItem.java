package com.example.recyclerviewgroupitem.models;

public class EventItem extends ListItem {

    private MyModel myModel;

    public EventItem(MyModel myModel) {
        this.myModel = myModel;
    }

    public MyModel getMyModel() {
        return myModel;
    }

    public void setMyModel(MyModel myModel) {
        this.myModel = myModel;
    }

    @Override
    public int getType() {
        return TYPE_EVENT;
    }
}
