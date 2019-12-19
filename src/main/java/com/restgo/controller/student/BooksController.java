package com.restgo.controller.student;

import com.restgo.App;
import com.restgo.controller.LoginController;
import com.restgo.controller.admin.BookDialogController;
import com.restgo.model.Book;
import com.restgo.service.BookService;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;

import java.io.IOException;
import java.sql.SQLException;

public class BooksController {

    @FXML
    private Button borrow;
    @FXML
    private Label message;

    @FXML
    private TableView<Book> booksTable;

    private BookService service;


    public void initialize() throws SQLException {
        service = new BookService();
        service.loadBorrowedBooks();
        booksTable.setItems(service.getBorrowedBooks());
//        delete.setDisable(true);
//        edit.setDisable(true);
    }

    @FXML
    public void borrowBook() throws IOException {
        int bookId = booksTable.getSelectionModel().getSelectedItem().getId();
        service.borrowBook(LoginController.userId,bookId);
        message.setText("Successfully borrowed");
        StudentController.updateUI("books");
    }
}
