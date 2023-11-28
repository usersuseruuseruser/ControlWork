package com.solncev.fx.chat;

import com.solncev.fx.chat.client.ChatClient;
import com.solncev.fx.chat.model.UserConfig;
import com.solncev.fx.chat.view.BaseView;
import com.solncev.fx.chat.view.ChatView;
import com.solncev.fx.chat.view.UserConfigView;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class ChatApplication {

    private UserConfig userConfig;
    private UserConfigView configView;
    private ChatView chatView;

    private BorderPane root;
    private ChatClient chatClient;

    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Chat");
        primaryStage.setOnCloseRequest(e -> System.exit(0));
        BaseView.setChatApplication(this);
        configView = new UserConfigView();
        chatView = new ChatView();

        chatClient = new ChatClient(this);
        root = new BorderPane();
        Scene scene = new Scene(root, 400, 300);
        primaryStage.setScene(scene);
        primaryStage.show();
        setView(configView);
    }

    public void appendMessage(String message) {
        chatView.appendMessage(message);
    }

    public void startChat() {
        chatClient.start();
    }

    public void setUserConfig(UserConfig userConfig) {
        this.userConfig = userConfig;
    }

    public UserConfig getUserConfig() {
        return userConfig;
    }

    public UserConfigView getConfigView() {
        return configView;
    }

    public ChatView getChatView() {
        return chatView;
    }

    public void setView(BaseView view) {
        root.setCenter(view.getView());
    }

    public ChatClient getChatClient() {
        return chatClient;
    }

    public void setChatClient(ChatClient chatClient) {
        this.chatClient = chatClient;
    }
}
