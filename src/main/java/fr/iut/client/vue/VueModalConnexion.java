package fr.iut.client.vue;

import fr.iut.client.controleur.CtrlModalDeConnexion;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Vue modal de la connexion
 */
public class VueModalConnexion extends Application {  //Vue interface principale du magasin
    String nomDuMagasin;

    public VueModalConnexion(String nomDuMagasin) {
        this.nomDuMagasin = nomDuMagasin;
    }

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("fxml/interface_modal_co.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 900, 600);
        CtrlModalDeConnexion controllerModalConnexion = fxmlLoader.getController();
        controllerModalConnexion.setNomMagasin(nomDuMagasin);
        stage.setTitle("Connexion à " + nomDuMagasin);
        stage.setScene(scene);
        stage.show();
    }
}
