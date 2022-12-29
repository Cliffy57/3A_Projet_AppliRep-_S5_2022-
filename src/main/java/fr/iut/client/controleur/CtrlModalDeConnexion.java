package fr.iut.client.controleur;

import fr.iut.serveur.modeles.Client;
import fr.iut.serveur.modeles.Ports;
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
public class CtrlModalDeConnexion {

    String nomDuMagasin;

    @FXML private TextArea melTextArea;
    @FXML private TextArea motDePasseTexteArea;
    @FXML private Button buttonConfirmation;

    /**
     *
     * @return Client qui est un client confirme.
     */
    public Client confirmerUnClient()
    {
        Client clientConfirme= null;
        try {
        MagasinInterface magasinInterface = (MagasinInterface) Naming.lookup("rmi://localhost:"+ Ports.Port_Magasin+"/java");
          //  if(!melTextArea.getText().isEmpty()&&!motDePasseTexteArea.getText().isEmpty()&&magasinInterface.coClient(melTextArea.getText()); //nomDuMagasin))
            {
                System.out.println("Client reconnu");
                clientConfirme=new Client(melTextArea.getText(), motDePasseTexteArea.getText());
            }
        } catch (RemoteException | MalformedURLException | NotBoundException e) {
            e.printStackTrace();
        }
        return clientConfirme;
    }

    public void setNomMagasin(String nomDuMagasin) {
        this.nomDuMagasin = nomDuMagasin;
    }
}
//TODO comment résoudre ces trucs de connexion