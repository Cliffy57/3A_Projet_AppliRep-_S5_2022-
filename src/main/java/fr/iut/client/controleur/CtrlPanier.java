package fr.iut.client.controleur;

import fr.iut.serveur.modeles.Client;
import fr.iut.serveur.modeles.Produit;
import fr.iut.serveur.skeleton.MagasinInterface;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.HashMap;
import java.util.Map;

import java.util.ArrayList;

public class CtrlPanier {
        //TODO gérer l'insertion de plus de 4 produits dans le panier
     //Panier d'un client
    private ArrayList<Produit> PanierClient = new ArrayList<Produit>(); //Panier d'un client

    @FXML private Button boutonRetour;
    @FXML private Button boutonDeConfirmation;
    @FXML private Label totalDuPanier;

    @FXML private Label lblNomDuProduit1;
    @FXML private Label lblNomDuProduit2;
    @FXML private Label lblNomDuProduit3;
    @FXML private Label lblNomDuProduit4;

    @FXML private Label prixDuProduit1;
    @FXML private Label prixDuProduit2;
    @FXML private Label prixDuProduit3;
    @FXML private Label prixDuProduit4;


//FIXME : Le panier est vide lors du premier lancement du panier, faut reset l'affichage pour qu'il recup le panier qui contient les trucs
    public void setPanierCtrl(ArrayList<Produit> a){this.PanierClient=a;}
    //TODO : Autre manière de faire utiliser l'interface: faire une méthode qui renvoit le panier du client mais je préfère cette methode car si on commence à déclarer tout et n'importe quoi ça va pas le faire même si là c'est dans une zone flou
    /**
     * Charge le panier
     */
    public void chargementDuPanier()
    {
        resetContenuPanier();
        System.out.println("Taille du panier"+PanierClient.size());
        if(PanierClient.isEmpty()) totalDuPanier.setText(String.valueOf(0));
        else
        {

            Map<String, Integer> counts = new HashMap<String,Integer>();  //Liste contenant les produits x quantité
            Double somme=0.0;
            //long count = animals.stream().filter(animal -> "bat".equals(animal)).count();
            for(Produit p : PanierClient)
            {

                Integer j = counts.get(p.getNom());
                counts.put(p.getNom(), (j == null) ? 1 : j + 1);
                somme += p.getPrix();
                if(j!=null) {
                    System.out.println("Affichage");
                   if(counts.size()==1) afficherContenuDuPanier(lblNomDuProduit1, prixDuProduit1,p.getPrix(),j,p.getNom());
                   else if(counts.size()==2) afficherContenuDuPanier(lblNomDuProduit2, prixDuProduit2,p.getPrix(),j,p.getNom());
                   else if(counts.size()==3) afficherContenuDuPanier(lblNomDuProduit3, prixDuProduit3,p.getPrix(),j,p.getNom());
                    System.out.println("Affichage update");
            }
            }
            int valeur= counts.size();
            System.out.println("TEST : "+counts.size());
            totalDuPanier.setText(String.valueOf(Math.round(somme)));

                for (Map.Entry<String, Integer> val : counts.entrySet()) {  //Pour chaque entry de counts
                  //  System.out.println(val.getValue()+"/"+val.getKey());
                   /* if(valeur ==3)contenulignegrid(nomproduit3,prix3,val.getKey().getPrix(),val.getValue(),val.getKey().getNom());
                    else if(valeur == 2)contenulignegrid(nomproduit2,prix2,val.getKey().getPrix(),val.getValue(),val.getKey().getNom());
                    else contenulignegrid(nomproduit1,prix1,val.getKey().getPrix(),val.getValue(),val.getKey().getNom());*/
                    //valeur--;
                }


/*
                //Pour chaque produit
            for (Map.Entry<Produit, Integer> val : counts.entrySet()) {
                      //Faut récupérer le nom fx.id du fxml pour pouvoir set les values
            }*/



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
            magasin.placeOrder(uuid, totalCost);
        } catch (RemoteException e) {
            throw new RuntimeException(e);
        }



    }

    public void btnRetourAction()
    {
        fermerFenetre(boutonRetour);
    }

    private void fermerFenetre(Button btn)   //Possibilité de micheliser tout ça avec juste un attribut Button btn
    {
        Stage stage=(Stage) btn.getScene().getWindow();
        stage.close();
    }

    private void resetContenuPanier()//TODO A opti via boucle
    {
        lblNomDuProduit1.setText("");
        prixDuProduit1.setText("");
        lblNomDuProduit2.setText("");
        prixDuProduit2.setText("");
        lblNomDuProduit3.setText("");
        prixDuProduit3.setText("");
        lblNomDuProduit4.setText("");
        prixDuProduit4.setText("");
    }


    /**
     * Affiche du contenu du panier dans une grid
     */
    private void afficherContenuDuPanier(Label labelNomDuProduit, Label labelPrixTotalDuProduit, double valeurDuProduit , int quantiteAjouteAuPanier, String nomDuProduit)
    {
        int quantiteReel=quantiteAjouteAuPanier+1;  //FIXME  sinon cran en dessous
        labelNomDuProduit.setText(nomDuProduit+" x "+quantiteReel);
        labelPrixTotalDuProduit.setText(Math.round(valeurDuProduit * quantiteReel) +"€");

    }

}
