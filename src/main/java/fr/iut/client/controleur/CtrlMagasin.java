package fr.iut.client.controleur;

import fr.iut.client.vue.VueMagasin;
import fr.iut.client.vue.VuePanier;
import fr.iut.projet.HelloApplication;
import fr.iut.serveur.modeles.Categories;
import fr.iut.serveur.modeles.Produit;
import fr.iut.serveur.modeles.ports;
import fr.iut.serveur.skeleton.MagasinInterface;
import javafx.beans.binding.Bindings;
import javafx.beans.value.ChangeListener;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.collections.ObservableListBase;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
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
import java.util.*;

import static javafx.collections.FXCollections.observableArrayList;

public class CtrlMagasin  {

    String nommagasin;  //Nom magasin
    ObservableList<String> CateMag ;
    ObservableList<Produit> truc;

    @FXML
    private ListView<String> ListCaté;

    @FXML private Hyperlink logo_panier;

    @FXML private Label nom_magasin;

    @FXML private TableView<Produit> table_produit;

    @FXML private TableColumn<Produit, ImageView> colImage = new TableColumn<>("Image");

    @FXML private TableColumn<Produit, String> colNom = new TableColumn<>("Nom");

    @FXML private TableColumn<Produit, String> colDescription = new TableColumn<>("Description");

    @FXML private TableColumn<Produit, Double> colPrix = new TableColumn<>("Prix");

    @FXML private TextArea Dts_nom;

    @FXML private TextArea dts_dsc;

    @FXML private Label dts_val;

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
            ArrayList<Produit> ProduitsDansMagasin = M.ConsulterListeProduitMagasin(nommagasin);//TODO mettre ça en fonction qui renvoie l'arraylist pour
            colImage.setCellValueFactory(new PropertyValueFactory<Produit,ImageView>("view"));
            colNom.setCellValueFactory(new PropertyValueFactory<Produit, String>("nom"));
            colDescription.setCellValueFactory(new PropertyValueFactory<Produit,String>("description"));
            colPrix.setCellValueFactory(new PropertyValueFactory<Produit,Double>("prix"));
           table_produit.getColumns().setAll(colImage,colNom,colDescription,colPrix);
           temp(ProduitsDansMagasin);
            table_produit.getItems().addAll(ProduitsDansMagasin);   //TODO Ajouter des listeners sur les cellules de ce tableview
            Attributcategorie(ProduitsDansMagasin);
            //table_produit.getSelectionModel().getSelectedCells().addListener();

        //addListener(new ListChangeListener<Produit>(){




            //tblproducts.getSelectionModel().selectedIndexProperty().addListener(this);

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (NotBoundException e) {
            e.printStackTrace();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    private void temp(ArrayList<Produit> ca) {//Charge les ImageView du produit Image
        for (Produit p :ca
             ) {
            p.setImg(new Image((Objects.requireNonNull(Produit.class.getResource("image/bag.jpg")).toString())));
            p.charge();
            //colImage.setGraphic(p.getView());
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

        ListCaté.setOnMouseClicked(event -> {
            ListCaté.getSelectionModel().getSelectedItem(); //Récup l'item selectionné donc dans notre cas ça veut le nom de la caté
            ObservableList<Produit> m = observableArrayList(liste) ;
            FilteredList<Produit> f = new FilteredList<>(m);
            SortedList<Produit> sortable = new SortedList<>(f);
          //  table_produit.setItems(sortable);
            f.predicateProperty().bind(Bindings.createObjectBinding(() -> categorie -> categorie.getCategorie().equals(ListCaté.getSelectionModel().getSelectedItem())));
            table_produit.setItems(f);
          //  System.out.println(table_produit.getItems().stream().sorted());

        });

    }
    public void chargerlogopanier()
    {
        String chemin = String.valueOf(getClass().getResource("image/panier.png"));

        Image img = new Image(chemin);
        ImageView view = new ImageView(img);
        view.setFitHeight(20);
        view.setPreserveRatio(true);
        logo_panier.setText("");
        logo_panier.setGraphic(view);
    }

    public void test_click()    //Quand on click sur la table view
    {
        //System.out.println(table_produit.getSelectionModel().getSelectedItem().getNom());
        dts_dsc.setText(table_produit.getSelectionModel().getSelectedItem().getDescription());
        dts_val.setText(String.valueOf(table_produit.getSelectionModel().getSelectedItem().getPrix()));
        Dts_nom.setText(table_produit.getSelectionModel().getSelectedItem().getNom());
        dts_dsc.setWrapText(true);
        Dts_nom.setWrapText(true);
    }


}
