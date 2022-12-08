package fr.iut.client.controleur;

import fr.iut.client.vue.VueMagasin;
import fr.iut.serveur.modeles.ports;
import fr.iut.serveur.skeleton.MagasinInterface;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.ResourceBundle;

public class CtrlSelection implements Initializable {

    @FXML
    private Button btn_magasin1;

    @FXML
    private Button btn_magasin2;



    @Override

    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void btn1(ActionEvent actionEvent) {
        try {
            MagasinInterface Inter = (MagasinInterface) Naming.lookup("rmi://localhost:"+ ports.Port_Magasin+"/java");
            if(Inter.CoClient("Mel@",btn_magasin1.getText()))   //TODO Ce if est à décaler lors d'un ajout d'un panier
            {
                VueMagasin vue = new VueMagasin(btn_magasin1.getText());

                vue.start(new Stage());
                fermer_fenetre(btn_magasin1);
            }

        } catch (RemoteException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (NotBoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void btn2(ActionEvent actionEvent) {

        fermer_fenetre(btn_magasin2);
    }

    private void fermer_fenetre(Button btn)   //Possibilité de micheliser tout ça avec juste un attribut Button btn
    {
        Stage stage=(Stage) btn.getScene().getWindow();
        stage.close();
    }
}
