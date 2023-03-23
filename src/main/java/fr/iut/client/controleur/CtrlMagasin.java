package fr.iut.client.controleur;

import fr.iut.client.vue.VueMagasin;
import fr.iut.client.vue.VuePanier;
import fr.iut.client.vue.VueSelection;
import fr.iut.serveur.modeles.Client;
import fr.iut.serveur.modeles.Produit;
import fr.iut.serveur.modeles.Ports;
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
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
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

    @FXML private Button btnRetour;

    public String getNomMagasin() {
        return nomMagasin;
    }

    public Client getUserCo(){ return userCo;}

    public void setUserCo(Client user){
        System.out.println("SetterUserco"+user.toString());this.userCo = user;}

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
            MagasinInterface M = (MagasinInterface) Naming.lookup("rmi://localhost:"+ Ports.Port_Magasin+"/java");
            System.out.println("VuePanier:"+userCo.toString());
            System.out.println("l83:"+userCo.hashCode());
            userCo.ConsultePanier();
            System.out.println((M.recupereClientActuel()));

            new VuePanier(getUserCo()).start(new Stage());
        }catch(Exception e){
            System.out.println("Une erreur est advenue lors du lancement du panier "+e);
        }
    }

    /**
     * Action lors du click utilisateur sur le bouton Ajouter au panier
     * @throws MalformedURLException,NotBoundException,RemoteException
     */
    public void ajoutPanier() throws MalformedURLException, NotBoundException, RemoteException {
        Registry registry = LocateRegistry.getRegistry();
        MagasinInterface  shop = (MagasinInterface) registry.lookup("shop");

            Produit p = new Produit(
                    tableProduit.getSelectionModel().getSelectedItem().getNom(),
                    tableProduit.getSelectionModel().getSelectedItem().getDescription(),
                    String.valueOf(tableProduit.getSelectionModel().getSelectedItem().getPrix()),
                    tableProduit.getSelectionModel().getSelectedItem().getCategorie()
            );
        //System.out.println("l104:"+shop.recupereClientActuel().toString());
            shop.ajoutProduit(p);
            userCo.AjouterPanier(p);
           // shop.consulterPanier();
        System.out.println("Ajout produit au client "+ userCo.toString()+"//Pointeur:"+shop.recupereClientActuel().hashCode());

    }

    /**
     * Initialise l'interface du magasin
     */
    public void lancement()
    {
        chargeLogoPanier();
        System.out.println("CtrlMag"+nomMagasin);
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
            Registry  registry = LocateRegistry.getRegistry();


            MagasinInterface  M = (MagasinInterface) registry.lookup(this.nomMagasin);
            System.out.println(M.consulterListeProduitMagasin(this.nomMagasin));
           // System.out.println(M.consulterListeProduitMagasin("shop").toString());
           // MagasinInterface M = (MagasinInterface) Naming.lookup("rmi://localhost:"+ Ports.Port_Magasin+"/java");
            //System.out.println(M.recupereClientActuel().toString());
            setUserCo(M.recupereClientActuel());
            nom_user.setText(userCo.getMel()+"/"+userCo.getUuid());

            ArrayList<Produit> produitsDansMagasin = M.consulterListeProduitMagasin(nomMagasin);
            System.out.println(Arrays.toString(M.consulterListeProduitMagasin(nomMagasin).toArray()));

            colImage.setCellValueFactory(new PropertyValueFactory<Produit,ImageView>("view"));
            colNom.setCellValueFactory(new PropertyValueFactory<Produit, String>("nom"));
            colDescription.setCellValueFactory(new PropertyValueFactory<Produit,String>("description"));
            colPrix.setCellValueFactory(new PropertyValueFactory<Produit,Double>("prix"));
            tableProduit.getColumns().setAll(colImage,colNom,colDescription,colPrix);

            ChargeImageView(produitsDansMagasin);
            tableProduit.getItems().addAll(produitsDansMagasin);
            Attributcategorie(produitsDansMagasin);

        } catch (NotBoundException | RemoteException e) {
            e.printStackTrace();
        }
    }

    /**
     *Charge les ImageView (Logo des produits) dans le tableview
     * @param listeProduit : ArrayList de produit à afficher
     */
    private void ChargeImageView(ArrayList<Produit> listeProduit) {//Charge les ImageView du produit Image
        int v=0;
        for (Produit p :listeProduit) {
            if(this.nomMagasin.equals("shop"))  //Chargement image du magasin un
            {
                p.setImg(new Image((Objects.requireNonNull(Produit.class.getResource("image/m"+ v +".jpg")).toString())));
                p.charge();
                v++;
            }
            else if(this.nomMagasin.equals("shop2")) //Chargement images du magasin deux
            {
                p.setImg(new Image((Objects.requireNonNull(Produit.class.getResource("image/n"+ v +".jpg")).toString())));
                p.charge();
                v++;
            }
            else    //Image par défaut
            {
                p.setImg(new Image((Objects.requireNonNull(Produit.class.getResource("image/bag.jpg")).toString())));   //Le produit.class deviendra p.geturl
                p.charge();
            }

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
    public void clickElmtTbl() throws RemoteException, NotBoundException {
        Registry registry = LocateRegistry.getRegistry();
        MagasinInterface  shop = (MagasinInterface) registry.lookup("shop");
        //shop.addItem("book", 19.99);
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

    /**
     * Action du bouton retour
     */
    public void retourMenu()
    {
        Stage stage = (Stage) btnRetour.getScene().getWindow();
        stage.close();
        try {
            new VueSelection().start(new Stage());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }



}
