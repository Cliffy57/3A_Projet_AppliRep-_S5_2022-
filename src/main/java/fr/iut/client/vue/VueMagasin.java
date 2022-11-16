package fr.iut.client.vue;

import fr.iut.client.controleur.CtrlMagasin;
import fr.iut.projet.HelloApplication;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class VueMagasin extends Application {  //Vue interface principale du magasin
    String nommagasin;

    public VueMagasin(String nommagasin) {
        this.nommagasin = nommagasin;
    }

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("fxml/interface_magasin.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 900, 600);
        CtrlMagasin Ctrl = fxmlLoader.getController();

        Ctrl.setNommagasin(nommagasin);
        Ctrl.Lancement();
       // System.out.println(Ctrl.getNommagasin());
        stage.setTitle("Magasin " +nommagasin);
        stage.setScene(scene);
        stage.show();
    }

}
