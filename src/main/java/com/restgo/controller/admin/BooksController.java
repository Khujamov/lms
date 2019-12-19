package com.restgo.controller.admin;

import com.restgo.App;
import com.restgo.model.Book;
import com.restgo.service.BookService;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableView;

import java.io.IOException;
import java.sql.SQLException;

public class BooksController {

    @FXML
    private TableView<Book> booksTable;

    private BookService service;

    public static Book editContact;

    public void initialize() throws SQLException {
        service = new BookService();
        service.loadContactsFromDb();
        booksTable.setItems(service.getContacts());
//        delete.setDisable(true);
//        edit.setDisable(true);
    }

    @FXML
    public void showAdd() throws IOException {
        App.setDialog("admin","bookdialog");
    }

    @FXML
    public void showEdit() throws IOException {
        editContact = booksTable.getSelectionModel().getSelectedItem();
        BookDialogController.setIsEdit(true);
        App.setDialog("admin","bookdialog");
    }

    @FXML
    public void showDelete() throws IOException {
        Book book = booksTable.getSelectionModel().getSelectedItem();
        ObservableList<Book> contacts = service.getContacts();
        contacts.remove(book);
        service.deleteBook(book);
        booksTable.setItems(contacts);
        //AdminController.updateUI("books");
    }
}
