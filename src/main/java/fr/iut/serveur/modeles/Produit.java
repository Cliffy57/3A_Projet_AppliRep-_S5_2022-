package fr.iut.serveur.modeles;

import java.io.Serializable;

public class Produit implements Serializable {

    int id; //Attribut à supprimer
    String nom;
    String description;
    Double prix;
    String categorie;
    String img; //Image du produit
    Categories c;

    public Produit(int id, String nom, String description, String prix,String categorie) {//Si caté vide alors caté == "vide"
        this.id = id;
        this.nom = nom;
        this.description = description;
        this.prix = Double.valueOf(prix);
        this.categorie = categorie;
    }

    public Produit( String nom, String description, String prix) {//Si caté vide alors caté == "vide"
        this.nom = nom;
        this.description = description;
        this.prix = Double.valueOf(prix);
        this.categorie = categorie;
    }

    public Produit(String nom, String dsc, String prix, String categorie) {
        this.nom = nom;
        this.description =dsc;
        this.prix = Double.valueOf(prix);
        this.categorie = categorie;
    }

    public String getCategorie() {
        return categorie;
    }

    public void setCategorie(String categorie) {
        this.categorie = categorie;
    }

    public Categories getC() {
        return c;
    }

    public void setC(Categories c) {
        this.c = c;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getPrix() {
        return prix;
    }

    public void setPrix(String prix) {
        this.prix = Double.valueOf(prix);
    }

    @Override
    public String toString() {
        return "Nom : "+getNom() + "/Description : "+getDescription()+"/Prix :"+getPrix();
    }
}
