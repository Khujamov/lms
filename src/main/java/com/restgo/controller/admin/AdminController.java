package com.restgo.controller.admin;

import com.restgo.App;
import com.restgo.controller.LoginController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;

import java.io.IOException;

public class AdminController {
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
        panelName.setText("Admin Panel");
    }

    @FXML
    public void setUsersListPane(ActionEvent event) throws IOException {
        panelName.setText("Students");
        mainFrame.setCenter(App.loadFXML("admin","students"));
    }

    @FXML
    public void setBooksListPane(ActionEvent event) throws IOException {
        panelName.setText("Books");
        mainFrame.setCenter(App.loadFXML("admin","books"));
    }

    @FXML
    public void logout(ActionEvent event) throws IOException {
        //  Saving all the changes to the database...
        //  ...
        //  Loading the Login page.
        App.setRoot("login");
    }

    @FXML
    public void setLibsListPane(ActionEvent event) throws IOException {
        panelName.setText("Librarians");
        mainFrame.setCenter(App.loadFXML("admin","libs"));
    }

    public static void updateUI(String fxml) throws IOException {
        pane.setCenter(App.loadFXML("admin",fxml));
    }
}