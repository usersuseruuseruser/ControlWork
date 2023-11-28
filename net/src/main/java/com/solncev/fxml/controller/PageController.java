package com.solncev.fxml.controller;

import com.solncev.fxml.model.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.VBox;

import java.util.stream.Collectors;

public class PageController {

    @FXML
    private TextField username;

    @FXML
    private Button search;

    @FXML
    private Label label;

    @FXML
    private VBox users;

    @FXML
    private TableView tableView;

    private ObservableList<User> userList = FXCollections.observableArrayList();
    private ObservableList<User> resultList = FXCollections.observableArrayList();

    public PageController() {
        userList.add(new User("Ivan", 50));
        userList.add(new User("Boris", 60));
        userList.add(new User("Stepan", 70));
    }

    @FXML
    private void initialize() {
        search.setText("Search");
        search.setStyle("-fx-background-color: #ff0000");

        search.setOnAction(event -> searchUsers());

        username.setOnKeyPressed(event -> {
            if (event.getCode().equals(KeyCode.ENTER)) {
                searchUsers();
            }
        });

        initTable();
    }

    private void searchUsers() {
        String searchText = username.getText();

        Task<ObservableList<User>> task = new Task<>() {
            @Override
            protected ObservableList<User> call() {
                return FXCollections.observableArrayList(
                        userList.stream()
                                .filter(u -> u.getUsername().toLowerCase().contains(searchText.toLowerCase()))
                                .collect(Collectors.toList())
                );
            }
        };

        task.setOnSucceeded(event -> {
            resultList = task.getValue();
            tableView.setItems(resultList);
        });

        Thread thread = new Thread(task);
        thread.start();
    }

    private void initTable() {
        tableView = new TableView(userList);
        tableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        TableColumn username = new TableColumn("USERNAME");
        username.setCellValueFactory(new PropertyValueFactory<>("username"));

        TableColumn points = new TableColumn("POINTS");
        points.setCellValueFactory(new PropertyValueFactory<>("points"));

        tableView.getColumns().addAll(username, points);
        tableView.getSortOrder().add(points);
        tableView.sort();

        users.getChildren().add(tableView);
    }
}
