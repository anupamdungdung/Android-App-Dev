package com.app.firebase.models;

public class NoticeModel {
    private String date;
    private String heading;
    private String description;
    private String link;

    public NoticeModel(){

    }
    public NoticeModel(String date, String heading, String description,String link) {
        this.date = date;
        this.heading = heading;
        this.description = description;
        this.link=link;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getHeading() {
        return heading;
    }

    public void setHeading(String heading) {
        this.heading = heading;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

}
