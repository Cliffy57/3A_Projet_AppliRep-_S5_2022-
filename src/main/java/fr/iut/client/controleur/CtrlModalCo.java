package fr.iut.client.controleur;

import fr.iut.serveur.modeles.Client;
import fr.iut.serveur.modeles.ports;
import fr.iut.serveur.skeleton.MagasinInterface;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

/**
 * Controlleur de la vue VueModalCo
 */
public class CtrlModalCo {

    String nommagasin;

    @FXML private TextArea text_mel;
    @FXML private TextArea text_pwd;
    @FXML private Button btn_conf;

    public Client confirmer()
    {
        Client c= null;
        try {
        MagasinInterface M = (MagasinInterface) Naming.lookup("rmi://localhost:"+ ports.Port_Magasin+"/java");

            if(!text_mel.getText().isEmpty()&&!text_pwd.getText().isEmpty()&&M.CoClient(text_mel.getText(),nommagasin))
            {
                System.out.println("Client reconnu");
                c=new Client(text_mel.getText(),text_pwd.getText());

            }
        } catch (RemoteException | MalformedURLException | NotBoundException e) {
            e.printStackTrace();
        }
        return c;
    }

    public void setNommagasin(String nommagasin) {
        this.nommagasin = nommagasin;
    }
}
//TODO comment résoudre ces trucs de connexion