package com.wordpress.qubiplatform.incipio.firebase.entity;

public class Quiz {
    private String id;
    private String idGame;
    private String type;
    private String question;
    private String status;
    private String result;
    private String correct;
    private String picture;

    //ponudjeni
    private String name_1;
    private String name_2;
    private String given_0;
    private String given_1;
    private String given_2;
    private String given_3;
    //not working array
    //private String[] given;
    private String color;

    public void setColor(String color) {
        this.color = color;
    }

    public String getColor() {

        return color;
    }


    public Quiz(){this.color="green";}

    //osnovni
    public Quiz(String idGame, String type, String question, String status, String result) {
        this.idGame = idGame;
        this.type = type;
        this.question = question;
        this.status = status;
        this.color="green";
        this.result=result;
    }
    //sa slikom
    public Quiz(String idGame, String type, String question, String status, String picture, String result) {
        this.idGame = idGame;
        this.type = type;
        this.question = question;
        this.status = status;
        this.picture = picture;
        this.color="green";
        this.result=result;
    }

    public Quiz(String idGame, String type, String question, String status, String result, String correct, String given_0, String given_1, String given_2, String given_3) {
        this.idGame = idGame;
        this.type = type;
        this.question = question;
        this.status = status;
        this.result = result;
        this.correct = correct;
        this.given_0 = given_0;
        this.given_1 = given_1;
        this.given_2 = given_2;
        this.given_3 = given_3;
    }

    public Quiz(String idGame, String type, String question, String status, String result, String given_0, String given_1, String given_2, String given_3) {
        this.idGame = idGame;
        this.type = type;
        this.question = question;
        this.status = status;
        this.result = result;
        this.given_0 = given_0;
        this.given_1 = given_1;
        this.given_2 = given_2;
        this.given_3 = given_3;
    }

    public Quiz(String idGame, String type, String question, String status, String result, String name1, String name2) {
        this.idGame = idGame;
        this.type = type;
        this.question = question;
        this.status = status;
        this.result = result;
        this.name_1 = name1;
        this.name_2 = name2;
    }

    /*
    //sa ponudjenim i slikom
    public Quiz(String idGame, String type, String question, String status, String result, String picture, String[] given) {
        this.idGame = idGame;
        this.type = type;
        this.question = question;
        this.status = status;
        this.result = result;
        this.picture = picture;
        this.given = given;
        this.color="green";
    }
    //ponudjeni bez slike
    public Quiz(String idGame, String type, String question, String status, String result, String[] given) {
        this.idGame = idGame;
        this.type = type;
        this.question = question;
        this.status = status;
        this.result = result;
        this.given = given;
        this.color="green";
    }
    */

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

    public void setCorrect(String correct) {
        this.correct = correct;
    }

    public void setName_1(String name_1) {
        this.name_1 = name_1;
    }

    public void setName_2(String name_2) {
        this.name_2 = name_2;
    }

    public void setGiven_0(String given_0) {
        this.given_0 = given_0;
    }

    public void setGiven_1(String given_1) {
        this.given_1 = given_1;
    }

    public void setGiven_2(String given_2) {
        this.given_2 = given_2;
    }

    public void setGiven_3(String given_3) {
        this.given_3 = given_3;
    }
    //    public void setGiven(String[] given) {
//        this.given = given;
//    }

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

    public String getCorrect() {
        return correct;
    }

    public String getName_1() {
        return name_1;
    }

    public String getName_2() {
        return name_2;
    }

    public String getGiven_0() {
        return given_0;
    }

    public String getGiven_1() {
        return given_1;
    }

    public String getGiven_2() {
        return given_2;
    }

    public String getGiven_3() {
        return given_3;
    }
    //    public String[] getGiven() {
//        return given;
//    }
}
