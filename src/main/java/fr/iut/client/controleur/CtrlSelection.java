package fr.iut.client.controleur;

import fr.iut.client.vue.VueMagasin;
import fr.iut.serveur.modeles.ports;
import fr.iut.serveur.skeleton.BanqueImpl;
import fr.iut.serveur.skeleton.MagasinImpl;
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
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UID;
import java.util.ResourceBundle;
import javafx.application.Platform;


public class CtrlSelection implements Initializable {

    @FXML
    private Button btn_magasin1;

    @FXML
    private Button btn_magasin2;



    @Override

    public void initialize(URL url, ResourceBundle resourceBundle) {

        //String result = performLongRunningTask();


        // Print the ID to the console

        // Generate a unique ID for the client

    }

    public void btn1(ActionEvent actionEvent) {
        //  MagasinInterface Inter = (MagasinInterface) Naming.lookup("rmi://localhost:"+ ports.Port_Magasin+"/java");
        //if(Inter.CoClient("Mel@",btn_magasin1.getText()))   //TODO Ce if est à décaler lors d'un ajout d'un panier
        // if(Inter.CoClient("hugo3@google.com",btn_magasin1.getText()))
        {
            // Perform a long-running task in a background thread

            try {
              Registry  registry = LocateRegistry.getRegistry();

                System.out.println(registry.lookup("shop"));

               // MagasinInterface shop2 = (MagasinInterface) registry.lookup("shop");
               // System.out.println("by");
               MagasinInterface  shop = (MagasinInterface) registry.lookup("shop");
               //MagasinImpl shop = (MagasinImpl) Naming.lookup("rmi://localhost:"+ ports.Port_Magasin+"/java");
                System.out.println( shop.getnom());

//                   MagasinInterface Inter = (MagasinInterface) Naming.lookup("rmi://localhost:"+ ports.Port_Magasin+"/java");



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
            } catch (RemoteException | NotBoundException e) {
                e.printStackTrace();
            }
/*
            VueMagasin vue = new VueMagasin(btn_magasin1.getText());

            vue.start(new Stage());
            fermer_fenetre(btn_magasin1);
*/
        }


    }

    public void btn2(ActionEvent actionEvent) {

            // Perform a long-running task in a background thread
            new Thread(() -> {
             //   String result = performLongRunningTask();

                // Update the GUI on the JavaFX application thread
                Platform.runLater(() -> {
                   // label.setText(result);
                });
            }).start();

        fermer_fenetre(btn_magasin2);
    }

    private void fermer_fenetre(Button btn)   //Possibilité de micheliser tout ça avec juste un attribut Button btn
    {
        Stage stage = (Stage) btn.getScene().getWindow();
        stage.close();
    }
}
