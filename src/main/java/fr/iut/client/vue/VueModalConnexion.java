package fr.iut.client.vue;

import fr.iut.client.controleur.CtrlMagasin;
import fr.iut.client.controleur.CtrlModalCo;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Vue modal de la connexion
 */
public class VueModalConnexion extends Application {  //Vue interface principale du magasin
    String nommagasin;

    public VueModalConnexion(String nommagasin) {
        this.nommagasin = nommagasin;
    }

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("fxml/interface_modal_co.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 900, 600);
        CtrlModalCo Ctrl = fxmlLoader.getController();

        Ctrl.setNommagasin(nommagasin);
        //Ctrl.Lancement();

        stage.setTitle("Connexion à " + nommagasin);
        stage.setScene(scene);
        stage.show();
    }
}
