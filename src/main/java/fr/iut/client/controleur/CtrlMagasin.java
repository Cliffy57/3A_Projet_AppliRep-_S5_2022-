package fr.iut.client.controleur;

import fr.iut.client.vue.VueMagasin;
import fr.iut.client.vue.VuePanier;
import fr.iut.projet.HelloApplication;
import fr.iut.serveur.modeles.Categories;
import fr.iut.serveur.modeles.Produit;
import fr.iut.serveur.modeles.ports;
import fr.iut.serveur.skeleton.MagasinInterface;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TableView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.ResourceBundle;

public class CtrlMagasin implements Initializable {

    String nommagasin;  //Nom magasin

    @FXML
    private ListView<Categories> ListCaté;

    @FXML private Hyperlink logo_panier;

    @FXML private Label lblnom_magasin;

    //@FXML private TableView<Produit>

    public String getNommagasin() {
        return nommagasin;
    }

    public void setNommagasin(String nommagasin) {
        this.nommagasin = nommagasin;
    }

    //Action sur le logo panier
    public void gotoGoogle() throws IOException {
        new VuePanier().start(new Stage());
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        chargerlogopanier();
        lblnom_magasin.setText(nommagasin);
        try {

            MagasinInterface M = (MagasinInterface) Naming.lookup("rmi://localhost:"+ ports.Port_Banque+"/java");
            M.ConsulterListeProduitMagasin(nommagasin);//TODO renvoie une arraylist de produit

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (NotBoundException e) {
            e.printStackTrace();
        } catch (RemoteException e) {
            e.printStackTrace();
        }

    }

    public void chargerlogopanier()
    {
        String chemin = String.valueOf(getClass().getResource("image/panier.png"));

        Image img = new Image(chemin);
        ImageView view = new ImageView(img);
        view.setFitHeight(20);
        view.setPreserveRatio(true);
        logo_panier.setGraphic(view);
        logo_panier.setText("");
    }
}
