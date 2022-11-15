package fr.iut.client.vue;

import fr.iut.projet.HelloApplication;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class VueMagasin extends Application {  //Vue interface principale du magasin
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("interface_magasin.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 900, 600);
        stage.setTitle("Magasin!");
        stage.setScene(scene);
        stage.show();
    }

}
