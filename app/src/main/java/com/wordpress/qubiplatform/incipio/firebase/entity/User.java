package com.wordpress.qubiplatform.incipio.firebase.entity;

public class User {

    private String id;
    private String email;
    private String firstName;
    private String lastName;
    private int points;
    private String profilePicture;

    public User(String email, String firstName, String lastName, int points, String profilePicture) {
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.points = points;
        this.profilePicture = profilePicture;
    }

    public User(){}

    public String getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public int getPoints() {
        return points;
    }

    public String getProfilePicture() {
        return profilePicture;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public void setProfilePicture(String profilePicture) {
        this.profilePicture = profilePicture;
    }
}
