package fr.iut.client.controleur;

import fr.iut.client.vue.VuePanier;
import fr.iut.serveur.modeles.Client;
import fr.iut.serveur.modeles.Produit;
import fr.iut.serveur.modeles.ports;
import fr.iut.serveur.skeleton.MagasinInterface;
import javafx.beans.binding.Bindings;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.*;

import static javafx.collections.FXCollections.observableArrayList;

public class CtrlMagasin {

    String nomMagasin;
    Client userCo = new Client("Mel2","123");

    ObservableList<String> cateMag;

    @FXML private Label nom_user;

    @FXML private ListView<String> listeCaté;

    @FXML private Hyperlink logo_panier;

    @FXML private Label nom_magasin;

    @FXML private TableView<Produit> tableProduit;

    @FXML private TableColumn<Produit, ImageView> colImage = new TableColumn<>("Image");

    @FXML private TableColumn<Produit, String> colNom = new TableColumn<>("Nom");

    @FXML private TableColumn<Produit, String> colDescription = new TableColumn<>("Description");

    @FXML private TableColumn<Produit, Double> colPrix = new TableColumn<>("Prix");

    @FXML private TextArea dtsNom;

    @FXML private TextArea dtsDsc;

    @FXML private Label dtsVal;

    @FXML private Button btnAjoutPanier;

    public String getNomMagasin() {
        return nomMagasin;
    }

    public Client getUserCo(){ return userCo;}

    public void setUserCo(Client user){ this.userCo = user;}

    public void setNomMagasin(String nomMagasin) {
        this.nomMagasin = nomMagasin;
    }

    /**
     * Lance l'interface du Panier lors du click sur le logo
     * @throws IOException si un problème lors du lancement de l'interface
     */
    public void lanceVuePanier() throws IOException {
        try
        {
            MagasinInterface M = (MagasinInterface) Naming.lookup("rmi://localhost:"+ ports.Port_Magasin+"/java");
            System.out.println(user_co.toString());
            user_co.ConsultePanier();
            new VuePanier(getUser_co()).start(new Stage());
        }catch(Exception e){
            System.out.println("Une erreur est advenue lors du lancement du panier "+e);
        }
    }

    /**
     * Action lors du click utilisateur sur le bouton Ajouter au panier
     * @throws MalformedURLException,NotBoundException,RemoteException
     */
    public void Ajout_panier() throws MalformedURLException, NotBoundException, RemoteException {
        MagasinInterface M = (MagasinInterface) Naming.lookup("rmi://localhost:"+ ports.Port_Magasin+"/java");
        //M.
      //  if(M.CoClient("Mel@",nommagasin)) { //SI client est co et appartient verif useless now

            Produit p = new Produit(
                    tableProduit.getSelectionModel().getSelectedItem().getNom(),
                    tableProduit.getSelectionModel().getSelectedItem().getDescription(),
                    String.valueOf(tableProduit.getSelectionModel().getSelectedItem().getPrix()),
                    tableProduit.getSelectionModel().getSelectedItem().getCategorie()
            );
            M.ajoutProduit(p);
        System.out.println("Ajout produit au client "+ userCo.getMdp());
    }

    /**
     * Initialise l'interface du magasin
     */
    public void lancement()
    {
        chargeLogoPanier();
        System.out.println(nomMagasin);
        nom_magasin.setText(getNomMagasin());
        chargeproduitdanstableau();
        btnAjoutPanier.setDisable(true);
    }

    /**
     * Permet de charger les produits dans le tableau
     */
    private void chargeproduitdanstableau()
    {
        try {
            MagasinInterface M = (MagasinInterface) Naming.lookup("rmi://localhost:"+ ports.Port_Magasin+"/java");
            setUser_co(M.RecupereClientActuel());
            nom_user.setText(user_co.getMel());
            ArrayList<Produit> ProduitsDansMagasin = M.ConsulterListeProduitMagasin(nommagasin);

            colImage.setCellValueFactory(new PropertyValueFactory<Produit,ImageView>("view"));
            colNom.setCellValueFactory(new PropertyValueFactory<Produit, String>("nom"));
            colDescription.setCellValueFactory(new PropertyValueFactory<Produit,String>("description"));
            colPrix.setCellValueFactory(new PropertyValueFactory<Produit,Double>("prix"));
            tableProduit.getColumns().setAll(colImage,colNom,colDescription,colPrix);

            ChargeImageView(produitsDansMagasin);
            tableProduit.getItems().addAll(produitsDansMagasin);
            Attributcategorie(produitsDansMagasin);

        } catch (MalformedURLException | NotBoundException | RemoteException e) {
            e.printStackTrace();
        }
    }

    /**
     *Charge les ImageView (Logo des produits) dans le tableview
     * @param listeProduit : ArrayList de produit à afficher
     */
    private void ChargeImageView(ArrayList<Produit> listeProduit) {//Charge les ImageView du produit Image
        for (Produit p :listeProduit) {
            p.setImg(new Image((Objects.requireNonNull(Produit.class.getResource("image/bag.jpg")).toString())));   //Le produit.class deviendra p.geturl
            p.charge();
        }
    }

    /**
     * Permet de charger les catégories dans la listview de manière unique
     * @param liste : Liste des produits du table view
     */
    private void Attributcategorie(ArrayList<Produit> liste)
    {
        ArrayList<String> listeCateg = new ArrayList<>();
        Set<String> uniqueGas = new HashSet<String>(listeCateg);
        for(Produit p : liste)
        {
            if(p.getCategorie() != null )
            {
                uniqueGas.add(p.getCategorie());
            }
        }
        cateMag = observableArrayList(uniqueGas);//Liste caté filtrée sans doublon

        listeCaté.setItems(cateMag);
        listViewClickFiltre(liste);

    }

    /**
     * Permet d'ajouter un listener sur la listeview : Filtre les produits en fonction des catgories
     * @param liste : ArrayList de produit de la table view (tableau)
     */
    private void listViewClickFiltre(ArrayList<Produit> liste) {
        listeCaté.setOnMouseClicked(event -> {
            listeCaté.getSelectionModel().getSelectedItem(); //Récup l'item selectionné donc dans notre cas ça veut le nom de la caté
            ObservableList<Produit> produitObservableList = observableArrayList(liste) ;
            FilteredList<Produit> produitFilteredList = new FilteredList<>(produitObservableList);

            produitFilteredList.predicateProperty().bind(Bindings.createObjectBinding(() -> categorie -> categorie.getCategorie().equals(listeCaté.getSelectionModel().getSelectedItem())));
            tableProduit.setItems(produitFilteredList);
        });
    }

    /**
     * Charge le logo du panier
     */
    private void chargeLogoPanier()
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
    public void clickElmtTbl()
    {
        //System.out.println(table_produit.getSelectionModel().getSelectedItem().getNom());
        dtsDsc.setText(tableProduit.getSelectionModel().getSelectedItem().getDescription());
        dtsVal.setText(String.valueOf(tableProduit.getSelectionModel().getSelectedItem().getPrix()));
        dtsNom.setText(tableProduit.getSelectionModel().getSelectedItem().getNom());
        dtsDsc.setWrapText(true);
        dtsNom.setWrapText(true);
        dtsDsc.setEditable(false);
        dtsNom.setEditable(false);
        btnAjoutPanier.setDisable(false);
    }



}
