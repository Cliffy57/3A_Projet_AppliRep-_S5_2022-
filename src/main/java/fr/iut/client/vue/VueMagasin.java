package fr.iut.client.vue;

import fr.iut.client.controleur.CtrlMagasin;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Vue du magasin
 */
public class VueMagasin extends Application {
    String nomDuMagasin;

    public VueMagasin(String nomDuMagasin) {
        this.nomDuMagasin = nomDuMagasin;
    }

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("fxml/interface_magasin.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 900, 600);
        CtrlMagasin controllerMagasin = fxmlLoader.getController();
        controllerMagasin.setNomMagasin(nomDuMagasin);
        controllerMagasin.lancement();
        stage.setTitle("Magasin " + nomDuMagasin);
        stage.setScene(scene);
        stage.show();
    }

}
