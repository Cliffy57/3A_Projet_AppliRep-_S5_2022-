package fr.iut.client.controleur;

import fr.iut.client.vue.VuePanier;
import fr.iut.serveur.modeles.Client;
import fr.iut.serveur.modeles.Produit;
import fr.iut.serveur.modeles.ports;
import fr.iut.serveur.skeleton.MagasinInterface;
import javafx.beans.binding.Bindings;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
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
    Client user_co = new Client("Mel2","123"); //le new est temporaire
    ObservableList<String> CateMag ;


    @FXML private Label nom_user;
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

    @FXML private Button btn_ajout_panier;

    public String getNommagasin() {
        return nommagasin;
    }

    public Client getUser_co(){ return user_co;}

    public void setUser_co(Client user){ this.user_co = user;}

    public void setNommagasin(String nommagasin) {
        this.nommagasin = nommagasin;
    }

    //Action sur le logo panier

    /**
     * Lance l'interface du Panier lors du click sur le logo
     * @throws IOException si un problème lors du lancement de l'interface
     */
    public void LanceVuePanier() throws IOException {
        try
        {
            MagasinInterface M = (MagasinInterface) Naming.lookup("rmi://localhost:"+ ports.Port_Magasin+"/java");
            System.out.println(user_co.toString());
            new VuePanier(getUser_co()).start(new Stage());
        }catch(Exception e){
            System.out.println("Une erreur est advenue lors du lancement du panier "+e);
        }


        //new VuePanier(user_co).start(new Stage());
    }

    /**
     * Action lors du click utilisateur sur le bouton Ajouter au panier
     */
    public void Ajout_panier() throws MalformedURLException, NotBoundException, RemoteException {
        MagasinInterface M = (MagasinInterface) Naming.lookup("rmi://localhost:"+ ports.Port_Magasin+"/java");
        //M.
      //  if(M.CoClient("Mel@",nommagasin)) { //SI client est co et appartient verif useless now

            Produit p = new Produit(
                    table_produit.getSelectionModel().getSelectedItem().getNom(),
                    table_produit.getSelectionModel().getSelectedItem().getDescription(),
                    String.valueOf(table_produit.getSelectionModel().getSelectedItem().getPrix()),
                    table_produit.getSelectionModel().getSelectedItem().getCategorie()
            );
            M.AjoutProduit(p);

            //System.out.println(M);

    }

    /**
     * Initialise l'interface du magasin
     */
    public void Lancement()
    {
        chargerlogopanier();
        System.out.println(nommagasin);
        nom_magasin.setText(getNommagasin());
        chargeproduitdanstableau();
        btn_ajout_panier.setDisable(true);

    }

    /**
     * Permet de charger les produits dans le tableau
     */
    private void chargeproduitdanstableau()
    {
        try {
            MagasinInterface M = (MagasinInterface) Naming.lookup("rmi://localhost:"+ ports.Port_Magasin+"/java");
            setUser_co(M.RecupereClientActuel());
            ArrayList<Produit> ProduitsDansMagasin = M.ConsulterListeProduitMagasin(nommagasin);

            colImage.setCellValueFactory(new PropertyValueFactory<Produit,ImageView>("view"));
            colNom.setCellValueFactory(new PropertyValueFactory<Produit, String>("nom"));
            colDescription.setCellValueFactory(new PropertyValueFactory<Produit,String>("description"));
            colPrix.setCellValueFactory(new PropertyValueFactory<Produit,Double>("prix"));
            table_produit.getColumns().setAll(colImage,colNom,colDescription,colPrix);

            ChargeImageView(ProduitsDansMagasin);
            table_produit.getItems().addAll(ProduitsDansMagasin);   //Ajoute les produits dans le table view
            Attributcategorie(ProduitsDansMagasin);

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (NotBoundException e) {
            e.printStackTrace();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    /**
     *Charge les ImageView (Logo des produits) dans le tableview
     * @param listeProduit : ArrayList de produit à afficher
     */
    private void ChargeImageView(ArrayList<Produit> listeProduit) {//Charge les ImageView du produit Image
        for (Produit p :listeProduit
             ) {
            p.setImg(new Image((Objects.requireNonNull(Produit.class.getResource("image/bag.jpg")).toString())));   //Le produit.class deviendra p.geturl
            p.charge();
            //colImage.setGraphic(p.getView());
        }
    }

    /**
     * Permet de charger les catégories dans la listview de manière unique
     * @param liste : Liste des produits du table view
     */
    private void Attributcategorie(ArrayList<Produit> liste)
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
        CateMag = observableArrayList(uniqueGas);//Liste caté filtrée sans doublon

        ListCaté.setItems(CateMag); //Ajoute les catégories dans la listView

        ListViewClickFiltre(liste);

    }

    /**
     * Permet d'ajouter un listener sur la listeview : Filtre les produits en fonction des c&tgories
     * @param liste : ArrayList de produit de la table view
     */
    private void ListViewClickFiltre(ArrayList<Produit> liste) {
        ListCaté.setOnMouseClicked(event -> {
            ListCaté.getSelectionModel().getSelectedItem(); //Récup l'item selectionné donc dans notre cas ça veut le nom de la caté
            ObservableList<Produit> m = observableArrayList(liste) ;
            FilteredList<Produit> f = new FilteredList<>(m);
            SortedList<Produit> sortable = new SortedList<>(f);
            f.predicateProperty().bind(Bindings.createObjectBinding(() -> categorie -> categorie.getCategorie().equals(ListCaté.getSelectionModel().getSelectedItem())));
            table_produit.setItems(f);
        });
    }

    /**
     * Charge le logo du panier
     */
    private void chargerlogopanier()
    {
        String chemin = String.valueOf(getClass().getResource("image/panier.png"));

        Image img = new Image(chemin);
        ImageView view = new ImageView(img);
        view.setFitHeight(20);
        view.setPreserveRatio(true);
        logo_panier.setText("");
        logo_panier.setGraphic(view);
    }

    /**
     * Met à jour la partie détails de l'interface en fonction du choix de l'utilisateur
     */
    public void test_click()    //Quand on click sur la table view
    {
        //System.out.println(table_produit.getSelectionModel().getSelectedItem().getNom());
        dts_dsc.setText(table_produit.getSelectionModel().getSelectedItem().getDescription());
        dts_val.setText(String.valueOf(table_produit.getSelectionModel().getSelectedItem().getPrix()));
        Dts_nom.setText(table_produit.getSelectionModel().getSelectedItem().getNom());
        dts_dsc.setWrapText(true);
        Dts_nom.setWrapText(true);
        dts_dsc.setEditable(false);
        Dts_nom.setEditable(false);
        btn_ajout_panier.setDisable(false);
    }



}
