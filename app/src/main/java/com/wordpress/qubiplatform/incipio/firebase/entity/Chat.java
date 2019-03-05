package com.wordpress.qubiplatform.incipio.firebase.entity;

public class Chat {
    private String userName;
    private String id;
    private String idGame;
    private String idUser;
    private String body;
    private String replyTo;
    private int likes;
    private int dislikes;

    public Chat(String idGame, String idUser, String body, String replyTo) {
        this.idGame = idGame;
        this.idUser = idUser;
        this.body = body;
        this.replyTo = replyTo;
    }
    public Chat(){

    }

    public void setId(String id) {
        this.id = id;
    }

    public void setIdGame(String idGame) {
        this.idGame = idGame;
    }

    public void setIdUser(String idUser) {
        this.idUser = idUser;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public void setReplyTo(String replyTo) {
        this.replyTo = replyTo;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }

    public void setDislikes(int dislikes) {
        this.dislikes = dislikes;
    }

    public String getId() {

        return id;
    }
    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserName() {

        return userName;
    }

    public String getIdGame() {
        return idGame;
    }

    public String getIdUser() {
        return idUser;
    }

    public String getBody() {
        return body;
    }

    public String getReplyTo() {
        return replyTo;
    }

    public int getLikes() {
        return likes;
    }

    public int getDislikes() {
        return dislikes;
    }
}
