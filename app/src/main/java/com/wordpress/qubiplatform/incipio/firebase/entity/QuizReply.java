package com.wordpress.qubiplatform.incipio.firebase.entity;

public class QuizReply {


    private String id;
    private String idQuiz;
    private String idUser;
    private String reply;
    private String status;

    public QuizReply(){}

    public QuizReply(String idQuiz, String idUser, String reply, String status) {
        this.idQuiz = idQuiz;
        this.idUser = idUser;
        this.reply = reply;
        this.status = status;
    }

    public String getId() {
        return id;
    }

    public String getIdQuiz() {
        return idQuiz;
    }

    public String getIdUser() {
        return idUser;
    }

    public String getReply() {
        return reply;
    }

    public String getStatus() {
        return status;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setIdQuiz(String idQuiz) {
        this.idQuiz = idQuiz;
    }

    public void setIdUser(String idUser) {
        this.idUser = idUser;
    }

    public void setReply(String reply) {
        this.reply = reply;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
