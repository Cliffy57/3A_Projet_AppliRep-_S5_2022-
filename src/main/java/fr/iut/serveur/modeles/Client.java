package fr.iut.serveur.modeles;

import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.ArrayList;

import static fr.iut.serveur.modeles.Magasin.listeClient;

public class Client implements Serializable {//Note: Ajout du serializable car export dans l'interface
            //TODO Pour le problème de plusieurs produits identiques dans le panier, on fera le traitement au niveau du ctrlmagasin
    private int id; //Id d'un client
    private String mel;
    private String mdp;
    private String solde; //Solde du compte bancaire d'un client
    private String codebancaire;    //Numéro de code bancaire du client
    private String id_bancaire; //Id de la banque du client (à voir jusqu'où on va dans le projet)


    private ArrayList<Produit> Panier = new ArrayList<Produit>(); //Panier d'un client


    public Client(String mel, String mdp)  //La renforcer en mode michel après
    {
        if(!mel.isEmpty()&&!mdp.isEmpty())
        {
            this.id=listeClient.size()+1;
            this.mel=mel.trim();
            this.mdp=mdp.trim();
        }else throw new IllegalArgumentException("Le champ mail ou mdp est vide");
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMel() {
        return mel;
    }

    public void setMel(String mel) {
        this.mel = mel;
    }

    public String getMdp() {
        return mdp;
    }

    public void setMdp(String mdp) {
        this.mdp = mdp;
    }

    public String getSolde() {
        return solde;
    }

    public void setSolde(String solde) {
        this.solde = solde;
    }

    public ArrayList<Produit> getPanier() {
        return Panier;
    }


    public void ConsultePanier()    //Retour à modifier par la suite pour la vue panier
    {
        for(Produit p : Panier)
        {
            System.out.println(p.toString());
        }
    }

    public String getCodebancaire() {
        return codebancaire;
    }

    public void setCodebancaire(String codebancaire) {
        this.codebancaire = codebancaire;
    }

    /**
     * Ajoute un produit au panier
     * @param produit
     */
    public void AjouterPanier(Produit produit)
    {
        Panier.add(produit);
    }

    public double CalculSommePanier()
    {
        double somme= 0;
        if(this.getPanier().isEmpty()) return somme;
        else
        {

            for(Produit p : Panier)
            {
                somme += p.getPrix();
            }
        }
        return somme;
    }

}
