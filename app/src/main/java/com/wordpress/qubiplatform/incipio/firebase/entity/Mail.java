package com.wordpress.qubiplatform.incipio.firebase.entity;

public class Mail {
    private String id;
    private String body;
    private String title;
    private String idUser;
    private String idGame;
    private String timestamp;

    public Mail(String body, String title, String idUser, String idGame, String timestamp) {
        this.body = body;
        this.title = title;
        this.idUser = idUser;
        this.idGame = idGame;
        this.timestamp = timestamp;
    }

    public Mail(){}

    public void setId(String id) {
        this.id = id;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setIdUser(String idUser) {
        this.idUser = idUser;
    }

    public void setIdGame(String idGame) {
        this.idGame = idGame;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getId() {
        return id;
    }

    public String getBody() {
        return body;
    }

    public String getTitle() {
        return title;
    }

    public String getIdUser() {
        return idUser;
    }

    public String getIdGame() {
        return idGame;
    }

    public String getTimestamp() {
        return timestamp;
    }
}
