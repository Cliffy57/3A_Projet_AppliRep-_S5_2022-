package fr.iut.client.controleur;

import fr.iut.client.vue.VueMagasin;
import fr.iut.client.vue.VuePanier;
import fr.iut.serveur.modeles.Magasin;
import fr.iut.serveur.modeles.Produit;
import fr.iut.serveur.skeleton.BanqueImpl;
import fr.iut.serveur.skeleton.MagasinInterface;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
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
            Produit p1 = new Produit("APLI Étiquettes Ø 19mm 5 Feuilles 100 Pièces","Doté d'une experience de plus de 60 ans, APLI a pour objectif principal de satisfaire les utilisateurs pour leurs besoins dans le domaine du bureau, informatique, école et maison, industriel.","1.09","HUGO");
            Produit p2 = new Produit("Scotch Tape Transparent 12mm x 66m","Depuis des décennies, Scotch célèbre l'ingéniosité au quotidien en inventant des produits pour fixer, assembler, fabriquer et créer. Il existe une solution à chaque problème et pour chaque projet, il y a la marque Scotch.\n","2.39","Rock");

            try {
              Registry  registry = LocateRegistry.getRegistry();

                System.out.println(registry.lookup("shop"));

               MagasinInterface  Shop = (MagasinInterface) registry.lookup("shop");

                System.out.println( Shop.getnom());

                BanqueImpl bank = new BanqueImpl("8001");

             //   Shop.coClient(Shop.recupereClientActuel());
              //  shop.addItem("pen", 0.99);

                // Invoke the remote methods
                UID clientId = new UID();   //Ca sera notre classe User après
                System.out.println("Client ID: " + clientId);
                // String name = shop.getName();
             //   double price = shop.getPrice("book");
            //    shop.placeOrder(String.valueOf(clientId)); validePanier
             //   shop.order("book");
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
     //   modalPaiementConfirme();
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
