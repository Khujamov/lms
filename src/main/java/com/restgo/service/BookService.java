package com.restgo.service;

import com.restgo.db.DataSource;
import com.restgo.model.Book;
import com.restgo.model.BorrowedBook;
import com.restgo.utils.DBUtils;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;
import java.time.LocalDate;

public class BookService {
    private Connection connection;
    private PreparedStatement statement;
    private CallableStatement cs;
    private ResultSet rs;
    private ObservableList<Book> books = FXCollections.observableArrayList();
    private ObservableList<Book> borrowedBooks = FXCollections.observableArrayList();
    private ObservableList<Book> myBorrowedBooks = FXCollections.observableArrayList();
    private ObservableList<BorrowedBook> borrowedBooksList = FXCollections.observableArrayList();

    private final String newContact = "INSERT INTO books(title,subject,author,isbn,publish_date) VALUES(?,?,?,?,?)";
    private final String allContacts = "SELECT * FROM books";
    private final String contactById = "SELECT * FROM books WHERE ID = ?";
    private final String updateContact = "UPDATE books SET title = ? , subject = ? , author = ? , isbn = ? , publish_date = ? WHERE id = ?";
    private final String deleteContact = "DELETE FROM books WHERE id = ?";
    private final String borrowBook = "call borrow_book(?,?,?,?)";
    private final String borrowBooks = "SELECT * FROM books where books.borrowed = '0'";
    private final String myborrowedBooks = "select b.* from books b \n" +
            "inner join borrowed_books bb on bb.book_id = b.id\n" +
            "inner join users u on bb.user_id = u.id\n" +
            "where u.id = ?";
    private final String returnBook = "call return_book(?)";
    private final String allBorrowedBooks = "select b.*,u.username,bb.taken_date,bb.return_date from books b \n" +
            "inner join borrowed_books bb on bb.book_id = b.id\n" +
            "inner join users u on bb.user_id = u.id;";

    public BookService() {

    }

    public ObservableList<Book> getContacts() {
        return books;
    }

    public ObservableList<Book> getBorrowedBooks() {
        return borrowedBooks;
    }

    public ObservableList<Book> getMyBorrowedBooks() {
        return myBorrowedBooks;
    }

    public ObservableList<BorrowedBook> getBorrowedBooksList() {
        return borrowedBooksList;
    }

    public void loadContactsFromDb() {
        try {
            connection = DataSource.getInstance().getConnection();
            statement = connection.prepareStatement(allContacts);
            rs = statement.executeQuery();
            while (rs.next()){
                books.add(new Book(rs.getInt("id"),
                        rs.getString("title"),
                        rs.getString("author"),
                        rs.getString("subject"),
                        rs.getString("isbn"),
                        rs.getDate("publish_date")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DBUtils.done(rs);
            DBUtils.done(statement);
            DBUtils.done(connection);
        }

    }

    public void loadBorrowedBooks(){
        try {
            connection = DataSource.getInstance().getConnection();
            statement = connection.prepareStatement(borrowBooks);
            rs = statement.executeQuery();
            while (rs.next()){
                borrowedBooks.add(new Book(rs.getInt("id"),
                        rs.getString("title"),
                        rs.getString("author"),
                        rs.getString("subject"),
                        rs.getString("isbn"),
                        rs.getDate("publish_date")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DBUtils.done(rs);
            DBUtils.done(statement);
            DBUtils.done(connection);
        }
    }

    public void loadMyBorrowedBooks(int userId){
        try {
            connection = DataSource.getInstance().getConnection();
            statement = connection.prepareStatement(myborrowedBooks);
            statement.setInt(1,userId);
            rs = statement.executeQuery();
            while (rs.next()){
                myBorrowedBooks.add(new Book(rs.getInt("id"),
                        rs.getString("title"),
                        rs.getString("author"),
                        rs.getString("subject"),
                        rs.getString("isbn"),
                        rs.getDate("publish_date")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DBUtils.done(rs);
            DBUtils.done(statement);
            DBUtils.done(connection);
        }
    }

    public void loadAllBorrowedBooks(){
        try {
            connection = DataSource.getInstance().getConnection();
            statement = connection.prepareStatement(allBorrowedBooks);
            rs = statement.executeQuery();
            while (rs.next()){
                borrowedBooksList.add(new BorrowedBook(rs.getString("title"),
                        rs.getString("username"),
                        rs.getDate("taken_date"),
                        rs.getDate("return_date")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DBUtils.done(rs);
            DBUtils.done(statement);
            DBUtils.done(connection);
        }
    }

    public void addBook(Book book) {
        try {
            connection = DataSource.getInstance().getConnection();
            statement = connection.prepareStatement(newContact);
            statement.setString(1,book.getTitle());
            statement.setString(2,book.getSubject());
            statement.setString(3,book.getAuthor());
            statement.setString(4,book.getIsbn());
            statement.setDate(5,book.getPublishDate());
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DBUtils.done(rs);
            DBUtils.done(statement);
            DBUtils.done(connection);
        }
    }

    public void deleteBook(Book book) {
        try {
            connection = DataSource.getInstance().getConnection();
            statement = connection.prepareStatement(deleteContact);
            statement.setInt(1,book.getId());
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DBUtils.done(statement);
            DBUtils.done(connection);
        }
    }

    public void editBook(Book book)  {
        try {
            connection = DataSource.getInstance().getConnection();
            statement = connection.prepareStatement(updateContact);
            statement.setString(1, book.getTitle());
            statement.setString(2, book.getSubject());
            statement.setString(3, book.getAuthor());
            statement.setString(4, book.getIsbn());
            statement.setDate(5, book.getPublishDate());
            statement.setInt(6,book.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DBUtils.done(rs);
            DBUtils.done(statement);
            DBUtils.done(connection);
        }
    }

    public void borrowBook(int userId,int bookId){
        try {
            connection = DataSource.getInstance().getConnection();
            cs = connection.prepareCall(borrowBook);
            cs.setInt(1,bookId);
            cs.setInt(2,userId);
            cs.setDate(3,Date.valueOf(LocalDate.now()));
            cs.setDate(4,Date.valueOf(LocalDate.now().plusDays(5)));
            cs.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DBUtils.done(rs);
            DBUtils.done(statement);
            DBUtils.done(connection);
        }
    }

    public void returnBook(int bookId){
        try {
            connection = DataSource.getInstance().getConnection();
            cs = connection.prepareCall(returnBook);
            cs.setInt(1,bookId);
            cs.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DBUtils.done(rs);
            DBUtils.done(statement);
            DBUtils.done(connection);
        }
    }
}
