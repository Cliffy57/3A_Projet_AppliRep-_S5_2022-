package fr.iut.client.controleur;

import fr.iut.client.vue.VueMagasin;
import fr.iut.client.vue.VuePanier;
import fr.iut.projet.HelloApplication;
import fr.iut.serveur.modeles.Categories;
import fr.iut.serveur.modeles.Produit;
import fr.iut.serveur.modeles.ports;
import fr.iut.serveur.skeleton.MagasinInterface;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.IOException;
import java.lang.reflect.Array;
import java.net.MalformedURLException;
import java.net.URL;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.ResourceBundle;
import java.util.Set;

import static javafx.collections.FXCollections.observableArrayList;

public class CtrlMagasin  {

    String nommagasin;  //Nom magasin
    ObservableList<String> CateMag ;

    @FXML
    private ListView<String> ListCaté;

    @FXML private Hyperlink logo_panier;

    @FXML private Label nom_magasin;

    @FXML private TableView<Produit> table_produit;

    @FXML private TableColumn<Produit, String> colImage = new TableColumn<>("Image");

    @FXML private TableColumn<Produit, String> colNom = new TableColumn<>("Nom");

    @FXML private TableColumn<Produit, String> colDescription = new TableColumn<>("Description");

    @FXML private TableColumn<Produit, Double> colPrix = new TableColumn<>("Prix");

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

    public void Lancement()
    {
        chargerlogopanier();
        System.out.println(nommagasin);
        nom_magasin.setText(getNommagasin());
        chargeproduitdanstableau();

    }

    public void chargeproduitdanstableau()
    {
        try {
            MagasinInterface M = (MagasinInterface) Naming.lookup("rmi://localhost:"+ ports.Port_Magasin+"/java");
            ArrayList<Produit> ProduitsDansMagasin = M.ConsulterListeProduitMagasin(nommagasin);
            colImage.setCellValueFactory(new PropertyValueFactory<Produit,String>("img"));
            colNom.setCellValueFactory(new PropertyValueFactory<Produit, String>("nom"));
            colDescription.setCellValueFactory(new PropertyValueFactory<Produit,String>("description"));
            colPrix.setCellValueFactory(new PropertyValueFactory<Produit,Double>("prix"));
           table_produit.getColumns().setAll(colImage,colNom,colDescription,colPrix);
            table_produit.getItems().addAll(ProduitsDansMagasin);   //TODO Ajouter des listeners sur les cellules de ce tableview
            Attributcategorie(ProduitsDansMagasin);
            //tblproducts.getSelectionModel().selectedIndexProperty().addListener(this);

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (NotBoundException e) {
            e.printStackTrace();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public void Attributcategorie(ArrayList<Produit> liste)
    {
        ArrayList<String> listecateg = new ArrayList<>();
        Set<String> uniqueGas = new HashSet<String>(listecateg);
        for(Produit p : liste)
        {
            if(p.getCategorie() != null )
            {
                uniqueGas.add(p.getCategorie());
            }
        }
        CateMag = observableArrayList(uniqueGas);

        ListCaté.setItems(CateMag);

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
