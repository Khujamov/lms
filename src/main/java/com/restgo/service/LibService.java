package com.restgo.service;

import com.restgo.db.DataSource;
import com.restgo.model.User;
import com.restgo.utils.DBUtils;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;

public class LibService {
    private Connection connection;
    private PreparedStatement statement;
    private CallableStatement cs;
    private ResultSet rs;
    private ObservableList<User> users;

    private final String newContact = "call insert_lib(?,?)";
    private final String allContacts = "select u.id,u.username,u.password from users u\n" +
            "inner join user_roles ur on ur.user_id = u.id\n" +
            "inner join roles r on ur.role_id = r.id where r.id = 20;";
    private final String updateContact = "UPDATE users SET username = ? , password = ?  WHERE id = ?";
    private final String deleteContact = "DELETE FROM users WHERE id = ?";

    public LibService() {
        users = FXCollections.observableArrayList();
    }

    public ObservableList<User> getContacts() {
        return users;
    }

    public void loadContactsFromDb() {
        try {
            connection = DataSource.getInstance().getConnection();
            statement = connection.prepareStatement(allContacts);
            rs = statement.executeQuery();
            while (rs.next()){
                users.add(new User(rs.getInt("id"),
                        rs.getString("username"),
                        rs.getString("password")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DBUtils.done(rs);
            DBUtils.done(statement);
            DBUtils.done(connection);
        }

    }

    public void addUser(User user) {
        try {
            connection = DataSource.getInstance().getConnection();
            cs = connection.prepareCall(newContact);
            cs.setString(1,user.getUsername());
            cs.setString(2,user.getPassword());
            cs.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DBUtils.done(cs);
            DBUtils.done(connection);
        }
    }

    public void deleteUser(User user) {
        try {
            connection = DataSource.getInstance().getConnection();
            statement = connection.prepareStatement(deleteContact);
            statement.setInt(1,user.getId());
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DBUtils.done(statement);
            DBUtils.done(connection);
        }
    }

    public void editUser(User user)  {
        try {
            connection = DataSource.getInstance().getConnection();
            statement = connection.prepareStatement(updateContact);
            statement.setString(1, user.getUsername());
            statement.setString(2, user.getPassword());
            statement.setInt(3, user.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DBUtils.done(rs);
            DBUtils.done(statement);
            DBUtils.done(connection);
        }
    }
}
