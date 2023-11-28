package com.solncev.fxml;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class FXMLApplication extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws IOException {
        FXMLLoader loader = new FXMLLoader(FXMLApplication.class.getResource("/page.fxml"));

        AnchorPane pane = loader.load();

        Scene scene = new Scene(pane);

//        scene.getStylesheets().add("/page.css");

        primaryStage.setTitle("Rating");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
