package com.solncev.test.controller;

import com.solncev.test.chat.ChatApplication;
import com.solncev.test.chat.server.ChatServer;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DialogPane;
import javafx.scene.control.TextField;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TestController {

    @FXML
    private Button btn;

    @FXML
    private TextArea conversation;
    @FXML
    private Pane pane;

    @FXML
    private TextField txt;

    private boolean isStarted = false;
    private Stage stage;

    public void setStage(Stage stage) {
        this.stage = stage;
    }
    @FXML
    private void handleButtonClick() {
        btn.setOnAction(event -> addMessage(txt.getText()));
    }

    @FXML
    private void initialize() {
        btn.setOnAction(event -> addMessage(txt.getText()));

        txt.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER) {
                addMessage(txt.getText());
            }
        });
    }
    public void addMessage(String message) {
        conversation.appendText("You: " + message + "\n");
        if (message.equals("start")) {
            isStarted = true;
            conversation.appendText("Bot: " + "started \n");
        }
        if (message.equals("end")) {
            isStarted = false;
            conversation.appendText("Bot: " + "ended \n");
        }
        if (message.equals("help")) {
            conversation.appendText("Bot: " + help() + "\n");
        }
        if (isStarted) {
            switch (message.split(" ")[0]) {
                case "weather" -> conversation.appendText("Bot: "+ weatherInfo(message.split(" ")[1]) + "\n");
                case "currency" -> conversation.appendText("Bot: "+ currencyInfo(message.split(" ")) + "\n");
                case "chat" -> goToChat();
            }
        }
        txt.clear();
    }
    private String help(){
        return "команда help: вся информация о командах \n" +
                "команда weather [название города]: погода в городе \n" +
                "команда currency [первая валюта на англ.] [вторая валюта на англ]: например currency USD RUB \n" +
                "команда chat: открывает чат\n" +
                "команда start: старует \n" +
                "команда end: заканчивает";
    }
    private String weatherInfo(String city){
        String jsonString;
        String temperature = "UNDEFINED";
        String humidity = "UNDEFINED";
        String weather = "UNDEFINED";
        try (InputStream stream = new URI("https://api.openweathermap.org/data/2.5/weather?q="
                + city
                + "&appid=0c9d371ebde9e8af7cf225e608128f8d").toURL().openStream()) {
            BufferedReader bfr = new BufferedReader(new InputStreamReader(stream));
            StringBuilder content = new StringBuilder();
            String line;
            while ((line = bfr.readLine()) != null) {
                content.append(line);
            }
            jsonString = content.toString();
            Pattern pattern = Pattern.compile("\"main\":\"([^\"]+)\".*\"temp\":([\\d.]+).*\"humidity\":(\\d+)");
            Matcher matcher = pattern.matcher(jsonString);

            while (matcher.find()) {
                weather = matcher.group(1);
                temperature = String.valueOf(Double.parseDouble(matcher.group(2)) - 273);
                humidity = matcher.group(3);
            }
        } catch (URISyntaxException e) {
            return "city not found";
        } catch (IOException ex){
            return "ошибка подключения к серверу open weather";
        }

        return "Weather: " + weather
                + " temperature: " + temperature
                + " humidity : " + humidity;
    }
    private String currencyInfo(String[] currencies){
        if (currencies.length != 3){
            return "невалидные данные, посмотрите help";
        }
        String resp = null;
        String firstCurrency = currencies[1];
        String secondCurrency = currencies[2];
        String forApi = firstCurrency.toUpperCase() + secondCurrency.toUpperCase();
        String jsonString;

        try (InputStream stream = new URI("https://currate.ru/api/?get=rates&pairs="
                + forApi
                + "&key=74b11a43302278de438ceef26cae7e6e").toURL().openStream()) {
            BufferedReader bfr = new BufferedReader(new InputStreamReader(stream));
            StringBuilder content = new StringBuilder();
            String line;
            while ((line = bfr.readLine()) != null) {
                content.append(line);
            }
            jsonString = content.toString();
            String regex = ":\"([0-9.]+)\"";

            Pattern pattern = Pattern.compile(regex);
            Matcher matcher = pattern.matcher(jsonString);
            if (matcher.find()){
                return matcher.group(1);
            } else{
                return "не нашлось ничево";
            }
        } catch (URISyntaxException e) {
            return "похоже таких обозначений currency нет";
        } catch (IOException ex){
            return "ошибка подключения к серверу currate.ru";
        }
    }
    private void goToChat(){
        new Thread(() -> ChatServer.main(new String[]{})).start();
        ChatApplication chatApplication = new ChatApplication();
        try {
            chatApplication.start(stage);
        } catch (Exception e) {

        }

    }
}
