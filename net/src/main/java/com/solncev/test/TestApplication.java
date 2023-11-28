package com.solncev.test;

import com.solncev.fxml.FXMLApplication;
import com.solncev.test.controller.TestController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class TestApplication extends Application {
    public static void main(String[] args) {
        launch(args);
    }
    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader loader = new FXMLLoader(FXMLApplication.class.getResource("/chatWindow.fxml"));

        Pane pane = loader.load();
        TestController testController = loader.getController();
        testController.setStage(primaryStage);

        Scene scene = new Scene(pane);
        primaryStage.setTitle("Chat");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
