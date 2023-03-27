package fr.iut.client.controleur;

import fr.iut.client.vue.VueMagasin;
import fr.iut.serveur.skeleton.BanqueImpl;
import fr.iut.serveur.skeleton.MagasinInterface;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.rmi.NotBoundException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UID;
import java.util.ResourceBundle;

/**
 * Controlleur de la vueSelection
 */
public class CtrlSelection implements Initializable {

    @FXML
    private Button btnMagasin1;

    @FXML
    private Button btnMagasin2;



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    /**
     * Evenement se déclenchant lors du click utilisateur sur le bouton 1
     * @param actionEvent
     */
    public void actionMagasin1(ActionEvent actionEvent) {
        {

            try {
              Registry  registry = LocateRegistry.getRegistry();

                System.out.println(registry.lookup("shop"));

               MagasinInterface  Shop = (MagasinInterface) registry.lookup("shop");

                System.out.println( Shop.getnom());

                BanqueImpl bank = new BanqueImpl("8001");

                UID clientId = new UID();
                System.out.println("Client ID: " + clientId);

                new VueMagasin("shop").start(new Stage());
                
                fermerFenetre();
            } catch (NotBoundException | IOException e) {
                e.printStackTrace();
            }

        }


    }

    /**
     * Evenement se déclenchant lors de l'action sur le bouton deux
     * @param actionEvent
     */
    public void actionMagasin2(ActionEvent actionEvent) {
        try {
            Registry  registry = LocateRegistry.getRegistry();

            System.out.println(registry.lookup("shop2"));

            MagasinInterface  Shop = (MagasinInterface) registry.lookup("shop2");

            System.out.println( Shop.getnom());

            BanqueImpl bank = new BanqueImpl("8001");

            UID clientId = new UID();
            System.out.println("Client ID: " + clientId);
            new VueMagasin("shop2").start(new Stage());

            fermerFenetre();
        } catch (NotBoundException | IOException e) {
            e.printStackTrace();
        }
    }



    /**
     * Ferme la fenêtre
     */
    private void fermerFenetre()
    {
        Stage stage = (Stage) btnMagasin1.getScene().getWindow();
        stage.close();
    }
}
