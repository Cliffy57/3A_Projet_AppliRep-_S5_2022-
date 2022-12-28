module fr.iut.projet {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.rmi;


    opens fr.iut.projet to javafx.fxml;
    opens fr.iut.client.controleur to javafx.fxml;
    exports fr.iut.serveur.modeles;
    exports fr.iut.serveur.skeleton;
    exports fr.iut.client.vue;
    exports fr.iut.client.controleur;
}