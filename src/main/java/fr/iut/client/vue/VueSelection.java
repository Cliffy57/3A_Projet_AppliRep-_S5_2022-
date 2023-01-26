package fr.iut.client.vue;


import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Vue qui se lance lors du lancement de l'application
 */
public class VueSelection extends Application {    //Selection du magasin
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("fxml/inter1.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 640, 480);
        stage.setTitle("Choix du magasin!");
        stage.setScene(scene);
        stage.show();


        //TODO Classloader
        URL[] urls = {new URL("file:/path/to/your/class/fr/iut/serveur/modeles/Client.class")};
        URLClassLoader classLoader = new URLClassLoader(urls);
        Class clientClass = classLoader.loadClass("fr.iut.serveur.modeles.Client");
        Client client = (Client) clientClass.newInstance();

        Class clientClass = Class.forName("fr.iut.serveur.modeles.Client");
        Client client = (Client) clientClass.newInstance();
    }
}
