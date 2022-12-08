package fr.iut.client.controleur;

import fr.iut.serveur.modeles.ports;
import fr.iut.serveur.skeleton.MagasinInterface;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;

import java.rmi.Naming;

public class CtrlModalCo {

    String nommagasin;

    @FXML private TextArea text_mel;
    @FXML private TextArea text_pwd;
    @FXML private Button btn_conf;

    public void confirmer()
    {
        //MagasinInterface M = (MagasinInterface) Naming.lookup("rmi://localhost:"+ ports.Port_Magasin+"/java");
        //if(M.CoClient("Mel@",nommagasin))
    }
}
