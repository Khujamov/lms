package com.restgo.controller.lib;

import com.restgo.model.User;
import com.restgo.service.StudentService;
import com.restgo.utils.AlertHelper;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TableView;
import javafx.stage.Window;

import java.io.IOException;
import java.sql.SQLException;

public class BlockedUsersController {
    @FXML
    private TableView<User> libsTable;

    StudentService service;

    public void initialize() throws SQLException {
        service = new StudentService();
        service.loadBlockedUsersFromDb();
        libsTable.setItems(service.getBlockedUsersList());
    }

    @FXML
    public void unblock() throws IOException {
        Window owner = libsTable.getScene().getWindow();
        User selectedItem = libsTable.getSelectionModel().getSelectedItem();
        service.unblockUser(selectedItem.getId());
        AlertHelper.showAlert(Alert.AlertType.INFORMATION, owner, "Deleted",
                "Deleted from blocked students list");
        LibController.updateBlockedUI("blockedusers");
    }

}
