package fr.iut.serveur.modeles.outils;

import fr.iut.serveur.modeles.Produit;

import java.util.ArrayList;

public class DataSet {


    public static void retourJeuDeDonnees_Magasin1(ArrayList<Produit> ListeMagasin)
    {
        ListeMagasin.add(new Produit("APLI Étiquettes Ø 19mm 5 Feuilles 100 Pièces","Doté d'une experience de plus de 60 ans, APLI a pour objectif principal de satisfaire les utilisateurs pour leurs besoins dans le domaine du bureau, informatique, école et maison, industriel.","1.09","Bricolage"));
        ListeMagasin.add(new Produit("Scotch Tape Transparent 12mm x 66m","Depuis des décennies, Scotch célèbre l'ingéniosité au quotidien en inventant des produits pour fixer, assembler, fabriquer et créer. Il existe une solution à chaque problème et pour chaque projet, il y a la marque Scotch.\n","2.39","Bricolage"));
        ListeMagasin.add(new Produit("COLOP Tampon Encreur Bleu 7x11cm","COLOP offre la possibilité de tamponner de manière permanente vos papiers","5.95","Bricolage"));
        ListeMagasin.add(new Produit("Boite cassoulet","Une bonne boite du cassoulet de nos regions","11.95","Alimentation"));
        ListeMagasin.add(new Produit("Eau Minerale Evian 1L","Eau Plate d'une contenance d'un 1L","3","Alimentation"));
        ListeMagasin.add(new Produit("Table 240*120","Dimensions 240*120","50","Meubles"));
        ListeMagasin.add(new Produit("Porte en Bois de chène","Porte réalisé en bois de chène protégé ultra résistant","30.50","Meubles"));
        ListeMagasin.add(new Produit("Eau Minerale Evian 6L","Eau Plate d'une contenance d'un 6L","10","Alimentation"));
        
    }


    public static void retourJeuDeDonnees_Magasin2(ArrayList<Produit> ListeMagasin)
    {
        ListeMagasin.add ( new Produit("Chemise", "T-shirt en coton pour homme", "19.99", "Vêtements"));
        ListeMagasin.add ( new Produit("Jeans", "Jean skinny pour femme", "49.99", "Vêtements"));
        ListeMagasin.add ( new Produit("Baskets", "Chaussures de course pour homme", "89.99", "Chaussures"));
        ListeMagasin.add ( new Produit("Bottes", "Bottes pour femme", "79.99", "Chaussures"));
        ListeMagasin.add ( new Produit("Casquette", "Casquette de baseball pour homme", "14.99", "Accessoires"));
        ListeMagasin.add ( new Produit("Écharpe", "Écharpe en laine pour femme", "29.99", "Accessoires"));
        ListeMagasin.add ( new Produit("Sac à dos", "Sac à dos unisexe", "39.99", "Sacs"));
        ListeMagasin.add ( new Produit("Sac fourre-tout", "Sac fourre-tout pour femme", "24.99", "Sacs"));
        ListeMagasin.add ( new Produit("Montre", "Montre numérique pour homme", "59.99", "Montres"));
        ListeMagasin.add( new Produit("Bracelet", "Bracelet de mode pour femme", "9.99", "Bijoux"));

    }
}
