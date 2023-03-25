package fr.iut.serveur.modeles;

import java.io.Serializable;
import java.util.ArrayList;

public class Magasin implements Serializable {
    String nomDuMagasin;
    public ArrayList<Produit> ListeProduits = new ArrayList<Produit>();
    public static ArrayList<Client> listeClient = new ArrayList<Client>();

    public Magasin(String nom) {
        this.nomDuMagasin = nom;
    }

    public String getNom() {
        return nomDuMagasin;
    }

    public void setNom(String nom) {
        this.nomDuMagasin = nom;
    }

    public ArrayList<Produit> getListeProduits() {
        return ListeProduits;
    }


    /**
     * Ajoute un client à la liste des clients existants d'un magasin
     * @param mdp : Mot de passe de client
     * @param mel : Adresse mel du client
     */
    public void ajouteClient(String mel, String mdp)
    {
        listeClient.add(new Client(mel,mdp));
    }
}
