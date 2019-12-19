package com.restgo.controller.lib;

import com.restgo.model.BorrowedBook;
import com.restgo.service.BookService;
import javafx.fxml.FXML;
import javafx.scene.control.TableView;

import java.sql.SQLException;

public class BorrowedBooksController {

    @FXML
    private TableView<BorrowedBook> booksTable;

    private BookService service;


    public void initialize() throws SQLException {
        service = new BookService();
        service.loadAllBorrowedBooks();
        booksTable.setItems(service.getBorrowedBooksList());
    }
}
