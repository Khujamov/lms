package com.restgo.controller;

import com.restgo.App;
import com.restgo.db.DataSource;
import com.restgo.utils.AlertHelper;
import com.restgo.utils.DBUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Window;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginController {

    private DataSource dataSource;
    private Connection connection;
    private PreparedStatement statement;
    private ResultSet rs;
    @FXML
    private TextField username;

    @FXML
    private PasswordField password;

    @FXML
    private Button login;

    public static int userId;
    public static String userName;
    public static boolean blocked;

    @FXML
    private void handleSubmit(ActionEvent event) throws IOException {
        Window owner = login.getScene().getWindow();
        String username = this.username.getText();
        String password = this.password.getText();
        if(this.username.getText().isEmpty()) {
            AlertHelper.showAlert(Alert.AlertType.ERROR, owner, "Form Error!",
                    "Please enter your name");
            return;
        }
        if(this.password.getText().isEmpty()) {
            AlertHelper.showAlert(Alert.AlertType.ERROR, owner, "Form Error!",
                    "Please enter a password");
            return;
        }
        if (isUserExist(username,password)){
            userName = username;
            App.setRoot("lib","lib");
        }else AlertHelper.showAlert(Alert.AlertType.ERROR, owner, "Registration Failed!",
                "Username or password is wrong");
    }

    private boolean isUserExist(String username, String password){
        try {
            dataSource = DataSource.getInstance();
            connection = dataSource.getConnection();
            statement = connection.prepareStatement("Select * from users u where u.username = ? and u.password = ?");
            statement.setString(1,username);
            statement.setString(2,password);
            rs = statement.executeQuery();
            if (rs.next()){
                userId = rs.getInt("id");
                blocked = rs.getBoolean("blocked");
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DBUtils.done(rs);
            DBUtils.done(statement);
            DBUtils.done(connection);
        }
        return false;
    }
}
