package fr.iut.client.controleur;

import fr.iut.serveur.modeles.Produit;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.util.HashMap;
import java.util.Map;

import java.util.ArrayList;

public class CtrlPanier {
        //TODO gérer l'insertion de plus de 4 produits dans le panier
     //Panier d'un client
    private ArrayList<Produit> PanierClient = new ArrayList<Produit>(); //Panier d'un client

    @FXML private Button btn_retour;
    @FXML private Button btn_confirmer;
    @FXML private Label total;

    @FXML private Label nomproduit1;
    @FXML private Label nomproduit2;
    @FXML private Label nomproduit3;
    @FXML private Label nomproduit4;

    @FXML private Label prix1;
    @FXML private Label prix2;
    @FXML private Label prix3;
    @FXML private Label prix4;



    public void setPanierCtrl(ArrayList<Produit> a){this.PanierClient=a;}   //Autre manière de faire utiliser l'interface: faire une méthode qui renvoit le panier du client mais je préfère cette methode car si on commence à déclarer tout et n'importe quoi ça va pas le faire même si là c'est dans une zone flou
    /**
     * Charge le panier
     */
    public void chargement()
    {
        resetgrid();
        if(PanierClient.isEmpty())total.setText(String.valueOf(0));
        else
        {

            Map<Produit, Integer> counts = new HashMap<Produit,Integer>();  //Liste contenant les produits x quantité
            for(Produit p : PanierClient)
            {
                Integer j = counts.get(p);
                counts.put(p, (j == null) ? 1 : j + 1);
            }
            Integer valeur = counts.size();

                for (Map.Entry<Produit, Integer> val : counts.entrySet()) {
                    if(valeur ==3)contenulignegrid(nomproduit3,prix3,val.getKey().getPrix(),val.getValue(),val.getKey().getNom());
                    else if(valeur == 2)contenulignegrid(nomproduit2,prix2,val.getKey().getPrix(),val.getValue(),val.getKey().getNom());
                    else contenulignegrid(nomproduit2,prix2,val.getKey().getPrix(),val.getValue(),val.getKey().getNom());
                    valeur--;
                }


/*
                //Pour chaque produit
            for (Map.Entry<Produit, Integer> val : counts.entrySet()) {
                      //Faut récupérer le nom fx.id du fxml pour pouvoir set les values
            }*/



        }
    }
    public void btn_confirmer_action()
    {
    }

    public void btn_retour_action()
    {
        fermer_fenetre(btn_retour);
    }

    private void fermer_fenetre(Button btn)   //Possibilité de micheliser tout ça avec juste un attribut Button btn
    {
        Stage stage=(Stage) btn.getScene().getWindow();
        stage.close();
    }

    private void resetgrid()//TODO A opti via boucle
    {
        nomproduit1.setText("");
        prix1.setText("");
        nomproduit2.setText("");
        prix2.setText("");
        nomproduit3.setText("");
        prix3.setText("");
        nomproduit4.setText("");
        prix4.setText("");
    }


    /**
     * Affiche du contenu dans la grid
     */
    private void contenulignegrid(Label l,Label l2, double valeurproduit ,int qty,String nomproduit)
    {
        l.setText(nomproduit+" x "+qty);
        l2.setText(String.valueOf(valeurproduit)+"€");

    }

    private Label getLabel(String nom,Integer ligne)
    {
         //this.nomproduit3.get;//nom+ligne;
        return null;
    }
}
