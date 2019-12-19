package com.restgo.controller.student;

import com.restgo.controller.LoginController;
import com.restgo.model.Book;
import com.restgo.service.BookService;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;

import java.io.IOException;
import java.sql.SQLException;

public class MyBooksController {
    @FXML
    private Button returnbook;
    @FXML
    private Label message;

    @FXML
    private TableView<Book> booksTable;

    private BookService service;


    public void initialize() throws SQLException {
        service = new BookService();
        service.loadMyBorrowedBooks(LoginController.userId);
        booksTable.setItems(service.getMyBorrowedBooks());
//        delete.setDisable(true);
//        edit.setDisable(true);
    }

    @FXML
    public void returnBook() throws IOException {
        int bookId = booksTable.getSelectionModel().getSelectedItem().getId();
        service.returnBook(bookId);
        message.setText("Successfully returned");
        StudentController.updateUI("mybooks");
    }
}
