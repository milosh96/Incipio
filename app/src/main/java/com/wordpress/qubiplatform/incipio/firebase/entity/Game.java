package com.wordpress.qubiplatform.incipio.firebase.entity;

public class Game {

    /***
     *komentatotore ne cuvam
     */

    //added ver 2
    private String startTime;
    private String endTime;
    private String date;

    private int channel;

    private String description;

    private String id;

    private String status;

    public void setChannel(int channel) {
        this.channel = channel;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getChannel() {

        return channel;
    }

    public String getDescription() {
        return description;
    }

    public String getId() {
        return id;
    }

    public String getStatus() {
        return status;
    }

    public String getStartTime() {
        return startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public String getDate() {
        return date;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Game(int channel, String description, String id, String status) {

        this.channel = channel;
        this.description = description;
        this.id = id;
        this.status = status;
    }

    public Game(String startTime, String endTime, String date, int channel, String description, String status) {
        this.startTime = startTime;
        this.endTime = endTime;
        this.date = date;
        this.channel = channel;
        this.description = description;
        this.status = status;
    }

    public Game(){

    }
}
