package fr.iut.serveur.modeles;

import java.util.ArrayList;

public class DataSet {


    public static void retourJeuDeDonnees_Magasin1(ArrayList<Produit> ListeMagasin)
    {
        ListeMagasin.add(new Produit("APLI Étiquettes Ø 19mm 5 Feuilles 100 Pièces","Doté d'une experience de plus de 60 ans, APLI a pour objectif principal de satisfaire les utilisateurs pour leurs besoins dans le domaine du bureau, informatique, école et maison, industriel.","1.09","HUGO"));
        ListeMagasin.add(new Produit("Scotch Tape Transparent 12mm x 66m","Depuis des décennies, Scotch célèbre l'ingéniosité au quotidien en inventant des produits pour fixer, assembler, fabriquer et créer. Il existe une solution à chaque problème et pour chaque projet, il y a la marque Scotch.\n","2.39","Rock"));
        ListeMagasin.add(new Produit("COLOP Tampon Encreur Bleu 7x11cm","COLOP offre la possibilité de tamponner de manière permanente vos papiers","5.95","HUGO"));
        ListeMagasin.add(new Produit("Boite cassoulet","Une bonne boite du cassoulet de nos regions","11.95","Alimentation"));
        ListeMagasin.add(new Produit("Eau Minerale Evian 1L","Eau Plate d'une contenance d'un 1L","3","Alimentation"));


    }


}
