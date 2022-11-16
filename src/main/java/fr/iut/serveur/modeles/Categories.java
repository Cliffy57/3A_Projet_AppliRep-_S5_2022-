package fr.iut.serveur.modeles;

import java.io.Serializable;

public class Categories implements Serializable {

    String nom;

    public Categories( String nomcateg) {
        this.nom = nom;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    @Override
    public String toString() {
        return nom;

    }
    //TODO Classe catégorie à supprimer svp c'était une idée de merde d'en faire une classe à part Cordialement (sauf pour l'observavble list)
}
