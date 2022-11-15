module fr.iut.projet {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.rmi;


    opens fr.iut.projet to javafx.fxml;
    exports fr.iut.projet;
    exports fr.iut.serveur.skeleton;
}