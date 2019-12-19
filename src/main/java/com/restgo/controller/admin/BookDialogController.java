package com.restgo.controller.admin;

import com.restgo.model.Book;
import com.restgo.service.BookService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.sql.Date;

public class BookDialogController {

    @FXML
    private TextField title;

    @FXML
    private TextField author;

    @FXML
    private TextField subject;

    @FXML
    private TextField isbn;

    @FXML
    private DatePicker date;

    private BookService service;

    public static boolean isEdit;

    public void initialize(){
        service = new BookService();
        if (isEdit){
            setBook(BooksController.editContact);
        }
    }

    @FXML
    public void cancel(ActionEvent e) throws IOException {
        Button button = (Button)e.getSource();
        button.getScene().getWindow().hide();
        AdminController.updateUI("books");
    }

    @FXML
    public void save(ActionEvent e) throws IOException {
        Button button = (Button)e.getSource();
        if (isEdit){
            service.editBook(getBook());
            button.getScene().getWindow().hide();
            AdminController.updateUI("books");
            setIsEdit(false);
            return;
        }
        service.addBook(getBook());
        button.getScene().getWindow().hide();
        AdminController.updateUI("books");

    }

    public Book getBook(){
        Book book = new Book();
        if (BooksController.editContact != null){
            book.setId(BooksController.editContact.getId());
        }
        book.setTitle(title.getText());
        book.setSubject(subject.getText());
        book.setAuthor(author.getText());
        book.setIsbn(isbn.getText());
        book.setPublishDate(Date.valueOf(date.getValue()));
        return book;
    }

    public void setBook(Book book){
        if (book != null){
            title.setText(book.getTitle());
            author.setText(book.getAuthor());
            subject.setText(book.getSubject());
            isbn.setText(book.getIsbn());
            date.setValue(book.getPublishDate().toLocalDate());
        }
    }

    public static void setIsEdit(boolean edit){
        isEdit = edit;
    }
}
