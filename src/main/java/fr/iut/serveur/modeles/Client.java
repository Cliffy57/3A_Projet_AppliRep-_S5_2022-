package fr.iut.serveur.modeles;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.UUID;

import static fr.iut.serveur.modeles.Magasin.listeClient;

public class Client implements Serializable {
    private String melClient;
    private String mdpClient;
    private String codebancaire;    //Numéro de code bancaire du client
    private String idBancaire;      //Id de la banque du client
    private UUID uuid;
    private double money = 50;


    private ArrayList<Produit> Panier = new ArrayList<Produit>(); //Panier d'un client


    public Client(String mel, String mdp)
    {
        if(!mel.isEmpty()&&!mdp.isEmpty())
        {
            this.melClient =mel.trim();
            this.mdpClient =mdp.trim();
            this.uuid = UUID.randomUUID();
        }else throw new IllegalArgumentException("Le champ mail ou mdp est vide");
    }

    public String getMel() {
        return melClient;
    }

    public void setMel(String mel) {
        this.melClient = mel;
    }

    public String getMdp() {
        return mdpClient;
    }

    public void setMdp(String mdp) {
        this.mdpClient = mdp;
    }


    public ArrayList<Produit> getPanier() {
        return Panier;
    }


    public void ConsultePanier()    //Retour à modifier par la suite pour la vue panier
    {
        System.out.println("Nb elmt du panier="+Panier.size());
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

    public String getUuid() {
        return uuid.toString();
    }

    @Override
    public String toString() {
        return "Client{" +
                "uuid=" + uuid + "/mel="+getMel()+
                '}';
    }

    public void setPanier(ArrayList<Produit> panier) {
        Panier = panier;
    }
    public void clearCart() {
        if(!Panier.isEmpty())
        {
            Panier.clear();
        }
    }
    public double getMoney() {
        return this.money;
    }

    public void setMoney(double money) {
        this.money = money;
    }
}
