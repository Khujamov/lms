package com.restgo.controller.lib;

import com.restgo.model.BorrowedBook;
import com.restgo.service.BookService;
import com.restgo.service.StudentService;
import com.restgo.utils.AlertHelper;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TableView;
import javafx.stage.Window;

import java.sql.SQLException;

public class BorrowedBooksController {

    @FXML
    private TableView<BorrowedBook> booksTable;

    private BookService service;

    private StudentService studentService;

    public void initialize() throws SQLException {
        service = new BookService();
        studentService = new StudentService();
        service.loadAllBorrowedBooks();
        booksTable.setItems(service.getBorrowedBooksList());
    }

    @FXML
    public void blockUser(){
        Window owner = booksTable.getScene().getWindow();
        BorrowedBook selectedItem = booksTable.getSelectionModel().getSelectedItem();
        studentService.blockUser(selectedItem.getUserId());
        AlertHelper.showAlert(Alert.AlertType.INFORMATION, owner, "Blocked",
                "Blocked student from borrowing a book");
    }

}
