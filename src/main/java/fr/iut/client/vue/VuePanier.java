package fr.iut.client.vue;

import fr.iut.client.controleur.CtrlPanier;
import fr.iut.serveur.modeles.Client;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class VuePanier extends Application {
     Client clientDuPanier;
     public VuePanier(Client client)
     {
         if(client!=null)this.clientDuPanier = client;
         else throw new NullPointerException("Lors de la construction du panier, le client fournit en argument s'est averé être null");
     }
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("fxml/panier.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 640, 400);
        CtrlPanier controllerPanier = fxmlLoader.getController();
        controllerPanier.setPanierCtrl(clientDuPanier.getPanier());
        controllerPanier.chargementDuPanier();
        stage.setTitle("Votre panier");
        stage.setScene(scene);
        stage.show();
    }
}
