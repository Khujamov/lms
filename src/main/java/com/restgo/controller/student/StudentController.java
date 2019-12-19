package com.restgo.controller.student;

import com.restgo.App;
import com.restgo.controller.LoginController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;

import java.io.IOException;

public class StudentController {
    @FXML
    public BorderPane mainFrame;
    @FXML
    private Label username;
    @FXML
    private Label panelName;
    @FXML
    private Button headerBorder_addButton;
    @FXML
    private Button headerBorder_logoutButton;
    @FXML
    private Button leftBorder_dashboardButton;
    @FXML
    private Button leftBorder_booksButton;
    @FXML
    private Button leftBorder_myBooksButton;
    @FXML
    private Button leftBorder_borrowedBooksButton;
    @FXML
    private Button leftBorder_readersButton;
    @FXML
    private Button leftBorder_blockedReadersButton;

    public static BorderPane pane;

    public void initialize() {
        pane = mainFrame;
        username.setText(LoginController.userName);
        panelName.setText("Empty Panel");
    }

    @FXML
    public void setBooksListPane(ActionEvent event) throws IOException {
        panelName.setText("Books");
        mainFrame.setCenter(App.loadFXML("student","books"));
    }

    @FXML
    public void setMyBooksListPane(ActionEvent event) throws IOException {
        panelName.setText("My Books");
        mainFrame.setCenter(App.loadFXML("student","mybooks"));
    }

    @FXML
    public void logout(ActionEvent event) throws IOException {
        App.setRoot("login");
    }


    public static void updateUI(String fxml) throws IOException {
        pane.setCenter(App.loadFXML("student",fxml));
    }
}
