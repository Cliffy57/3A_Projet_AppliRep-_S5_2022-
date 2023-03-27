package fr.iut.client.controleur;

import fr.iut.serveur.modeles.Client;
import fr.iut.serveur.modeles.Produit;
import fr.iut.serveur.skeleton.MagasinInterface;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.*;

public class CtrlPanier {

    private ArrayList<Produit> PanierClient = new ArrayList<Produit>(); //Panier d'un client

    @FXML private Button boutonRetour;
    @FXML private Button boutonDeConfirmation;

    @FXML private Label lblNomDuProduit1, lblNomDuProduit2, lblNomDuProduit3, lblNomDuProduit4,lblNomDuProduit5, lblNomDuProduit6, lblNomDuProduit7, lblNomDuProduit8, lblNomDuProduit9, lblNomDuProduit10;

    @FXML private Label prixDuProduit1, prixDuProduit2, prixDuProduit3, prixDuProduit4,prixDuProduit5, prixDuProduit6, prixDuProduit7, prixDuProduit8,prixDuProduit9, prixDuProduit10;

    @FXML private Label totalDuPanier;
    private final Label[] lblNomDuProduit = new Label[10];
    private final Label[] prixDuProduit = new Label[10];
    public void setPanierCtrl(ArrayList<Produit> a){this.PanierClient=a;}

    /**
     * Initialise les tableaux contenant tous les labels du gridpane
     */
    public void initialize() {
        lblNomDuProduit[0] = lblNomDuProduit1;
        lblNomDuProduit[1] = lblNomDuProduit2;
        lblNomDuProduit[2] = lblNomDuProduit3;
        lblNomDuProduit[3] = lblNomDuProduit4;
        lblNomDuProduit[4] = lblNomDuProduit5;
        lblNomDuProduit[5] = lblNomDuProduit6;
        lblNomDuProduit[6] = lblNomDuProduit7;
        lblNomDuProduit[7] = lblNomDuProduit8;
        lblNomDuProduit[8] = lblNomDuProduit9;
        lblNomDuProduit[9] = lblNomDuProduit10;


        prixDuProduit[0] = prixDuProduit1;
        prixDuProduit[1] = prixDuProduit2;
        prixDuProduit[2] = prixDuProduit3;
        prixDuProduit[3] = prixDuProduit4;
        prixDuProduit[4] = prixDuProduit5;
        prixDuProduit[5] = prixDuProduit6;
        prixDuProduit[6] = prixDuProduit7;
        prixDuProduit[7] = prixDuProduit8;
        prixDuProduit[8] = prixDuProduit9;
        prixDuProduit[9] = prixDuProduit10;
    }


    /**
     * Met à jour le produit dans la vue si il existe déjà
     * @param produit
     * @param counts
     */
    public void UpdateProduct(Produit produit, Map<String, Integer> counts) {
        for (int i = 0; i < lblNomDuProduit.length; i++) {

            String productName = lblNomDuProduit[i].getText();
            if (productName.length() > 4) {
                productName = productName.substring(0, productName.length() - 4);
            }
            if (productName.equals(produit.getNom())) {
                // If the product is found, remove it from the view
                lblNomDuProduit[i].setText("");
                prixDuProduit[i].setText("");
                Integer j = counts.get(produit.getNom());
                afficherContenuDuPanier(lblNomDuProduit[i], prixDuProduit[i], produit.getPrix(), j, produit.getNom());

                break;
            }
        }
    }
    /**
     * Charge le panier
     */
    public void chargementDuPanier()
    {
        resetContenuPanier();
        initialize();
        System.out.println("Taille du panier"+PanierClient.size());
        if(PanierClient.isEmpty())
        {
            totalDuPanier.setText("Total : " +0+"€");
            this.boutonDeConfirmation.setDisable(true);
        }
        else
        {

            Map<String, Integer> counts = new HashMap<String,Integer>(); // Map containing the products and their quantities
            Double somme = 0.0;
            Set<String> printedProducts = new HashSet<>();
            int index = 0;
            for (Produit p : PanierClient) {
                Integer j = counts.get(p.getNom());
                counts.put(p.getNom(), (j == null) ? 1 : j + 1);
                somme += p.getPrix();

                // Check if the product has already been printed
                if (printedProducts.contains(p.getNom())) {
                    // Product has already been printed, increment the count
                    UpdateProduct(p,counts);
                    index--;
                } else {
                    // Product has not been printed yet, print it with count 1
                    afficherContenuDuPanier(lblNomDuProduit[index], prixDuProduit[index], p.getPrix(), 1, p.getNom());
                    printedProducts.add(p.getNom());
                }

                // Increment the index
                index++;
            }
            totalDuPanier.setText("TOTAL : "+ Math.round(somme) +"€");
        }
    }
    //Envoye le panier au magasin
    public void btnConfirmerAction() {

        MagasinInterface magasin;
        try {
            magasin = (MagasinInterface) Naming.lookup("rmi://localhost/Magasin");
        } catch (NotBoundException | MalformedURLException | RemoteException e) {
            throw new RuntimeException(e);
        }
        Client client;
        double totalCost;
        try {
            client = magasin.recupereClientActuel();
            client.setPanier(PanierClient);
            client.ConsultePanier();
            totalCost = magasin.calcSommeProduit(client);
            String uuid = client.getUuid();
            if(magasin.placeOrder(uuid, totalCost))
            {
                client.setMoney(client.getMoney()-totalCost);//Met à jour le solde du client
                modalPaiementConfirme("Paiement confirme. \n Solde Restant :"+client.getMoney());

            }
        } catch (RemoteException e) {
            modalPaiementConfirme("Paiement refusé \n Solde insuffisant ! ");
        }



    }

    /**
     * Action du bouton retour
     */
    public void btnRetourAction()
    {
        fermerFenetre(boutonRetour);
    }

    private void fermerFenetre(Button btn)
    {
        Stage stage=(Stage) btn.getScene().getWindow();
        stage.close();
    }

    /**
     * Mets tous les labels à vide
     */
    private void resetContenuPanier()
    {        for (int i = 0; i < 10; i++) {
            lblNomDuProduit[i].setText("");
            prixDuProduit[i].setText("");
        }
    }


    /**
     * Affiche du contenu du panier dans une grid
     */
    private void afficherContenuDuPanier(Label labelNomDuProduit, Label labelPrixTotalDuProduit, double valeurDuProduit , int quantiteAjouteAuPanier, String nomDuProduit)
    {
        int quantiteReel=quantiteAjouteAuPanier;
        labelNomDuProduit.setText(nomDuProduit+" x "+quantiteReel);
        labelPrixTotalDuProduit.setText(Math.round(valeurDuProduit * quantiteReel) +"€");

    }

    /**
     * Affiche la modal de paiement
     * @param message : Soit affiche paiement confirmé si le paiement a été effectué sinon affiche paiement refusé
     */
    private static void modalPaiementConfirme(String message) {
        // Create a modal dialog
        Stage dialog = new Stage();
        dialog.initModality(Modality.APPLICATION_MODAL);
        dialog.setTitle("Etat de votre commande");

        dialog.setResizable(false);

        // Create a label to display the message
        Label label = new Label(message);

        // Create a layout and add the label to it
        VBox layout = new VBox(10);
        layout.getChildren().add(label);

        // Create a scene and add the layout to it
        Scene scene = new Scene(layout);

        // Set the scene and show the dialog
        dialog.setScene(scene);
        dialog.showAndWait();
    }

}
