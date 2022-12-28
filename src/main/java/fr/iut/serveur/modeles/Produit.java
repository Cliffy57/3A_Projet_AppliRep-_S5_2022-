package fr.iut.serveur.modeles;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.Serializable;
import java.util.Objects;

public class Produit implements Serializable {

    String nom;
    String description;
    Double prix;
    String categorie;
    Image img; //Image du produit url
    ImageView view;

    public Produit(String nom, String description, String prix,String categorie) {//Si caté vide alors caté == "vide"
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





    public String getCategorie() {
        return categorie;
    }

    public void setCategorie(String categorie) {
        this.categorie = categorie;
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

    public Image getImg() {
        return img;
    }

    public void setImg(Image img) {
        this.img = img;
    }

    public ImageView getView() {
        return view;
    }

    /**
     * Instancie le paramètre ImageView d'un produit et règle sa taille
     */
    public void charge()
    {
        this.view = new ImageView(getImg());
        this.view.setPreserveRatio(true);
        this.view.setFitHeight(60);
    }

    @Override
    public String toString() {
        return "Nom : "+getNom() + "/Description : "+getDescription()+"/Prix :"+getPrix();
    }

    @Override
    public boolean equals(Object obj) {
        if(obj instanceof Produit) {
            return Objects.equals(((Produit) obj).getNom(), this.getNom());
        }
         return false;
    }
}
