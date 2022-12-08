package fr.iut.client.vue;

import fr.iut.projet.HelloApplication;
import fr.iut.serveur.modeles.Client;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class VuePanier extends Application {
     Client c;
     public VuePanier(Client client)
     {
         this.c= client;
     }
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("fxml/panier.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 640, 400);
        stage.setTitle("Choix du magasin!");
        stage.setScene(scene);
        stage.show();
    }
}
