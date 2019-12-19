package com.restgo;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * JavaFX App
 */
public class App extends Application {

    private static Scene scene;
    private static Stage stage;

    @Override
    public void start(Stage primaryStage) throws IOException {
        stage = primaryStage;
        stage.getIcons().add(new Image(getClass().getResourceAsStream("img/icon.png")));
        stage.setTitle("Library Management System");
        scene = new Scene(loadFXML("login"), 1024, 576);
        scene.getStylesheets().add(getClass().getResource("style/style.css").toExternalForm());
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }

    public static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
        scene.getStylesheets().add(App.class.getResource("style/style.css").toExternalForm());
    }
    public static void setRoot(String name,String fxml) throws IOException {
        scene.setRoot(loadFXML(name,fxml));
        scene.getStylesheets().add(App.class.getResource("style/style.css").toExternalForm());
    }
    public static void setDialog(String name,String fxml) throws IOException {
        Scene scene1 = new Scene(loadFXML(name,fxml),300,250);
        Stage stage1 = new Stage();
        stage1.setScene(scene1);
        stage1.show();
    }
    public static Parent loadFXML(String fxml) throws IOException {
//        Parent root = FXMLLoader.load(App.class.getResource("view/" + fxml + ".fxml"));
//        return root;
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("view/"+fxml + ".fxml"));
        return fxmlLoader.load();
    }
    public static Parent loadFXML(String name,String fxml) throws IOException {
//        Parent root = FXMLLoader.load(App.class.getResource("view/" + name +"/"+ fxml + ".fxml"));
//        return root;
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("view/" + name +"/"+ fxml + ".fxml"));
        return fxmlLoader.load();
    }
    public static void main(String[] args) {
        launch();
    }

}