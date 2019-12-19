package com.restgo.controller.admin;

import com.restgo.App;
import com.restgo.model.User;
import com.restgo.service.StudentService;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableView;

import java.io.IOException;
import java.sql.SQLException;

public class StudentsController {
    @FXML
    private TableView<User> libsTable;

    private StudentService service;

    public static User editContact;

    public void initialize() throws SQLException {
        service = new StudentService();
        service.loadContactsFromDb();
        libsTable.setItems(service.getContacts());
//        delete.setDisable(true);
//        edit.setDisable(true);
    }

    @FXML
    public void showAdd() throws IOException {
        App.setDialog("admin","studentdialog");
    }

    @FXML
    public void showEdit() throws IOException {
        editContact = libsTable.getSelectionModel().getSelectedItem();
        StudentDialogController.setIsEdit(true);
        App.setDialog("admin","studentdialog");
    }

    @FXML
    public void showDelete() throws IOException {
        User user = libsTable.getSelectionModel().getSelectedItem();
        ObservableList<User> contacts = service.getContacts();
        contacts.remove(user);
        service.deleteUser(user);
        libsTable.setItems(contacts);
        //AdminController.updateUI("books");
    }
}
