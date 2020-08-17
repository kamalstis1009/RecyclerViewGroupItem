package com.example.recyclerviewgroupitem.models;

public class EventItem extends ListItem {

    private EventModel eventModel;

    public EventItem(EventModel eventModel) {
        this.eventModel = eventModel;
    }

    public EventModel getEventModel() {
        return eventModel;
    }

    public void setEventModel(EventModel eventModel) {
        this.eventModel = eventModel;
    }

    @Override
    public int getType() {
        return TYPE_EVENT;
    }

}
