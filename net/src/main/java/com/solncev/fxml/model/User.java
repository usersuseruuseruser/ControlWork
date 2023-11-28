package com.solncev.fxml.model;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class User {

    SimpleStringProperty username;
    SimpleIntegerProperty points;

    public User(String username, Integer points) {
        this.username = new SimpleStringProperty(username);
        this.points = new SimpleIntegerProperty(points);
    }

    public String getUsername() {
        return username.get();
    }

    public SimpleStringProperty usernameProperty() {
        return username;
    }

    public void setUsername(String username) {
        this.username.set(username);
    }

    public int getPoints() {
        return points.get();
    }

    public SimpleIntegerProperty pointsProperty() {
        return points;
    }

    public void setPoints(int points) {
        this.points.set(points);
    }
}
