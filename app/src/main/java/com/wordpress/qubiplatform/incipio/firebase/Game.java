package com.wordpress.qubiplatform.incipio.firebase;

public class Game {

    /***
     *komentatotore ne cuvam
     */

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

    public Game(int channel, String description, String id, String status) {

        this.channel = channel;
        this.description = description;
        this.id = id;
        this.status = status;
    }
}
