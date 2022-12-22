package fr.iut.client.controleur;

import fr.iut.serveur.skeleton.BanqueImpl;
import fr.iut.serveur.skeleton.MagasinInterface;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.net.URL;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
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

               MagasinInterface  shop = (MagasinInterface) registry.lookup("shop");

                System.out.println( shop.getnom());

                BanqueImpl bank = new BanqueImpl("8001");
                shop.addItem("book", 19.99);
                shop.addItem("pen", 0.99);

                // Invoke the remote methods
                UID clientId = new UID();
                System.out.println("Client ID: " + clientId);
                // String name = shop.getName();
                double price = shop.getPrice("book");
                shop.placeOrder(String.valueOf(clientId));
                shop.order("book");
                
                fermerFenetre();
            } catch (RemoteException | NotBoundException e) {
                e.printStackTrace();
            }

        }


    }

    /**
     * Evenement se déclenchant lors de l'action sur le bouton deux
     * @param actionEvent
     */
    public void actionMagasin2(ActionEvent actionEvent) {

        fermerFenetre();
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
