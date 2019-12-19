package com.restgo.controller.admin;

import com.restgo.App;
import com.restgo.model.User;
import com.restgo.service.LibService;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableView;

import java.io.IOException;
import java.sql.SQLException;

public class LibsController {
    @FXML
    private TableView<User> libsTable;

    private LibService service;

    public static User editContact;

    public void initialize() throws SQLException {
        service = new LibService();
        service.loadContactsFromDb();
        libsTable.setItems(service.getContacts());
//        delete.setDisable(true);
//        edit.setDisable(true);
    }

    @FXML
    public void showAdd() throws IOException {
        App.setDialog("admin","libdialog");
    }

    @FXML
    public void showEdit() throws IOException {
        editContact = libsTable.getSelectionModel().getSelectedItem();
        LibDialogController.setIsEdit(true);
        App.setDialog("admin","libdialog");
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
