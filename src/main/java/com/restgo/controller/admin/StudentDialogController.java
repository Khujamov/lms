package com.restgo.controller.admin;

import com.restgo.model.User;
import com.restgo.service.StudentService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.io.IOException;

public class StudentDialogController {
    @FXML
    private TextField username;

    @FXML
    private PasswordField password;

    private StudentService service;

    public static boolean isEdit;

    public void initialize(){
        service = new StudentService();
        if (isEdit){
            setUser(StudentsController.editContact);
        }
    }

    @FXML
    public void cancel(ActionEvent e) throws IOException {
        Button button = (Button)e.getSource();
        button.getScene().getWindow().hide();
        AdminController.updateUI("libs");
    }

    @FXML
    public void save(ActionEvent e) throws IOException {
        Button button = (Button)e.getSource();
        if (isEdit){
            service.editUser(getUser());
            button.getScene().getWindow().hide();
            AdminController.updateUI("students");
            setIsEdit(false);
            return;
        }
        service.addUser(getUser());
        button.getScene().getWindow().hide();
        AdminController.updateUI("students");

    }

    public User getUser(){
        User user = new User();
        if (StudentsController.editContact != null){
            user.setId(LibsController.editContact.getId());
        }
        user.setUsername(username.getText());
        user.setPassword(password.getText());
        return user;
    }

    public void setUser(User user){
        if (user != null){
            username.setText(user.getUsername());
            password.setText(user.getPassword());
        }
    }

    public static void setIsEdit(boolean edit){
        isEdit = edit;
    }
}
