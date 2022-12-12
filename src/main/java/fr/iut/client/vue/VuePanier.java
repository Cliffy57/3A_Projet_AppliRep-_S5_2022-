package fr.iut.client.vue;

import fr.iut.client.controleur.CtrlMagasin;
import fr.iut.client.controleur.CtrlPanier;
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
         if(client!=null)this.c= client;
         else throw new NullPointerException("Lors de la construction du panier, le client fournit en argument s'est averé être null");
     }
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("fxml/panier.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 640, 400);
        CtrlPanier Ctrl = fxmlLoader.getController();
        Ctrl.setPanierCtrl(c.getPanier());
        Ctrl.chargement();

        stage.setTitle("Votre panier");
        stage.setScene(scene);
        stage.show();
    }
}
