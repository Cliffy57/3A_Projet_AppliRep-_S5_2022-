package fr.iut.client.controleur;

import fr.iut.serveur.modeles.Produit;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Collections;
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


//FIXME : Le panier est vide lors du premier lancement du panier, faut reset l'affichage pour qu'il recup le panier qui contient les trucs
    public void setPanierCtrl(ArrayList<Produit> a){this.PanierClient=a;}   //Autre manière de faire utiliser l'interface: faire une méthode qui renvoit le panier du client mais je préfère cette methode car si on commence à déclarer tout et n'importe quoi ça va pas le faire même si là c'est dans une zone flou
    /**
     * Charge le panier
     */
    public void chargement()
    {
        resetgrid();
        System.out.println("Taille du panier"+PanierClient.size());
        if(PanierClient.isEmpty())total.setText(String.valueOf(0));
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
                   if(counts.size()==1) contenulignegrid(nomproduit1,prix1,p.getPrix(),j,p.getNom());
                   else if(counts.size()==2)contenulignegrid(nomproduit2,prix2,p.getPrix(),j,p.getNom());
                   else if(counts.size()==3)contenulignegrid(nomproduit3,prix3,p.getPrix(),j,p.getNom());
                    System.out.println("Affichage update");
            }
            }
            int valeur= counts.size();
            total.setText(String.valueOf(round(somme,2)));
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
        int qty2=qty+1;  //Car sinon cran en dessous
        l.setText(nomproduit+" x "+qty2);
        l2.setText(String.valueOf(round(valeurproduit*qty2,2))+"€");

    }

    private Label getLabel(String nom,Integer ligne)
    {
         //this.nomproduit3.get;//nom+ligne;
        return null;
    }

    public static double round(double value, int places) {  //C'est soit ça soit on dl une librairie....je préfère la deuxieme option
        if (places < 0) throw new IllegalArgumentException();

        BigDecimal bd = BigDecimal.valueOf(value);
        bd = bd.setScale(places, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }
}
