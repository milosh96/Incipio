package com.wordpress.qubiplatform.incipio.firebase.entity;

import java.util.List;

public class Quiz {
    private String id;
    private String idGame;
    private String type;
    private String question;
    private String status;
    private String result;
    private String picture;
    private List<Given> given;
    private String color;

    public void setColor(String color) {
        this.color = color;
    }

    public String getColor() {

        return color;
    }

    class Given{
        private int order;
        private String answer;

        public void setOrder(int order) {
            this.order = order;
        }

        public void setAnswer(String answer) {
            this.answer = answer;
        }

        public int getOrder() {

            return order;
        }

        public String getAnswer() {
            return answer;
        }

        public Given() {

        }

        public Given(int order, String answer) {

            this.order = order;
            this.answer = answer;
        }
    }

    public Quiz(){}

    //osnovni
    public Quiz(String idGame, String type, String question, String status) {
        this.idGame = idGame;
        this.type = type;
        this.question = question;
        this.status = status;
    }
    //sa slikom
    public Quiz(String idGame, String type, String question, String status, String picture) {
        this.idGame = idGame;
        this.type = type;
        this.question = question;
        this.status = status;
        this.picture = picture;
    }

    //sa ponudjenim i slikom
    public Quiz(String idGame, String type, String question, String status, String result, String picture, List<Given> given) {
        this.idGame = idGame;
        this.type = type;
        this.question = question;
        this.status = status;
        this.result = result;
        this.picture = picture;
        this.given = given;
    }
    //ponudjeni bez slike
    public Quiz(String idGame, String type, String question, String status, String result, List<Given> given) {
        this.idGame = idGame;
        this.type = type;
        this.question = question;
        this.status = status;
        this.result = result;
        this.given = given;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setIdGame(String idGame) {
        this.idGame = idGame;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public void setGiven(List<Given> given) {
        this.given = given;
    }

    public String getId() {

        return id;
    }

    public String getIdGame() {
        return idGame;
    }

    public String getType() {
        return type;
    }

    public String getQuestion() {
        return question;
    }

    public String getStatus() {
        return status;
    }

    public String getResult() {
        return result;
    }

    public String getPicture() {
        return picture;
    }

    public List<Given> getGiven() {
        return given;
    }
}
